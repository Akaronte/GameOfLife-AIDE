package org.barbasman.gameoflife3;
import android.util.Log;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class OnscreenControlRenderer
{
    SpriteBatch batch;
    TextureRegion down;
    TextureRegion left;
    TextureRegion right;
	TextureRegion up;
    TextureRegion zoomup;
    TextureRegion zoomdown;
    float ancho;
    float alto;
    float butancho;
    float butalto;
    float deltaTime;
    float deltatouch;
    
    boolean touchbutton;
    
    Rectangle rleft;
    Rectangle rright;
    Rectangle rup;
    Rectangle rdown;
    
    Rectangle rzoomup;
    Rectangle rzoomdown;
    
    Rectangle rpresplay;
    
    float distmove=0;
    Paint paint;
    Colors color;
    Font font;
    Texture fonttexture;
    public enum ESTADO {vivos,pausados};
    ESTADO estado;
    
    public OnscreenControlRenderer () {
        
        ancho=Gdx.graphics.getWidth();
        alto=Gdx.graphics.getHeight();
        distmove=ancho/16;
        
        touchbutton=false;
        
        if(ancho>alto){
        butancho=ancho/12;
        butalto=ancho/12;
        }else{
            butancho=alto/12;
            butalto=alto/12;
        }
        
        rleft = new Rectangle(ancho-butancho, alto/2-butalto/2,butancho,butalto);
        rright = new Rectangle(0, alto/2-butalto/2,butancho,butalto);
        rup = new Rectangle (ancho/2-butancho/2, alto-butalto,butancho,butalto);
        rdown = new Rectangle(ancho/2-butancho/2, 0,butancho,butalto);
        rzoomup = new Rectangle(0, 0,butancho,butalto);
        rzoomdown = new Rectangle(ancho-butancho, 0,butancho,butalto);
        rpresplay = new Rectangle(ancho-butancho*2 , 0,butancho,butalto);
        
        loadAssets();
        paint=new Paint();
        estado=ESTADO.pausados;
	}
    
    private void loadAssets(){
        Texture texture = new Texture(Gdx.files.internal("CustomControls.png"));
        
        
        //Typeface font=new Typeface(Gdx.files.internal("font.ttf"));
        left = new TextureRegion(texture, 0, 0, 256, 256);
        right = new TextureRegion(texture, 256, 0, 256, 256);
        up = new TextureRegion(texture, 256, 256, 256, 256);
        down  = new TextureRegion(texture, 0, 256, 256, 256);
       
        batch=new  SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    public void render (OrthographicCamera cam,Coords coords) {
        
            batch.begin();
            
            batch.draw(left, ancho-butancho, alto/2-butalto/2,butancho,butalto);
            batch.draw(right, 0, alto/2-butalto/2,butancho,butalto);
            batch.draw(up, ancho/2-butancho/2, alto-butalto,butancho,butalto);
            batch.draw(down, ancho/2-butancho/2, 0,butancho,butalto);
            
            batch.draw(up, 0, alto-butalto,butancho,butalto);
            batch.draw(down, ancho-butancho, alto-butalto,butancho,butalto);
            switch(estado){
                case vivos:
                    batch.draw(up,ancho-butancho*2 , alto-butalto,butancho,butalto);
                    break;
                case pausados:
                    batch.draw(left,ancho-butancho*2 , alto-butalto,butancho,butalto);
                    break;
            }
            batch.end();
            
            
            
            processKeys(cam,coords);
        
        String text=""+(int)cam.position.x/coords.getSizeCell()+"/"+(int)cam.position.y/coords.getSizeCell();
        fontRender(text,butancho,alto-butalto/2);
            
      
        
	}
    
    private void processKeys (OrthographicCamera cam, Coords coords) {
        touchbutton=false;
        //Log.d("barbasman","t"+Gdx.input.isTouched(0)+" x:"+Gdx.input.getX(0)+" y:"+Gdx.input.getY(0));
        boolean moveleft = (Gdx.input.isTouched(0) && rleft.contains(Gdx.input.getX(0),Gdx.input.getY(0))||
            Gdx.input.isTouched(1) && rleft.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(moveleft){cam.translate(distmove,0.0f);cam.update();touchbutton=true;} 
        boolean moveright = (Gdx.input.isTouched(0) && rright.contains(Gdx.input.getX(0),Gdx.input.getY(0))||
            Gdx.input.isTouched(1) && rright.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(moveright){cam.translate(-distmove,0.0f);cam.update();touchbutton=true;} 
        boolean moveup = (Gdx.input.isTouched(0) && rup.contains(Gdx.input.getX(0),Gdx.input.getY(0))||
            Gdx.input.isTouched(1) && rup.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(moveup){cam.translate(0.0f,-distmove);cam.update();touchbutton=true;}  
        boolean movedown = (Gdx.input.isTouched(0) && rdown.contains(Gdx.input.getX(0),Gdx.input.getY(0))||
            Gdx.input.isTouched(1) && rdown.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(movedown){cam.translate(0.0f,distmove);cam.update();touchbutton=true;} 
        boolean movezoomup = (Gdx.input.isTouched(0) && rzoomup.contains(Gdx.input.getX(0),Gdx.input.getY(0))||
            Gdx.input.isTouched(1) && rzoomup.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(movezoomup){
            
            if(cam.zoom>0.1){
            cam.zoom=cam.zoom-0.1f;
            }
            //Log.d("barbasman","zoomup"+cam.zoom);
            cam.update();
            touchbutton=true;
        } 
        boolean movezoomdown = (Gdx.input.isTouched(0) && rzoomdown.contains(Gdx.input.getX(0),Gdx.input.getY(0))||
            Gdx.input.isTouched(1) && rzoomdown.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(movezoomdown){
            if(cam.zoom<=1.6){
            cam.zoom=cam.zoom+0.1f;
            }
            //Log.d("barbasman","zoomdown"+cam.zoom);
            cam.update();
            touchbutton=true;
        }
        deltaTime = Gdx.graphics.getDeltaTime();
        deltatouch=deltatouch+deltaTime;
        
        boolean presplay = (Gdx.input.justTouched() && rpresplay.contains(Gdx.input.getX(0),Gdx.input.getY(0)));
        //||Gdx.input.isTouched(1) && rpresplay.contains(Gdx.input.getX(1),Gdx.input.getY(1)));
        if(presplay){Log.d("barbasman","prespla1!");touchbutton=true;}
        if(presplay){
            switch(estado){
                case vivos:
                    estado=ESTADO.pausados;
                break;
                case pausados:
                    estado=ESTADO.vivos;
                break;
            }
        }
       
    }
    
    public void fontRender(String text,float posx,float posy){
        Texture fonttexture = new Texture(Gdx.files.internal("FontText.png"));
        batch.begin();
        font=new Font(fonttexture,0,0,16,32,32);
        font.drawText(batch,text,posx,posy);
        batch.end();
    }
    
    public boolean getEstado(){
        boolean  run = false;
        switch(estado){
            case vivos:
                run=true;
                break;
            case pausados:
                run=false;
                break;
        }
        return run;
    }
    
    public boolean getTouchbutton(){
        return touchbutton;
    }
}

