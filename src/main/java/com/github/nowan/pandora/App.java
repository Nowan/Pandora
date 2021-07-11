package com.github.nowan.pandora;

import com.github.nowan.pandora.events.Event;
import com.github.nowan.pandora.events.EventPayload;

public class App
{
    private static Game game;

    public static void main( String[] args )
    {
        game = new Game();

        game.on(Event.ROUND_START, App::onPickAvailable);
        game.on(Event.ROUND_END, App::onRoundEnd);
        game.on(Event.GAME_OVER, App::onGameOver);

        game.start();
    }

    private static void onPickAvailable(EventPayload<?> eventPayload) {
        System.out.println(eventPayload);
    }

    private static void onGameOver(EventPayload<?> eventPayload) {
        System.out.println(eventPayload);
    }

    private static void onRoundEnd(EventPayload<?> eventPayload) {
        System.out.println(eventPayload);
    }
}
