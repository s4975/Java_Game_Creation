package Block.Entity;

import Block.Block;
import Block.BlockType;
import Block.BlockPosition;
import Game.Field;
import Game.Map;

public class Player extends Entity
{
    public Player()
    {
        super(BlockType.Player);
    }


    @Override
    public boolean Move(Map map, BlockPosition position, int direction)
    {
        BlockPosition before = new BlockPosition(position.getPosX(),  position.getPosY()); //이전 좌표 저장

        //switch에 break 미사용 : 원하는 결과 나오지 않음
        switch (direction)
        {
            case 0: //North
                position.goUp();
                break;

            case 1: //East
                position.goRight();
                break;

            case 2: //South
                position.goDown();
                break;

            case 3: //West
                position.goLeft();
                break;
        }

       Block overlap = map.blockMap.getOrDefault(position, null); //이동시 만나는 블록

        if (overlap != null) //만약 존재한다면
        {
            if (overlap.getState() >= 2 ) //막히었다면
            {
                position.setPos(before.getPosX(), before.getPosY());
                return false; //움직이기 실패
            }
        } else
        {
            for (int i = 1; i < map.entities.size(); i++) //모든 객체 확인
            {
                if (map.entityPos.get(i).equals(position)) //해당 위치가 일치아면
                {
                    if (map.entities.get(i).getState() >= 2) //만약 갈 수 없으면
                    {
                        position.setPos(before.getPosX(), before.getPosX());
                        return false; //움직이기 실패
                    }
                }
            }
        }

        return true; //반환 성공

    }

    @Override
    public void Vision(Field field) //구현 준비중
    {

    }

    @Override
    public void turnEnd(boolean observed) //존재 할수도
    {


    }

    @Override
    public void collisionAct(BlockType type) {} //존재 안함

}


