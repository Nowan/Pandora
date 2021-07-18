package com.github.nowan.pandora.game.command;

import com.github.nowan.pandora.game.gameplay.PickOption;
import com.github.nowan.pandora.game.state.GameState;

import java.util.List;

public class PickCommand extends Command<PickOption> {
    public List<PickOption> options;

    public PickCommand(GameState state) {
        this.options = state.getPickOptions();
    }

    public void resolve(PickOption pickedOption) {
        complete(pickedOption);
    }
}
