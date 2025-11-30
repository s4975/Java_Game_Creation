package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Field extends JPanel implements KeyListener {

    private final GameGraphics G_Graphics = new GameGraphics(); //그래픽 생성

    private final Map start_map; //초기 맵

    private Map current_map; //현재 맵

    public Field(Map start_map)
    {
        this.start_map = start_map;
        this.current_map = start_map; //현재 맵 초기 지정

        repaint();

        this.requestFocus();
        setFocusable(true); //초점 맞추기 하기
        this.addKeyListener(this); //keylistener 추가
    }

    public void paintComponent(Graphics g) //다시 그리는 함수
    {
        //System.out.println(current_map.entityPos.get(0).getPosX() + " " + current_map.entityPos.get(0).getPosY());

        // float scale = 1.0f;//scale 조정하기 구현 예정

        BufferedImage image = null; //출력 객체

        super.paintComponent(g);
        g.setColor(Color.RED);

        g.drawImage( G_Graphics.getImg(null, 0), 0, 0, null);

        //iterator(반복 가능한 객체) 람다 식으로 작성
        current_map.blockMap.forEach(((position, block) ->

                g.drawImage(G_Graphics.getImg(block.getType(), block.getState()),
                        (position.getPosX() * 85) + 55, (position.getPosY() * 90) + 1, null)
        ));

        //람다 식으로 앤티티 출력
        for (int i = 0; i < current_map.entities.size(); i++)
        {
            g.drawImage(G_Graphics.getImg(current_map.entities.get(i).getType(), current_map.entities.get(i).getState()),
                    (current_map.entityPos.get(i).getPosX() * 88) + 56, (current_map.entityPos.get(i).getPosY() * 95) + 1, null);
        }


    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        boolean temp = false;

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
        {
           // System.out.println("Up");
            temp = current_map.entities.getFirst().Move(current_map, current_map.entityPos.getFirst(), 0 );

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            //System.out.println("Down");
            temp = current_map.entities.getFirst().Move(current_map, current_map.entityPos.getFirst(), 1 );
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            //System.out.println("Left");
            temp = current_map.entities.getFirst().Move(current_map, current_map.entityPos.getFirst(), 2 );

        }
        if  (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            //System.out.println("Right");
            temp = current_map.entities.getFirst().Move(current_map, current_map.entityPos.getFirst(), 3 );
        }

        repaint();

        //if (temp == true)
            //System.out.println(current_map.entityPos.getFirst().getPosX() + " " + current_map.entityPos.getFirst().getPosY());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
