package Game.Panel;

import Block.Block;
import Block.BlockPosition;
import Game.Map_Storage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sight extends JPanel
{
    boolean[][] Player_Sight = new boolean[7][7]; //7*7 배열 생성, 가운데 [3][3]은 Player 위치, [x],[y]를 지칭
    Map_Storage map;

    public Sight()
    {
        this.setBounds(0, 0, 1124, 643);
        this.setBackground(new Color(0,0,0,0)); //무색 코드 지정
        this.setOpaque(false); //투명한 레이어

        for (int x = 0; x < 7; x++)
        {
            for (int y = 0; y < 7; y++)
            {
                //만약 원 3칸 안에 들어온다면 true, 아니면 false 부여하기
                Player_Sight[x][y] = (x - 3) * (x - 3) + (y - 3) * (y - 3) <= 10; //배열 기본값 설정
            }
        }
    }

    public void setMap(Map_Storage map)
    {
        this.map = map; //stage map 재설정
        setPlayer_Sight();  //플레이어 시아 재 설정
    }

    public void setPlayer_Sight()
    {
        int num = map.getEntityCount(); //블록 정보 받기
        ArrayList<BlockPosition> invisible = new ArrayList<>(); //invisible 상태의 블록 저장

        for (int i = 0; i < num; i++)
        {
            if (map.getEntityByIndex(i).getState() % 2 == 1 ) //보이지 않는 상태면
                invisible.add(map.getEntityPosByIndex(i)); //해당 좌표 받아서 저장
        }

        sight_check(invisible, 0, 3, 2, 3, true); //북
        sight_check(invisible, 1, 4, 3, 3, true); //동
        sight_check(invisible, 2, 3, 4, 3, true); //남
        sight_check(invisible, 3, 2, 3, 3, true); //서

        sight_check(invisible, 4, 4, 2, 2, true); //북동
        sight_check(invisible, 5, 4, 4, 2, true); //남동
        sight_check(invisible, 6, 2, 4, 2, true); //남서
        sight_check(invisible, 7, 2, 2, 2, true); //북서

        sight_check(invisible, 0, 4, 1, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX(),map.getPlayerPos().getPosY() + - 1))); //북동북
        sight_check(invisible, 1, 5, 2, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX() + 1,map.getPlayerPos().getPosY() ))); //북동동
        sight_check(invisible, 1, 5, 4, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX() + 1,map.getPlayerPos().getPosY() ))); //남동동
        sight_check(invisible, 2, 4, 5, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX() ,map.getPlayerPos().getPosY() + 1))); //남동남
        sight_check(invisible, 2, 2, 5, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX(),map.getPlayerPos().getPosY() + 1 ))); //남서남
        sight_check(invisible, 3, 1, 4, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX() - 1,map.getPlayerPos().getPosY() ))); //남서서
        sight_check(invisible, 3, 1, 2, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX() - 1,map.getPlayerPos().getPosY() ))); //북서서
        sight_check(invisible, 0, 2, 1, 2,
                sight_check2(invisible, new BlockPosition(map.getPlayerPos().getPosX(),map.getPlayerPos().getPosY() - 1))); //북서북
    }

    //(invisible 상태의 블록, 진행 방향, Player_Sight x, player_Sight y, 반복 횟수 (최대 3번), 이전 좌표가 막힌 상태인지)
    private void sight_check(ArrayList<BlockPosition> invisible, int direction, int x, int y, int i, boolean before)
    {
        BlockPosition playerPos = map.getPlayerPos(); //플레이어 위치 알기

        for (int j = i; j > 0; j --) //재귀 호출 대신 for문으로 수행
        {
            Player_Sight[x][y] = before; //x,y를 before로 설정

            //before이 false면 진행 안함
            if (before) //만약 before이 true라면
            {
                //before이 false면 진행 안함
                int t_x = playerPos.getPosX() + (x - 3);
                int t_y = playerPos.getPosY() + (y - 3);

                if (t_x >= 0 && t_x <= 11 && t_y >= 0 && t_y <= 6) //범위 안인 경우
                {
                    //본인 블록 시아 확인
                    before = sight_check2(invisible, new BlockPosition(t_x, t_y));
                }
            }

            switch (direction) //탐색 방향
            {
                case 0:
                    y -= 1;
                    break; //북 방향
                case 1:
                    x += 1;
                    break;//동 방향
                case 2:
                    y += 1;
                    break; //남 방향
                case 3:
                    x -= 1;
                    break; //서 방향
                case 4:
                    y -= 1;
                    x += 1;
                    break; //북동 방향
                case 5:
                    y += 1;
                    x += 1;
                    break; //남동 방향
                case 6:
                    y += 1;
                    x -= 1;
                    break; //남서 방향
                case 7:
                    y -= 1;
                    x -= 1;
                    break; //북서 방향
            }

        }
    }




    //해당 위치 블록이 보이는 블록인지 감지
    private boolean sight_check2(ArrayList<BlockPosition> invisible, BlockPosition pos)
    {
        //블록이 invisible인지 확인
        Block block = map.getBlock(pos);
        if (block != null)
        {
            if (block.getState() % 2 == 1) //만약 보이지 않는 경우라면
            {
                return false; //false 반환
            }
        }

        return !(invisible.contains(pos)); //invisible에 pos가 있는지 검사
    }

    public boolean[][] getPlayer_Sight()
    {
        return Player_Sight; //player 시아를 반환
    }

    @Override
    public void paint(Graphics g)
    {
        //player sight index와 같아지게끔 맞추기
        int sight_X = 3 - map.getPlayerPos().getPosX(); //3 - 플레이어 x 좌표
        int sight_Y = 3 - map.getPlayerPos().getPosY(); //3 - 플레이어 y 좌표

        super.paint(g); //부모 호출

        g.setColor(new Color(0, 0, 0, 200));

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
