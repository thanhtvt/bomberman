package uet.oop.bomberman.entities.character.enemy.ai;
import java.util.Random;

public class AILow extends AI {

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		Random generator = new Random();
		int value = generator.nextInt(5) + 1;
		return value;
	}

}
