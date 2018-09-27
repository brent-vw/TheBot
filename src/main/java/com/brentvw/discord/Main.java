package com.brentvw.discord;

import com.brentvw.discord.handler.BasicallyHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String... args) throws Exception {
        Listener listener = new Listener();
        listener.addHandler(new BasicallyHandler());

        String key = parseKey();
        JDA jda = new JDABuilder(key)
                .addEventListener(listener)
                .build();

        // optionally block until JDA is ready
        jda.awaitReady();
    }

    private static String parseKey() throws IOException {
        InputStream keyFile = Main.class.getResourceAsStream(".keyfile");

        if(keyFile == null) {
            throw new IOException();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(keyFile));
        String key = bufferedReader.readLine();

        if(key == null || key.isEmpty()) {
            throw new IOException();
        }

        return key;
    }
}
