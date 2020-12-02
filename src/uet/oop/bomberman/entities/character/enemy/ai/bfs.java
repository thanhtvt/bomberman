package uet.oop.bomberman.entities.character.enemy.ai;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.Ghost;
import static uet.oop.bomberman.level.FileLevelLoader._map;

public class bfs extends AI{

    Bomber _bomber;
    Enemy _e;
    Board _board;
    int[][] xd = new int[13][31];
    int[] a = new int[13*31];
    Node[] mang = new Node[13*31];

    public bfs(Bomber bomber, Enemy e) {
        super();
        _bomber = bomber;
        _e = e;
    }



    @Override
    public int calculateDirection() {
        for(int i=1; i<= 12; i++) {
            for(int j=1; j<=30; j++){
                xd[i][j] = 0;
            }
        }

        // TODO: cài đặt thuật toán tìm đường đi

        int sx = _e.getYTile();
        int sy = _e.getXTile();
        int tx = sx, ty = sy;
            tx = _bomber.getYTile();
            ty = _bomber.getXTile();
        xd[sx][sy] = 1;
        xd[tx][ty] = 3;

        int x=0 , y=0;
        Node node = new Node(sx, sy, 0, 0);
        int i=0, n=0;
        ++n;
        mang[n] = node;
        while (i < n) {
            ++i;
            node = mang[i];
            x = node.getX();
            y = node.getY();
            if( xd[x][y] == 3) break;
            for(int j=0; j<=3; j++){
                if (j == 3) {
                    --y;

                    if(xd[x][y] != 1 && _map[x][y] != '#') {
                        xd[x][y] = 1;
                        mang[++n] = new Node(x,y,3, i);

                        if( x== tx && y== ty) break;
                    }
                    ++y;
                }
                if(j == 0){
                    --x;
                    if(xd[x][y] != 1 && _map[x][y] != '#') {

                        xd[x][y] = 1;
                        mang[++n] = new Node(x,y,0, i);

                        if( x== tx && y== ty) break;
                    }
                    ++x;
                }
                if(j == 2) {
                    ++x;
                    if(xd[x][y] != 1 && _map[x][y] != '#') {
                        xd[x][y] = 1;

                        mang[++n] = new Node(x,y,2, i);

                        if( x== tx && y== ty) break;
                    }
                    --x;
                }
                if(j == 1) {
                    ++y;
                    if(xd[x][y] != 1 && _map[x][y] != '#') {

                        xd[x][y] = 1;
                        mang[++n] = new Node(x,y,1, i);

                        if( x== tx && y== ty) break;
                    }
                    --y;
                }

            }

            if( x== tx && y== ty) {
                break;
            }
        }

        Stack<Integer> stack = new Stack<Integer>();
        while (n > 1){
            int xxx = mang[n].getPath();
            stack.push(xxx);
            n = mang[n].getPrev();
        }


        int xx = stack.pop();
       return xx;

    }
}
