package no.lau.vdvil.renderer.jotify;

import no.bouvet.kpro.tagger.persistence.XStreamParser;
import no.lau.tagger.model.Composition;
import no.lau.tagger.model.AudioPart;
import no.lau.tagger.model.SimpleSong;

import java.util.ArrayList;
import java.util.List;

public class PlayExample {

    public static void main(String[] args) throws Exception {
        JotifyPlayer player = new JotifyPlayer(new Composition(135F, parts()));
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

    public static List<AudioPart> parts() {
        SimpleSong returning = new XStreamParser().load("/Users/stiglau/kpro/holden-nothing-93_returning_mix.dvl");
        List<AudioPart> parts = new ArrayList<AudioPart>();
        parts.add(new AudioPart(returning, 0F, 16F, returning.segments.get(3)));
        parts.add(new AudioPart(returning, 12F, 32F, returning.segments.get(6)));
        parts.add(new AudioPart(returning, 32F, 62.5F, returning.segments.get(9)));
        parts.add(new AudioPart(returning, 62F, 63.5F, returning.segments.get(10)));
        parts.add(new AudioPart(returning, 63F, 64.5F, returning.segments.get(11)));
        parts.add(new AudioPart(returning, 64F, 128F, returning.segments.get(12)));
        parts.add(new AudioPart(returning, 128F, 256F, returning.segments.get(14)));
        return parts;
    }
}