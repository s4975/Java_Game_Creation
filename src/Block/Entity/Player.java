package Block.Entity;

import Block.BlockType;
import Block.Position;
import Game.Field;

public class Player extends Entity
{
    public Player()
    {
        super(BlockType.Player);
    }


    @Override
    public void Move(Field field, Position position, int direction)
    {

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
