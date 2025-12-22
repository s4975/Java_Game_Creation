package Game.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Success extends JPanel implements ActionListener
{
    LayeredField layer; //layer 가져오기

    JButton[] Buttons = new JButton[4]; //버튼 저장

    public Success(LayeredField layer)
    {
        this.layer = layer; //레이어
        this.setLayout(new GridLayout(5,0,10,10)); //레이아웃
        this.setBackground(new Color(73,73,73,100)); //배경색 지정
        this.setBounds(200, 200, 800, 400); //경계 지정

        //JLabel 설정
        JLabel UpText = new JLabel("스테이지를 클리어 했습니다.");

        UpText.setFont(new Font("Monospaced", Font.BOLD, 30)); //폰트 설정
        UpText.setForeground(Color.white); //글자 색상 변경
        UpText.setHorizontalAlignment(JLabel.CENTER); //글자 중앙에 위치하게 하기

        add(UpText); //추가

        for (int i = 0; i < 4; i++)
        {
            switch(i)
            {
                case 0:
                    Buttons[0] = new JButton("다음 스테이지로"); break;
                case 1:
                    Buttons[1] = new JButton("스테이지 목록으로");  break;
                case 2:
                    Buttons[2] = new JButton("다시 시도"); break;
                case 3:
                    Buttons[3] = new JButton("게임 종료"); break;
            }

            Buttons[i].addActionListener(this); //ActionListener 지정

            Buttons[i].setBackground(new Color(220,220,220));
            Buttons[i].setFont(new Font("Monospaced", Font.BOLD, 25)); //버튼 폰트 설정

            //버튼 삽입하기
            this.add(Buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if  (e.getSource() == Buttons[0]) //다음 스테이지로
        {
            layer.GotoNextPanel();
        }

        if (e.getSource() == Buttons[1]) //스테이지 목록으로
        {
            layer.GotoStagePanel();
        }

        if (e.getSource() == Buttons[2]) //다시 시도
        {
            layer.GotoCurrentPanel(this);
        }

        if (e.getSource() == Buttons[3]) //게임 종료
        {
            layer.GotoLobbyPanel();
        }
    }
}


