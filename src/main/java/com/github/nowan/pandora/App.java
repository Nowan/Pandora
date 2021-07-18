package com.github.nowan.pandora;

import com.github.nowan.pandora.config.GameConfig;
import com.github.nowan.pandora.game.Game;
import com.github.nowan.pandora.game.command.PickCommand;
import com.github.nowan.pandora.game.event.Event;
import com.github.nowan.pandora.game.reward.Reward;
import com.github.nowan.pandora.game.reward.RewardsPool;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.nowan.pandora.game.reward.RewardsPool.entry;

public class App
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for (int i = 0; i < 2; i++){
                executor.execute(App::runGame);
            }
        } catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();
    }

    private static void runGame() {
        Game game = new Game(GameConfig.builder()
                .withRewardsPool(rewardsPool)
                .withLastChanceTries(1)
                .withInitialLifeCount(1)
                .withPickOptionsAmount(12)
                .build()
        );

        game.on(Event.PICK_READY, (Object pickCommand) -> onPickReady((PickCommand) pickCommand));
        game.on(Event.GAME_OVER, (Object wonAmount) -> onGameOver((BigDecimal) wonAmount));

        game.start();
    }

    private static void onPickReady(PickCommand pickCommand) {
        pickCommand.resolve(pickCommand.options.get(0));
    }

    private static void onGameOver(BigDecimal wonAmount) {
        System.out.println(wonAmount);
    }

    private static RewardsPool rewardsPool = RewardsPool.ofEntries(
            entry(Collections.nCopies(5, Reward.GAIN_MONEY_5)),
            entry(Collections.nCopies(2, Reward.GAIN_MONEY_20)),
            entry(Collections.nCopies(3, Reward.LOSE_LIFE_1)),
            entry(Reward.GAIN_MONEY_100),
            entry(Reward.GAIN_LIFE_1)
    );
}
