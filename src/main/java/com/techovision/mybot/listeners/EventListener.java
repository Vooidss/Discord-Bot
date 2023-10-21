package com.techovision.mybot.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class EventListener extends ListenerAdapter {

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

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        final Dotenv config;
        config = Dotenv.configure().ignoreIfMalformed().load();
        String VK = config.get("VK");
        String TG = config.get("TELEGRAM");

        switch (message){
            case "мой телеграм" -> event.getChannel().sendMessage(TG).queue();
            case "мой vk" -> event.getChannel().sendMessage(VK).queue();
            case "кто моя любимая девочка?" -> event.getChannel().sendMessage("Ксюшечка").queue();
        }
    }

}
