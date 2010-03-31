package no.lau.vdvil.renderer.jotify;

import no.bouvet.kpro.renderer.*;
import no.bouvet.kpro.renderer.audio.*;
import no.lau.tagger.model.Composition;
import no.lau.tagger.model.AbstractPart;
import org.apache.log4j.Logger;

/**
 * This is the Jotify Player class, it is responsible for loading the different renderers
 */
public class JotifyPlayer implements Player {
    private final Renderer renderer;
    private final Float masterBpm;
    static Logger log = Logger.getLogger(JotifyPlayer.class);


    public JotifyPlayer(Composition composition) throws Exception {
        masterBpm = composition.masterBpm;
        Instructions instructions = createInstructionsFromParts(composition);
        renderer = new Renderer(instructions);
        renderer.addRenderer(new JotifyRenderer(new AudioPlaybackTarget()));
    }

    static Instructions createInstructionsFromParts(Composition composition) throws Exception {
        Instructions instructions = new Instructions();
        for (AbstractPart part : composition.parts) {
            instructions.append(part.translateToInstruction(composition.masterBpm));
        }
        return instructions;
    }

    public void play(Float startCue) {
        Float startCueInMillis = (startCue * Renderer.RATE * 60)/ masterBpm;
        renderer.start(startCueInMillis.intValue());
    }

    public void stop() {
        renderer.stop();
    }
}
