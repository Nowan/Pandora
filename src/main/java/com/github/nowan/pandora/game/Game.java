package com.github.nowan.pandora.game;

import com.github.nowan.pandora.game.config.GameConfig;
import com.github.nowan.pandora.eventemitter.EventEmitter;
import com.github.nowan.pandora.game.command.PickCommand;
import com.github.nowan.pandora.game.event.Event;
import com.github.nowan.pandora.game.gameplay.PickOption;
import com.github.nowan.pandora.game.reward.Reward;
import com.github.nowan.pandora.game.reward.RewardsPool;
import com.github.nowan.pandora.game.state.GameState;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game extends EventEmitter<Event> {
    private final GameConfig config;
    private final GameState state;

    public Game(GameConfig config) {
        super(Event.class);

        this.config = config;
        this.state = GameState.builder()
                .withWonAmount(BigDecimal.ZERO)
                .withLifeCount(config.getInitialLifeCount())
                .withLastChanceTries(config.getLastChanceTries())
                .withPickOptions(generatePickOptions(config.getRewardsPool()))
                .build();
    }

    public void start() {
        startNextRound();
    }

    private void startNextRound() {
        PickCommand command = new PickCommand(state);
        command.thenAccept(this::onPlayerPick);
        this.emit(Event.PICK_READY, command);
    }

    private void endGame() {
        this.emit(Event.GAME_OVER, this.state.getWonAmount());
    }

    private void onPlayerPick(PickOption pickedOption) {
        resolvePlayerChoice(pickedOption);

        if (this.state.getLifeCount() > 0) {
            startNextRound();
        }
        else {
            endGame();
        }
    }

    private void resolvePlayerChoice(PickOption pickedOption) {
        Reward reward = pickedOption.getReward();

        switch (reward.type) {
            case GAIN_MONEY:
                resolveGainMoney(reward);
                break;
            case GAIN_LIFE:
                resolveGainLife(reward);
                break;
            case LOSE_LIFE:
                resolveLoseLife(reward);
                break;
        }

        this.state.getPickOptions().remove(pickedOption);
    }

    private void resolveGainMoney(Reward reward) {
        this.state.setWonAmount(this.state.getWonAmount().add((BigDecimal) reward.amount));
    }

    private void resolveGainLife(Reward reward) {
        this.state.setLifeCount(this.state.getLifeCount() + (Integer) reward.amount);
    }

    private void resolveLoseLife(Reward reward) {
        int nextLifeCount = state.getLifeCount() - (Integer) reward.amount;

        if (nextLifeCount <= 0 && state.getLastChanceTries() > 0) {
            nextLifeCount = 1;
            state.setLastChanceTries(state.getLastChanceTries() - 1);
        }

        state.setLifeCount(nextLifeCount);
    }

    private List<PickOption> generatePickOptions(RewardsPool rewardsPool) {
        var pickOptions = Arrays.stream(rewardsPool.items).map(PickOption::new).collect(Collectors.toList());
        Collections.shuffle(pickOptions);
        return pickOptions;
    }
}