package Block.Entity;

import Block.Block;
import Block.BlockType;
import Block.BlockPosition;
import Game.Panel.Field;
import Game.Map_Storage;

import java.util.ArrayList;

public class Player extends Entity
{
    public Player()
    {
        super(BlockType.Player);
    }

    @Override
    public boolean Move(Map_Storage mapStorage, int index, int direction, Field field)
    {
        BlockPosition position = mapStorage.getEntityPosByIndex(index); //첫번째 위치가 플레이어 위치
        BlockPosition before = new BlockPosition(position); //이전 좌표 저장, 원본 객체 복사 생성

        //switch에 break 미사용 : 원하는 결과 나오지 않음
        //break 시행 시 비교 문으로 작동하는 것 같음
        switch (direction)
        {
            case 0: //North
                position.moveTo(0,-1);
                break;

            case 1: //East
                position.moveTo(1,0);
                break;

            case 2: //South
                position.moveTo(0,1);
                break;

            case 3: //West
                position.moveTo(-1,0);
                break;
        }

        Block overlap = mapStorage.getBlock(position); //이동시 만나는 블록
        ArrayList<Entity> samePosEntity = mapStorage.getEntity(position); //이동시 만나는 entity

        if (overlap != null) //만약 존재한다면
        {
            if (overlap.getState() >= 2 ) //막히었다면
            {
                position.setPos(before.getPosX(), before.getPosY());
                return false; //움직이기 실패
            }
        } else
        {
            for (int i = 1; i < samePosEntity.size(); i++) //처음 객체 제외 모든 객체 확인
            {
                if (samePosEntity.get(i).getState() >= 2) //만약 갈 수 없으면
                {
                        position.setPos(before.getPosX(), before.getPosX());
                        return false; //움직이기 실패
                }
            }
        }

        if (overlap != null)
        {
            overlap.collisionAct(type, field); //Block 충돌 처리 시행

            for (int i = 1; i < samePosEntity.size(); i++) //Entity 충돌 처리 시행
            {
                samePosEntity.get(i).collisionAct(type, field); //Entity 충돌 처리
            }
        }

        return true; //반환 성공

    }

    //블록 복사하기
    @Override
    public Entity Block_Copy()
    {
        return new Player(); //새로운 player를 반환
    }

    @Override
    public void turnEnd(boolean observed) {} //존재 할수도

    @Override
    public void collisionAct(BlockType type, Field field) {} //존재 안함

}


