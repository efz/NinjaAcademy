package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.screens.GameScreen;

public class StaticTextureComponent extends TexturedDrawableGameComponent {

	public StaticTextureComponent(GLGame glGame, GameScreen gameScreen,
			Texture texture, TextureRegion textureRegion, Vector2 position) {
		super(glGame, gameScreen, texture, textureRegion);
		this.position = position;
	}

	@Override
	public void update(float deltaTime) {		
		
	}

	@Override
	public void present(float deltaTime, SpriteBatcher batcher) {
		batcher.beginBatch(texture);
		batcher.drawSprite(position.x +texture.width/2   , position.y + texture.height/2 , texture.width , texture.height , textureRegion);
		batcher.endBatch();
		
	}

}
