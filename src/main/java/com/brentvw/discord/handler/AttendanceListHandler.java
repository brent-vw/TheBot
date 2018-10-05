package com.brentvw.discord.handler;

import com.brentvw.discord.context.RequestContext;
import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@MessageHandler
public class AttendanceListHandler implements Handler {

    private final List<String> names;

    private String name;

    public AttendanceListHandler() {
        this.names = new ArrayList<>();
        this.name = "";

        initializeNames();
    }

    private void initializeNames() {
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

    @VisibleForTesting
    void addName(String name) {
        names.add(name);
    }

    @VisibleForTesting
    void clearNames() {
        names.clear();
    }

    @VisibleForTesting
    void setName(String name) {
        this.name = name;
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
                name = names.get((int) (Math.random() * (names.size() - 1)))
                        + " zal de verantwoordelijke zijn voor de lijst.";
                return name;

        }
        return "Invalid arguments";

    }

    public String getCommand() {
        return "!list";
    }

    public String currentResponsibleForList() {
        return name;
    }

}
