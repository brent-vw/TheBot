package com.brentvw.discord.handler;

@MessageHandler
public class IkHebEenVraagjeHandler extends CounterHandler {
    private static int COUNT = 0;

    @Override
    protected String getCommand() {
        return "!ikhebeenvraagje";
    }

    @Override
    protected int getCount() {
        return COUNT;
    }

    @Override
    protected void setCount(int i) {
        COUNT = i;
    }

    @Override
    protected String getEmoji() {
        return "<:ikhebeenvraagje:494850108853059584>";
    }
}
