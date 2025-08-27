package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {


    public static final int WIDTH = 240, HEIGHT = 120;
    public static int SCALE = 3;
    public static Player player;
    public static Oponente oponente;
    public static Bola bola;

    public BufferedImage fundo = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


    public Game(){
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        player = new Player(WIDTH-20, HEIGHT/2);
        oponente = new Oponente(15, HEIGHT/2);
        bola = new Bola(WIDTH/2, HEIGHT/2);

    }

    public static void main(String[] args) {

        Game game = new Game();

        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

        new Thread(game).start();

    }
    public void tick(){
        player.tick();
        oponente.tick();
        bola.tick();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = fundo.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(g);
        oponente.render(g);
        bola.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(fundo, 0, 0,WIDTH*SCALE,HEIGHT*SCALE, null);

        g = bs.getDrawGraphics();


        bs.show();
    }

    @Override
    public void run() {
        while(true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = false;
        }

    }
}