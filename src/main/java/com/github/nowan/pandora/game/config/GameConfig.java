package com.github.nowan.pandora.game.config;

import com.github.nowan.pandora.game.reward.RewardsPool;

public class GameConfig {
    private int initialLifeCount;
    private int lastChanceTries;
    private RewardsPool rewardsPool;
    private int pickOptionsAmount;

    public int getInitialLifeCount() {
        return initialLifeCount;
    }

    public int getLastChanceTries() {
        return lastChanceTries;
    }

    public RewardsPool getRewardsPool() {
        return rewardsPool;
    }

    public int getPickOptionsAmount() {
        return pickOptionsAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int initialLifeCount = 1;
        private int lastChanceTries = 1;
        private RewardsPool rewardsPool = new RewardsPool();
        private int pickOptionsAmount = rewardsPool.items.length;

        public Builder withRewardsPool(RewardsPool rewardsPool) {
            this.rewardsPool = rewardsPool;
            return this;
        }

        public Builder withLastChanceTries(int lastChanceTries) {
            this.lastChanceTries = lastChanceTries;
            return this;
        }

        public Builder withInitialLifeCount(int initialLifeCount) {
            this.initialLifeCount = initialLifeCount;
            return this;
        }

        public Builder withPickOptionsAmount(int pickOptionsAmount) {
            this.pickOptionsAmount = pickOptionsAmount;
            return this;
        }

        public GameConfig build() {
            GameConfig config = new GameConfig();
            config.initialLifeCount = this.initialLifeCount;
            config.lastChanceTries = this.lastChanceTries;
            config.rewardsPool = this.rewardsPool;
            config.pickOptionsAmount = this.pickOptionsAmount;
            return config;
        }
    }
}