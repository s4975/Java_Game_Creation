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
    }

    public void paintComponent(Graphics g) //다시 그리는 함수
    {
        // float scale = 1.0f;//scale 조정하기 구현 예정

        BufferedImage image = null; //출력 객체

        super.paintComponent(g);
        g.setColor(Color.RED);

        g.drawImage( G_Graphics.getImg(null, 0), 0, 0, null);

        //iterator(반복 가능한 객체) 람다 식으로 작성
        current_map.blockMap.forEach(((position, block) ->

                g.drawImage(G_Graphics.getImg(block.getType(), block.getState()),
                        (position.getPosX() * 84) + 55, (position.getPosY() * 89) + 1, null)
        ));

        //람다 식으로 배경 출력
        current_map.entityMaps.forEach(entityMap ->

                g.drawImage(G_Graphics.getImg(entityMap.entity.getType(), entityMap.entity.getState()),
                        (entityMap.pos.getPosX() * 84) + 55, (entityMap.pos.getPosY() * 89) + 1, null)
        );
    }

    @Override
    public void keyTyped(KeyEvent e)
    {


    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
