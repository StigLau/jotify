package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.cache.SubstreamCache;
import de.felixbruns.jotify.media.Track;
import no.bouvet.kpro.renderer.audio.AudioInstruction;

public class JotifyAudioInstruction extends AudioInstruction {
    final Track track;
    final byte[] key;

    public JotifyAudioInstruction(int start, int end, int cue, int duration, Track track, byte[] key) {
        super(start, end, null, cue, duration);
        this.track = track;
        this.key = key;
    }

    /*
     * This function is VERY errorprone. Used for testing purposes!!!!
     */
    public String hash() {
        int offset = 0;
        return new SubstreamCache().hash(track, offset, track.getLength());
    }
}
