package no.lau.vdvil.renderer.jotify;

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
        if(instruction == null) {
            _finished = true;
        }
        else if (instruction instanceof JotifyAudioInstruction) {
            JotifyAudioInstruction jotifyAudioInstruction = (JotifyAudioInstruction) instruction;
            try {
                jotifyAudioInstruction.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


