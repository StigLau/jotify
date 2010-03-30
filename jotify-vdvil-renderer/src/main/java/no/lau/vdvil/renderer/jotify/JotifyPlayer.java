package no.lau.vdvil.renderer.jotify;

import no.bouvet.kpro.renderer.*;
import no.bouvet.kpro.renderer.audio.*;
import no.lau.tagger.model.Composition;
import no.lau.tagger.model.AbstractPart;
import org.apache.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This is the master class, responsible for playing a small demoset of VDVIL music
 */
public class JotifyPlayer implements Player {
    private final Renderer renderer;
    private final Float masterBpm;
    static Logger log = Logger.getLogger(JotifyPlayer.class);


    public JotifyPlayer(Composition composition) throws LineUnavailableException {
        masterBpm = composition.masterBpm;
        Instructions instructions = createInstructionsFromParts(composition);
        renderer = new Renderer(instructions);
        renderer.addRenderer(new JotifyRenderer(new AudioPlaybackTarget()));
    }

    static Instructions createInstructionsFromParts(Composition composition) {
        Instructions instructions = new Instructions();
        //Here is where I lay my hack --- jotify instructions
        AudioSource source = null;
        try {
            source = AudioSourceFactory.load(new File("/Users/stiglau/kpro/space_manoeuvres-stage_one_original.mp3"));
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        JotifyAudioInstruction jottAudio = new JotifyAudioInstruction(0, 206959, source, 0, Renderer.RATE);
        instructions.append(jottAudio);

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
