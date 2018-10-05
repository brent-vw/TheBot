package com.brentvw.discord.handler;

import com.brentvw.discord.context.RequestContextImpl;

public interface Handler {
    boolean canHandle(RequestContextImpl context);
    String handle(RequestContextImpl context);
}
