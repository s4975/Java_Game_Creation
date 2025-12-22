package Block;

import Game.Field;

public class Trap extends Block
{
    //기본 생성자
    public Trap()
    { super(BlockType.Trap);}

    //생성자
    public Trap(boolean blocked, boolean invisible,
                boolean changedBlocked, boolean changedInvisible)
    {
        super(blocked, invisible, changedBlocked, changedInvisible, BlockType.Trap);
    }

    //복사 생성
    @Override
    public Block Block_Copy() {
        return new Trap(this.blocked, this.invisible, this.changedBlocked, this.changedInvisible);
    }

    @Override
    public void turnEnd(boolean observed)
    {
        if (observed)
        {
            return; //관찰된 상태면 바로 return
        }

        if (changedBlocked) //막힘 상태 바꾸기
        {
            blocked = ! blocked; //반전
        }

        if (changedInvisible) //보이는 상태 바꾸기
        {
            invisible = ! invisible;
        }
    }

    @Override
    public void collisionAct(BlockType type, Field field)
    {
        //reset 수행
        field.reset();
    }
}
