package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;



public abstract class GameComponent {	
	
	public boolean isEnabled = false;
	public boolean isVisible = false;
	
	public Vector2 position;
	
	protected GLGame glGame;
	
	public GameComponent(GLGame glGame){
		this.glGame = glGame;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime, SpriteBatcher batcher);
}
