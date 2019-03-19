package org.barbasman.gameoflife3;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import android.util.Log;
import org.barbasman.gameoflife3.Coords;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame implements ApplicationListener
{
	
    ShapeRenderer shapeRenderer;
    OnscreenControlRenderer onscreencontrols;
    float deltaTime;
    float deltatouch;
    Coords coords=new Coords();
    Cells cells=new Cells();
    float seg=0.0f;
    OrthographicCamera cam;
    Vector2 poscam;
    Colors color;
    Rectangle[][] rcelulas;
    boolean celltouch= false;
    
    
    
	@Override
	public void create()
	{
	
        shapeRenderer = new ShapeRenderer();
        
       
        
        
        deltaTime = Gdx.graphics.getDeltaTime();
        coords.setWidth(Gdx.graphics.getWidth());
        coords.setHeight(Gdx.graphics.getHeight());
        cells.createCells(coords);
        
        cam=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cam.position.set(0.0f,0.0f,0.0f);
        cam.translate(coords.getCentrox(),coords.getCentroy());
        poscam=new Vector2(0,0);
        cam.update();
        onscreencontrols = new OnscreenControlRenderer();
        
        
        rcelulas=new Rectangle[1000][1000];
        for(int fn=0;fn<1000;fn++){
            for(int cn=0;cn<1000;cn++){
                rcelulas[fn][cn]=new Rectangle(0,0,0,0);
            }
        }
        
	}

	@Override
	public void render()
	{    
        deltaTime = Gdx.graphics.getDeltaTime();
        
        
	    Gdx.gl.glClearColor(1,1,1,1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        seg = seg + deltaTime;
        if (seg>=5)
        {
            
            //coords.setSizeCell(coords.getSizeCell()+1);
            
            seg = 0.0f;
        }
        if(onscreencontrols.getEstado()){
        cells.updateCells(coords);
        }
        
        
        
        
       
        poscam.set((int)cam.position.x/coords.getSizeCell(),(int)cam.position.y/coords.getSizeCell());
        int frucellswidth=(coords.getWidth()/coords.getSizeCell())/2;
        int frucellsheight=(coords.getWidth()/coords.getSizeCell())/2;
        int fc=(int)poscam.x-frucellswidth;
        int cc=(int)poscam.y-frucellsheight;
        if(fc<0){fc=0;}
        if(cc<0){cc=0;}
        int maxfc=(int)poscam.x+frucellswidth;
        int maxcc=(int)poscam.y+frucellsheight;
        if(maxfc>1000){maxfc=1000;}
        if(maxcc>1000){maxcc=1000;}
        //Log.d("barbasman","cam"+cam.position+" frucells:"+frucellswidth+" maxfx:"+maxfc+" fc:"+fc);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.setColor(color.get("GRAY"));
        for (int f=fc;f <maxfc;f++)
        {
            for (int c=cc;c < maxcc;c++)
            {
                if (cells.celulas[f][c].getCuralive() == true)
                {
                    shapeRenderer.rect(cells.celulas[f][c].getX() * coords.getSizeCell(), cells.celulas[f][c].getY() * coords.getSizeCell(), coords.getSizeCell(), coords.getSizeCell());
                }
            }
        }
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.setColor(color.get("BLACK"));
        
        Vector2 tpos = new Vector2();
        
        if(Gdx.input.justTouched()&&!onscreencontrols.getTouchbutton()){
           
            tpos.set((cam.position.x-(Gdx.graphics.getWidth()*cam.zoom)/2)+(Gdx.input.getX(0)*cam.zoom),(Gdx.graphics.getHeight()*cam.zoom-Gdx.input.getY(0)*cam.zoom)+cam.position.y-(Gdx.graphics.getHeight()*cam.zoom)/2);
            celltouch=true;
            Log.d("barbasman","cam"+cam.position+" t:"+tpos+" zoom:"+cam.zoom);
        }
       
        if(onscreencontrols.getEstado()){
            for (int f=fc;f < maxfc;f++)
            {
                for (int c=cc;c < maxcc;c++)
                {
                    shapeRenderer.rect(cells.celulas[f][c].getX() * coords.getSizeCell(), cells.celulas[f][c].getY() * coords.getSizeCell(), coords.getSizeCell(), coords.getSizeCell());  
                }
            }
        }else{
            for (int f=fc;f < maxfc;f++)
            {
                for (int c=cc;c < maxcc;c++)
                {
                    shapeRenderer.rect(cells.celulas[f][c].getX() * coords.getSizeCell(), cells.celulas[f][c].getY() * coords.getSizeCell(), coords.getSizeCell(), coords.getSizeCell());  
                    rcelulas[f][c].set(cells.celulas[f][c].getX() * coords.getSizeCell(), cells.celulas[f][c].getY() * coords.getSizeCell(), coords.getSizeCell(), coords.getSizeCell());


                    if(celltouch){
                        if(rcelulas[f][c].contains(tpos)){
                            if(cells.celulas[f][c].getCuralive()==true){
                                cells.celulas[f][c].setCuralive(false);
                            }else{
                                cells.celulas[f][c].setCuralive(true);
                            }
                        }
                    }
                }
            }
        }
        
        shapeRenderer.end();
        
        onscreencontrols.render(cam,coords);
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
        
        cells.createCells(coords);
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
        //coords.setWidth(Gdx.graphics.getWidth());
        //coords.setHeight(Gdx.graphics.getHeight());
        //cells.createCells(coords);
	}
    
    


}
