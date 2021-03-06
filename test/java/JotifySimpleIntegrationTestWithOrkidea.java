import de.felixbruns.jotify.JotifyConnection;
import de.felixbruns.jotify.exceptions.AuthenticationException;
import de.felixbruns.jotify.exceptions.ConnectionException;
import de.felixbruns.jotify.exceptions.ProtocolException;
import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Result;
import de.felixbruns.jotify.media.Track;
import de.felixbruns.jotify.media.User;
import de.felixbruns.jotify.player.ChannelPlayer;
import de.felixbruns.jotify.player.PlaybackAdapter;
import org.junit.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JotifySimpleIntegrationTestWithOrkidea {

    private static JotifyConnection jotify = new JotifyConnection();

    /* Current track. */
    private static int position = -1;


    @BeforeClass
    public static void before() throws AuthenticationException, ConnectionException {
        String username = "";
        String password = "";
        jotify.login(username, password);
    }

    @AfterClass
    public static void after() throws ConnectionException {
        jotify.close();
    }

    @Test
    public void testGettingUserInfo() throws TimeoutException {
        User user = jotify.user();
        assertEquals("stig.lau", user.getName());
        assertEquals("NO", user.getCountry());
        assertEquals("You have 3 invites.  <a href=\"https://www.spotify.com/account/share/\">Share</a> Spotify with your friends!", user.getNotification());
    }

    @Test
    public void readPlaylist() throws TimeoutException {
        Result result = jotify.search("Orkidea");
        List<Track> tracks = result.getTracks();
        /* Load metadata (files etc.) for track. */
        Track track = jotify.browse(tracks.get(0));
        assertEquals("Orkidea", track.getArtist().getName());
        assertEquals("Metaverse", track.getTitle());
        assertEquals("Metaverse", track.getAlbum().getName());
        assertEquals(2008, track.getYear());
    }

    @Test
    public void testPlayingOrkidea() throws TimeoutException, InterruptedException {
        Result result = jotify.search("Orkidea");
        Track track = result.getTracks().get(0);

        jotify.play(track, new PlaybackAdapter() {
            public void playbackFinished(Track track) {
                try {
                    System.out.println("Playback finished");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(5000);
        jotify.pause();
        Thread.sleep(2000);
        jotify.play();
        Thread.sleep(60000);
        jotify.stop();
    }

    @Test
    public void testPlayAndSkip() throws TimeoutException, InterruptedException {
        Result result = jotify.search("Orkidea");
        Track track = result.getTracks().get(0);

        for (File file : track.getFiles()) {
            System.out.println("file = " + file);
            System.out.println("file.getId() = " + file.getId());
        }
    }

    @Test
    public void haveFunInCache() {
        JotifyConnection jot = jotify;
        List<String> jalla = jot.cache.list("substream");
        byte[] bytestr�m = jot.cache.load("substream", jalla.get(0));
        System.out.println("bytestr�m = " + bytestr�m);
    }
}
