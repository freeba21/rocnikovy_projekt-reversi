import  java.util.*;


public class Hra {
    private Plocha plocha;
    private Hrac h1;
    private Hrac h2;
    private String string;
    private boolean player2=false;
    private int invalidmove=0;
    private Tah tah;

    public Hra(Plocha plocha, Hrac h1, Hrac h2){
        this.plocha=plocha;
        this.h1=h1;
        this.h2=h2;
    }
    public void start() {
        Scanner s = new Scanner(System.in);

        do {
            do {
                plocha.print();
                if(!player2) {
                    System.out.println("Hrá hrač č." + h1.getplayer());
                    if(plocha.validmoves(h1)>=2) {
                        System.out.println("Zadaj suradnice(riadok,stlpec): ");
                        tah = new Tah(plocha, s.nextInt() - 1, s.nextInt() - 1, h1);
                        if (tah.change()) {
                            System.out.println("Ťah hrača č." + h1.getplayer());
                            tah.setPlocha();
                            player2 = true;

                        } else {
                            System.out.println("Zadal si nespravny tah. Zadaj ho znova:");
                        }
                    }else{
                        if(invalidmove++<2)
                            System.out.println("\nNevieš zahrať ťah.");
                        else {
                            System.out.println("Ani jeden hráč nevie hrať, hra je u konca.");
                         }
                    }
                }else  {
                    System.out.println("Hrá hrač č." + h2.getplayer());
                    if (plocha.validmoves(h2) >= 2) {
                        System.out.println("Zadaj suradnice(riadok,stlpec): ");
                        tah = new Tah(plocha, s.nextInt() - 1, s.nextInt() - 1, h2);
                        if (tah.change()) {
                            System.out.println("Ťah hrača č." + h2.getplayer());
                            tah.setPlocha();
                            player2 = false;

                        } else {
                            System.out.println("Zadal si nespravny tah. Zadaj ho znova:");tah = new Tah(plocha, s.nextInt() - 1, s.nextInt() - 1, h2);
                        }

                    }else {
                        if (invalidmove++ < 2) {
                            System.out.println("\nNevieš zahrať ťah.");
                        } else {
                            System.out.println("Ani jeden hráč nevie hrať, hra je u konca.");
                        }
                    }
                }

            } while (invalidmove<2);

            plocha.print();
            plocha.skore();
            System.out.println("Chceš hrať znova(y/n): ");
            string=s.next();

        }while(string.equals("y"));
    }
}
