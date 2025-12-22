package Block;
//블록 위치의 경우 Map으로 설정

import Game.Field;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

//변수 좌표와 성질을 저장하는 구조체
//구조체 선언 위해 수정 불가능한 final 사용
public abstract class Block implements Serializable //직렬화 가능
{
    //블록 성질
    protected boolean blocked; //이동이 막히는지
    protected boolean invisible; //볼 수 있는지

    //블록 변화 유무
    protected boolean changedBlocked;
    protected boolean changedInvisible;

    //블록 특징
    protected BlockType type; //현재 블록 타입

    //생성자
    public Block(boolean blocked, boolean invisible,
                 boolean changedBlocked, boolean changedInvisible, BlockType type)
    {
        this.blocked = blocked;
        this.invisible = invisible;
        this.changedBlocked = changedBlocked;
        this.changedInvisible = changedInvisible;
        this.type = type;
    }

    //type만 가지고 생성하는 기능
    public Block(BlockType type)
    {
        this.blocked = true; //막힘
        this.invisible = false; //투과하지 않음
        this.changedBlocked = false; //변경 없음
        this.changedInvisible = false; //변경 없음
        this.type = type; //type 지정
    }

    //상태 반환하기
    //(막힌 상태) (투과해서 보이지 않는 상태) 반환하기
    public int getState()
    {
        int temp = 0;

        if (invisible) //보이지 않으면 +1
            temp += 1; //2^0

        if (blocked) //막혀 있으면 +2
            temp += 2; //2^1

        return temp;
    }

    //Block을 복사해서 반환하는 요소
    public abstract Block Block_Copy();

    //type 반환하기
    public BlockType getType()
    {
        return type;
    }

    //턴 끝닐때 수행 기능
    public abstract void turnEnd(boolean observed); //관찰된 상태인지

    //같은 칸 도달시 해야할 일
    public abstract void collisionAct(BlockType type, Field field);



}
