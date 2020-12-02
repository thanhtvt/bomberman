package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.BlueBomber;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Ghost;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.Pontan;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	public static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		String path = "res/levels/Level" + level + ".txt";
		try {
			File mapFile = new File(path);
			Scanner sc = new Scanner(mapFile);
			String[] info = sc.nextLine().split(" ");
			_level = Integer.parseInt(info[0]);
			_height = Integer.parseInt(info[1]);
			_width = Integer.parseInt(info[2]);

			_map = new char[_height][_width];
			for(int i = 0; i < _height; i++) {
				String mapRow = sc.nextLine();
				for(int j = 0; j < _width; j++) {
					_map[i][j] = mapRow.charAt(j);
				}
			}
		} catch (NullPointerException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		for(int i = 0; i < _height; i++) {
			for (int j = 0; j < _width; j++) {
				switch (_map[i][j]) {
					case '#':
						// Thêm Wall
						_board.addEntity(j + i * _width, new Wall(j, i, Sprite.wall));
						break;
					case '*':
						// Thêm Brick
						_board.addEntity(j + i * _width,
								new LayeredEntity(j, i,
										new Grass(j, i, Sprite.grass),
										new Brick(j, i, Sprite.brick)
								)
						);
						break;
					case 'x':
						// Thêm Portal kèm Brick che phủ ở trên
						_board.addEntity(j + i * _width,
								new LayeredEntity(j, i,
										new Grass(j ,i, Sprite.grass),
										new Portal(j, i, Sprite.portal, _board),
										new Brick(j, i, Sprite.brick)
								)
						);
						break;
					case 'z':
						// Thêm Portal nhưng không có Brick che phủ
						_board.addEntity(j + i * _width,
								new LayeredEntity(j, i,
										new Grass(j, i, Sprite.grass),
										new Portal(j, i, Sprite.portal, _board)
								)
						);
						break;
					case 'p':
						// Thêm Bomber
						int xBomber = j, yBomber = i;
						_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
						_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
						break;
					case 'l':
						// Thêm Blue Bomber (Link)
						int xBlueBomber = j, yBlueBomber = i;
						_board.addCharacter(new BlueBomber(Coordinates.tileToPixel(xBlueBomber), Coordinates.tileToPixel(yBlueBomber) + Game.TILES_SIZE, _board));
						_board.addEntity(xBlueBomber + yBlueBomber * _width, new Grass(xBlueBomber, yBlueBomber, Sprite.grass));
						break;
					case '1':
						// Thêm Enemy Balloon
						_board.addCharacter( new Balloon(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(j + i * _width, new Grass(j, i, Sprite.grass));
						break;
					case '2':
						// Thêm Enemy Oneal
						_board.addCharacter( new Oneal(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(j + i * _width, new Grass(j, i, Sprite.grass));
						break;
					case '3':
						// Thêm Enemy Ghosts
						_board.addCharacter( new Ghost(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(j + i * _width, new Grass(j, i, Sprite.grass));
						break;
					case '4':
						// Thêm Pontan
						_board.addCharacter( new Pontan(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(j + i * _width, new Grass(j, i, Sprite.grass));
						break;
					case 'b':
						// Thêm Bomb Item kèm Brick che phủ lên
						_board.addEntity(j + i * _width,
								new LayeredEntity(j, i,
										new Grass(j, i, Sprite.grass),
										new BombItem(j, i, Sprite.powerup_bombs),
										new Brick(j, i, Sprite.brick)
								)
						);
						break;
					case 'f':
						// Thêm Flame Item kèm Brick che phủ lên
						_board.addEntity(j + i * _width,
								new LayeredEntity(j, i,
										new Grass(j, i, Sprite.grass),
										new FlameItem(j, i, Sprite.powerup_flames),
										new Brick(j, i, Sprite.brick)
								)
						);
						break;
					case 's':
						// Thêm Speed Item kèm Brick che phủ lên
						_board.addEntity(j + i * _width,
								new LayeredEntity(j, i,
										new Grass(j, i, Sprite.grass),
										new SpeedItem(j, i, Sprite.powerup_speed),
										new Brick(j, i, Sprite.brick)
								)
						);
						break;
					default:
						// Thêm Grass
						_board.addEntity(j + i * _width, new Grass(j, i, Sprite.grass));
						break;
				}
			}
		}
	}

}
