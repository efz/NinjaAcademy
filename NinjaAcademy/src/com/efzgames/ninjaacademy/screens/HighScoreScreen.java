package com.efzgames.ninjaacademy.screens;

import javax.microedition.khronos.opengles.GL10;

import com.efzgames.framework.Game;
import com.efzgames.ninjaacademy.Assets;
import com.efzgames.ninjaacademy.Defines;

public class HighScoreScreen extends GameScreen{
	
	 // High-score screen constants
    public static final int highScorePlaceLeftMargin = 70;
    public static final int highScoreNameLeftMargin = 120;
    public static final int highScoreScoreLeftMargin = 575;
    public static final int highScoreTitleTopMargin = 25;
    public static final int highScoreTopMargin = 100;
    public static final int highScoreVerticalJump = 50;
	
	public HighScoreScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.highscoreScreenBackground);
		batcher.drawSprite(400, 240, 800, 480,
				Assets.highscoreScreenBackgroundRegion);
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.beginBatch(Assets.highscoreTitle);
		batcher.drawSprite(400, 480-25-highScoreTitleTopMargin, 286, 49,
				Assets.highscoreTitleRegion);
		batcher.endBatch();

		
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}

	
}
