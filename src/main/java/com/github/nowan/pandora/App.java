package com.github.nowan.pandora;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for (int i = 0; i < 10000; i++){
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Game game = new Game();
                        game.start();

                        while(!game.isOver()) {
                            game.pickReward(game.rewardsToPick.get(0));
                        }

                        System.out.println(game.wonAmount.toString());
                    }
                });
            }
        } catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown();
    }
}
