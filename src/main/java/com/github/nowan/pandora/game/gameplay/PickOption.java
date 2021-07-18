package com.github.nowan.pandora.game.gameplay;

import com.github.nowan.pandora.game.reward.Reward;

public class PickOption {
    private Reward reward;

    public PickOption(Reward reward) {
        this.reward = reward;
    }

    public Reward getReward() {
        return reward;
    }
}
