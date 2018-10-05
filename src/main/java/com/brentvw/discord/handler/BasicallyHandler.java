package com.brentvw.discord.handler;

@MessageHandler
public class BasicallyHandler extends CounterHandler {
    private int count;

    @Override
    protected String getCommand() {
        return "!basically";
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
        return "<:basically:494798716780412928>";
    }


}
