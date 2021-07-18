package com.github.nowan.pandora.game.command;

import java.util.concurrent.CompletableFuture;

public abstract class Command<T> extends CompletableFuture<T> {

}
