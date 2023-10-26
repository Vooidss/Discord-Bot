package com.techovision.mybot.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReactionAdd extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String mychanel = event.getChannel().getAsMention();
        String jumplink = event.getJumpUrl();

        String massage = user.getGlobalName() + " отправил реакцию - " + emoji + " в канале " + mychanel;
        TextChannel channel = event.getGuild().getTextChannelById(event.getChannel().getId());
        channel.sendMessage(massage).queue();
    }
}

