package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Track;
import no.lau.tagger.model.Composition;
import java.util.ArrayList;
import java.util.List;

public class PlayExample {

    public static void main(String[] args) throws Exception {
        List<JotifyAudioPart> parts = new ArrayList<JotifyAudioPart>();
        parts.add(createJotifyTrack());
        parts.add(createSolidaritetTrack());
        
        JotifyPlayer player = new JotifyPlayer(new Composition(135F, parts));
        try {
            player.play(0F);
            Thread.sleep(20000);
        } finally {
            player.stop();
            Thread.sleep(200);
        }
        System.exit(0);
    }

    private static JotifyAudioPart createJotifyTrack() {
        Track metaVerse = new Track("bb8eb2dd0e1d4686acf30764d453e380");
        //metaVerse.setArtist(new Artist("05cec1463f9f47eb967a0be9ddf12575", "Orkidea"));
        //metaVerse.setTitle("Metaverse");
        metaVerse.addFile(new File("fab6cc69498316b47828e2f705f81f62ad816db2", "Ogg Vorbis,96000,1,32,4"));
        //metaVerse.addFile(new File("352f3c58f3dfbc1ce9e0510f2fd5b307d7755944", "Ogg Vorbis,160000,1,32,4"));
        //metaVerse.addFile(new File("3fc685de8aa63cadcd07028e7805ef23c4f53c45", "Ogg Vorbis,320000,1,32,4"));
        byte[] metaVerseKey = new byte[]{-111, 0, -18, -94, 4, 15, 43, -114, -61, -4, -42, -86, 56, -94, 52, -65};
        return new JotifyAudioPart(metaVerse, metaVerseKey, 0F, 16F, 130F, 0F);
    }

    private static JotifyAudioPart createSolidaritetTrack() {
        Track solidaritet = new Track("918dcb3151244138a1af501da2fbf9bd");
        solidaritet.addFile(new File("2936b3e54f469f42af9f79294ffe754bd611d626", "Ogg Vorbis,96000,1,32,4"));
        //solidaritet.addFile(new File("d3c159aae9453d0cfcd414016f064673454735", "Ogg Vorbis,160000,1,32,4"));
        byte[] metaVerseKey = new byte[]{-94, 38, 111, -52, 120, 69, -63, 97, -17, -95, -36, 37, -78, 28, -38, 117};
        return new JotifyAudioPart(solidaritet, metaVerseKey, 12F, 32F, 128F, 0F);
    }
}