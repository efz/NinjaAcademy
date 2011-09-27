package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.Assets;

public class HitPointsComponent extends GameComponent{

	public int totalHitPoints;
	public int currentHitPoints;

	private static final Vector2 hitPointsOrigin = new Vector2(740, 480 - 15);
	public static final float hitPointsSpace = 5;
	
	public HitPointsComponent(GLGame glGame){
		super(glGame);
	}
	
	@Override
	public void update(float deltaTime){
		
	}

	@Override
	public void present(float deltaTime, SpriteBatcher batcher) {
		int missingHP = totalHitPoints - currentHitPoints;
		float drawingPositionX = hitPointsOrigin.x;
		float drawingPositionY = hitPointsOrigin.y;

		if (missingHP > 0) {
			batcher.beginBatch(Assets.emptyHeart);
			for (int i = 0; i < missingHP; i++) {
				batcher.drawSprite(drawingPositionX, drawingPositionY- Assets.emptyHeart.height/2,
						Assets.emptyHeart.width, Assets.emptyHeart.height,
						Assets.emptyHeartRegion);
				drawingPositionX -= (Assets.emptyHeart.width + hitPointsSpace);
			}
			batcher.endBatch();
		}

		if (currentHitPoints > 0) {
			batcher.beginBatch(Assets.fullHeart);
			for (int i = 0; i < currentHitPoints; i++) {
				batcher.drawSprite(drawingPositionX, drawingPositionY- Assets.fullHeart.height/2,
						Assets.fullHeart.width, Assets.fullHeart.height,
						Assets.fullHeartRegion);
				drawingPositionX -= (Assets.fullHeart.width + hitPointsSpace);
			}
			batcher.endBatch();
		}
	}
}
