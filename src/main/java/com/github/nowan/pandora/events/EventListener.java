package com.github.nowan.pandora.events;

public interface EventListener extends java.util.EventListener {
    void run(EventPayload<?> payload);
}
