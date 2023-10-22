package com.techovision.mybot;

import com.techovision.mybot.listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class mybot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public mybot(){
        config = Dotenv.configure().ignoreIfMalformed().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("DISCORD"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                GatewayIntent.AUTO_MODERATION_CONFIGURATION,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_VOICE_STATES);
        //Просматривает всех пользователей
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ACTIVITY,CacheFlag.CLIENT_STATUS);
        shardManager = builder.build();

        shardManager.addEventListener(new EventListener());
    }


    public ShardManager getShardManager() {
        return shardManager;
    }
    public Dotenv getConfig(){return config; }

    public static void main(String[] args){
        mybot bot = new mybot();


    }
}
