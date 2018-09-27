package com.brentvw.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
    public static void main(String... args) throws Exception {
        JDA jda = new JDABuilder("")
                .addEventListener(new Listener())
                .build();

        // optionally block until JDA is ready
        jda.awaitReady();
    }
}
