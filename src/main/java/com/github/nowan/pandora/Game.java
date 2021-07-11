package com.github.nowan.pandora;

import com.github.nowan.pandora.commands.PickCommand;
import com.github.nowan.pandora.events.CommandPayload;
import com.github.nowan.pandora.events.Event;
import com.github.nowan.pandora.events.EventEmitter;
import com.github.nowan.pandora.events.EventPayload;
import com.github.nowan.pandora.rewards.Reward;
import com.github.nowan.pandora.rewards.RewardPipeline;
import com.github.nowan.pandora.rewards.RewardPool;

import java.util.Collections;

import static com.github.nowan.pandora.rewards.RewardPool.entry;

public class Game extends EventEmitter {

    public Game() {

    }

    public void start() {
        this.emit(Event.ROUND_START, (CommandPayload<PickCommand>)PickCommand::new);
    }

    private void reset() {

    }
}
