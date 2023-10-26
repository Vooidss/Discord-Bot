package com.techovision.mybot;

import com.techovision.mybot.listeners.EventListener;
import com.techovision.mybot.listeners.ReadyEventListener;
import com.techovision.mybot.listeners.SlashComandsEvent;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class mybot {

    private final Dotenv config;

    public mybot(){

        config = Dotenv.configure().ignoreIfMalformed().load();
        String token = config.get("TOKEN");

        JDABuilder builder = JDABuilder.createDefault(token);

        //Запуск и настройка бота
        JDA jda = builder.enableIntents(
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.AUTO_MODERATION_CONFIGURATION,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new EventListener(),new SlashComandsEvent())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableCache(CacheFlag.ACTIVITY,CacheFlag.CLIENT_STATUS)
                .setActivity(Activity.playing("pussy your Mother"))
                .setStatus(OnlineStatus.ONLINE)
                .build();

    }

    public Dotenv getConfig(){return config; }

    public static void main(String[] args){
        mybot bot = new mybot();
    }
}
