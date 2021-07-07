package com.github.nowan.pandora;

import com.github.nowan.pandora.rewards.Reward;
import com.github.nowan.pandora.rewards.RewardPool;
import static com.github.nowan.pandora.rewards.RewardPool.entry;

import java.util.Collections;

public class App
{
    public static void main( String[] args )
    {
        RewardPool rewardPool = RewardPool.ofEntries(
                entry(Collections.nCopies(5, Reward.GAIN_MONEY_5)),
                entry(Collections.nCopies(2, Reward.GAIN_MONEY_20)),
                entry(Collections.nCopies(3, Reward.LOSE_LIFE_1)),
                entry(Reward.GAIN_MONEY_100),
                entry(Reward.GAIN_LIFE_1)
        );

        for (Reward reward : rewardPool.items) {
            System.out.println(reward);
        }
    }
}
