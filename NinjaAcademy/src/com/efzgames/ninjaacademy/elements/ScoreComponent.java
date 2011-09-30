package com.efzgames.ninjaacademy.elements;

import android.graphics.Color;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.impl.SpriteText;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.Assets;

public class ScoreComponent extends GameComponent{
	
	
	public ScoreComponent(GLGame glGame){
		super(glGame);
	}
	
	public int score;
	public int highscore;
	
	public static final Vector2 ScorePosition = new Vector2(10, 480-15); 
	
	@Override
	public void update(float deltaTime){
		
	}

	@Override
	public void present(float deltaTime, SpriteBatcher batcher) {
		String scoreText = "Score: "+ score +"/"+highscore;
		
		SpriteText scoreSprite = new SpriteText(glGame, scoreText,
				Assets.moireBoldFont,
				36,  Color.WHITE, 550, 60);				
		scoreSprite.draw(batcher, ScorePosition.x + scoreSprite.getTextWidth()/2,
				ScorePosition.y - scoreSprite.getTextHeight()/2);
		scoreSprite.dispose();
	}
}
