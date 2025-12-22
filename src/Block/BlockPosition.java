package Block;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class BlockPosition implements Serializable, Comparator<BlockPosition> //직렬화 가능
{
    //X, Y의 위치
    private int posX; //x 죄표 (0~11)
    private int posY; //y 좌표 (0~6)

    public BlockPosition(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;

        //범위 지정 연산자
        if (this.posX < 0) this.posX = 0;
        if (this.posX > 11) this.posX = 11;
        if (this.posY < 0) this.posY = 0;
        if (this.posY > 6) this.posY = 6;
    }

    //복사생성자: 입력 position을 복사해서 새로운 position을 만든다.
    public BlockPosition(BlockPosition copied)
    {
        //복사 생성해서 제작
        this.posX = copied.posX;
        this.posY = copied.posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    //반환
    public int getPosX() {return posX;}

    public int getPosY() {return posY;}

    //x 죄표 (0~11), y 좌표 (0~6)
    //dx, dy 크기 만큼 블록의 좌표를 옮긴다.
    public void moveTo(int dx, int dy)
    {
        this.posX += dx;
        if (this.posX < 0) this.posX = 0; //0보다 작으면 0으로
        if (this.posX > 11) this.posX = 11; //11보다 크면 11로

        this.posY += dy;
        if (this.posY < 0) this.posY = 0; //0보다 작으면 0으로
        if (this.posY > 6) this.posY = 6; //6보다 크면 6으로
    }

    @Override
    public int compare(BlockPosition o1, BlockPosition o2)
    {
        if (o1.posX == o2.posX) //posX가 서로 같다면
        {
            return o1.posY - o2.posY; //Y 좌표 비교
        }
        else return o1.posX - o2.posX; //아니면 X좌표 비교
    }

    //equals 매서드 오버라이팅
    //객체의 x, y좌표가 같다면 true, 다르다면 false를 반환
    //Game.Map_Storage 기능을 사용하기 위해 오버라이딩을 사용함
    //미 실시시 기본 object의 equal 함수 (주소값을 비교)를 불러와서 원하는 값을 얻을수가 없음
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BlockPosition blockPosition)) return false;
        return posX == blockPosition.posX && posY == blockPosition.posY;
    }

    //equals를 사용 시 따라와야 하는 코드
    //비교 시행할 때 hashCode부터 읽기 때문에 반드시 오버라이딩 해야 함
    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
