package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLGame;

public abstract class GameComponent {	
	
	public boolean IsEnabled = false;
	public boolean IsVisible = false;
	
	protected GLGame glGame;
	
	public GameComponent(GLGame glGame){
		this.glGame = glGame;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime, SpriteBatcher batcher);
}
