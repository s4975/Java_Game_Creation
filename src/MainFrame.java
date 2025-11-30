import Game.Field;
import Game.Map;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() //생성자
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        Map current_map = new Map();
        add(new Field(current_map));
        setVisible(true);
        setTitle("슈뢰딩거의 고양이를 아십니까?");
    }
}
