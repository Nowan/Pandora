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
import java.util.Random;
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
                .withPickOptions(generatePickOptions(config.getMainRewardsPool()))
                .withLastChanceActive(true)
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
        resolveReward(pickedOption.getReward());

        if (this.state.getLifeCount() > 0) {
            this.state.getPickOptions().remove(pickedOption);
            startNextRound();
        }
        else {
            endGame();
        }
    }

    private void resolveReward(Reward reward) {
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
    }

    private void resolveGainMoney(Reward reward) {
        this.state.setWonAmount(this.state.getWonAmount().add((BigDecimal) reward.amount));
    }

    private void resolveGainLife(Reward reward) {
        this.state.setLifeCount(this.state.getLifeCount() + (Integer) reward.amount);
    }

    private void resolveLoseLife(Reward reward) {
        state.setLifeCount(state.getLifeCount() - (Integer) reward.amount);

        if (state.getLifeCount() <= 0) {
            var rewardsPool = state.isLastChanceActive() ? config.getLastChanceRewardsPool() : config.getConsolationRewardsPool();
            var lastChanceReward = pickRandomReward(rewardsPool);
            
            resolveReward(lastChanceReward);
            if (lastChanceReward.type == Reward.Type.GAIN_LIFE) {
                this.state.setLastChanceUsed(true);
                this.state.setPickOptions(generatePickOptions(this.config.getMainRewardsPool()));
            }
        }
    }

    private Reward pickRandomReward(RewardsPool rewardsPool) {
        Random rand = new Random();

        return rewardsPool.items[rand.nextInt(rewardsPool.items.length)];
    }

    private List<PickOption> generatePickOptions(RewardsPool rewardsPool) {
        var pickOptions = Arrays.stream(rewardsPool.items).map(PickOption::new).collect(Collectors.toList());
        Collections.shuffle(pickOptions);
        return pickOptions;
    }
}