package uet.oop.bomberman.entities.character.enemy.ai;

public class Node {
    int x;
    int y;
    int path;
    int prev;
    public Node (int x,int y,int path, int prev) {
        this.x = x;
        this.y = y;
        this.path = path;
        this.prev = prev;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getPath() {
        return this.path;
    }

    public int getPrev() {
        return this.prev;
    }
}
