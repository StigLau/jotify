package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.player.ChannelPlayer;
import no.bouvet.kpro.renderer.AbstractRenderer;
import no.bouvet.kpro.renderer.Instruction;
import no.bouvet.kpro.renderer.audio.AudioInstruction;
import no.bouvet.kpro.renderer.audio.AudioRenderer;
import no.bouvet.kpro.renderer.audio.AudioTarget;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This renderer is responsible for adding video instructions to the list of a
 * player's active instructions at the right times. I doesn't have to remove the
 * video instructions since the movie player will take care of that itself.
 *
 * @author Stig Lau mailto:Stig@Lau.no
 *
 */

public class JotifyRenderer extends AudioRenderer {
    public JotifyRenderer(AudioTarget target) {
        super(target);
    }

    /**
     * The instruction parameter may be null, which indicates no more
	 * instructions.
	 * 
	 * @param time the current rendering time in samples
	 * @param instruction the instruction that has occurred, or null
	 */

    @Override
    public void handleInstruction(int time, Instruction instruction) {
        System.out.println("Instruction arrived " + instruction);
        if(instruction == null) {
            _finished = true;
        }
        else if (instruction instanceof JotifyAudioInstruction) {
            JotifyAudioInstruction jotifyAudioInstruction = (JotifyAudioInstruction) instruction;
            try {
                ChannelPlayer player = new ChannelPlayer(jotifyAudioInstruction.track, jotifyAudioInstruction.key);
                player.cache.load("substream", jotifyAudioInstruction.hash(), player);
                player.open(player.input);
                player.play();
            } catch (Exception e) {
                throw new RuntimeException("Problem with ChannelPlayer in JotifyAudioInstruction", e);
            }
        }
    }
}


