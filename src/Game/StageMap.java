package Game;
//데이터 직렬화 예정
//위험하긴 해서 나중에 JSon 으로 바꿀 예정

import Block.*;
import Block.Entity.Entity;
import Block.Entity.Player;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class StageMap implements Serializable //직렬화 가능
{
    //Block.Block: 모든 블록의 부모 역활
    //Block.Entity: 모든 Entity의 부모 역활
    private final HashMap<BlockPosition, Block> blockMap = new HashMap<BlockPosition, Block>();

    private final ArrayList<BlockPosition> entityPos = new ArrayList<BlockPosition>(); //entity 위치
    private final ArrayList<Entity> entities = new ArrayList<>(); //entity 객체


    public StageMap()
    {
        setting();
    }

    //객체 복사 생성자
    public StageMap(final StageMap copied)
    {
        //for each문으로 전체를 복사
        copied.blockMap.forEach((blockPos, block) ->
            this.blockMap.put(new BlockPosition(blockPos), block.Block_Copy())); //각각 요소들을 복사한 다음 대입

        copied.entityPos.forEach(entityPos ->
                this.entityPos.add(new BlockPosition(entityPos))); //새로운 객체를 저장

        copied.entities.forEach(entity
                -> this.entities.add(entity.Block_Copy())); //새로운 객체 생성, 복사해서 저장
    }


    //초기 세팅
    private void setting()
    {
        //초기 필드 상태 대입 예정
        blockMap.put(new BlockPosition(0,0), new Wall(true, true, true, false));
        blockMap.put(new BlockPosition(2,2), new Wall(true, true, false, true));
        blockMap.put(new BlockPosition(3,5), new Wall(true, true, true, false));
        blockMap.put(new BlockPosition(7,9), new Wall() );

        entityPos.add(new BlockPosition(6,7));
        entities.add(new Player());
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


    public Entity getPlayer() //플레이어 객체 (entity중 처음에 있는 객체) 반환
    {
        return entities.getFirst(); //처음 요소 반환
    }

    public void drawing(GameGraphics graphics, Graphics g)
    {
        //iterator(반복 가능한 객체) 람다 식으로 작성
        blockMap.forEach(((position, block) ->

                g.drawImage(graphics.getImg(block.getType(), block.getState()),
                        (position.getPosX() * 85) + 60, (position.getPosY() * 87) + 20, null)
        ));

        //람다 식으로 앤티티 출력
        for (int i = 0; i < entities.size(); i++)
        {
            g.drawImage(graphics.getImg(entities.get(i).getType(), entities.get(i).getState()),
                    (entityPos.get(i).getPosX() * 85) + 60, (entityPos.get(i).getPosY() * 87) + 20, null);
        }
    }

    public void turnEnd()
    {
        //BlockMap의 종료 동작을 시행
        blockMap.forEach(((position, block) ->
                block.turnEnd(false) //turn end를 수행
        ));

        //Entity의 종료 동작을 시행
        entities.forEach(entity ->
                entity.turnEnd(false) //turn end를 수행
        );
    }
}
