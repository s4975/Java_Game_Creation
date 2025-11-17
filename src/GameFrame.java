import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GameFrame() //생성자
    {
        setSize(1500, 1000);
        add(new Field());
        setVisible(true);
        setTitle("슈뢰딩거의 고양이를 아십니까?");
    }
}
