package com.techovision.mybot;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import javax.security.auth.login.LoginException;

public class mybot {

    private final ShardManager shardManager;

    public mybot(){
        String token = "MTE2NTAwNjc2MjI3MzkzNTQzMA.Ghizle.k0DCsXdOLoe3q8K9QF3D2qhzbRiIv1AqNyjKt4";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("DISCORD"));
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args){
        mybot bot = new mybot();


    }

}
