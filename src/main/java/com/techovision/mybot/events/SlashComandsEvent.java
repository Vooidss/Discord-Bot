package com.techovision.mybot.events;

import com.techovision.mybot.ListSlashCommands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SlashComandsEvent extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        StringBuilder txt = new StringBuilder();

        if(event.getName().equalsIgnoreCase("show_commands")){
            try {
                File file = new File("C:\\Users\\Егор\\IdeaProjects\\Discord-Bot-main\\src\\main\\java\\com\\techovision\\mybot\\Help.txt");
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    txt.append(line).append("\n");
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String result = txt.toString();
            System.out.println(txt);

            event.getChannel().sendMessage(result).queue();
        }

    }
}
