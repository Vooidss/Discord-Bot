package com.techovision.mybot.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

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
        String redirected = "";
        String Id_VoiceChanel = "";
        String VK = config.get("VK");
        String TG = config.get("TELEGRAM");
        OnlineStatus onlineStatus = event.getMember().getOnlineStatus();
        List<Member> members = event.getGuild().getMembers();
        VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById("1164997029295034413");
        AudioManager audioManager = event.getGuild().getAudioManager();

        if(message.contains("присоединись к голосовому каналу")){
            joinVoiceChannel(event.getGuild(), event.getGuild().getVoiceChannelById(WhichVoiceChannel(message.substring(message.indexOf("каналу")+6))));
        }

        switch (message){
            case "мой телеграм" -> event.getChannel().sendMessage(TG).queue();
            case "мой vk" -> event.getChannel().sendMessage(VK).queue();
            case "кто моя любимая девочка?" -> event.getChannel().sendMessage("Ксюшечка").queue();
            case "@all" -> event.getChannel().sendMessage(MemberstOnlineStatus(members)).queue();
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getMember().getAvatar();
    }

    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {

        User user = event.getUser();
        OnlineStatus status = event.getMember().getOnlineStatus();
        String statusMember = "";
        String txt ="";
        List<Member> members= event.getGuild().getMembers();
        int CountUsersInOnline = 0;

        for(Member member : members){
            if(member.getOnlineStatus() == OnlineStatus.ONLINE)
                CountUsersInOnline++;
        }

        switch (status){
            case ONLINE -> statusMember = "Онлайн";
            case DO_NOT_DISTURB -> statusMember = "Небеспокоить";
            case IDLE -> statusMember = "Отошёл";
            case OFFLINE -> statusMember ="Вышел";
        }

            if (statusMember != "Вышел")
                txt = "**" + user.getName() + "** обновил свой статус на -> " + statusMember;
            else
                txt = "**" + user.getName() + "** -> " + statusMember;

            if(statusMember == "Онлайн")
                txt += "\nТеперь участников онлайн -> " + CountUsersInOnline;


        TextChannel channel = event.getGuild().getSystemChannel();
        channel.sendMessage(txt).queue();

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

    public void joinVoiceChannel(Guild guild, VoiceChannel voiceChannel) {
        AudioManager audioManager = guild.getAudioManager();
        audioManager.openAudioConnection(voiceChannel);
    }
    public String WhichVoiceChannel(String channel){;
        switch (channel.toLowerCase(Locale.ROOT)){
            case " основной" -> {
                return "1164997029295034413";
            }
            case " второй" -> {
                return "1165762172975058994";
            }
        }
    return null;
    }
}

