package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;

	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		if(_bomber == null)
			return random.nextInt(4);
		int r=random.nextInt(2);
		if(r==1){
			int t=Col();
			if(t!=-1) return t;
			else return Row();
		}
		else if(r==0){
			int t=Row();
			if(t!=-1) return t;
			else return Col();
		}
		return -1;
	}
	protected int Col(){
		if(_bomber.getXTile()<_e.getXTile()) return 3;
		else if(_bomber.getXTile()>_e.getXTile()) return 1;
		return -1;
	}
	protected int Row(){
		if(_bomber.getYTile()<_e.getYTile()) return 0;
		else if(_bomber.getYTile()>_e.getYTile()) return 2;
		return -1;
	}

}
