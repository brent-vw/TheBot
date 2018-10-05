package com.brentvw.discord.handler;

@MessageHandler
public class IkHebEenVraagjeHandler extends CounterHandler {
    private int count;

    @Override
    protected String getCommand() {
        return "!ikhebeenvraagje";
    }

    @Override
    protected int getCount() {
        return count;
    }

    @Override
    protected void setCount(int count) {
        this.count = count;
    }

    @Override
    protected String getEmoji() {
        return "<:ikhebeenvraagje:494850108853059584>";
    }
}
