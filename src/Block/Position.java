package Block;

import java.util.Objects;

public class Position
{
    //X, Y의 위치
    private int posX; //x 죄표 (0~11)
    private int posY; //y 좌표 (0~6)

    public Position(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;

        //범위 지정 연산자
        if (this.posX < 0) this.posX = 0;
        if (this.posX > 11) this.posX = 11;
        if (this.posY < 0) this.posY = 0;
        if (this.posY > 6) this.posY = 6;
    }

    public int getPosX() {return posX;}

    public int getPosY() {return posY;}

    public void goUp()
    {
        if (posY > 0) this.posY -= 1;
    }

    public void goDown()
    {
        if (posY < 6) this.posY += 1;
    }

    public void goLeft()
    {
        if (posX > 0) this.posX -= 1;
    }

    public void goRight()
    {
        if (posX < 11) this.posX += 1;
    }

    //equals 매서드 오버라이팅
    //객체의 x, y좌표가 같다면 true, 다르다면 false를 반환
    //Game.Map 기능을 사용하기 위해 오버라이딩을 사용함
    //미 실시시 기본 object의 equal 함수 (주소값을 비교)를 불러와서 원하는 값을 얻을수가 없음
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position position)) return false;
        return posX == position.posX && posY == position.posY;
    }

    //equals를 사용 시 따라와야 하는 코드
    //비교 시행할 때 hashCode부터 읽기 때문에 반드시 오버라이딩 해야 함
    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

}
