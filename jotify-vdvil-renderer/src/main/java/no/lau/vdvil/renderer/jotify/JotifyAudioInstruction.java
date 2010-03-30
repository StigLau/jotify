package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.media.Artist;
import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Track;
import de.felixbruns.jotify.player.ChannelPlayer;
import de.felixbruns.jotify.player.PlaybackListener;
import no.bouvet.kpro.renderer.audio.AudioInstruction;
import no.bouvet.kpro.renderer.audio.AudioSource;

public class JotifyAudioInstruction extends AudioInstruction {

    public JotifyAudioInstruction(int start, int end, AudioSource source, int cue, int duration) {
        super(start, end, source, cue, duration);
    }

    public void play() throws Exception {
        Track metaVerse = new Track("bb8eb2dd0e1d4686acf30764d453e380");
        metaVerse.setArtist(new Artist("05cec1463f9f47eb967a0be9ddf12575", "Orkidea"));
        metaVerse.setTitle("Metaverse");
        metaVerse.setLength(401008);
        metaVerse.addFile(new File("fab6cc69498316b47828e2f705f81f62ad816db2", "Ogg Vorbis,96000,1,32,4"));
        metaVerse.addFile(new File("352f3c58f3dfbc1ce9e0510f2fd5b307d7755944", "Ogg Vorbis,160000,1,32,4"));
        metaVerse.addFile(new File("3fc685de8aa63cadcd07028e7805ef23c4f53c45", "Ogg Vorbis,320000,1,32,4"));

        byte[] metaVerseKey  = new byte[]{-111,0,-18,-94,4,15,43,-114,-61,-4,-42,-86,56,-94,52,-65};

        ChannelPlayer player = new ChannelPlayer(metaVerse, metaVerseKey);
        String hash = "fab6cc69498316b47828e2f705f81f62ad816db2/fab6cc69498316b47828e2f705f81f62ad816db2-0-102400";

        player.cache.load("substream", hash, player);
        player.open(player.input);

        player.play();
    }
}
