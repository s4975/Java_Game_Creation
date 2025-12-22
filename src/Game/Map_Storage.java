package Game;
//데이터 직렬화 예정
//위험하긴 해서 나중에 JSon 으로 바꿀 예정

import Block.*;
import Block.Entity.Entity;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Map_Storage implements Serializable //직렬화 가능
{
    //Block.Block: 모든 블록의 부모 역활
    //Block.Entity: 모든 Entity의 부모 역활
    protected HashMap<BlockPosition, Block> blockMap = new HashMap<BlockPosition, Block>();

    protected ArrayList<BlockPosition> entityPos = new ArrayList<BlockPosition>(); //entity 위치
    protected ArrayList<Entity> entities = new ArrayList<>(); //entity 객체


    public Map_Storage() {} //기본생성자

    public Map_Storage(int stage)
    {
        New_Map newStage = new New_Map(stage);

        blockMap = newStage.GetBlockMap();
        entityPos = newStage.GetEntityPos();
        entities = newStage.entities();
    }

    //객체 복사 생성자
    public Map_Storage(final Map_Storage copied)
    {
        //for each문으로 전체를 복사
        copied.blockMap.forEach((blockPos, block) ->
            this.blockMap.put(new BlockPosition(blockPos), block.Block_Copy())); //각각 요소들을 복사한 다음 대입

        copied.entityPos.forEach(entityPos ->
                this.entityPos.add(new BlockPosition(entityPos))); //새로운 객체를 저장

        copied.entities.forEach(entity
                -> this.entities.add(entity.Block_Copy())); //새로운 객체 생성, 복사해서 저장
    }

    public Block getBlock(BlockPosition position) //특정 위치의 블록을 얻는 코드
    {
        return blockMap.getOrDefault(position, null); //존재 하면 해당 블록을, 아니면 null을 반환
    }

    //특정 지점 위에 있는 모든 앤티티를 반환
    public ArrayList<Entity> getEntity(BlockPosition position)
    {
        ArrayList<Entity> temp = new ArrayList<>(); //임시 저장 객체

        //모든 entity에 대해서 확인
        for (int i = 0; i<entities.size();i++)
        {
            //해당 위치면 entity
            if (position.equals(entityPos.get(i))) //서로 같다면
                temp.add(entities.get(i)); //entity를 집여넣기
        }

        return temp; //temp 반환하기
    }

    //index에 저장된 객체를 반환
    public Entity getEntityByIndex(int index)
    {
        return entities.get(index);
    }

    //index에 저장된 객체의 위치를 반환
    public BlockPosition getEntityPosByIndex(int index)
    {
        return entityPos.get(index);
    }

    public int getEntityCount()
    {
        return entities.size();
    }


    public Entity getPlayer() //플레이어 객체 (entity중 처음에 있는 객체) 반환
    {
        return entities.getFirst(); //처음 요소 반환
    }

    public BlockPosition getPlayerPos()
    {
        return entityPos.getFirst(); //처음 요소 반환
    }

    public void drawing(GameGraphics graphics, Graphics g)
    {
        //iterator(반복 가능한 객체) 람다 식으로 작성
        blockMap.forEach(((position, block) ->

                g.drawImage(graphics.getImg(block.getType(), block.getState()),
                        (position.getPosX() * 85) + 60, (position.getPosY() * 87) + 20, null)
        ));

        for (int i = 0; i < entities.size(); i++)
        {
            g.drawImage(graphics.getImg(entities.get(i).getType(), entities.get(i).getState()),
                    (entityPos.get(i).getPosX() * 85) + 60, (entityPos.get(i).getPosY() * 87) + 20, null);
        }
    }

    public void turnEnd(boolean[][] Player_Sight)
    {
        //7*7 배열
        BlockPosition player_pos = getPlayerPos(); //플레이어 위치 받기

        //BlockMap의 종료 동작을 시행
        blockMap.forEach(((position, block) ->
                Turn_End_Act(Player_Sight, player_pos, position, block)
        ));

        int i = 0;
        //Entity의 종료 동작을 시행
        entities.forEach(entity ->
        {
            BlockPosition entity_pos = getEntityPosByIndex(i); //위치 얻기

            Turn_End_Act(Player_Sight, player_pos, entity_pos, entity);
        });


    }

    private void Turn_End_Act(boolean[][] Player_Sight, BlockPosition player_pos, BlockPosition position, Block block)
    {
        int x =  player_pos.getPosX() - position.getPosX() + 3; //시아 배열에서의 x 좌표
        int y = player_pos.getPosY() - position.getPosY() + 3; //시아 배열에서의 y 좌표

        if (x < 0 || x >= 7 || y < 0 || y >= 7) //좌표 밖이라면
            block.turnEnd(false);//turn end를 수행
        else block.turnEnd(Player_Sight[x][y]); //수행
    }
}



