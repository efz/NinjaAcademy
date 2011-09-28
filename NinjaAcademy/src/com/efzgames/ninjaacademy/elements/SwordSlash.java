package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.screens.GameScreen;

public class SwordSlash extends TexturedDrawableGameComponent{

	enum State
    {
        Static,
        Appearing,
        Fading
    }
	
	private final Vector2 textureOrigin = new Vector2(6, 75);
	
	private State state = State.Appearing;	
	private float fadeDuration = 0.5f;
	private float growthDuration = 0.1f;
	private float timer = 0;
	
	private float desiredScale;
	private float alpha = 1;
	
	public float rotation;
	
	private Vector2 scaleVector = new Vector2(1, 1);
	
	public float getStretch(){
		return scaleVector.y;
	}
	
	public void setStretch(float value){
		scaleVector.y = value;
	}
	
	public SwordSlash(GLGame glGame, GameScreen gameScreen, Texture texture,
			TextureRegion textureRegion) {
		super(glGame, gameScreen, texture, textureRegion);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime, SpriteBatcher batcher) {
		// TODO Auto-generated method stub
		
	}

}
