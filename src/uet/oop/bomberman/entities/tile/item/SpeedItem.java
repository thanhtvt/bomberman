package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if(e instanceof Bomber) {
			if(timeOfCollision % 2 == 0) {
				((Bomber) e).addBomberSpeed(0.3);
				playSoundEffect();
				remove();
			}
			timeOfCollision++;
			return true;
		}
		else if(e instanceof Flame) {
			remove();
			return true;
		}
		return false;
	}
}
