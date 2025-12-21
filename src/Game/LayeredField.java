package Game;

import Main.MainFrame;

import javax.swing.*;
import java.awt.*;

//필드를 서로 겹치기 위해 생성한 또 다른 panel
public class LayeredField
{
    private final JLayeredPane layeredPane; //레이어페널

    MainFrame frame; //여기서 사건 받아서 해결할 예정
    //생성자
    public LayeredField(StageMap stage_Map, MainFrame frame)
    {
        this.frame = frame; //부모 frame 받기
        layeredPane = this.frame.getLayeredPane();

        Sight temp = new Sight(stage_Map);

        layeredPane.add(new Field(stage_Map, temp,  this), 2);
        //layeredPane.add(temp, 0);
    }
}
