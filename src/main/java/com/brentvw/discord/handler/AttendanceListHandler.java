package com.brentvw.discord.handler;

import java.util.ArrayList;
import java.util.List;
@MessageHandler
public class AttendanceListHandler implements Handler{

    private static final List<String> names;


    static{
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
    public boolean canHandle(String message) {
        return message.startsWith("!delijst");
    }

    @Override
    public String handle(String message) {
        return names.get((int)(Math.random()*15+1))+"zal de verantwoordelijke zijn voor de lijst.";
    }
    
}
