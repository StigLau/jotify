package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Track;
import no.lau.tagger.model.Composition;
import java.util.ArrayList;
import java.util.List;

public class PlayExample {

    public static void main(String[] args) throws Exception {
        JotifyPlayer player = new JotifyPlayer(new Composition(135F, createJotifyTrack()));
        try {
            player.play(0F);
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            player.stop();
            Thread.sleep(200);
        }
        System.exit(0);
    }

    private static List<JotifyAudioPart> createJotifyTrack() {
        Track metaVerse = new Track("bb8eb2dd0e1d4686acf30764d453e380");
        //metaVerse.setArtist(new Artist("05cec1463f9f47eb967a0be9ddf12575", "Orkidea"));
        //metaVerse.setTitle("Metaverse");
        metaVerse.setLength(102400);
        metaVerse.addFile(new File("fab6cc69498316b47828e2f705f81f62ad816db2", "Ogg Vorbis,96000,1,32,4"));
        //metaVerse.addFile(new File("352f3c58f3dfbc1ce9e0510f2fd5b307d7755944", "Ogg Vorbis,160000,1,32,4"));
        //metaVerse.addFile(new File("3fc685de8aa63cadcd07028e7805ef23c4f53c45", "Ogg Vorbis,320000,1,32,4"));
        byte[] metaVerseKey = new byte[]{-111, 0, -18, -94, 4, 15, 43, -114, -61, -4, -42, -86, 56, -94, 52, -65};
        List<JotifyAudioPart> parts = new ArrayList<JotifyAudioPart>();
        parts.add(new JotifyAudioPart(metaVerse, metaVerseKey, 0F, 16F, 130F, 0F));
        parts.add(new JotifyAudioPart(metaVerse, metaVerseKey, 12F, 32F, 130F, 0F));
        return parts;
    }
}