package Game;

import Block.BlockType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameGraphics {

    //외부에서 바로 접근 가능
    //그래픽을 저장하고 있는
    final private BufferedImage img_Field; //필드 사진을 담는 변수

    final private BufferedImage[] img_Wall = new BufferedImage[2]; //벽 상태를 받는 변수

    public GameGraphics()
    {
        try
        {
            img_Field = ImageIO.read(new File("Graphic/Field.png")); //사진 받아오기
            img_Wall[0] = ImageIO.read(new File("Graphic/Wall_0.png")); //벽 사진
            img_Wall[1] = ImageIO.read(new File("Graphic/Wall_1.png")); //유리 사진

        } catch (IOException e) {
            System.out.println("error: 경로가 잘못 되었습니다.");
            throw new RuntimeException(e); //error 던지기
        }
    }

    //이미지 필드 반환
    //나중에 x, y에 맞게끔 수정 후 제공 예정

    public BufferedImage getImg(BlockType type, int statement)
    {
        switch (type)
        {

            case Wall:

                if (statement == 2) //not invisible, blocked
                {return img_Wall[0];}

                else if (statement == 3) //invisible, blocked
                {return img_Wall[1];}

                break;

            case Player:
                break;

            case null : return img_Field;
        }

        return null; //오류 발생시 반환
    }

    public BufferedImage getImgField()
    {
        return img_Field;
    }

    public  BufferedImage getWall(int statement)
    {
        if (statement == 2) //not invisible, blocked
        {
            return img_Wall[0];
        }
        else if (statement == 3) //invisible, blocked
        {
            return img_Wall[1];
        }


        //else문에 걸리지 않을 경우
        return null;
    }


}
