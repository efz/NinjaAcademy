package com.efzgames.ninjaacademy.screens;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.efzgames.framework.Game;
import com.efzgames.ninjaacademy.Assets;
import com.efzgames.ninjaacademy.GameConfiguration;
import com.efzgames.ninjaacademy.elements.GameComponent;
import com.efzgames.ninjaacademy.elements.HitPointsComponent;
import com.efzgames.ninjaacademy.elements.ScoreComponent;
import com.efzgames.ninjaacademy.NinjaAcademy;

public class GameplayScreen  extends GameScreen {
	
	private HitPointsComponent hitPointsComponent;
	private ScoreComponent scoreComponent;
	
	public GameplayScreen(Game game) {
		super(game);
		
		CreateHUDComponents();
	}
	
	@Override
	public void update(float deltaTime) {
	
		for(GameComponent comp: ((NinjaAcademy)game).components){
			if(!comp.IsEnabled)
				continue;
			
			comp.update(deltaTime);
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
		
		hitPointsComponent.present(deltaTime, batcher);
		scoreComponent.present(deltaTime, batcher);
		
		for(GameComponent comp: ((NinjaAcademy)game).components){
			if(!comp.IsVisible)
				continue;
			
			comp.update(deltaTime);
		}
		
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
	
	  /// <summary>
    /// Creates the components used to display the game HUD.
    /// </summary>
    private void CreateHUDComponents()
    {        // Create the component for displaying hit points
        hitPointsComponent = new HitPointsComponent(glGame);        
        hitPointsComponent.totalHitPoints = GameConfiguration.playerLives;
        hitPointsComponent.currentHitPoints = GameConfiguration.playerLives;   
        
        scoreComponent = new ScoreComponent(glGame);
        scoreComponent.score =0;
    }
}
