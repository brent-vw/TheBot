package com.brentvw.discord.handler;

import com.brentvw.discord.context.RequestContext;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MessageHandler
public class AttendanceListHandler implements Handler {

    private static final List<String> names;

    private static String name;


    static {
        names = new ArrayList<>();
        names.add("Ben");
        names.add("Brent");
        names.add("Daniel");
        names.add("Diego");
        names.add("Gerard");
        names.add("Jasper");
        names.add("Jens");
        names.add("Kaisum");
        names.add("Kjell");
        names.add("Leander");
        names.add("Mouaad");
        names.add("Remco");
        names.add("Sam");
        names.add("Shaun");
        names.add("Stijn");
    }


    @Override
    public boolean canHandle(RequestContext context) {
        return context.getMessage().startsWith(getCommand());
    }

    @Override
    public String handle(RequestContext context) {
        String event = context.getMessage().replace(getCommand(), "").trim();
        switch (event) {
            case "who":
                if (StringUtils.isEmpty(name)) {
                    return "No name found.";
                }
                return name;
            case "choose":
                name = names.get((int) (Math.random() * (names.size() - 1))) + " zal de verantwoordelijke zijn voor de lijst.";
                return name;

        }
        return "Invalid arguments";

    }

    public String getCommand() {

        return "!list";

    }

    public static List<String> getNames() {
        return Collections.unmodifiableList(names);
    }


    public void currentResponsibleForList() {

    }


}
