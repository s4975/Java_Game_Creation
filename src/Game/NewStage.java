package Game;

import java.util.ArrayList;
import java.util.HashMap;

import Block.Block;
import Block.BlockPosition;
import Block.Entity.Entity;
import Block.Entity.Player;
import Block.Wall;

public class NewStage extends StageMap
{
    protected NewStage(int stage)
    {
        switch (stage)
        {
            case 1:
                //초기 필드 상태 대입 예정
                blockMap.put(new BlockPosition(0,0), new Wall(true, true, true, false));
                blockMap.put(new BlockPosition(2,2), new Wall(true, true, false, true));
                blockMap.put(new BlockPosition(3,5), new Wall(true, true, true, false));
                blockMap.put(new BlockPosition(7,9), new Wall() );

                entityPos.add(new BlockPosition(6,7));
                entities.add(new Player());
                break;

            case 2:
                for (int i = 0; i < 12; i++)
                    for (int j = 0; j < 7; j++)
                        blockMap.put(new BlockPosition(i,j), new Wall(false, false, true, false));

                entityPos.add(new BlockPosition(0,5));
                entities.add(new Player());
                break;
        }
    }

    protected HashMap<BlockPosition, Block> GetBlockMap()
    {
        return blockMap;
    }

    protected ArrayList<BlockPosition> GetEntityPos()
    {
        return entityPos;
    }

    protected ArrayList<Entity> getentities() //entity 객체
    {
        return entities;
    }

}
