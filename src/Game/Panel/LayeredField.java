package Game.Panel;

import Game.Map_Storage;
import Main.MainFrame;

import javax.swing.*;

//필드를 서로 겹치기 위해 생성한 또 다른 panel
public class LayeredField extends JLayeredPane
{
    MainFrame frame; //여기서 사건 받아서 해결할 예정
    Field field;

    Fail fail; //실패
    Pause pause; //일시 정지
    Success success; //성공

    //생성자
    public LayeredField(Map_Storage stage_Map, MainFrame frame)
    {
        super(); //부모 생성자 호츨
        this.frame = frame; //부모 frame 받기

        this.setBounds(0,0,1200,800);

        this.setOpaque(false); //투명하게 보이기

        Sight temp = new Sight();

        field =  new Field(stage_Map, temp,  this);

        fail = new Fail( this);
        pause = new Pause( this);
        success = new Success(this);

        add(field, (Integer)0);
        add(temp, (Integer)200);
        add(pause, (Integer)300);
        add(success, (Integer)400);
        add(fail, (Integer)500);

        pause.setVisible(false);
        success.setVisible(false);
        fail.setVisible(false);

        revalidate();
        repaint();
    }

    public void openPause() {openCom(pause);} //pause 창 활성화
    public void openFail() {openCom(fail);} //fail 창 활성화
    public void openSuccess() {openCom(success);}

    private void openCom(JPanel panel)
    {
        field.loseFocused(); //필드의 초점 해제
        panel.setVisible(true); //보이게끔 설정

        repaint(); //다시 그리기

        //해당 화면 초점 맞추기
        panel.requestFocus();
        panel.setFocusable(true);
    }

    void closeCom(JPanel panel)
    {
        field.getFocused(); //필드의 초점 가지기
        panel.setVisible(false); //보이지 않게 설정

        repaint(); //다시 그리기

        field.requestFocus();
        field.setFocusable(true);
    }



    public void GotoStagePanel()
    {
        this.setVisible(false); //해당 화면 보이지 않게 하기
        frame.changeToStagePanel(); //스테이지 패널로 이동
        frame.remove(this); //해당 화면 제거
    }

    public void GotoLobbyPanel()
    {
        this.setVisible(false); //해당 화면 보이지 않게
        frame.changeToLobbyPanel(); //로비 패널로 이동
        frame.remove(this); //해당 화면 적재 해제
    }
    public void GotoNextPanel()
    {
        this.setVisible(false);
        frame.changeToNextStage();
        frame.remove(this);
    }

    public void GotoCurrentPanel(JPanel panel)
    {
        closeCom(panel); //창 닫기
        field.reset(); //리셋하기
    }
}
