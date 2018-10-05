package com.brentvw.discord.context;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class RequestContextImpl implements RequestContext {
    private final List<Member> mentions;
    private final List<Member> users;
    private final Channel channel;
    private final User sender;
    private final String message;
    private final List<Message.Attachment> attachments;

    public RequestContextImpl(Message message) {
        this.channel = message.getTextChannel();
        this.sender = message.getAuthor();
        this.users = message.getTextChannel().getMembers();
        this.mentions = message.getMentionedMembers();
        this.message = message.getContentRaw();
        this.attachments = message.getAttachments();
    }

    public List<Member> getUsers() {
        return users;
    }

    public Channel getChannel() {
        return channel;
    }

    public User getSender() {
        return sender;
    }

    public List<Member> getMentions() {
        return mentions;
    }

    public String getMessage() {
        return message;
    }

    public List<Message.Attachment> getAttachments() {
        return attachments;
    }
}
