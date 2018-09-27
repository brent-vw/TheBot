package com.brentvw.discord.handler;

public interface Handler {
    boolean canHandle(String message);
    String handle(String message);
}
