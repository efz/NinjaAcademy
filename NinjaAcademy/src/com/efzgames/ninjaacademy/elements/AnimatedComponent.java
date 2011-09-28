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
    
    public float getWidth(){
    	return animation.getFrameWidth();
    }
    
    public float getHeight(){
    	return animation.getFrameHeight();
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
	public void update(float deltaTime) {	
		//if(animation != null)
			animation.update(deltaTime);
	}

	@Override
	public void present(float deltaTime, SpriteBatcher batcher) {
	
		
	}
	
	public void resetAnimation(){
        animation.playFromFrameIndex(0);
    }
	
	 public void pause(){
         animation.isActive = false;
     }
	 
	 public void resume(){
         animation.isActive = true;
     }

}
