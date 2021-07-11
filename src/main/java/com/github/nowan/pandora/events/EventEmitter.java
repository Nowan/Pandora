package com.github.nowan.pandora.events;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EventEmitter {
    private Map<Event, List<EventListener>> listeners = new HashMap<>();

    public EventEmitter() {
        for (Event event : Event.values()) {
            this.listeners.put(event, new ArrayList<>());
        }
    }

    public void on(Event event, EventListener listener) {
        listeners.get(event).add(listener);
    }

    public void off(Event event, EventListener listener) {
        listeners.get(event).remove(listener);
    }

    public void emit(Event event) {
        for (EventListener listener : listeners.get(event)) {
            listener.run(EventPayload.EMPTY);
        }
    }

    public void emit(Event event, EventPayload<?> payload) {
        for (EventListener listener : listeners.get(event)) {
            listener.run(payload);
        }
    }
}
