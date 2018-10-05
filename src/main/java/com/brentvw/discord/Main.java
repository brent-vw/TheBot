package com.brentvw.discord;

import com.brentvw.discord.handler.Handler;
import com.brentvw.discord.handler.MessageHandler;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        Listener listener = new Listener();
        scanHandlers(listener);
        String key = parseKey();
        JDA jda = new JDABuilder(key)
                .addEventListener(listener)
                .build();

        // optionally block until JDA is ready
        jda.awaitReady();
    }

    private static void scanHandlers(Listener listener) {
        String messageHandlerClass = MessageHandler.class.getName();

        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .whitelistPackages("com.brentvw.discord.handler")
                .scan()) {
            ClassInfoList classes = scanResult.getClassesWithAnnotation(messageHandlerClass);
            classes.stream()
                    .map(ClassInfo::loadClass)
                    .filter(Handler.class::isAssignableFrom)
                    .forEach(classZ -> {
                        try {
                            listener.addHandler((Handler) classZ.newInstance());
                        } catch (InstantiationException | IllegalAccessException exception) {
                            LOG.error("Annotation scan failed (could not create instance) {}",
                                    exception);
                        }
                    });
        }
    }

    private static String parseKey() throws IOException {
        InputStream keyFile = Main.class.getResourceAsStream(".keyfile");

        if (keyFile == null) {
            throw new IOException();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(keyFile));
        String key = bufferedReader.readLine();

        if (key == null || key.isEmpty()) {
            throw new IOException();
        }

        return key;
    }
}
