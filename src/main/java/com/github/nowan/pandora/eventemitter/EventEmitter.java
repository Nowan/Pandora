package com.github.nowan.pandora.eventemitter;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EventEmitter<TEvent extends Enum<TEvent>> {
    private Map<TEvent, List<EventListener<?>>> listeners = new HashMap<>();

    public EventEmitter(Class<TEvent> eventEnumType) {
        for (TEvent event : eventEnumType.getEnumConstants()) {
            this.listeners.put(event, new ArrayList<>());
        }
    }

    public void on(TEvent event, EventListener<?> listener) {
        listeners.get(event).add(listener);
    }

    public void off(TEvent event, EventListener<?> listener) {
        listeners.get(event).remove(listener);
    }

    public void emit(TEvent event) {
        for (EventListener<?> listener : listeners.get(event)) {
            listener.run(null);
        }
    }

    public <TPayload> void emit(TEvent event, TPayload payload) {
        for (EventListener<?> listener : listeners.get(event)) {
            try {
                ((EventListener<TPayload>)listener).run(payload);
            }
            catch(Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
