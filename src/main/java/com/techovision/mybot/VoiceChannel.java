package com.techovision.mybot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoiceChannel {

    public void joinVoiceChannel(Guild guild, net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel voiceChannel) {
        AudioManager audioManager = guild.getAudioManager();
        audioManager.openAudioConnection(voiceChannel);
    }
    public String WhichVoiceChannel(Guild guild,String channel){;

        Map<String, net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel> voiceChannelsId = new HashMap<>();
        List<net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel> voiceChanels = guild.getVoiceChannels();

        for(net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel voiceChannel : voiceChanels){

            voiceChannelsId.put(voiceChannel.getId(),voiceChannel);

        }

        for(net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel Id : voiceChannelsId.values()){

            if((String.valueOf(Id).toLowerCase()).contains(channel)){

                return Id.getId();

            }

        }

        return null;
    }

}
