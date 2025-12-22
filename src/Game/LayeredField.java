package Game;

import Main.MainFrame;

import javax.swing.*;
import java.awt.*;

//필드를 서로 겹치기 위해 생성한 또 다른 panel
public class LayeredField extends JLayeredPane
{
    MainFrame frame; //여기서 사건 받아서 해결할 예정
    //생성자
    public LayeredField(StageMap stage_Map, MainFrame frame)
    {
        super(); //부모 생성자 호츨
        this.frame = frame; //부모 frame 받기

        this.setBounds(0,0,1200,800);

        this.setOpaque(false);

        Sight temp = new Sight();

        Field temp2 =  new Field(stage_Map, temp,  this);

        add(temp2, (Integer)0);
        add(temp, (Integer)2);

        revalidate();
        repaint();
    }

    public void repainting()
    {
        repaint();
    }
}
