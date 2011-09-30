package com.efzgames.ninjaacademy.elements;

import android.graphics.Color;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.impl.SpriteText;
import com.efzgames.ninjaacademy.Assets;
import com.efzgames.ninjaacademy.GameConstants;

public class GameOverComponent extends GameComponent {
	
	private boolean isHighScore = false;
	private int highScore;

	public GameOverComponent(GLGame glGame, boolean isHighScore, int hightScore) {
		super(glGame);
		this.isHighScore = isHighScore;
		this.highScore = hightScore;
	}

	@Override
	public void update(float deltaTime) {
		
		if(isHighScore)
			Assets.playSound(Assets.highScoreSound);
	}

	@Override
	public void present(float deltaTime, SpriteBatcher batcher) {
		SpriteText gameoverSprite = new SpriteText(glGame, "Game Over",
				Assets.moireBoldFont,
				36,  Color.RED, 350, 60);				
		gameoverSprite.draw(batcher, GameConstants.viewPortWidth/2,
				GameConstants.viewPortHeight/2);
		
		if(isHighScore){
			SpriteText congratSprite = new SpriteText(glGame, "New High Score Record!",
					Assets.moireBoldFont,
					36,  Color.WHITE, 400, 60);				
			congratSprite.draw(batcher, GameConstants.viewPortWidth/2,
					GameConstants.viewPortHeight/2  - 100);
			
			SpriteText highScoreSprite = new SpriteText(glGame, String.valueOf(highScore),
					Assets.moireBoldFont,
					36,  Color.RED, 400, 60);				
			highScoreSprite.draw(batcher, GameConstants.viewPortWidth/2,
					GameConstants.viewPortHeight/2  - 200);
		}
		
	}

}
