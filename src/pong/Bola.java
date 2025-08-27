package pong;

import java.awt.*;
import java.util.Random;

public class Bola {
    public double x,y,dx,dy, speed = 1.2;
    public int height, width;;

    public Bola(double x, double y){
        this.x = x;
        this.y = y;
        this.height = 3;
        this.width = 3;
        int angulo = new Random().nextInt(120 - 45) + 80 + 1;
        dx = Math.cos(Math.toRadians(angulo));
        dy = Math.cos(Math.toRadians(angulo));
    }
    public void tick(){
        if(y+(dy*speed)+ height >= Game.HEIGHT){
            dy *=-1;
            speed += 0.1;
        }else if(y+(dy*speed) < 0){
            dy *=-1;
            speed += 0.1;
        }

        if(x >= Game.WIDTH){

            new Game();
            return;
        }else if(x <= 0){
            new Game();
            return;

        }

        Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)), width, height);

        Rectangle boundPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height) ;
        Rectangle boundOponente = new Rectangle((int)Game.oponente.x,(int)Game.oponente.y, Game.oponente.width, Game.oponente.height);

        if(bounds.intersects(boundPlayer)){
            speed += 0.1;
            int angulo = new Random().nextInt(120 - 45) + 100 + 1;
            dx = Math.cos(Math.toRadians(angulo));
            dy = Math.cos(Math.toRadians(angulo));
            if(dx > 0){
                dx *= -1;
            }
        }else if(bounds.intersects(boundOponente)){
            speed += 0.1;
            int angulo = new Random().nextInt(120 - 45) + 100 + 1;
            dx = Math.cos(Math.toRadians(angulo));
            dy = Math.cos(Math.toRadians(angulo));
            if(dx < 0){
                dx *= -1;
            }
        }

        x+=dx*speed;
        y+=dy*speed;

    }
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect((int)x,(int)y,width,height);
    }
}
