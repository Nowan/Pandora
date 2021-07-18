package com.github.nowan.pandora.game.reward;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RewardsQueue {
    private final RewardsPool rewardsPool;
    private final List<Reward> items;
    private int head = 0;

    public RewardsQueue(RewardsPool rewardsPool) {
        this.rewardsPool = rewardsPool;
        this.items = Arrays.asList(rewardsPool.items);
    }

    public Reward next() {
        if (head == 0) Collections.shuffle(items);
        head = (head + 1) % items.size();
        return items.get(head);
    }

    public void reset() {
        head = 0;
    }
}
