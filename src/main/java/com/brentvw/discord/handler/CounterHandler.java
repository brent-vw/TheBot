package com.brentvw.discord.handler;

@MessageHandler
public abstract class CounterHandler implements Handler {
    protected abstract String getCommand();
    protected abstract int getCount();
    protected abstract void setCount(int i);
    protected abstract String getEmoji();

    @Override
    public boolean canHandle(String message) {
        return message.startsWith(getCommand());
    }

    @Override
    public String handle(String message) {
        String event = message.replace(getCommand(), "");
        switch (event) {
            case "++":
                setCount(getCount() + 1);
                return getMessage();
            case "--":
                setCount(getCount() - 1);
                return getMessage();
            case "":
                return getMessage();
        }

        String[] content = message.replace(getCommand(), "").trim().split(" ");

        if(content.length < 2) {
            return "Invalid arguments";
        }

        if(content[0].equals("set")) {
            return setCount(content[1]);
        }

        return "Invalid arguments";
    }

    private String setCount(String count) {
        try {
            setCount(Integer.parseInt(count));
            return getMessage();
        } catch (NumberFormatException e) {
            return "RESPECT RULE 2";
        }
    }

    protected String getMessage() {
        if(getCount() % 100 == 0) {
            return String.format("%s WE DID IT REDDIT: %s :tada: :tada: :tada: :tada: :tada:", getEmoji(), getCount());
        } else if(getCount() % 10 == 0) {
            return String.format("%s %s :tada: :tada: :tada: :tada: :tada:", getEmoji(), getCount());
        } else {
            return String.format("%s %s", getEmoji(), getCount());
        }
    }
}
