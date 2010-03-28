package no.lau.vdvil.renderer.jotify;

import no.bouvet.kpro.renderer.AbstractRenderer;
import no.bouvet.kpro.renderer.Instruction;

/**
 * This renderer is responsible for adding video instructions to the list of a
 * player's active instructions at the right times. I doesn't have to remove the
 * video instructions since the movie player will take care of that itself.
 *
 * @author Stig Lau mailto:Stig@Lau.no
 *
 */

public class JotifyRenderer extends AbstractRenderer  {
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
            return;
        }
        if (instruction instanceof JotifyAudioInstruction) {
            JotifyAudioInstruction jotifyAudioInstruction = (JotifyAudioInstruction) instruction;
            jotifyAudioInstruction.play();
        }
    }
}

