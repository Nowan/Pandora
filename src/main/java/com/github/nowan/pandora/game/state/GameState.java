package com.github.nowan.pandora.game.state;

import com.github.nowan.pandora.game.gameplay.PickOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private BigDecimal wonAmount = BigDecimal.ZERO;
    private int lifeCount = 1;
    private int lastChanceTries = 0;
    private List<PickOption> pickOptions = new ArrayList<>();

    public BigDecimal getWonAmount() {
        return wonAmount;
    }

    public void setWonAmount(BigDecimal wonAmount) {
        this.wonAmount = wonAmount;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getLastChanceTries() {
        return lastChanceTries;
    }

    public void setLastChanceTries(int lastChanceTries) {
        this.lastChanceTries = lastChanceTries;
    }

    public List<PickOption> getPickOptions() {
        return pickOptions;
    }

    public static GameState.Builder builder() {
        return new GameState.Builder();
    }

    public static class Builder {
        private BigDecimal wonAmount;
        private int lifeCount;
        private int lastChanceTries;
        private List<PickOption> pickOptions = new ArrayList<>();

        public Builder() {
            this.wonAmount = BigDecimal.ZERO;
            this.lifeCount = 1;
            this.lastChanceTries = 0;
        }

        public Builder withWonAmount(BigDecimal wonAmount) {
            this.wonAmount = wonAmount;
            return this;
        }

        public Builder withLifeCount(int lifeCount) {
            this.lifeCount = lifeCount;
            return this;
        }

        public Builder withLastChanceTries(int lastChanceTries) {
            this.lastChanceTries = lastChanceTries;
            return this;
        }

        public Builder withPickOptions(List<PickOption> rewards) {
            this.pickOptions = rewards;
            return this;
        }

        public GameState build() {
            GameState config = new GameState();
            config.wonAmount = this.wonAmount;
            config.lifeCount = this.lifeCount;
            config.lastChanceTries = this.lastChanceTries;
            config.pickOptions = this.pickOptions;
            return config;
        }
    }
}
