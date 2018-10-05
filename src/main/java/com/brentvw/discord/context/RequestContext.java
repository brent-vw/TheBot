package com.brentvw.discord.context;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public interface RequestContext {
    List<Member> getUsers();

    Channel getChannel();

    User getSender();

    List<Member> getMentions();

    String getMessage();

    List<Message.Attachment> getAttachments();
}
