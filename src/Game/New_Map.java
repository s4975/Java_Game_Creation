package Game;

import java.util.ArrayList;
import java.util.HashMap;

import Block.*;
import Block.Entity.*;

public class New_Map extends Map_Storage
{
    protected New_Map(int stage)
    {
        switch (stage)
        {
            case 1:
                //초기 필드 상태 대입 예정
                for (int i = 0; i < 7; i++)
                {
                    blockMap.put(new BlockPosition(0,i), new Wall(true, true, false, false));
                    blockMap.put(new BlockPosition(11,i), new Wall(true, true, false, false));
                }
               for (int i = 1; i < 11; i++)
               {
                   blockMap.put(new BlockPosition(i,0), new Wall(true, true, false, false));
                   blockMap.put(new BlockPosition(i,6), new Wall(true, true, false, false));

                   if (i % 2 == 0)
                   {
                       blockMap.put(new BlockPosition(i,1), new Wall(false, false, true, false));
                       blockMap.put(new BlockPosition(i,5), new Wall(false, false, true, false));
                   }
                   else
                   {
                       blockMap.put(new BlockPosition(i,1), new Wall(true, false, true, false));
                       blockMap.put(new BlockPosition(i,5), new Wall(true, false, true, false));
                   }
               }




               blockMap.put(new BlockPosition(9,2), new Goal(false, false, false, false));

                entityPos.add(new BlockPosition(2,3));
                entities.add(new Player());
                break;

            case 2:

                for (int i = 0; i < 7; i++)
                {
                    blockMap.put(new BlockPosition(5, i), new Wall(true, true, true, true));
                    blockMap.put(new BlockPosition(6, i), new Wall(true, true, true, true));
                }

                blockMap.put(new BlockPosition(10,3), new Goal(false,false, true, false));

                entityPos.add(new BlockPosition(1,3));
                entities.add(new Player());
                break;


            case 3:
                for (int i = 0; i < 12; i++)
                    for (int j = 0; j < 7; j++)
                    {
                        blockMap.put(new BlockPosition(i, j), new Wall(false, false, true, false));
                        if (i == 11 && j == 6)
                        {
                            blockMap.put(new BlockPosition(11,6), new Goal(false,false, true, false));
                        }
                    }

                entityPos.add(new BlockPosition(0,5));
                entities.add(new Player());
                break;

            case 4:
                for (int i = 0; i < 7; i++)
                    blockMap.put(new BlockPosition(4,i), new Wall(false, false, true, true));

                blockMap.put(new BlockPosition(0,6), new Trap(true, false, true, false));
                blockMap.put(new BlockPosition(5,7), new Trap());
                blockMap.put(new BlockPosition(3,7), new Trap());
                blockMap.put(new BlockPosition(2,7), new Trap());
                blockMap.put(new BlockPosition(7,9), new Goal(true, false, true, false));

                entityPos.add(new BlockPosition(0,0));
                entities.add(new Player());
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

    protected ArrayList<Entity> entities() //entity 객체
    {
        return entities;
    }

}
