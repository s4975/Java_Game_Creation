package Game;//데이터 직렬화 예정
//위험하긴 해서 나중에 JSon 으로 바꿀 예정

import Block.*;
import Block.Entity.Entity;
import Block.Entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

//static 사용 위해서 사용 (이유는 모르지만 오류는 발생함)
public record Map()
{
    //Block.Block: 모든 블록의 부모 역활
    //Block.Entity: 모든 Entity의 부모 역활
    public static HashMap<BlockPosition, Block> blockMap = new HashMap<BlockPosition, Block>();

    public static ArrayList<BlockPosition> entityPos = new ArrayList<BlockPosition>(); //entity 위치
    public static ArrayList<Entity> entities = new ArrayList<Entity>(); //entity 객체


    public Map()
    {
        Block block = new Wall(true, true, false, false);
        //초기 필드 상태 대입 예정
        blockMap.put(new BlockPosition(0,0), new Wall());
        blockMap.put(new BlockPosition(2,2), new Wall());
        blockMap.put(new BlockPosition(3,5), block);
        blockMap.put(new BlockPosition(7,9), block);

        entityPos.add(new BlockPosition(5,7));
        entities.add(new Player());

    }
}
