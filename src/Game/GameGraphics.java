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
    final private BufferedImage[] img_Wall = new BufferedImage[4]; //벽 상태를 받는 변수
    final private BufferedImage[] img_Trap = new BufferedImage[4]; //벽 상태를 받는 변수
    final private BufferedImage[] img_Goal = new BufferedImage[4]; //벽 상태를 받는 변수
    final private BufferedImage img_Player; //플레이어 이미지


    public GameGraphics()
    {
        try
        {
            img_Field = ImageIO.read(new File("Graphic/Field.png")); //사진 받아오기

            img_Wall[0] = null;
            img_Wall[1] = ImageIO.read(new File("Graphic/Wall_1.png")); //안개 사진
            img_Wall[2] = ImageIO.read(new File("Graphic/Wall_2.png")); //유리 사진
            img_Wall[3] = ImageIO.read(new File("Graphic/Wall_3.png")); //벽 사진

            img_Goal[0] = ImageIO.read(new File("Graphic/Goal_0.png"));
            img_Goal[1] = ImageIO.read(new File("Graphic/Goal_1.png"));
            img_Goal[2] = ImageIO.read(new File("Graphic/Goal_2.png"));
            img_Goal[3] = ImageIO.read(new File("Graphic/Goal_3.png"));

            img_Trap[0] = ImageIO.read(new File("Graphic/Trap_0.png"));
            img_Trap[1] = ImageIO.read(new File("Graphic/Trap_1.png")); //안개 사진
            img_Trap[2] = ImageIO.read(new File("Graphic/Trap_2.png")); //유리 사진
            img_Trap[3] = img_Wall[3]; //벽 사진

            img_Player = ImageIO.read(new File("Graphic/Player.png")); //플레이어 사진

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
                if (statement >= 0 && statement < 4)
                    return img_Wall[statement]; //현재 상태 wall을 반환
                break;

            case Player: return img_Player; //player 이미지를 반환

            case Trap:
                if (statement >= 0 && statement < 4)
                    return img_Trap[statement]; //현재 상태 wall을 반환
                break;

            case Goal:
                if (statement >= 0 && statement < 4)
                    return img_Goal[statement]; //현재 상태 wall을 반환
                break;

            case null : return img_Field;
        }

        return null; //오류 발생시 반환
    }
}
