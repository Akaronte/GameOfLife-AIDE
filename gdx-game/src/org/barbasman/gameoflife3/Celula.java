package org.barbasman.gameoflife3;

public class Celula
{
    boolean curalive=false;
    boolean newalive=false;
    int x;
    int y;
    int width;
    int height;
    
    
    public Celula(int x, int y,int width, int height,boolean curalive,boolean newalive){
    this.x=x;
    this.y=y;
    this.width=width;
    this.height=height;
    this.curalive=curalive;
    this.newalive=newalive;
    }
    
    public boolean getCuralive(){
        return curalive;
    }
    
    public boolean getNewalive(){
        return newalive;
    }
    
    public void setCuralive(boolean calive ){
        curalive=calive;
    }
    public void setNewalive(boolean nalive){
        newalive=nalive;
    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
