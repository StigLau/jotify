package no.lau.vdvil.renderer.jotify;

import de.felixbruns.jotify.media.File;
import de.felixbruns.jotify.media.Track;
import no.lau.tagger.model.Composition;
import java.util.ArrayList;
import java.util.List;

public class MixTest {

    public static void main(String[] args) throws Exception {
        Track solidaritet = new Track("918dcb3151244138a1af501da2fbf9bd");
        solidaritet.addFile(new File("2936b3e54f469f42af9f79294ffe754bd611d626", "Ogg Vorbis,96000,1,32,4"));
        byte[] solidaritetKey = new byte[]{-94, 38, 111, -52, 120, 69, -63, 97, -17, -95, -36, 37, -78, 28, -38, 117};

        float speed = 128F;

        List<JotifyAudioPart> parts = new ArrayList<JotifyAudioPart>();
        parts.add(new JotifyAudioPart(solidaritet, solidaritetKey, 0F, 16F, speed, 2.963F));
        parts.add(new JotifyAudioPart(solidaritet, solidaritetKey, 2F, 32F, speed, 2.963F));
        parts.add(new JotifyAudioPart(solidaritet, solidaritetKey, 4F, 32F, speed, 2.963F));

        JotifyPlayer player = new JotifyPlayer(new Composition(speed, parts));
        try {
            player.play(0F);
            System.out.println("playing");
            Thread.sleep((long) (64000 + 2963));
            System.out.println("Stopped");
        } finally {
            player.stop();
            Thread.sleep(200);
        }
        System.exit(0);
    }
}