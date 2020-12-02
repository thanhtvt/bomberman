package uet.oop.bomberman.sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    public static final String BACKGROUND_THEME = "background_theme";
    public static final String ENEMY_DEAD = "enemy_dead";
    public static final String EXPLOSION = "explosion";
    public static final String MULTIPLAYER = "multiplayer";
    public static final String PLACE_BOMB = "place_bomb";
    public static final String PLAYER_DEAD = "player_dead";
    public static final String POWER_UP = "power_up";
    public static final String STAGE_COMPLETE = "stage_complete";
    public static final String STAGE_START = "stage_start";

    private static boolean _muted = false;
    private Clip clip;

    public SoundEffect(String filename) {
        String path = "res/sounds/" + filename + ".wav";
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(!_muted) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop() {
        if(!_muted) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        clip.stop();
    }

    public static void setMute(boolean muted) {
        _muted = muted;
    }

    public static boolean isMuted() {
        return _muted;
    }
}
