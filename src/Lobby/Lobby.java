package Lobby;

import Main.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lobby extends JPanel implements ActionListener
{
    BufferedImage Lobby_Img; //로비 이미지 화면
    JButton startButton; //시작 버튼


    public  Lobby(MainFrame frame)
    {
        //로비 화면 가져오기
        try
        {
            Lobby_Img = ImageIO.read(new File("Graphic/Lobby.png")); //사진 받아오기
        } catch (IOException e) {
            System.out.println("error: 경로가 잘못 되었습니다.");
            throw new RuntimeException(e); //error 던지기
        }

        setLayout(null); //배치 관리자 없애기

        startButton = new JButton("Start"); //start 버튼 생성
        startButton.setFont(new Font("Monospaced", Font.BOLD, 60)); //폰트 설정
        startButton.addActionListener(this); //action listener 추가

        startButton.setBounds(770, 500, 300, 70);

        this.add(startButton); //버튼 추가
    }

    public void paint(Graphics g)
    {
        super.paint(g); //그림 그리기

        g.drawImage(Lobby_Img, 0, 0, this); //로비 그림 그리기
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == startButton)
        {

        }


    }
}
