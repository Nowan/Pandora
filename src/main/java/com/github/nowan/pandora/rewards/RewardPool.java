package com.github.nowan.pandora.rewards;

import java.util.Collection;
import java.util.Arrays;
import java.util.Objects;

public class RewardPool {
    public Reward[] items;

    public RewardPool(Reward... rewards) {
        this.items = rewards;
    }

    public static RewardPool ofEntries(Entry... entries) {
        Objects.requireNonNull(entries);
        Reward[] rewards = Arrays.stream(entries).flatMap((entry) -> Arrays.stream(entry.rewards)).toArray(Reward[]::new);

        return new RewardPool(rewards);
    }

    public static Entry entry(Reward reward) {
        return new Entry(reward);
    }

    public static Entry entry(Collection<Reward> rewards) {
        return new Entry(rewards.toArray(Reward[]::new));
    }

    public static class Entry {
        public Reward[] rewards;

        private Entry(Reward... rewards) {
            this.rewards = rewards;
        }
    }
}