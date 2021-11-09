package core;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;

public class Clear {
    private int x, y;

    public Color background = new Color(50, 58, 74);
    public Color orange = new Color(255, 165, 0);

    public boolean isHovered = false;
    public Board board;

    public Clear(Board board) {
        this.x = 800;
        this.y = 120;
        this.board = board;
    }

    public void render(Graphics2D g) {        
        g.setFont(new Font("SansSerif", Font.PLAIN, 24));

        int offX = g.getFontMetrics().stringWidth("clear grid");
        int offY = g.getFontMetrics().getHeight() / 2;

        g.setColor(isHovered ? orange : background);
        g.fillRect(x, y, 120, 36);

        g.setColor(isHovered ? background : orange);
        g.drawString("clear grid", x + 60 - offX / 2, y + 18 + offY / 2);
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= x && e.getX() <= x + 120) {
            if (e.getY() >= y && e.getY() <= y + 36) {
                isHovered = true;
            } else isHovered = false;
        } else isHovered = false;
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getX() >= x && e.getX() <= x + 120) {
            if (e.getY() >= y && e.getY() <= y + 36) {
                board.clear();
            }
        }
    }

    public boolean isHovered() { return isHovered; }
    public void setHovered(boolean hovered) { this.isHovered = hovered; }
}
