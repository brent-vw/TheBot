package com.brentvw.discord.handler;

@MessageHandler
public class BasicallyHandler extends CounterHandler {
    private static int COUNT = 0;

    @Override
    protected String getCommand() {
        return "!basically";
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
        return "<:basically:494798716780412928>";
    }


}
