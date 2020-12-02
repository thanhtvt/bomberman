package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.SoundEffect;

public abstract class Item extends Tile {
	public static int timeOfCollision = 0;

	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	public void playSoundEffect() {
		SoundEffect powerup = new SoundEffect(SoundEffect.POWER_UP);
		powerup.play();
	}
}
