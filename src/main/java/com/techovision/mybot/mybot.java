package com.techovision.mybot;

import com.techovision.mybot.events.MessageReactionAdd;
import com.techovision.mybot.events.MessageReceived;
import com.techovision.mybot.events.SlashComandsEvent;
import com.techovision.mybot.events.UserUpdateOnlineStatus;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Arrays;

public class mybot {

    private final Dotenv config;

    public final static GatewayIntent[] INTENTS = { GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_PRESENCES, GatewayIntent.SCHEDULED_EVENTS};


    public mybot(){

        config = Dotenv.configure().ignoreIfMalformed().load();
        String token = config.get("TOKEN");

        JDABuilder builder = JDABuilder.createDefault(token);

        //Запуск и настройка бота

            JDA jda = builder.enableIntents(Arrays.asList(INTENTS))
                .addEventListeners(new MessageReactionAdd(),new SlashComandsEvent(),new MessageReceived(),new SlashComandsEvent(),new UserUpdateOnlineStatus())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.ACTIVITY,CacheFlag.CLIENT_STATUS)
                .setActivity(Activity.playing("pussy your Mother"))
                .setStatus(OnlineStatus.ONLINE)
                .build();

            jda.upsertCommand("show_commands","Will show all commands").queue();

    }

    public Dotenv getConfig(){return config; }

    public static void main(String[] args){
        mybot bot = new mybot();
    }
}
