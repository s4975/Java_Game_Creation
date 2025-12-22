package Block;

import Game.Field;

//block을 상속 받아서 Wall을 구현
public class Wall extends Block
{
    //기본 생성자
    public Wall()
    {
        super(BlockType.Wall);
    }

    //생성자
    public Wall(boolean blocked, boolean invisible,
                boolean changedBlocked, boolean changedInvisible)
    {
        super(blocked, invisible, changedBlocked, changedInvisible, BlockType.Wall);
    }

    //블록 복사하기
    @Override
    public Block Block_Copy()
    {
        return new Wall(this.blocked, this.invisible, this.changedBlocked, this.changedInvisible);
    }


    //턴이 끝났을 시 수행하는 매서드
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

    //해당 칸 도칙시 수행하는 매서드
    @Override
    public void collisionAct(BlockType type,  Field field) {}
}
