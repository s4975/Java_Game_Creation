package StagePanel;

import Main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StagePanel extends JPanel implements ActionListener, KeyListener
{
    final int Max_stage = 1; //현재 제작한 스테이지 개수

    MainFrame frame;
    JButton[] StageButton = new JButton[Max_stage]; //버튼 저장 객체
    JPanel Buttons = new JPanel(); //버튼들을 보여줄 JPanel


    public StagePanel(MainFrame frame)
    {
        this.frame = frame;

        //this 설정
        this.setLayout(new BorderLayout());
        setBackground(new Color(73,73,73)); //배경 색

        this.addKeyListener(this); //KeyListener 추가

        //JLabel 설정
        JLabel UpText = new JLabel("슈뢰딩거의 고양이를 아십니까?");

        UpText.setFont(new Font("Monospaced", Font.BOLD, 30)); //폰트 설정
        UpText.setForeground(Color.white); //글자 색상 변경
        UpText.setHorizontalAlignment(JLabel.CENTER); //글자 중앙에 위치하게 하기

        add(UpText, BorderLayout.NORTH); //북쪽에 글씨 적기

        //JButton 설정
        Buttons.setLayout(new GridLayout(10, 10, 20, 20));
        Buttons.setBackground(new Color(73,73,73)); //색상 동일하게

        for (int i = 0; i < Max_stage; i++)
        {
            StageButton[i] = new JButton((i+1) + ""); //버튼 지정하기

            StageButton[i].addActionListener(this); //ActionListener 지정
            StageButton[i].setActionCommand("Stage" + (i+1)); //ActionCommend 지정

            StageButton[i].setBackground(new Color(200,200,100));
            StageButton[i].setFont(new Font("Monospaced", Font.BOLD, 25)); //버튼 폰트 설정

            //버튼 삽입하기
            Buttons.add(StageButton[i]);
        }

        add(Buttons, BorderLayout.CENTER); //중앙에 두기
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        String event = e.getActionCommand(); //ActionCommand 얻기

        if (event.contains("Stage")) //만약 Stage가 들어있는 문자열 이라면
        {
            frame.changeToGamePanel(event); //이벤트 처리 보내기
        }

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        //만약 뒤로 가기 키를 누르면
        if  (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            frame.changeToLobbyPanel(); //로비 화면으로 바꾸기
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
