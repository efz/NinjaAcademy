package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.Animation;
import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.screens.GameScreen;

public class DisappearingAnimationComponent extends AnimatedComponent {

	public DisappearingAnimationComponent(GLGame glGame, GameScreen gameScreen,
			Animation animation) {
		super(glGame, gameScreen, animation);
	}

	@Override
	public synchronized void update(float deltaTime) {		
		super.update(deltaTime);
		 if (!animation.isActive)
         {
             isEnabled = false;
             isVisible = false;
         }
	}

	@Override
	public synchronized void present(float deltaTime, SpriteBatcher batcher) {
		batcher.beginBatch(texture);
		animation.present(deltaTime, batcher, position, 1 , visualCenter);	
		batcher.endBatch();
	}
	
	public synchronized void show(Vector2 position)
    {
        this.position = new Vector2(position);
        animation.playFromFrameIndex(0);
        isEnabled = true;
        isVisible = true;
    }
}
