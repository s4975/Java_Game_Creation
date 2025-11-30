package Block.Entity;

import Block.*;
import Game.Field;
import Game.Map;

public abstract class Entity extends Block
{
    //field의 경우 필요할 때마다 가져와서 작업할 예정

    public Entity(BlockType type)
    {
        super(type); //player 지정
    }

    public abstract boolean Move(Map map, BlockPosition blockPosition, int direction);

    public abstract void Vision(Field field); //시아 각도
}
