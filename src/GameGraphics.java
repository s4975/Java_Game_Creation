import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameGraphics {

    //외부에서 바로 접근 가능
    //그래픽을 저장하고 있는
    final private BufferedImage img_Field; //필드 사진을 담는 변수

    GameGraphics()
    {
        try
        {
            img_Field = ImageIO.read(new File("Graphic/Field.png"));
        } catch (IOException e) {
            System.out.println("error: 경로가 잘못 되었습니다.");
            throw new RuntimeException(e); //error 던지기
        }
    }

    //이미지 필드 반환
    //나중에 x, y에 맞게끔 수정 후 제공 예정
    public BufferedImage getImgField()
    {
        return img_Field;
    }


}
