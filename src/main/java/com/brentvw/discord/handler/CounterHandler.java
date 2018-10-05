package com.brentvw.discord.handler;

import com.brentvw.discord.context.RequestContext;

@MessageHandler
public abstract class CounterHandler implements Handler {
    protected abstract String getCommand();

    protected abstract int getCount();

    protected abstract String getEmoji();

    protected abstract void setCount(int count);

    private String setCount(String count) {
        try {
            setCount(Integer.parseInt(count));
            return getMessage();
        } catch (NumberFormatException e) {
            return "RESPECT RULE 2 <:ban:494876784123445313>";
        }
    }

    @Override
    public boolean canHandle(RequestContext context) {
        return context.getMessage().startsWith(getCommand());
    }

    @Override
    public String handle(RequestContext context) {
        String event = context.getMessage().replace(getCommand(), "");
        switch (event) {
            case "++":
                setCount(getCount() + 1);
                return getMessage();
            case "--":
                setCount(getCount() - 1);
                return getMessage();
            case "":
                return getMessage();
            default:
                break;
        }

        String[] content = context.getMessage().replace(getCommand(), "").trim().split(" ");

        if (content.length == 2 && content[0].equals("set")) {
            return setCount(content[1]);
        }

        return "Invalid arguments";
    }

    protected String getMessage() {
        return String.format("%s %s", getEmoji(), getCount());
    }
}
