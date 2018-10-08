package com.brentvw.discord.handler;

import com.brentvw.discord.context.RequestContext;
import com.google.common.annotations.VisibleForTesting;

import java.util.*;

@MessageHandler
public class MortalKombatHandler implements Handler {

    private final Set<String> combatants;

    public MortalKombatHandler() {
        this.combatants = new HashSet<>();
        initCombatants();
    }

    private void initCombatants() {
        combatants.add("Ben");
        combatants.add("Brent");
        combatants.add("Daniel");
        combatants.add("Diego");
        combatants.add("Gerard");
        combatants.add("Jasper");
        combatants.add("Jens");
        combatants.add("Kaisum");
        combatants.add("Kjell");
        combatants.add("Leander");
        combatants.add("Mouaad");
        combatants.add("Remco");
        combatants.add("Sam");
        combatants.add("Shaun");
        combatants.add("Stijn");
        combatants.add("Tim");
    }

    @Override
    public boolean canHandle(RequestContext context) {
        return context.getMessage().startsWith(getCommand());
    }

    @Override
    public String handle(RequestContext context) {

        String[] content = context.getMessage().replace(getCommand(), "").trim().split(" ");

        if (content.length < 2) {
            return "Invalid arguments";
        }

        if (checkNames(content)) {
            return determineWinner(content);
        }

        return "Invalid arguments";
    }

    public String getCommand() {
        return "!fight";
    }

    @VisibleForTesting
    void addCombattant(String name) {
        combatants.add(name);
    }

    private boolean checkNames(String[] content) {

        if (!content[0].equals(content[1])) {
            List<String> contentAsList = Arrays.asList(content);
            return contentAsList.containsAll(combatants);
        }
        return false;
    }

    private String determineWinner(String[] content) {

        Random random = new Random();
        String winner;
        String loser;

        if (random.nextInt(2) == 0) {
            winner = content[0];
            loser = content[1];
        } else {
            winner = content[1];
            loser = content[0];
        }

        return fightDescriptions(winner, loser);

    }

    private String fightDescriptions(String winner, String loser) {
        return new FightDescriptor().determineWinner(winner, loser);
    }

}
