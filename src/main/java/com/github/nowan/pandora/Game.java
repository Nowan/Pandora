package com.github.nowan.pandora;

import com.github.nowan.pandora.rewards.Reward;
import com.github.nowan.pandora.rewards.RewardsPool;
import com.github.nowan.pandora.rewards.RewardsQueue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.nowan.pandora.rewards.RewardsPool.entry;

public class Game {
    private final RewardsQueue rewardsQueue = new RewardsQueue(RewardsPool.ofEntries(
            entry(Collections.nCopies(5, Reward.GAIN_MONEY_5)),
            entry(Collections.nCopies(2, Reward.GAIN_MONEY_20)),
            entry(Collections.nCopies(3, Reward.LOSE_LIFE_1)),
            entry(Reward.GAIN_MONEY_100),
            entry(Reward.GAIN_LIFE_1)
    ));

    public ArrayList<Reward> rewardsToPick = new ArrayList<>();

    private int lifeCount = 1;
    public BigDecimal wonAmount = new BigDecimal(0);
    private boolean lastChanceAvailable = true;

    public void start() {
        rewardsQueue.reset();
        rewardsToPick.clear();
        for (int i = 0; i < 12; i++) {
            rewardsToPick.add(rewardsQueue.next());
        }
    }

    public boolean isOver() {
        return lifeCount <= 0 && !lastChanceAvailable;
    }

    public void pickReward(Reward reward) {
        rewardsToPick.remove(reward);
        applyResult(reward);
    }

    private void applyResult(Reward reward) {
        switch(reward.type) {
            case GAIN_MONEY:
                wonAmount = wonAmount.add(reward.amount);
                break;
            case GAIN_LIFE:
                lifeCount += reward.amount.intValue();
                break;
            case LOSE_LIFE:
                lifeCount -= reward.amount.intValue();
                if (lifeCount <= 0 && lastChanceAvailable) {
                    lifeCount += 1;
                    lastChanceAvailable = false;
                }
                break;
        }
    }
}
