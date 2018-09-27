package com.brentvw.discord.handler;

public class BasicallyHandler implements Handler {
    static int AMOUNT = 237;

    @Override
    public boolean canHandle(String message) {
        return message.startsWith("!basically");
    }

    @Override
    public String handle(String message) {
        String event = message.replace("!basically", "");
        switch (event) {
            case "++":
                AMOUNT++;
                return getMessage();
            case "--":
                AMOUNT--;
                return getMessage();
            case "":
                return getMessage();
        }

        String[] content = message.replace("!basically ", "").split(" ");

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
            AMOUNT = Integer.parseInt(count);
            return getMessage();
        } catch (NumberFormatException e) {
            return "RESPECT RULE 2";
        }
    }

    private String getMessage() {
        if(AMOUNT % 100 == 0) {
            return String.format("<:basically:494798716780412928> WE DID IT REDDIT: %s :tada: :tada: :tada: :tada: :tada:", AMOUNT);
        } else if(AMOUNT % 10 == 0) {
            return String.format("<:basically:494798716780412928> %s :tada: :tada: :tada: :tada: :tada:", AMOUNT);
        } else {
            return String.format("<:basically:494798716780412928> %s", AMOUNT);
        }
    }
}
