package com.brentvw.discord;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.impl.EmoteImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    private static Logger LOG = LoggerFactory.getLogger(Listener.class);
    private static int AMOUNT = 156;
    private Message message;
    private static boolean FLAG = false;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if(message.equals("!boom")) {
            if(!FLAG) {
                FLAG = true;
                for (int i = 0; i < 20; i++) {
                    event.getChannel().sendMessage(String.format("<:basically:494798716780412928>")).complete();
                }
            }
        }

        if(message.equals("!basically++")) {
            AMOUNT++;
            sendCount(event);
            return;
        }

        if(message.equals("!basically--")) {
            AMOUNT--;
            sendCount(event);
            return;
        }

        if(message.equals("!basically")) {
            sendCount(event);
            return;
        }

        if(!message.startsWith("!basically")) {
            return;
        }

       String[] content = message.replace("!basically ", "").split(" ");

        if(content.length < 2) {
            return;
        }

        switch (content[0]) {
            case "set":
                setCount(event, content[1]);
        }
    }

    private void setCount(MessageReceivedEvent event, String count) {
        AMOUNT = Integer.parseInt(count);
        sendCount(event);
    }

    private void sendCount(MessageReceivedEvent event) {
        if(AMOUNT % 10 == 0) {
            event.getChannel().sendMessage(String.format("<:basically:494798716780412928> %s :tada: :tada: :tada: :tada: :tada:", AMOUNT)).complete();
            return;
        }
        event.getChannel().sendMessage(String.format("<:basically:494798716780412928> %s", AMOUNT)).complete();
    }
}
