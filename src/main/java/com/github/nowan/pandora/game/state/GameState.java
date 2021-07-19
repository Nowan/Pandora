package com.github.nowan.pandora.game.state;

import com.github.nowan.pandora.game.gameplay.PickOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    private BigDecimal wonAmount = BigDecimal.ZERO;
    private int lifeCount = 1;
    private boolean lastChanceUsed = false;
    private List<PickOption> pickOptions;

    public GameState(List<PickOption> pickOptions) {
        this.pickOptions = Collections.synchronizedList(pickOptions);
    }

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

    public List<PickOption> getPickOptions() {
        return pickOptions;
    }

    public void setPickOptions(List<PickOption> pickOptions) {
        this.pickOptions = pickOptions;
    }

    public boolean isLastChanceActive() {
        return !lastChanceUsed;
    }

    public void setLastChanceUsed(boolean lastChanceUsed) {
        this.lastChanceUsed = lastChanceUsed;
    }

    public static GameState.Builder builder() {
        return new GameState.Builder();
    }

    public static class Builder {
        private BigDecimal wonAmount = BigDecimal.ZERO;
        private int lifeCount = 1;
        private boolean lastChanceUsed = false;
        private List<PickOption> pickOptions = new ArrayList<>();

        public Builder withWonAmount(BigDecimal wonAmount) {
            this.wonAmount = wonAmount;
            return this;
        }

        public Builder withLifeCount(int lifeCount) {
            this.lifeCount = lifeCount;
            return this;
        }

        public Builder withPickOptions(List<PickOption> rewards) {
            this.pickOptions = rewards;
            return this;
        }

        public Builder withLastChanceActive(boolean isLastChanceActive) {
            this.lastChanceUsed = !isLastChanceActive;
            return this;
        }

        public GameState build() {
            GameState config = new GameState(this.pickOptions);
            config.wonAmount = this.wonAmount;
            config.lifeCount = this.lifeCount;
            return config;
        }
    }
}
