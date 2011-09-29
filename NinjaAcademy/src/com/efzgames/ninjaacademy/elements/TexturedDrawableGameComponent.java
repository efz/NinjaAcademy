package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.screens.GameScreen;

public abstract class TexturedDrawableGameComponent extends GameComponent {
	
	 protected Vector2 halfTextureDimensions;

     protected SpriteBatcher spriteBatch;
     protected Texture texture;  
     protected TextureRegion textureRegion; 
     protected GameScreen gameScreen;
     
     public Vector2 position;
     
     public Vector2 visualCenter;
     
     public Vector2 getBoundingBoxMin(){
    	 return new Vector2( position.x , position.y);
     }
     
     public Vector2 getBoundingBoxMax(){
    	 return new Vector2( position.x +texture.width, position.y + texture.height);
     }
     
     public float getBoundingHeight(){
    	 return texture.height;
     }
     
     public float getBoundingWidth(){
    	 return texture.width;
     }
	
	public TexturedDrawableGameComponent(GLGame glGame, GameScreen gameScreen, Texture texture, TextureRegion textureRegion) {
		super(glGame);
		
		this.gameScreen = gameScreen;
		this.texture = texture;
		this.textureRegion = textureRegion;
		
		halfTextureDimensions = new Vector2(texture.width/2, texture.height/2);
		
		visualCenter = halfTextureDimensions;
	}	
}
