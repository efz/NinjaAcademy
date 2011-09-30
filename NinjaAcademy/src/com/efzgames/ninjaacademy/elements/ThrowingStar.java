package com.efzgames.ninjaacademy.elements;

import java.util.Random;

import com.efzgames.framework.gl.Animation;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.GameConstants;
import com.efzgames.ninjaacademy.screens.GameScreen;

public class ThrowingStar extends StraightLineScalingComponent {

	 Random random;
	
	public ThrowingStar(GLGame glGame, GameScreen gameScreen,
			Animation animation) {
		
		super(glGame, gameScreen, animation);
		random = new Random();
		
	}
	
	public void throwStar(Vector2 destination)
    {
        isEnabled = true;
        isVisible = true;

        // cause each star thrown to spin differently            
        animation.frameIndex = random.nextInt(animation.frameCount);

        moveAndScale(GameConstants.throwingStarFlightDuration, GameConstants.throwingStarOrigin,
            destination, 1, GameConstants.throwingStarEndScale);
    }

}
