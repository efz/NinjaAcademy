package com.efzgames.ninjaacademy.screens;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Color;

import com.efzgames.framework.Game;
import com.efzgames.framework.Input.TouchEvent;
import com.efzgames.framework.impl.SpriteText;
import com.efzgames.ninjaacademy.Assets;
import com.efzgames.ninjaacademy.GameConstants;

public class HighScoreScreen extends GameScreen{
	
	 // High-score screen constants
    public static final int highScorePlaceLeftMargin = 70;
    public static final int highScoreNameLeftMargin = 120;
    public static final int highScoreScoreLeftMargin = 575;
    public static final int highScoreTitleTopMargin = 25;
    public static final int highScoreTopMargin = 100;
    public static final int highScoreVerticalJump = 50;
    
    private List<SimpleEntry<String,Integer>> highScore = new ArrayList<SimpleEntry<String,Integer>>();
    
    private static final int highscorePlaces = 7;
	
	public HighScoreScreen(Game game) {
		super(game);	
		loadDefaultHighscores();
		
	}
	
	private void loadDefaultHighscores(){
		highScore.add(new SimpleEntry<String,Integer>("Goku",9001));
		highScore.add(new SimpleEntry<String,Integer>("Ellen",500));
		highScore.add(new SimpleEntry<String,Integer>("Terry",250));
		highScore.add(new SimpleEntry<String,Integer>("Dave",100));
		highScore.add(new SimpleEntry<String,Integer>("Biff",50));
		highScore.add(new SimpleEntry<String,Integer>("Michael",20));
		highScore.add(new SimpleEntry<String,Integer>("Dan Hibiki",10));
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

		for (int i=0; i< highScore.size(); i++ ) {
			SimpleEntry<String,Integer> temp = highScore.get(i);
			if(temp.getKey() != null && !temp.getKey().isEmpty()){
				
				// Draw place number
				int positionX = highScorePlaceLeftMargin;
				int positionY = 480 - (i*highScoreVerticalJump + highScoreTopMargin);
								
				String placeString = (i+1)+".";
				SpriteText placeStringSprite = new SpriteText(glGame, placeString, Assets.moireFont,
						36,  Color.WHITE, Color.BLACK, 50, 60);				
				placeStringSprite.draw(batcher, positionX + placeStringSprite.getTextWidth()/2,
						positionY - placeStringSprite.getHeight()/2);
				
				// Draw Name
				positionX = highScoreNameLeftMargin;
				SpriteText nameSprite = new SpriteText(glGame, temp.getKey(), Assets.moireFont,
						36,  Color.WHITE, Color.BLACK, temp.getKey().length()*20, 60 );
				nameSprite.draw(batcher, positionX + nameSprite.getTextWidth()/2,
						positionY - nameSprite.getHeight()/2);
				
				// Draw score
				positionX = highScoreScoreLeftMargin;
				SpriteText scoreSprite = new SpriteText(glGame, temp.getValue().toString(), Assets.moireFont,
						36,  Color.WHITE, Color.BLACK, temp.getKey().length()*20, 60 );
				scoreSprite.draw(batcher, positionX + scoreSprite.getTextWidth()/2,
						positionY - scoreSprite.getHeight()/2);
			}				
		}
		
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
	}

	
}
