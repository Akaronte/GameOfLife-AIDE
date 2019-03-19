package org.barbasman.gameoflife3;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import com.badlogic.gdx.math.MathUtils;



public class Cells
{
    
    static Celula celulas[][];
    int cellsPerRow;
    int cellsPerCol;
    int cellSize;
    MathUtils math=new MathUtils();
    
    
   public void createCells(Coords coords){
    
        
        cellsPerRow=coords.getCellsPerRow();
        cellsPerCol=coords.getCellsPerCol();
        cellSize=cellsPerRow*cellsPerCol;
        
        
       celulas=new Celula[cellsPerRow][cellsPerCol];
       Log.d("barbasman",""+cellsPerRow); 
        
        for(int f=0;f<1000;f++){
            for(int c=0;c<1000;c++){
                celulas[f][c]=new Celula(f,c,coords.getSizeCell(),coords.getSizeCell(),false,false);
                
                celulas[f][c].setCuralive(false);
                celulas[f][c].setNewalive(false);
            }
        }
       /*celulas[58][50].setCuralive(true);
       celulas[59][50].setCuralive(true);
       celulas[60][50].setCuralive(true);
       celulas[60][51].setCuralive(true);
       celulas[59][52].setCuralive(true);
       celulas[9][10].setCuralive(true);
       celulas[9][11].setCuralive(true);
       celulas[9][12].setCuralive(true);
       celulas[8][14].setCuralive(true);
       celulas[12][24].setCuralive(true);
       celulas[13][24].setCuralive(true);
       celulas[134][24].setCuralive(true);
       celulas[68][69].setCuralive(true);
       celulas[68][70].setCuralive(true);
       celulas[69][70].setCuralive(true);
       celulas[69][69].setCuralive(true);*/
       /*celulas[94][11].setCuralive(true);
       celulas[95][10].setCuralive(true);
       celulas[95][11].setCuralive(true);
       celulas[1][12].setCuralive(true);
       celulas[7][14].setCuralive(true);
       celulas[12][15].setCuralive(true);
       celulas[15][16].setCuralive(true);
       celulas[15][12].setCuralive(true);*/
       /*for(int i=0;i<1000/2;i++){
           for(int t=0;t<1000/2;t++){
               
               int nx=math.random(0,1000);
               int ny=math.random(0,1000);
               //Log.d("barbasman",""+nx);
               if(nx>0&&nx<coords.getCellsPerRow()&&ny>0&&ny<coords.getCellsPerCol()){
                   celulas[nx][ny].setCuralive(true);
               }
               
           }
       }*/
    }
    
    public void updateCells(Coords coords){
       
        for(int f=0;f<coords.getCellsPerRow()-1;f++){
            for(int c=0;c<coords.getCellsPerCol()-1;c++){
                int cont=0;
                if(celulas[f][c+1].getCuralive()==true){
                    cont++;
                }
                if(celulas[f+1][c+1].getCuralive()==true){
                    cont++;
                }
                if(celulas[f+1][c].getCuralive()==true){
                    cont++;
                }
                if(c>=1){
                if(celulas[f+1][c-1].getCuralive()==true){
                    cont++;
                }}
                if(c>=1){
                if(celulas[f][c-1].getCuralive()==true){
                    cont++;
                }}
                if(f>=1&&c>=1){
                if(celulas[f-1][c-1].getCuralive()==true){
                    cont++;
                }}
                if(f>=1){
                if(celulas[f-1][c].getCuralive()==true){
                    cont++;
                }}
                if(f>=1){
                if(celulas[f-1][c+1].getCuralive()==true){
                    cont++;
                }}
                if(celulas[f][c].getCuralive()==false){
                    if(cont==3){
                        celulas[f][c].setNewalive(true);
                    }else{
                        celulas[f][c].setNewalive(false);
                    }

                }
                
                if(celulas[f][c].getCuralive()==true){
                    if(cont==1||cont==0){
                        celulas[f][c].setNewalive(false);
                    }
                    if(cont>=4){
                        celulas[f][c].setNewalive(false);
                    }
                    if(cont==2||cont==3){
                        celulas[f][c].setNewalive(true);
                    }
                    
                }
            }
        }
        for(int f=0;f<1000;f++){
            for(int c=0;c<1000;c++){
                
                //Log.d("barbasman","x"+celulas[f][c].getX());
                celulas[f][c].setCuralive(celulas[f][c].getNewalive());
                
            }
        }
        
    }
    
    
    
    public Celula[][] getCelulas(){
        return celulas;
    }
}
