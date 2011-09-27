package com.efzgames.ninjaacademy.screens;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Color;

import com.efzgames.framework.Game;
import com.efzgames.framework.Input.TouchEvent;
import com.efzgames.framework.impl.SpriteText;
import com.efzgames.ninjaacademy.Assets;

public class CountdownScreen  extends GameScreen {
	
	private float countdownInterval = 1.0f;
    private int countdownValue = 3;
    private float intervalTimer =0;
    
	public CountdownScreen(Game game) {
		super(game);		
	}
	
	@Override
	public void update(float deltaTime) {
		intervalTimer += deltaTime;
		
		if (intervalTimer >= countdownInterval)
        {
            intervalTimer = 0;
            countdownValue--;
        }
		
		if (countdownValue <= 0)
        {
			Assets.playSound(Assets.menuSelectionSound);
			game.setScreen(new MainMenuScreen(game));		
        }
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.gamePlayBackground);
		batcher.drawSprite(400, 240, 800, 480,
				Assets.gamePlayBackgroundRegion);
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.beginBatch(Assets.room);
		batcher.drawSprite(400, 240, 800, 480,
				Assets.roomRegion);
		batcher.endBatch();
		
		SpriteText countdownValueSprite = new SpriteText(glGame, String.valueOf(countdownValue),
				Assets.moireBoldFont,
				60,  Color.WHITE, Color.BLACK, 100, 100);				
		countdownValueSprite.draw(batcher, 400 + countdownValueSprite.getTextWidth()/2,
				240 - countdownValueSprite.getHeight()/2);
	
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}

}
