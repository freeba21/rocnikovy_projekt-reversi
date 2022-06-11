package Reversi;

import javax.swing.*;
import java.awt.*;

public class main extends JFrame{
    private static JPanel panel;
    public main(){
        super("Reversi");
        setLayout(new BorderLayout());
        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        int height=screen.height;
        int width=screen.width;
        setSize(width/2,height/2);
        setLocationRelativeTo(null);
        panel=new Hra();
        add(panel,BorderLayout.CENTER);
        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new main();
    }
}
