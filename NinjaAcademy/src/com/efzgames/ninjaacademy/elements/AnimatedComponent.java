package com.efzgames.ninjaacademy.elements;

import android.graphics.Point;

import com.efzgames.framework.Game;
import com.efzgames.framework.gl.Animation;
import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.screens.GameScreen;

public class AnimatedComponent extends TexturedDrawableGameComponent{
	
	private Vector2 halfFrameSize;

    protected Animation animation;
    
    public synchronized float getWidth(){
    	return animation.getFrameWidth();
    }
    
    public synchronized float getHeight(){
    	return animation.getFrameHeight();
    }
    
    @Override
    public synchronized Vector2 getBoundingBoxMin(){
   	 return new Vector2( position.x , position.y);
    }
    
    @Override
    public synchronized Vector2 getBoundingBoxMax(){
   	 return new Vector2( position.x +animation.getFrameWidth(), position.y + animation.getFrameHeight());
    }
    
    @Override
    public synchronized float getBoundingHeight(){
   	 return animation.getFrameHeight();
    }
    
    @Override
    public synchronized float getBoundingWidth(){
   	 return animation.getFrameWidth();
    }
    
    public AnimatedComponent(GLGame glGame, GameScreen gameScreen, Animation animation){
    	super(glGame, gameScreen, animation.animationSheet, null);
        this.animation = animation;
        visualCenter = animation.visualCenter;
        halfFrameSize = new Vector2(animation.getFrameWidth()/2f, animation.getFrameHeight()/2f) ;
    }

	public AnimatedComponent(GLGame glGame, GameScreen gameScreen,
			Texture texture, TextureRegion textureRegion) {
		
		this(glGame, gameScreen, new Animation(texture, new Point(texture.width, texture.height), 1, 
                new Vector2(texture.width/2f, texture.height/2f) , false, 0));
	}

	@Override
	public synchronized void update(float deltaTime) {	
			animation.update(deltaTime);
	}

	@Override
	public synchronized void present(float deltaTime, SpriteBatcher batcher) {
	
		
	}
	
	public synchronized void resetAnimation(){
        animation.playFromFrameIndex(0);
    }
	
	 public synchronized void pause(){
         animation.isActive = false;
     }
	 
	 public synchronized void resume(){
         animation.isActive = true;
     }

}
