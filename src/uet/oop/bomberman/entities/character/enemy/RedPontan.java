package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.enemy.ai.bfs;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;


public class RedPontan extends Enemy {

    public RedPontan(int x, int y, Board board) {
        super(x, y, board, Sprite.RedPontan_dead, Game.getBomberSpeed(), 400);

        _sprite = Sprite.RedPontan_right1;
        _speed = 1;
        _ai = new bfs(_board.getBomber(), this);
        _direction  = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                if(_moving)
                    _sprite = Sprite.movingSprite(Sprite.RedPontan_right1, Sprite.RedPontan_right2, Sprite.RedPontan_right3, _animate, 60);
                else
                    _sprite = Sprite.RedPontan_left1;
                break;
            case 2:
            case 3:
                if(_moving)
                    _sprite = Sprite.movingSprite(Sprite.RedPontan_left1, Sprite.RedPontan_left2, Sprite.RedPontan_left3, _animate, 60);
                else
                    _sprite = Sprite.RedPontan_left1;
                break;
        }
    }

}
