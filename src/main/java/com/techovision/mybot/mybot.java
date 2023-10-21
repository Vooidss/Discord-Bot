package com.techovision.mybot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
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
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
    public Dotenv getConfig(){return config; }

    public static void main(String[] args) throws LoginException {
        mybot bot = new mybot();


    }

}
