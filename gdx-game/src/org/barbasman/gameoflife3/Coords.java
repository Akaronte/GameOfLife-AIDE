package org.barbasman.gameoflife3;
import com.badlogic.gdx.graphics.*;

public class Coords
{
    int width;
    int height;
    int centrox;
    int centroy;
    int sizeCell=5;
    int CellsPerRow=1000;
    int CellsPerCol=1000;
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setWidth(int setwidth){
        width=setwidth;
    }
    public void setHeight(int setheight){
        height=setheight;
    }
    public int getCentrox(){
        if(width>1){
            centrox=(CellsPerRow-(CellsPerRow%sizeCell))/2;
        }
        return centrox*sizeCell;
        
    }
    public int getCentroy(){
        if(height>1){
            centroy=(CellsPerCol-(CellsPerCol%sizeCell))/2;
        }
        return centroy*sizeCell;

    }
    
    public int getCellsPerRow(){
        
        //CellsPerRow=(width-(width%sizeCell)/sizeCell);
        //CellsPerRow=width/5;
        return CellsPerRow;
    }
    public int getCellsPerCol(){
        //CellsPerCol= (height-(height%sizeCell)/sizeCell);
        //CellsPerCol=height/5;
        return CellsPerCol;
    }
    public int getSizeCell(){
        return sizeCell;
    }
    
    public void setSizeCell(int size){
        if(size<4){size=4;}
        sizeCell=size;
    }
    
    
}
