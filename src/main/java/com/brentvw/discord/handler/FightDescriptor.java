package com.brentvw.discord.handler;

import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FightDescriptor {

    private Map<Integer, String> fightDescriptions;
    private Map<String, String> values = new HashMap<>();

    public FightDescriptor() {
        fightDescriptions = new HashMap<>();
        fightDescriptions.put(0, "%(winner) just gave %(loser) " +
                "a vicious beatdown with nothing but his bare fists.");
        fightDescriptions.put(1, "%(winner) pulls out a hammer from his backpocket, then clubs " +
                "%(loser)'s head into a new shape.");
        fightDescriptions.put(2,
                "%(winner) and %(loser) destroyed each other through unknown brutal means. " +
                        "Only dust is left.");
        fightDescriptions.put(3, "%(winner) and %(loser) have a dance-off. %(winner) " +
                        "won flamboyantly.");
        fightDescriptions.put(4,
                "%(loser) flees and falls off a cliff. %(winner) wins.");
        fightDescriptions.put(5, "%(winner) and %(loser) fight for three days and three nights " +
                "until %(loser) collapses of exhaustion.");
        fightDescriptions.put(6,
                "%(winner) punches %(loser) while he's asleep.");
        fightDescriptions.put(7,
                "%(winner) waits for %(loser) but %(loser) never shows up. ");
        fightDescriptions.put(8, "%(winner) throws %(loser) down a flight of stairs " +
                "and declares himself the victor.");
    }

    public Map<Integer, String> getFightDescriptions() {
        return fightDescriptions;
    }

    public void setFightDescriptions(Map<Integer, String> fightDescriptions) {
        this.fightDescriptions = fightDescriptions;
    }

    public String determineWinner(String winner, String loser) {
        values.put("winner", winner);
        values.put("loser", loser);
        StrSubstitutor sub = new StrSubstitutor(values, "%(", ")");
        return sub.replace(fightDescriptions.get(new Random().nextInt(9)));
    }
}
