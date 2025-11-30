import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class Field extends JPanel {


    public Field()
    {
        repaint();

        this.requestFocus();
        setFocusable(true); //초점 맞추기 하기
    }

    public void paintComponent(Graphics g) //다시 그리는 함수
    {
        super.paintComponent(g);
        g.setColor(Color.RED);

        g.drawImage( G_Graphics.getImgField(), 0, 0, null);
    }

}
