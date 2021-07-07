package com.github.nowan.pandora.rewards;

import java.math.BigDecimal;

public enum Reward {
    GAIN_MONEY_100(Type.GAIN_MONEY, BigDecimal.valueOf(100)),
    GAIN_MONEY_20(Type.GAIN_MONEY, BigDecimal.valueOf(20)),
    GAIN_MONEY_5(Type.GAIN_MONEY, BigDecimal.valueOf(5)),
    GAIN_LIFE_1(Type.GAIN_LIFE, BigDecimal.valueOf(1)),
    LOSE_LIFE_1(Type.LOSE_LIFE, BigDecimal.valueOf(1));

    public enum Type {
        GAIN_MONEY,
        GAIN_LIFE,
        LOSE_LIFE
    }

    public Type type;
    public BigDecimal amount;

    Reward(Type type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }
}
