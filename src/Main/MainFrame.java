package Main;

import Game.Field;
import Game.StageMap;
import Lobby.Lobby;
import StagePanel.StagePanel;

import javax.swing.*;
import java.io.*;

public class MainFrame extends JFrame
{
    JPanel currentPanel; //현재 화면에 보여지고 있는 패널

    boolean DeveloperMode = false; //개발자 모드

    public MainFrame() //생성자
    {
        setTitle("슈뢰딩거의 고양이를 아십니까?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        if (!DeveloperMode)  //기본 코드
        {
            currentPanel = new StagePanel(this);
            add(currentPanel);
        }

        if (DeveloperMode) //개발자 코드
        {
            //객체 직렬화 test
            //외부 파일 명
            String fileName = "StagePanel/Stage1.obj";

            //직렬화 할 객체
            StageMap To_File = new StageMap();

            //객체 저장
            SaveField(To_File, fileName);

            //정보 받을 객체
            StageMap From_File = LoadField(fileName);

            add(new Field(From_File, this));
        }

        setVisible(true);

    }

    //객체 직렬화하기
    private void SaveField(StageMap Saved_map, String file_name)
    {
        try(
                //파일 입출력을 연 다음, 객체 입출력을 시행
                //해당 try catch문 수행 중에만 파일이 열려 있음
                FileOutputStream fos = new FileOutputStream(file_name); //file output stream
                ObjectOutputStream out = new ObjectOutputStream(fos); //object output stream
        )
        {
            //직렬화 가능 객체를 바이트 스트림으로 변환, 파일에 저장
            out.writeObject(Saved_map); //저장
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //객체 역 직렬화 후 반환
    private StageMap LoadField(String file_name)
    {
        try(
                //파일 입출력을 연 다음, 객체 입출력을 시행
                FileInputStream fis = new FileInputStream(file_name);
                ObjectInputStream in = new ObjectInputStream(fis);
        )
        {
            return (StageMap) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void SaveGame(String file_name)
    {
    }
    private void LoadGame(String file_name)
    {
    }

    //현재 보여지는 화면을 Field로 바꾼다.
    public void changeToField()
    {

    }

}
