package com.github.nowan.pandora.events;

import javax.lang.model.type.NullType;

public interface EventPayload<T> {
    public static final EventPayload<NullType> EMPTY = new EventPayload<>() {};
}
