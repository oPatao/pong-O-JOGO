package pong;

import java.awt.*;

public class Player {

    public boolean up = false, down = false;

    public int x, y, height = 30, width = 5;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick(){

        if (up && y>0){
            y--;
        } else if (down && y < (Game.HEIGHT - height)) y++;
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);
    }
}
