public class Hrac {
    private int hrac;
    private int opponent;

    public Hrac(int hrac){
        this.hrac=hrac;
    }
    public int getplayer(){
        return hrac;
    }
    public int getopponent(Hrac hrac){
        if(hrac.getplayer()==1)
            opponent=2;
        else
            opponent=1;
        return opponent;
    }

}
