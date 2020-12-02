package uet.oop.bomberman.entities.character.enemy.ai;
import java.util.Random;

public class AILow extends AI {

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		int value = random.nextInt(4) ;
		return value;
	}

}
