package Reversi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Hra extends JPanel {
    JPanel panel;
    JPanel board;
    static JLabel score1;
    static JLabel score2;
    static JButton newG;
    static JButton[] policko;
    static Plocha plocha;
    static ArrayList<Plocha> list=new ArrayList<>();

    static int player1=2;
    static int player2=2;
    private static Plocha start;
    static int row=8;
    static int col=8;
    static Color color=Color.LIGHT_GRAY;

    public Hra(){
        super(new BorderLayout());
        setPreferredSize(new Dimension(800,700));
        setLocation(0,0);

        plocha=new Plocha();
        start=plocha;
        list.add(new Plocha(plocha));

        panel=new JPanel();
        panel.setPreferredSize(new Dimension(800,60));
        newG=new JButton();
        newG.setPreferredSize(new Dimension(80,50));
        try {
            Image img= ImageIO.read(getClass().getResource("obrazky/start.png"));
            newG.setIcon(new ImageIcon(img));
        }catch (IOException e){
        }
        panel.add(newG);
        add(panel,BorderLayout.SOUTH);

        board=new JPanel(new GridLayout(8,8));
        policko=new JButton[64];
        int k=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                policko[k]=new JButton();
                policko[k].setSize(50,50);
                policko[k].setBackground(color);
                policko[k].setBorder(BorderFactory.createLineBorder(Color.darkGray));
                if(plocha.bunky[i][j].getCh()=='X') {
                    try {
                        Image img = ImageIO.read(getClass().getResource("obrazky/dark.png"));
                        policko[k].setIcon(new ImageIcon(img));
                    } catch (IOException e) {
                    }
                }
                else if(plocha.bunky[i][j].getCh()=='O'){
                    try {
                        Image img = ImageIO.read(getClass().getResource("obrazky/light.png"));
                        policko[k].setIcon(new ImageIcon(img));
                    } catch (IOException e) {
                    }
                }else if(i==2 &&j==4 || i==3 && j==5 || i==4 && j==2 || i==5 &&j==3){
                    try {
                        Image img = ImageIO.read(getClass().getResource("obrazky/legal.png"));
                        policko[k].setIcon(new ImageIcon(img));
                    } catch (IOException e) {
                    }
                }
                policko[k].addActionListener(new Action());
                board.add(policko[k]);
                k++;
            }
        }
        add(board,BorderLayout.CENTER);
        JPanel score=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        score.setPreferredSize(new Dimension(210,800));
        JLabel dark=new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("obrazky/dark.png"));
            dark.setIcon(new ImageIcon(img));
        } catch (IOException e) { }
        JLabel light=new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("obrazky/light.png"));
            light.setIcon(new ImageIcon(img));
        } catch (IOException e) { }
        score1=new JLabel();
        score1.setText(" Player1: "+player1+" ");
        score1.setFont(new Font("Serif",Font.BOLD,22));
        score2=new JLabel();
        score2.setText(" Player2: "+player2+" ");
        score2.setFont(new Font("Serif",Font.BOLD,22));

        c.gridx=0;
        c.gridy=1;
        score.add(dark,c);
        c.gridx=1;
        c.gridy=1;
        score.add(score1,c);

        c.gridx=0;
        c.gridy=2;
        score.add(light,c);
        c.gridx=1;
        c.gridy=2;
        score.add(score2,c);

        add(score,BorderLayout.EAST);
    }
    static class Action implements ActionListener{
        boolean bool=false;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==newG){
                plocha.reset();
                list.clear();
                list.add(new Plocha(start));
                int k=0;
                for(int i=0;i<row;i++){
                    for(int j=0;j<col;j++) {
                        policko[k].setIcon(null);
                        if (plocha.bunky[i][j].getCh() == 'X') {
                            try {
                                Image img = ImageIO.read(getClass().getResource("obrazky/dark.png"));
                                policko[k].setIcon(new ImageIcon(img));
                            } catch (IOException ie) {
                            }
                        } else if (plocha.bunky[i][j].getCh() == 'O') {
                            try {
                                Image img = ImageIO.read(getClass().getResource("obrazky/light.png"));
                                policko[k].setIcon(new ImageIcon(img));
                            } catch (IOException ie) {
                            }
                        } else if (i == 2 && j == 4 || i == 3 && j == 5 || i == 4 && j == 2 || i == 5 && j == 3) {
                            try {
                                Image img = ImageIO.read(getClass().getResource("obrazky/legal.png"));
                                policko[k].setIcon(new ImageIcon(img));
                            } catch (IOException ie) {
                            }
                        }
                        k++;
                    }
                }
                player1=2;
                player2=2;
                score1.setText(" Player1: "+player1+" ");
                score2.setText(" Player2: "+player2+" ");
            }else{
                Hrac h1=new Hrac('X');
                Hrac h2=new Hrac('O');
                for(int i=0;i<64;i++){
                    if(e.getSource()==policko[i]){
                        int cX,cY;
                        int point,ct=-100;
                        int arr[]=new int[3];
                        if(i==0){
                            cX=0;
                            cY=0;
                        }else{
                            cY=i%8;
                            cX=i/8;
                        }
                        ct= plocha.tah1(cX,cY);
                        if(ct==0) {
                            list.add(new Plocha(plocha));
                            int k = 0;
                            for (int r = 0; r < row; r++) {
                                for (int j = 0; j < col; j++) {
                                    if (plocha.bunky[r][j].getCh() == 'X') {
                                        try {
                                            Image img = ImageIO.read(getClass().getResource("obrazky/dark.png"));
                                            policko[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ie) {
                                        }
                                    } else if (plocha.bunky[r][j].getCh() == 'O') {
                                        try {
                                            Image img = ImageIO.read(getClass().getResource("obrazky/light.png"));
                                            policko[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ie) {
                                        }
                                    }
                                    k++;
                                }
                            }
                            
                            plocha.control(arr);
                            player1 = arr[0];
                            player2 = arr[1];
                            point = arr[2];
                            score1.setText("Player1: " + player1 + " ");
                            score2.setText("Player2: " + player2 + " ");
                        }if(ct==-1){
                            list.add(new Plocha(plocha));
                            int k = 0;
                            for (int r = 0; r < row; r++) {
                                for (int j = 0; j < col; j++) {
                                    if (plocha.bunky[r][j].getCh() == 'X') {
                                        try {
                                            Image img = ImageIO.read(getClass().getResource("obrazky/dark.png"));
                                            policko[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ie) {
                                        }
                                    } else if (plocha.bunky[r][j].getCh() == 'O') {
                                        try {
                                            Image img = ImageIO.read(getClass().getResource("obrazky/light.png"));
                                            policko[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ie) {
                                        }
                                    }
                                    k++;
                                }
                            }
                            ArrayList<Integer> arrl = new ArrayList<>();
                            plocha.findMove(arrl);
                            for (int j = 0; j < arrl.size(); j += 2) {
                                try {
                                    Image img = ImageIO.read(getClass().getResource("obrazky/legal.png"));
                                    policko[arrl.get(j) * row + arrl.get(j + 1)].setIcon(new ImageIcon(img));
                                } catch (IOException ioException) {
                                }
                            }
                            plocha.control(arr);
                            player1 = arr[0];
                            player2 = arr[1];
                            point = arr[2];
                            score1.setText("Player1: " + player1 + " ");
                            score2.setText("Player2: " + player2 + " ");
                        }

                    }
                }
                int st=plocha.end();
                if(st==0){
                    if(player1 > player2)
                        JOptionPane.showMessageDialog(null,"No legal move!\nPlayer1 Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null,"No legal move!\nPlayer2 Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
                else if(st == 1 || st == 3)
                {
                    JOptionPane.showMessageDialog(null,"Player2 Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
                else if(st == 2 || st == 4)
                {
                    JOptionPane.showMessageDialog(null,"Player1 Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
                else if(st == 3)
                {
                    JOptionPane.showMessageDialog(null,"Scoreless!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

}
