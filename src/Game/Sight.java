package Game;

import Block.BlockPosition;

import javax.swing.*;
import java.awt.*;

public class Sight extends JPanel
{
    boolean[][] Player_Sight = new boolean[7][7]; //7*7 배열 생성, 가운데 [3][3]은 Player 위치, [x],[y]를 지칭
    StageMap stage_Map;

    public Sight()
    {
        this.setBounds(0, 0, 1124, 643);
        this.setBackground(new Color(0,0,0,0)); //무색 코드 지정
        this.setOpaque(false); //투명한 레이어

        for (int x = 0; x < 7; x++)
        {
            for (int y = 0; y < 7; y++)
            {
                Player_Sight[x][y] = true; //배열 기본값 설정
            }
        }
    }

    public void setStage_Map(StageMap stage_Map)
    {
        this.stage_Map = stage_Map; //stage map 재설정
        setPlayer_Sight();  //플레이어 시아 재 설정
    }

    public void setPlayer_Sight()
    {

    }


    public boolean[][] getPlayer_Sight()
    {
        return Player_Sight; //player 시아를 반환
    }



    @Override
    public void paint(Graphics g)
    {

        System.out.println("시아 좌표 : " + stage_Map.getPlayerPos().getPosX() + ',' + stage_Map.getPlayerPos().getPosY());
        //player sight index와 같아지게끔 맞추기
        int sight_X = 3 - stage_Map.getPlayerPos().getPosX(); //3 - 플레이어 x 좌표
        int sight_Y = 3 - stage_Map.getPlayerPos().getPosY(); //3 - 플레이어 y 좌표

        super.paint(g); //부모 호출

        g.setColor(new Color(0, 0, 0, 200));
       // g.fillRect(0, 0, 1142, 643);

        for (int x = 0; x < 12; x++)
        {
            int X_pos = sight_X + x;
            for (int y = 0; y < 7; y++)
            {
                int Y_pos = sight_Y + y;

                //만약 해당 좌표 바깥이면
                if (X_pos < 0 || X_pos >= 7 || Y_pos < 0 || Y_pos >= 7)
                {
                    g.fillRect(x * 85 + 60, y * 87 + 20, 85, 87 ); //사각형 그리기
                }
                //해당 좌표 안 쪽일때
                else if (! Player_Sight[X_pos][Y_pos]) //플레이어가 보이지 않는 부분이라면
                {
                    g.fillRect(x * 85 + 60, y * 87 + 20, 85, 87 ); //사각형 그리기
                }
            }
        }
    }
}
