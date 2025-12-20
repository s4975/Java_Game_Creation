package Game;

import Main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Field extends JPanel implements KeyListener {

    private final GameGraphics G_Graphics = new GameGraphics(); //그래픽 생성
    private final MainFrame MAIN_Frame; //MainFrame 알기

    private final StageMap start_Map; //초기 맵
    private StageMap current_Map; //현재 맵

    public Field(StageMap stage_Map, MainFrame frame)
    {
        this.MAIN_Frame = frame;

        this.start_Map = stage_Map; //start_Map은 그대로 사용
        this.current_Map = new StageMap(stage_Map); //current_Map은 복사해서 사용

        this.setBackground(new Color(73, 73, 73));
        this.addKeyListener(this); //listener 추가
    }

    //필드 상태를 초기 상태로 reset하는 함수
    public void reset()
    {
        current_Map = new StageMap(start_Map); //초기로 되돌리기
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
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 0 );
        }
        //"Down"
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 1 );
        }
        //"Left"
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 2 );
        }
        //"Right"
        else if  (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            Turn_passed = current_Map.getPlayer().Move(current_Map, 0, 3 );
        }

        if (Turn_passed)
            current_Map.turnEnd(); //턴 종료 수행

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void paintComponent(Graphics g) //다시 그리는 함수
    {

        super.paintComponent(g);

        g.drawImage( G_Graphics.getImg(null, 0) , 0, 0, null); //필드 화면 그리기

        current_Map.drawing(G_Graphics, g); //map class 안에서 그림 그리기 수행

    }
}
