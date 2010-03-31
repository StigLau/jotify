package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.media.Track;
import no.bouvet.kpro.renderer.Instruction;
import no.lau.tagger.model.AbstractPart;
import no.lau.tagger.model.IPart;

import java.util.List;

/**
 * Simple holder of info for Jotify-Vdvil usage
 */
public class JotifyAudioPart extends AbstractPart {

    public final Float bpm;
    public final float initialOffset;
    public final Track track;
    public final byte[] key;

    public JotifyAudioPart(Track track, byte[] key, Float startCue, Float endCue, Float bpm, float initialOffset) {
        super(startCue, endCue);
        this.track = track;
        this.key = key;
        this.bpm = bpm;
        this.initialOffset = initialOffset;
    }

    @Override
    public Instruction translateToInstruction(Float masterBpm) {
        Float partCompositionDiff = bpm / masterBpm;
        float speedFactor = 44100 * 60 / bpm;

        int start = new Float(startCue * speedFactor * partCompositionDiff).intValue() ;
        int end = new Float(endCue * speedFactor * partCompositionDiff).intValue();

        int cue = new Float((startCue * speedFactor) + (initialOffset * 44100)).intValue();
        int duration = end - start;

        return new JotifyAudioInstruction(cue, end, 0, duration, track, key);
    }

    public List<? extends IPart> children() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}