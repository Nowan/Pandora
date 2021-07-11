package com.github.nowan.pandora.events;

import com.github.nowan.pandora.commands.Command;

public interface CommandPayload<TCommand extends Command> extends EventPayload<TCommand>{
    public abstract TCommand getCommand();
}
