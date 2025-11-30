import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Field extends JPanel {

    private final GameGraphics G_Graphics = new GameGraphics(); //그래픽 생성

    private final Map start_map;

    public Field(Map start_map)
    {
        this.start_map = start_map;

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

        g.drawImage( G_Graphics.getImgField(), 0, 0, null);

        start_map.blockmap.forEach(((position, block) ->
        {
            switch (block.getType())
            {
                case Wall ->
                {
                   if(block.getState() >=2 ) //block되지 않은 상태
                   {
                       g.drawImage(G_Graphics.getWall(block.getState()),
                               (position.posX * 82) + 55 , (position.posY * 90 )+ 1, null);
                   }
                    break;
                }

                case Player ->
                {
                    break;
                }




            }

            g.drawImage(G_Graphics.getWall(block.getState()),
                    (position.posX * 82) + 55 ,
                    (position.posY * 90 )+ 1,
                    null);




        }));
    }

}
