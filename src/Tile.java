import java.awt.Graphics2D;
import java.awt.Color;

public class Tile {
    private int x, y;
    private Board board;

    public static int TILE_SIZE = 25;

    private boolean isHovered = false;
    private boolean isClicked = false;

    private Color color = new Color(240, 238, 209);
    
    public Tile(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public void render(Graphics2D g) {
        int x = this.x * TILE_SIZE + App.WIDTH / 3 - board.getWidth() * TILE_SIZE / 2;
        int y = this.y * TILE_SIZE + App.HEIGHT / 2 - board.getHeight() * TILE_SIZE / 2;

        if (!isClicked) g.setColor((isHovered) ? color.darker() : color);
        else g.setColor(Color.BLACK);

        g.fillRect(x , y, TILE_SIZE, TILE_SIZE);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public boolean isHovered() { return isHovered; }
    public boolean isClicked() { return isClicked; }

    public void setHovered(boolean hovered) { this.isHovered = hovered; }
    public void setClicked(boolean clicked) { this.isClicked = clicked; }
}
