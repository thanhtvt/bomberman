package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class BlueBomber extends Bomber {
    public BlueBomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprite.player2_right;
    }

    @Override
    public void render(Screen screen) {
        if (_alive)
            chooseSprite();
        else {
            if(_timeAfter > 0) {
                _sprite = Sprite.player2_dead1;
            }
            else {
                _sprite = Sprite.movingSprite(Sprite.player2_dead1, Sprite.player2_dead2, Sprite.player2_dead3, _animate, 10);
            }
        }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    @Override
    protected void detectPlaceBomb() {
        // TODO: kiểm tra xem phím điều khiển đặt bom có được gõ và giá trị _timeBetweenPutBombs, Game.getBombRate() có thỏa mãn hay không
        // TODO:  Game.getBombRate() sẽ trả về số lượng bom có thể đặt liên tiếp tại thời điểm hiện tại
        // TODO: _timeBetweenPutBombs dùng để ngăn chặn Bomber đặt 2 Bomb cùng tại 1 vị trí trong 1 khoảng thời gian quá ngắn
        // TODO: nếu 3 điều kiện trên thỏa mãn thì thực hiện đặt bom bằng placeBomb()
        // TODO: sau khi đặt, nhớ giảm số lượng Bomb Rate và reset _timeBetweenPutBombs về 0
        if(_input.space1 && _timeBetweenPutBombs < 0 && this.getBombRate() > 0 && _board.getBombAt(getXTile(), getYTile()) == null) {
            placeBomb(getXTile(), getYTile());
            _timeBetweenPutBombs = 0;
            this.addBombRate(-1);
        }
    }

    @Override
    protected void calculateMove() {
        double bomberSpeed = this.getBomberSpeed();
        double x = _x, y = _y;
        if(_input.left1) {
            x = _x - 1 * bomberSpeed;
        }
        if(_input.right1) {
            x = _x + 1 * bomberSpeed;
        }
        if(_input.up1) {
            y = _y - 1 * bomberSpeed;
        }
        if(_input.down1) {
            y = _y + 1 * bomberSpeed;
        }
        if(x != _x || y != _y) {
            _moving = true;
            move(x, y);
        }
        else {
            _moving = false;
        }
        // Check this again!!!
        movementRounding();
    }

    @Override
    public void move(double xa, double ya) {
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: nhớ cập nhật giá trị _direction sau khi di chuyển
        if(_input.up1) {
            _direction = 0;
        }
        else if(_input.right1) {
            _direction = 1;
        }
        else if(_input.down1) {
            _direction = 2;
        }
        else if(_input.left1) {
            _direction = 3;
        }

        if(canMove(xa, ya)) {
            _x = xa;
            _y = ya;
        }
    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player2_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_up_1, Sprite.player2_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player2_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_right_1, Sprite.player2_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player2_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_down_1, Sprite.player2_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player2_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_left_1, Sprite.player2_left_2, _animate, 20);
                }
                break;
        }
    }
}
