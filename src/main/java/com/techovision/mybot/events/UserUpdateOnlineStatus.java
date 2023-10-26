package com.techovision.mybot.events;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserUpdateOnlineStatus  extends ListenerAdapter {
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



}
