package com.techovision.mybot.events;

import com.techovision.mybot.VoiceChannel;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MessageReceived  extends MessageReactionAdd {

    private final String[] BAD_WORLDS = {"чурка","долбаёб","хуесос","ебал"};


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw().toLowerCase(Locale.ROOT);
        final Dotenv config;
        config = Dotenv.configure().ignoreIfMalformed().load();
        String VK = config.get("VK");
        String TG = config.get("TELEGRAM");
        List<Member> members = event.getGuild().getMembers();

        VoiceChannel voiceChannel = new VoiceChannel();


        for(String word : BAD_WORLDS){

            if(event.getMessage().getContentRaw().contains(word)){

                TextChannel staffChannel = event.getJDA().getTextChannelById("1167205300957360158");

                event.getChannel().sendMessage("Молодой человек, не произносите такие некультурные вещи").queue();



            }

        }

        if(message.contains("присоединись к голосовому каналу")){
            voiceChannel.joinVoiceChannel(event.getGuild(), event.getGuild().getVoiceChannelById(voiceChannel.WhichVoiceChannel(event.getGuild(), message.substring(message.indexOf("каналу")+7))));
        }

        if (message.equalsIgnoreCase("help")) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                File file = new File("C:\\Users\\Егор\\IdeaProjects\\Discord-Bot-main\\src\\main\\java\\com\\techovision\\mybot\\Help.txt");
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    stringBuilder.append(line).append("\n");
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String result = stringBuilder.toString();
            System.out.println(stringBuilder);

            event.getChannel().sendMessage(result).queue();

        }

        switch (message){
            case "мой телеграм" -> event.getChannel().sendMessage(TG).queue();
            case "мой vk" -> event.getChannel().sendMessage(VK).queue();
            case "кто моя любимая девочка?" -> event.getChannel().sendMessage("Ксюшечка").queue();
            case "кто мой любимый кот?" -> event.getChannel().sendMessage("Болбес под именем Шуга").queue();
            case "@all" -> event.getChannel().sendMessage(MemberstOnlineStatus(members)).queue();
        }

    }

    public static String MemberstOnlineStatus(List<Member> members) {

        String txt = "";

        for(Member member : members){
            if(member.getOnlineStatus() == OnlineStatus.ONLINE){
                txt += String.format("Участник %s сейчас онлайн\n",member);
            }
        }
        return txt;
    }


}
