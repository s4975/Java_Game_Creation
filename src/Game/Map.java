package Game;//데이터 직렬화 예정
//위험하긴 해서 나중에 JSon 으로 바꿀 예정

import Block.*;
import Block.Entity.Entity;
import Block.Entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public record Map()
{
    //Block.Block: 모든 블록의 부모 역활
    //Block.Entity: 모든 Entity의 부모 역활
    static HashMap<Position, Block> blockMap = new HashMap<Position, Block>();

    //내부 class static 설정
    static class EntityMap
    {
        public Position  pos;
        public Entity entity;

        public EntityMap(Position pos, Entity entity)
        {
            this.pos = pos;
            this.entity = entity;
        }
    }

    static ArrayList<EntityMap> entityMaps = new ArrayList<EntityMap>(); //entity map들

    public Map()
    {
        Block block = new Wall(true, true, false, false);
        //초기 필드 상태 대입 예정
        blockMap.put(new Position(0,0), new Wall());
        blockMap.put(new Position(2,2), new Wall());
        blockMap.put(new Position(3,5), block);
        blockMap.put(new Position(7,9), block);

        entityMaps.addFirst
                (new EntityMap(new Position(3,3), new Player()));

    }
}
