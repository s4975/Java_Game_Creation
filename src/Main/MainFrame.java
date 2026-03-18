package Main;

import Game.Panel.LayeredField;
import Game.Map_Storage;
import Lobby.Lobby;
import StagePanel.StagePanel;

import javax.swing.*;
import java.io.*;

public class MainFrame extends JFrame
{
    final int Max_stage = 4; //최대 스테이지 갯수

    JPanel currentPanel; //현재 화면에 보여지고 있는 패널
    JPanel StagePanel; //스테이지 페널
    JPanel LobbyPanel; //Lobby 패널

    String Game_Stage = ""; //현재 play 하고 있는 stage

    boolean DeveloperMode = true; //개발자 모드

    public MainFrame() //생성자
    {
        StagePanel = new StagePanel(this, Max_stage); //stage panel 생성
        LobbyPanel = new Lobby(this); //로비 화면 생성

        setTitle("슈뢰딩거의 고양이를 아십니까?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        if (!DeveloperMode)  //기본 코드
        {
            currentPanel = LobbyPanel; //현재 화면은 로비 패널
            add(currentPanel);
        }

        if (DeveloperMode) //개발자 코드
        {
            //여러 map 객체 파일로 저장 시 for문으로 저장, 로드 되는지 확인하는 과정으로 하면 됨
            Map_Storage From_File = null;

            //int i = 1;
            for (int i = 1; i <= Max_stage; i++) {
                //객체 직렬화 test
                //외부 파일 명
                String fileName = "Stage/Stage" + i + ".obj";

                //객체 저장
                SaveField(new Map_Storage(i), fileName);

                //정보 받을 객체
                if (i == 2)
                    From_File = LoadField(fileName);


            }

            add(new LayeredField(From_File, this)); //새로운 LayeredField 생성
        }

        setVisible(true);

    }

    //객체 직렬화하기
    private void SaveField(Map_Storage Saved_map, String file_name)
    {
        try(
                //파일 입출력을 연 다음, 객체 입출력을 시행
                //해당 try catch문 수행 중에만 파일이 열려 있음
                FileOutputStream fos = new FileOutputStream(file_name); //file output stream
                ObjectOutputStream out = new ObjectOutputStream(fos) //object output stream
        )
        {
            //직렬화 가능 객체를 바이트 스트림으로 변환, 파일에 저장
            out.writeObject(Saved_map); //저장
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //객체 역 직렬화 후 반환
    private Map_Storage LoadField(String file_name)
    {
        try(
                //파일 입출력을 연 다음, 객체 입출력을 시행
                FileInputStream fis = new FileInputStream(file_name);
                ObjectInputStream in = new ObjectInputStream(fis)
        )
        {
            return (Map_Storage) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //현재 보여지는 화면을 Stage로 바꾼다.
    public void changeToStagePanel()
    {
        changePanel(StagePanel);
    }

    //현재 보이는 화면을 Lobby Panel로 바꾼다.
    public void changeToLobbyPanel()
    {
        changePanel(LobbyPanel);
    }

    //현재 보여지는 화면을 GamePanel로 바꾼다.
    public void changeToGamePanel(String stage)
    {
        if (currentPanel != null)
            remove (currentPanel); //현재 패널 삭제
        currentPanel = null; //null로 설정

        Game_Stage = stage; //스테이지 설정
        //객체 직렬화 test

        //정보 받을 객체
        Map_Storage From_File = LoadField("Stage/" + stage + ".obj");

        add (new LayeredField(From_File, this)); //새로운 LayeredField 생성

        setVisible(true);
    }

    public void changeToNextStage()
    {
        currentPanel = null; //null로 설정

        int i = Integer.parseInt(Game_Stage.replace("Stage", "" ));

        if (i < Max_stage && i > -1 ) {

            Game_Stage = "Stage" + ++i; //스테이지 증가

            //정보 받을 객체
            Map_Storage From_File = LoadField("Stage/" + Game_Stage + ".obj");

            add(new LayeredField(From_File, this)); //새로운 LayeredField 생성

            setVisible(true);
        }
        else
        {
            changeToStagePanel(); //스테이지 선택 화면으로
            //(부모 컴포넌트, 메세지, 타이틀, 아이콘)
            JOptionPane.showMessageDialog(null, "마지막 스테이지를 클리어 했습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
        }

    }

    //Panel로 화면 바꾸는 함수
    private void changePanel(JPanel panel)
    {
        if (currentPanel != null)
            remove(currentPanel); //현재 패널 제거

        currentPanel = panel; //Panel로 변경
        add(currentPanel); //다시 불러오기

        //해당 화면 초점 맞추기
        currentPanel.requestFocus();
        currentPanel.setFocusable(true);

        currentPanel.repaint(); //다시 그리기
        setVisible(true); //화면에 보이게 하기
    }

}
