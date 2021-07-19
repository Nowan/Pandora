package com.github.nowan.pandora.game.config;

import com.github.nowan.pandora.game.reward.RewardsPool;

public class GameConfig {
    private int initialLifeCount;
    private RewardsPool mainRewardsPool;
    private RewardsPool consolationRewardsPool;
    private RewardsPool lastChanceRewardsPool;

    public int getInitialLifeCount() {
        return initialLifeCount;
    }

    public RewardsPool getMainRewardsPool() {
        return mainRewardsPool;
    }

    public RewardsPool getConsolationRewardsPool() {
        return consolationRewardsPool;
    }

    public RewardsPool getLastChanceRewardsPool() {
        return lastChanceRewardsPool;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int initialLifeCount = 1;
        private RewardsPool mainRewardsPool = new RewardsPool();
        private RewardsPool consolationRewardsPool = new RewardsPool();
        private RewardsPool lastChanceRewardsPool = new RewardsPool();

        public Builder withMainRewardsPool(RewardsPool rewardsPool) {
            this.mainRewardsPool = rewardsPool;
            return this;
        }

        public Builder withConsolationRewardsPool(RewardsPool rewardsPool) {
            this.consolationRewardsPool = rewardsPool;
            return this;
        }

        public Builder withLastChanceRewardsPool(RewardsPool rewardsPool) {
            this.lastChanceRewardsPool = rewardsPool;
            return this;
        }

        public Builder withInitialLifeCount(int initialLifeCount) {
            this.initialLifeCount = initialLifeCount;
            return this;
        }

        public GameConfig build() {
            GameConfig config = new GameConfig();
            config.initialLifeCount = this.initialLifeCount;
            config.mainRewardsPool = this.mainRewardsPool;
            config.consolationRewardsPool = this.consolationRewardsPool;
            config.lastChanceRewardsPool = this.lastChanceRewardsPool;
            return config;
        }
    }
}
