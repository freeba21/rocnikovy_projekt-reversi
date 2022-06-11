package Reversi;

import java.util.ArrayList;

public class Plocha {
    private int row=8;
    private int col=8;
    public Bunka bunky[][];
    private Hrac h1=new Hrac('X');
    private Hrac h2=new Hrac('O');
    private Hrac nikto=new Hrac('.');
    private int user1=0;
    private int user2=0;





    public Plocha(){
        int mid=row/2;
        bunky=new Bunka[8][8];
        for(int i=0;i<row;i++){
            bunky[i]=new Bunka[8];
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                bunky[i][j]=new Bunka();
               char c=(char) (97+j);
                if((i == mid-1) && (j == mid-1)){
                    bunky[i][j].setPosition(c, h1.getH(), i+1);
                }
                else if((i == mid-1) && (j == mid))
                {
                    bunky[i][j].setPosition(c, h2.getH(), i+1);
                }
                else if((i == mid) && (j == mid-1))
                {
                    bunky[i][j].setPosition(c, h2.getH(), i+1);
                }
                else if((i == mid) && (j == mid))
                {
                    bunky[i][j].setPosition(c, h1.getH(), i+1);
                }
                else
                {
                    bunky[i][j].setPosition(c, nikto.getH(), i+1);
                }
            }
        }
    }
    public Plocha(Plocha plocha){
        int y;
        char x;
        char h;
        bunky=new Bunka[8][8];
        for(int i=0;i<row;i++)
            bunky[i]=new Bunka[8];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                bunky[i][j]=new Bunka();
                h = plocha.bunky[i][j].getCh();
                y = plocha.bunky[i][j].getY();
                x = plocha.bunky[i][j].getX();
                bunky[i][j].setPosition(x, h, y);
            }
        }
    }
    public void findMove(ArrayList<Integer> list){
        int change=0,max=0;
        for(int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(bunky[i][j].getCh()=='.'){
                    int nMove[]=new int[1];
                    move(i,j,change, h1, h2, nMove);
                    if(nMove[0]!=0){
                        list.add(i);
                        list.add(j);
                    }
                }
            }
        }
    }

    private int move(int x, int y, int change, Hrac hrac1, Hrac hrac2, int[] nMove) {
        int con,st2=0,st=0;
        int status=-1,cX,cY,temp;
        char str;
        int ix;
        nMove[0]=0;
        if((x+1<row)&&(bunky[x+1][y].getCh()==hrac2.getH())){
            con=x;
            while ((con<row)&&(st2!=-1)&&(st!=2)) {
                con++;
                if (con < row) {
                    if (bunky[con][y].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[con][y].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=con-x-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                for(int i=x;i<con;i++){
                    str=bunky[i][y].getX();
                    ix=bunky[i][y].getY();
                    bunky[i][y].setPosition(str,hrac1.getH(),ix);
                }
                status=0;
            }
            st=0;
            st2=0;
        }

        if((x-1>=0)&&(bunky[x-1][y].getCh()==hrac2.getH())){
            con=x;
            while ((con>=0)&&(st2!=-1)&&(st!=2)) {
                con--;
                if (con >=0) {
                    if (bunky[con][y].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[con][y].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=x-con-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                for(int i=con;i<=x;i++){
                    str=bunky[i][y].getX();
                    ix=bunky[i][y].getY();
                    bunky[i][y].setPosition(str,hrac1.getH(),ix);
                }
                status=0;
            }
            st=0;
            st2=0;
        }

        if((y+1 < col) && (bunky[x][y+1].getCh() == hrac2.getH())){

            con = y;
            while((con < col) && (st2 != -1) && (st != 2))
            {
                con++;
                if(con < col){
                    if(bunky[x][con].getCh() == hrac2.getH())
                        st = 1;
                    else if(bunky[x][con].getCh() == hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if (st == 2)
            {
                temp = con - y - 1;
                nMove[0] += temp;
            }
            if(st == 2 && change == 1)
            {
                for (int i = y; i < con; ++i)
                {
                    str = bunky[x][i].getX();
                    ix = bunky[x][i].getY();
                    bunky[x][i].setPosition(str,hrac1.getH(),ix);
                }
                status = 0;
            }
            st=0;st2=0;
        }
        if((y-1>=0)&&(bunky[x][y-1].getCh()==hrac2.getH())){
            con=y;
            while ((con>=0)&&(st2!=-1)&&(st!=2)) {
                con--;
                if (con >=0) {
                    if (bunky[x][con].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[x][con].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=y-con-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                for(int i=con;i<=y;i++){
                    str=bunky[x][i].getX();
                    ix=bunky[x][i].getY();
                    bunky[x][i].setPosition(str,hrac1.getH(),ix);
                }
                status=0;
            }
            st=0;
            st2=0;
        }
        if((x-1>=0)&&(y+1<col)&&(bunky[x-1][y+1].getCh()==hrac2.getH())){
            cX=x;
            cY=y;
            while ((cX>=0)&&(cY<col)&&(st2!=-1)&&(st!=2)) {
                cX--;
                cY++;
                if ((cX >=0)&&(cY<col)) {
                    if (bunky[cX][cY].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[cX][cY].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=x-cX-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                while ((cX<=x)&&(y<cY)){
                    cX++;
                    cY--;
                    if((cX<=x)&&(y<=cY)) {
                        str = bunky[cX][cY].getX();
                        ix = bunky[cX][cY].getY();
                        bunky[cX][cY].setPosition(str, hrac1.getH(), ix);
                    }
                }
                status=0;
            }
            st=0;
            st2=0;
        }
        if((x-1>=0)&&(y-1 >= 0)&&(bunky[x-1][y-1].getCh()==hrac2.getH())){
            cX=x;
            cY=y;
            while ((cX>=0)&&(cY>=0)&&(st2!=-1)&&(st!=2)) {
                cX--;
                cY--;
                if ((cX >=0)&&(cY>=0)) {
                    if (bunky[cX][cY].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[cX][cY].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=x-cX-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                while ((cX<=x)&&(y>=cY)){
                    cX++;
                    cY++;
                    if((cX<=x)&&(y>=cY)) {
                        str = bunky[cX][cY].getX();
                        ix = bunky[cX][cY].getY();
                        bunky[cX][cY].setPosition(str, hrac1.getH(), ix);
                    }
                }
                status=0;
            }
            st=0;
            st2=0;
        }
        if((x+1<row)&&(y+1<col)&&(bunky[x+1][y+1].getCh()==hrac2.getH())){
            cX=x;
            cY=y;
            while ((cX<row)&&(cY<col)&&(st2!=-1)&&(st!=2)) {
                cX++;
                cY++;
                if ((cX <row)&&(cY<col)) {
                    if (bunky[cX][cY].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[cX][cY].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=cX-x-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                while ((cX>=x)&&(y<=cY)){
                    cX--;
                    cY--;
                    if((cX>=x)&&(y<=cY)) {
                        str = bunky[cX][cY].getX();
                        ix = bunky[cX][cY].getY();
                        bunky[cX][cY].setPosition(str, hrac1.getH(), ix);
                    }
                }
                status=0;
            }
            st=0;
            st2=0;
        }
        if((x+1<row)&&(y-1>=0)&&(bunky[x+1][y-1].getCh()==hrac2.getH())){
            cX=x;
            cY=y;
            while ((cX<row)&&(cY>=0)&&(st2!=-1)&&(st!=2)) {
                cX++;
                cY--;
                if ((cX <row)&&(cY>=0)) {
                    if (bunky[cX][cY].getCh()==hrac2.getH())
                        st = 1;
                    else if (bunky[cX][cY].getCh()==hrac1.getH())
                        st = 2;
                    else
                        st2 = -1;
                }
            }
            if(st==2){
                temp=cX-x-1;
                nMove[0]+=temp;
            }
            if(st==2 && change==1){
                while ((cX>=x)&&(cY<=y)){
                    cX--;
                    cY++;
                    if((cX>=x)&&(y>=cY)) {
                        str = bunky[cX][cY].getX();
                        ix = bunky[cX][cY].getY();
                        bunky[cX][cY].setPosition(str, hrac1.getH(), ix);
                    }
                }
                status=0;
            }
            st=0;
            st2=0;
        }
        if(status==0)
            return 0;
        else
            return -1;
    }
    public int tah1(int x,int y){
        int status;
        int change=0,max=0;
        int nMove[]=new int[1];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(bunky[i][j].getCh()==nikto.getH()){
                    move(i,j,change,h1,h2,nMove);
                    if(max<nMove[0]){
                        max=nMove[0];
                    }
                }
            }
        }
        user1=max;
        if(user1==0){
            user1=-1;
            return -1;
        }if(user1!=0){
            change=1;
            if(bunky[x][y].getCh()!=nikto.getH())
                return 1;
            status=move(x,y,change,h1,h2,nMove);
            if(status==-1)
                return 1;
        }
        vypis();
        return 0;

    }


    private void vypis() {
        System.out.println("   1 2 3 4 5 6 7 8");
        System.out.println("  -----------------");
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(j!=7)
                    if(j==0)
                        System.out.printf((i+1)+"| "+bunky[i][j].getCh()+" ");
                    else
                        System.out.printf("%c ",bunky[i][j].getCh());
                else
                    System.out.printf("%c\n",bunky[i][j].getCh());
            }
        }
    }
    public int end(){
        int[] list=new int[3];
        int h1,h2,n;
        control(list);
        h1=list[0];
        h2=list[1];
        n=list[2];
        if((user1==-1 && user2==-1)|| n==0){
            if(user1==-1 && user2==-1)
                return 0;
            if(h2>h1)
                return 1;
            else if(h1>h2)
                return 2;
            else if(h1==0)
                return 3;
            else if(h2==0)
                return 4;
            else
                return 5;
        }
        return -1;

    }

    public void control(int[] list) {
        int hr1=0,hr2=0,n=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(bunky[i][j].getCh()==h1.getH())
                    hr1++;
                else if(bunky[i][j].getCh()==h2.getH())
                    hr2++;
                else if(bunky[i][j].getCh()==nikto.getH())
                    n++;
            }
        }
        list[0]=hr1;list[1]=hr2;list[2]=n;
    }

    public void reset() {
        int mid=row/2;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                char c=(char) (97+j);
                if((i == mid-1) && (j == mid-1)){
                    bunky[i][j].setPosition(c, h1.getH(), i+1);
                }
                else if((i == mid-1) && (j == mid))
                {
                    bunky[i][j].setPosition(c, h2.getH(), i+1);
                }
                else if((i == mid) && (j == mid-1))
                {
                    bunky[i][j].setPosition(c, h2.getH(), i+1);
                }
                else if((i == mid) && (j == mid))
                {
                    bunky[i][j].setPosition(c, h1.getH(), i+1);
                }
                else
                {
                    bunky[i][j].setPosition(c, nikto.getH(), i+1);
                }
                System.out.printf("i: %d, j: %d, h: %c\n",i,j,bunky[i][j].getCh());
            }
        }
    }
}
