package core;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Board {
    private Tile[][] map;
    private int width, height;

    public Board(int width, int height) {
        map = new Tile[height][width];

        this.width = width;
        this.height = height;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(j, i, this);
            }
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].render(g);
            }
        }
    }

    public Digit toDigit() {
        double[] data = new double[28 * 28];

        int index = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                data[index] = map[i][j].isClicked() ? 1 : 0;
                index++;
            }
        }

        return new Digit(data, -1);
    }

    public void clear() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(j, i, this);
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX() / Tile.TILE_SIZE - 2;
        int y = e.getY() / Tile.TILE_SIZE - 2;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].setHovered(false);
            }
        }

        if (x < 0 || x > 27) return;
        if (y < 0 || y > 27) return;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i != y || j != x) map[i][j].setHovered(false);
                else map[i][j].setHovered(true);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / Tile.TILE_SIZE - 2;
        int y = e.getY() / Tile.TILE_SIZE - 2;

        if (x < 0 || x > 27) return;
        if (y < 0 || y > 27) return;

        map[y][x].setClicked(true);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}