public class Plocha {
    private int[][] plocha;
    private int row;
    private int col;

    public Plocha(Hrac h1, Hrac h2){
        plocha=new int[8][8];
        plocha[3][3]=h2.getplayer();
        plocha[4][4]=h2.getplayer();
        plocha[3][4]=h1.getplayer();
        plocha[4][3]=h1.getplayer();
        row=8;
        col=8;
    }
    public int getrow(){
        return row;
    }
    public int getcol(){
        return col;
    }
    public int getbox(int x,int y){
        return plocha[x][y];
    }
    public void set(int x, int y, int p){
        plocha[x][y]=p;
    }

    public void print(){
        System.out.println("   1 2 3 4 5 6 7 8");
        System.out.println("  -----------------");
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(j!=7)
                    if(j==0)
                        System.out.printf((i+1)+"| "+plocha[i][j]+" ");
                    else
                        System.out.printf("%d ",plocha[i][j]);
                else
                System.out.printf("%d\n",plocha[i][j]);
            }
        }
    }
    public void skore(){
        int h1=0;
        int h2=0;
        for(int r=0;r<row;r++){
            for(int c=0;c<col;c++){
                if(plocha[r][c]==1)
                    h1++;
                else if(plocha[r][c]==2)
                    h2++;
            }
        }
        if(h1+h2!=64){
            if(h1>h2){
                h1+=64-h2-h1;
            }else if(h2>h1)
                h2+=64-h1-h2;
        }
        System.out.println("\nFinálne skóre je:");
        System.out.println("Hráč č.1: "+h1+" Hráč č.2: "+h2);
    }
    public int validmoves(Hrac hrac){
        int move=0;
        for(int r=1;r<row;r++){
            for (int c=1;c<col;c++){
                if(plocha[r][c]==hrac.getplayer()){
                    for (int i=-1;i<=1;i++){
                        for (int j=-1;j<=1;j++){
                            if(r+i>0 &&r+i<7&&c+j>0&&c+j<7)
                            if(plocha[r+i][c+j]==hrac.getopponent(hrac)){
                                for (int k=-1;k<=1;k++){
                                    for (int l=-1;l<=1;l++){
                                        if(r+i+k>-1 &&r+i+k<8&&c+j+l>-1&&c+j+l<8)
                                        if(plocha[r+i+k][c+j+l]==0)
                                            move++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return move;
    }

}
