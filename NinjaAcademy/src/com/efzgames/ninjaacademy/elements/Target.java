package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.Animation;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Sphere;
import com.efzgames.framework.math.Vector2;
import com.efzgames.framework.math.Vector3;
import com.efzgames.ninjaacademy.GameConstants;
import com.efzgames.ninjaacademy.screens.GameScreen;



public class Target extends StraightLineMovementComponent{
	
	
	
	public TargetPosition designation;
	
	public boolean isGolden;

	public Target(GLGame glGame, GameScreen gameScreen, Animation animation) {
		super(glGame, gameScreen, animation);
	}
	
	public Target(GLGame glGame, GameScreen gameScreen,
			Texture texture, TextureRegion textureRegion) {
		super(glGame, gameScreen, texture, textureRegion);
	}
	
    public boolean checkHit(Vector2 hitLocation)
    {
        Sphere exactBounds = new Sphere(new Vector3(position.x, position.y, 0), GameConstants.targetRadius);

        return exactBounds.contains(hitLocation);
    }

}
