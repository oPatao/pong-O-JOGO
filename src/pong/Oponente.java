package pong;

import java.awt.*;
import java.util.Random;

public class Oponente {
    public double x,y;
    public int height, width;
    public double speed = new Random().nextDouble(0.1);

    public Oponente(double x, double y){
        this.x = x;
        this.y = y;
        this.height = 30;
        this.width = 5;
    }
    public void tick(){

        y +=  ((Game.bola.y - y - 6) * speed);
        speed = new Random().nextDouble(0.1);

    }
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect((int)x,(int)y,width,height);
    }
}
