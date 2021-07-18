package com.github.nowan.pandora.eventemitter;

public interface EventListener<TPayload> extends java.util.EventListener {
    void run(TPayload payload);
}
