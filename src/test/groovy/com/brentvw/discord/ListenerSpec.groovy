package com.brentvw.discord

import com.brentvw.discord.handler.Handler
import net.dv8tion.jda.core.entities.Channel
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.requests.restaction.MessageAction
import spock.lang.Specification

class ListenerSpec extends Specification {
    Handler cannotHandle
    Handler canHandle
    Listener listener
    MessageReceivedEvent event
    MessageReceivedEvent bot

    def setup() {
        cannotHandle = Mock(Handler.class) {
            canHandle(_) >> false
        }

        canHandle = Mock(Handler.class) {
            canHandle(_) >> true
        }

        listener = new Listener()
        event = Mock(MessageReceivedEvent.class) {
            getAuthor() >> Mock(User) {
                isBot() >> false
            }
            getMessage() >> Mock(Message.class) {
                getTextChannel() >> Mock(TextChannel)
            }
            getChannel() >> Mock(MessageChannel.class) {
                sendMessage(_) >> Mock(MessageAction)
            }
        }
        bot = Mock(MessageReceivedEvent.class) {
            getAuthor() >> Mock(User) {
                isBot() >> true
            }
            getMessage() >> Mock(Message.class)
            getChannel() >> Mock(MessageChannel.class)
        }
    }

    def "The listener should use the handler if it can handle the message"() {
        given:
        listener.addHandler(canHandle)

        when:
        listener.onMessageReceived(event)

        then:
        1 * canHandle.handle(_)
    }

    def "The listener should not use the handler if it can't handle the message"() {
        given:
        listener.addHandler(cannotHandle)

        when:
        listener.onMessageReceived(event)

        then:
         0 * cannotHandle.handle(_)
    }

    def "The listener should not use the handler if it comes from a bot"() {
        given:
        listener.addHandler(canHandle)

        when:
        listener.onMessageReceived(bot)

        then:
        0 * canHandle.handle(_)
    }
}
