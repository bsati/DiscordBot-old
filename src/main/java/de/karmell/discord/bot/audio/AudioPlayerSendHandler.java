package de.karmell.discord.bot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;


/**
 * Implementation as mentioned by https://github.com/sedmelluq/lavaplayer to integrate with JDA
 */
public class AudioPlayerSendHandler implements AudioSendHandler
{
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    public AudioPlayerSendHandler(AudioPlayer audioPlayer)
    {
        this.audioPlayer = audioPlayer;
    }

    public boolean canProvide()
    {
        if (lastFrame == null)
        {
            lastFrame = audioPlayer.provide();
        }

        return lastFrame != null;
    }

    public byte[] provide20MsAudio()
    {
        if (lastFrame == null)
        {
            lastFrame = audioPlayer.provide();
        }

        byte[] data = lastFrame != null ? lastFrame.getData() : null;
        lastFrame = null;

        return data;
    }

    public boolean isOpus()
    {
        return true;
    }
}
