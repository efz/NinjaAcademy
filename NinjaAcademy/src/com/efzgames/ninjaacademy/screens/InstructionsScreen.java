package com.efzgames.ninjaacademy.screens;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Color;

import com.efzgames.framework.Game;
import com.efzgames.framework.Input.TouchEvent;
import com.efzgames.framework.impl.SpriteText;
import com.efzgames.ninjaacademy.Assets;

public class InstructionsScreen  extends GameScreen {
	
	public InstructionsScreen(Game game) {
		super(game);		
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> events = game.getInput().getTouchEvents();
		int len = events.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = events.get(i);
			if (event.type == TouchEvent.TOUCH_UP){
				Assets.playSound(Assets.menuSelectionSound);
				game.setScreen(new MainMenuScreen(game));
				break;
			}
		}
		
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.instructionsScreenBackground);
		batcher.drawSprite(400, 240, 800, 480,
				Assets.instructionsScreenBackgroundRegion);
		batcher.endBatch();
	
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}

}
