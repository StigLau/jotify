package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.cache.SubstreamCache;
import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Track;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CacheHashTest {

    @Test
    public void createHash() {
        Track track = new Track();
        track.addFile(new File("fab6cc69498316b47828e2f705f81f62ad816db2", ""));
        track.setLength(102400);
        byte[] key = new byte[]{-111, 0, -18, -94, 4, 15, 43, -114, -61, -4, -42, -86, 56, -94, 52, -65};
        JotifyAudioInstruction jap = new JotifyAudioInstruction(0, 0, 0, 0, track, key);
        
        assertEquals("fab6cc69498316b47828e2f705f81f62ad816db2/fab6cc69498316b47828e2f705f81f62ad816db2-0-102400", jap.hash());
    }


}
