package com.efzgames.ninjaacademy.screens;

import javax.microedition.khronos.opengles.GL10;

import com.efzgames.ninjaacademy.Assets;
import com.efzgames.framework.gl.Camera2D;
import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.Game;
import com.efzgames.framework.impl.GLScreen;

enum ElementState
{
    Invisible,
    Appearing,
    Visible
}

public class MainMenuScreen extends GLScreen{
	
	Camera2D guiCam;
	SpriteBatcher batcher;
	
	public MainMenuScreen(Game game) {
		super(game);
		
		guiCam = new Camera2D(glGraphics, 800, 480);
		batcher = new SpriteBatcher(glGraphics, 10);
	}
	
	@Override
	public void update(float deltaTime) {
	
	}
	
	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.titleScreenBackground);
		batcher.drawSprite(400, 240, 800, 480, Assets.titleScreenBackgroundRegion);
		batcher.endBatch();
		
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
	
	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
