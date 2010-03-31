import de.felixbruns.jotify.cache.SubstreamCache;
import de.felixbruns.jotify.media.Artist;
import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Track;
import de.felixbruns.jotify.player.ChannelPlayer;
import de.felixbruns.jotify.player.PlaybackListener;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class OfflinePlayTest {
    /*
     * This test is a proof of concept
     * This test requires that one has played/cached Orkidea Metaverse
     * @throws Exception
     */
    @Test
    public void playFromDiskCache() throws Exception {
        Track metaVerse = new Track("bb8eb2dd0e1d4686acf30764d453e380");
        metaVerse.setArtist(new Artist("05cec1463f9f47eb967a0be9ddf12575", "Orkidea"));
        metaVerse.setTitle("Metaverse");
        metaVerse.setLength(401008);
        metaVerse.addFile(new File("fab6cc69498316b47828e2f705f81f62ad816db2", "Ogg Vorbis,96000,1,32,4"));
        metaVerse.addFile(new File("352f3c58f3dfbc1ce9e0510f2fd5b307d7755944", "Ogg Vorbis,160000,1,32,4"));
        metaVerse.addFile(new File("3fc685de8aa63cadcd07028e7805ef23c4f53c45", "Ogg Vorbis,320000,1,32,4"));

        byte[] metaVerseKey  = new byte[]{-111,0,-18,-94,4,15,43,-114,-61,-4,-42,-86,56,-94,52,-65};

        String hash = "fab6cc69498316b47828e2f705f81f62ad816db2/fab6cc69498316b47828e2f705f81f62ad816db2-0-102400";

        ChannelPlayer player = new ChannelPlayer(metaVerse, metaVerseKey);
        player.cache.load("substream", hash, player);
        player.open(player.input);


        
        player.play(metaVerse, new PlaybackListener() {
            public void playbackStarted(Track track) {
                System.out.println("playbackStarted");
            }

            public void playbackStopped(Track track) {
                System.out.println("playBackStopped");
            }

            public void playbackResumed(Track track) {
                System.out.println("resumed");
            }

            public void playbackPosition(Track track, int ms) {
                System.out.println("playbackPosition" + "track = " + track + "ms = " + ms);
            }

            public void playbackFinished(Track track) {
                System.out.println("playbackFinished");
            }
        });
        Thread.sleep(2000);
        player.pause();
        player.play();
        Thread.sleep(2000);
        player.stop();

    }

    @Test
    public void playSolidaritet() throws Exception {
        Track solidaritet = new Track("918dcb3151244138a1af501da2fbf9bd");
        int streamLength = 160 * 1024 * 5 / 8;
        solidaritet.addFile(new File("2936b3e54f469f42af9f79294ffe754bd611d626", "Ogg Vorbis,96000,1,32,4"));
        //solidaritet.addFile(new File("0fd3c159aae9453d0cfcd414016f064673454735", "Ogg Vorbis,160000,1,32,4"));
        byte[] solidaritetKey = new byte[]{-94, 38, 111, -52, 120, 69, -63, 97, -17, -95, -36, 37, -78, 28, -38, 117};
        int offset = 0;
        String hash = new SubstreamCache().hash(solidaritet, offset, streamLength);
        
        ChannelPlayer player = new ChannelPlayer(solidaritet, solidaritetKey);
        player.cache.load("substream", hash, player);
        player.open(player.input);
        player.play();
    }
}
