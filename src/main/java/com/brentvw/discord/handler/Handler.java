package com.brentvw.discord.handler;

import com.brentvw.discord.context.RequestContext;
import com.brentvw.discord.context.RequestContextImpl;

public interface Handler {
    boolean canHandle(RequestContext context);
    String handle(RequestContext context);
}
