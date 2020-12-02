package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

import java.awt.*;

public class Pontan extends Enemy {

    public Pontan(int x, int y, Board board) {
        super(x, y, board, Sprite.Pontan_dead, Game.getBomberSpeed(), 200);

        _sprite = Sprite.Pontan_left1;
        _speed = 1;
        _ai = new AIMedium(_board.getBomber(), this);
        _direction  = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                if(_moving)
                    _sprite = Sprite.movingSprite(Sprite.Pontan_right1, Sprite.Pontan_right2, Sprite.Pontan_right3, _animate, 60);
                else
                    _sprite = Sprite.Pontan_left1;
                break;
            case 2:
            case 3:
                if(_moving)
                    _sprite = Sprite.movingSprite(Sprite.Pontan_left1, Sprite.Pontan_left2, Sprite.Pontan_left3, _animate, 60);
                else
                    _sprite = Sprite.Pontan_left1;
                break;
        }
    }

    @Override
    public void kill() {

        if(!_alive) return;
        _alive = false;

        _board.addPoints(_points);

        Message msg = new Message("+" + _points, getXMessage(), getYMessage(), 2, Color.white, 14);
        _board.addMessage(msg);
        _board.addCharacter( new RedPontan(Coordinates.tileToPixel(this.getXTile()), Coordinates.tileToPixel(this.getYTile()) + Game.TILES_SIZE, _board));

    }
}
