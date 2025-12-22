package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Field extends JPanel implements KeyListener {

    private final GameGraphics G_Graphics = new GameGraphics(); //그래픽 생성

    private final StageMap start_Map; //초기 맵
    private StageMap current_Map; //현재 맵

    private LayeredField layeredField; //겹쳐진 필드 받기
    private final Sight sight;

    public Field(StageMap stage_Map, Sight player_Sight, LayeredField layeredField)
    {

        this.layeredField = layeredField; //부모 패널 알기
        this.sight = player_Sight; //플레이어 시아 일기

        this.start_Map = stage_Map; //start_Map은 그대로 사용
        this.current_Map = new StageMap(stage_Map); //current_Map은 복사해서 사용

        sight.setMap(current_Map); //스테이지 맵 설정

        this.setBounds(0, 0, 1200, 800);
        this.setBackground(new Color(73, 73, 73));
        this.addKeyListener(this); //listener 추가

    }

    //필드 상태를 초기 상태로 reset하는 함수
    public void reset()
    {
        current_Map = new StageMap(start_Map); //초기로 되돌리기
        sight.setMap(current_Map); //현재 맵으로 스테이지 재 설정
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        boolean Turn_passed = false; //턴이 지나 갔는지 확인하는 변수

        //"Reset"
        if (e.getKeyCode() == KeyEvent.VK_R)
        {
            reset();
        }
        // "Up"
        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 0 , this);
        }
        //"Down"
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 1 , this);
        }
        //"Left"
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 2 , this);
        }
        //"Right"
        else if  (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 3 , this);
        }

        if (Turn_passed)
        {
            sight.setPlayer_Sight(); //플레이어 시아 재 설정
            current_Map.turnEnd(sight.getPlayer_Sight()); //턴 종료 수행

            repaint(); //다시 그리기
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void paint(Graphics g) //다시 그리기
    {
        //해당 화면 초점 맞추기
        this.requestFocus();
        this.setFocusable(true);

        super.paintComponent(g);

        g.drawImage( G_Graphics.getImg(null, 0) , 0, 0, null); //필드 화면 그리기

        current_Map.drawing(G_Graphics, g); //map class 안에서 그림 그리기 수행
    }
}
