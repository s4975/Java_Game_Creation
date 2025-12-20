package Block.Entity;

import Block.*;
import Game.Field;
import Game.StageMap;

import java.io.Serializable;

public abstract class Entity extends Block implements Serializable //직렬화 가능
{
    //field의 경우 필요할 때마다 가져와서 작업할 예정

    public Entity(BlockType type)
    {
        super(type); //player 지정
    }

    //Java의 고유 기술
    //Java에서 오버라이딩할때 함수의 return type을 바꿀 수 있음 (어떻게 가능 한지는 모름)
    @Override
    public abstract Entity Block_Copy();

    public abstract boolean Move(StageMap stageMap, int index, int direction);

    public abstract void Vision(Field field); //시아 각도
}
