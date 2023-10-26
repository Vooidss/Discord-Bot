package com.techovision.mybot.listeners;

import com.techovision.mybot.ListSlashCommands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class SlashComandsEvent extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        ListSlashCommands ListSlashCommands = new ListSlashCommands();

        for(String listCommand : ListSlashCommands.getAnswers().values()){

            if(listCommand.equals(event.getName())){
                event.reply(ListSlashCommands.getAnswers().get(listCommand));
            }
        }

    }
}
