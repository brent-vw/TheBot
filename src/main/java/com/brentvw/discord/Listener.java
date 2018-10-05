package com.brentvw.discord;


import com.brentvw.discord.context.RequestContext;
import com.brentvw.discord.context.RequestContextImpl;
import com.brentvw.discord.handler.Handler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Listener extends ListenerAdapter {
    private final Set<Handler> handlers;

    public Listener() {
        this.handlers = new HashSet<>();
    }

    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) {
            return;
        }

        RequestContext context = new RequestContextImpl(event.getMessage());

        handlers.stream()
                .filter(h -> h.canHandle(context))
                .findFirst()
                .ifPresent(h -> event.getChannel().sendMessage(h.handle(context)).complete());
    }
}
