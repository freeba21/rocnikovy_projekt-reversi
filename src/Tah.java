import java.util.ArrayList;

public class Tah {
    private int riadok;
    private int stlpec;
    private Plocha plocha;
    private Hrac hrac;

    public Tah(Plocha plocha,int riadok,int stlpec, Hrac hrac){
        this.riadok=riadok;
        this.stlpec=stlpec;
        this.plocha=plocha;
        this.hrac=hrac;
    }

    public boolean change(){
        if(riadok<0 || riadok>7 || stlpec<0 || stlpec>7)
            return false;
        int dole=down();
        int hore=up();
        int pravo=right();
        int lavo=left();
        int horeP=upRight();
        int horeL=upLeft();
        int doleL=downLeft();
        int doleP=downRight();
        if(plocha.getbox(riadok,stlpec)==hrac.getplayer() || plocha.getbox(riadok,stlpec)== hrac.getopponent(hrac))
            return false;
        int pocet=dole+hore+pravo+lavo+horeL+horeP+doleL+doleP;
        if(pocet==0)
            return false;
        return true;

    }
    private int downRight(){
        int dr=0;

        for (int i=1;i<8-riadok;i++) {
            if (riadok == riadok + i || stlpec + i > 7 || riadok + i > 7) break;
            if (plocha.getbox(riadok + i, stlpec + i) == hrac.getopponent(hrac) && riadok + i < 7 && stlpec + i < 7)
                dr++;
            else if (plocha.getbox(riadok + i, stlpec + i) == hrac.getplayer())
                break;
            else dr = 0;
        }

        return dr;
    }
    private int downLeft(){
        int dl=0;
        int i=0;
        while (true){
            if(riadok==riadok+i ||  riadok+i>7 || stlpec-i<0) break;
            if(plocha.getbox(riadok+i,stlpec-i)==hrac.getopponent(hrac) && riadok+i<7 && stlpec-i>0)
                dl++;
            else if (plocha.getbox(riadok+i,stlpec-i)==hrac.getplayer())
                break;
            else dl=0;
            i++;
        }
        return dl;
    }
    private int upLeft(){
        int ul=0;

        for (int i=1;i<riadok;i++){
            if(riadok-i==riadok || riadok-i<0 || stlpec-i<0) break;
            if(plocha.getbox(riadok-i,stlpec-i)==hrac.getopponent(hrac) && riadok-i>0 && stlpec-i>0)
               ul++;
            else if (plocha.getbox(riadok-i,stlpec-i)==hrac.getplayer())
                break;
            else ul=0;

        }
        return ul;
    }
    private int upRight(){
        int ur=0;
        for (int i=1;i<riadok;i++){
            if( riadok-i==riadok || stlpec+i>7 ) break;
            if(plocha.getbox(riadok-i,stlpec+i)==hrac.getopponent(hrac) && riadok-i>0 && stlpec+i<7)
                ur++;
            else if (plocha.getbox(riadok-i,stlpec+i)==hrac.getplayer())
                break;
            else ur=0;
        }
        return ur;
    }
    private int up(){
        int u=0;
        for (int i=riadok-1;i>-1;i--){
            if(plocha.getbox(i,stlpec)==hrac.getopponent(hrac) && i>0){
                u++;
            }else if (plocha.getbox(i,stlpec)==hrac.getplayer() )
                break;
            else u=0;
        }
        return u;
    }
    private int down(){
        int d=0;
        for (int i=riadok+1;i<8;i++){
            if(plocha.getbox(i,stlpec)==hrac.getopponent(hrac) && i<7){
                d++;
            }else if (plocha.getbox(i,stlpec)==hrac.getplayer())
                break;
            else d=0;
        }
        return d;
    }
    private int right(){
        int r=0;
        for(int s=stlpec+1;s<8;s++) {
            if (plocha.getbox(riadok,  s) == hrac.getopponent(hrac) &&  s < 7) {
                r++;
            } else if (plocha.getbox(riadok, s ) == hrac.getplayer())
                break;
            else r = 0;
        }
       return r;
    }
    private int left(){
        int l= 0;
        for(int s=stlpec-1;s>-1;s--) {
            if (plocha.getbox(riadok, s) == hrac.getopponent(hrac) &&  s > 0) {
                l++;
            } else if (plocha.getbox(riadok,  s) == hrac.getplayer())
                break;
            else l = 0;
        }

        return l;
    }

    public void setPlocha(){
        plocha.set(riadok,stlpec,hrac.getplayer());
        int pravo=right()+1;
        for(int i=1;i<pravo;i++){
            if(plocha.getbox(riadok,stlpec+i)==hrac.getopponent(hrac))
                plocha.set(riadok,stlpec+i,hrac.getplayer());
            else break;
        }
        int lavo=left()+1;
        for (int i=1;i<lavo;i++){
            if(plocha.getbox(riadok,stlpec-i)== hrac.getopponent(hrac)) {
                plocha.set(riadok, stlpec - i, hrac.getplayer());
            }
            else break;
        }
        int hore=up()+1;
        for (int i=1;i<hore;i++){
            if(plocha.getbox(riadok-i,stlpec)== hrac.getopponent(hrac))
                plocha.set(riadok-i,stlpec,hrac.getplayer());
            else break;
        }
        int dole=down()+1;
        for (int i=1;i<dole;i++){
            if(plocha.getbox(riadok+i,stlpec)== hrac.getopponent(hrac))
                plocha.set(riadok+i,stlpec,hrac.getplayer());
            else break;
        }
        int hp=upRight()+1;
        for (int i=1;i<hp;i++){
            if(plocha.getbox(riadok-i,stlpec+i)== hrac.getopponent(hrac))
                plocha.set(riadok-i,stlpec+i,hrac.getplayer());
            else break;
        }
        int hl=upLeft()+1;
        for (int i=1;i<hl;i++){
            if(plocha.getbox(riadok-i,stlpec-i)== hrac.getopponent(hrac))
                plocha.set(riadok-i,stlpec-i,hrac.getplayer());
            else break;
        }
        int dl=downLeft()+1;
        for (int i=1;i<dl;i++){
            if(plocha.getbox(riadok+i,stlpec-i)== hrac.getopponent(hrac))
                plocha.set(riadok+i,stlpec-i,hrac.getplayer());
            else break;
        }
        int dp=downRight()+1;
        for (int i=1;i<dp;i++){
            if(plocha.getbox(riadok+i,stlpec+i)== hrac.getopponent(hrac))
                plocha.set(riadok+i,stlpec+i,hrac.getplayer());
            else break;
        }
    }

}
