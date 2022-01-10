import java.util.*;

public class Reversi {
    public static void main(String[] args) {
        Hrac hrac1=new Hrac(1) ;
        Hrac hrac2=new Hrac(2);
        Plocha plocha=new Plocha(hrac1,hrac2);
        Hra reversi=new Hra(plocha,hrac1,hrac2);
        reversi.start();
    }
}
