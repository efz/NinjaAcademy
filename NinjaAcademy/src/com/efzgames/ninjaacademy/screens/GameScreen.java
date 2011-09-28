package com.efzgames.ninjaacademy.screens;

import com.efzgames.framework.Game;
import com.efzgames.framework.gl.Camera2D;
import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLScreen;
import com.efzgames.ninjaacademy.GameConstants;



public abstract class GameScreen extends GLScreen{
	Camera2D guiCam;
	SpriteBatcher batcher;
	
	public GameScreen(Game game) {
		super(game);

		guiCam = new Camera2D(glGraphics, GameConstants.viewPortWidth,
				GameConstants.viewPortHeight);
		batcher = new SpriteBatcher(glGraphics, 10);
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	 
}
