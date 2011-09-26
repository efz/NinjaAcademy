package com.efzgames.ninjaacademy.screens;

import javax.microedition.khronos.opengles.GL10;

import com.efzgames.ninjaacademy.Assets;
import com.efzgames.framework.gl.Camera2D;
import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.Game;
import com.efzgames.framework.impl.GLScreen;
import com.efzgames.framework.math.Vector2;

enum ElementState
{
    Invisible,
    Appearing,
    Visible
}

public class MainMenuScreen extends GLScreen{
	
	Camera2D guiCam;
	SpriteBatcher batcher;
	
	private ElementState ninjaState = ElementState.Invisible;
    private ElementState titleState = ElementState.Invisible;
    
    private float ninjaAppearDelay = 3.0f;
    private float titleAppearDelay = 2.0f;
    private float ninjaAppearDuration = 0.5f;
    private float titleAppearDuration = 0.5f;
    
    private float ninjaTimer = 0.0f;
    private float titleTimer = 0.0f;
    
    private Vector2 ninjaPosition = new Vector2(184, 238);
    private Vector2 titlePosition = new Vector2(524, 396);

    private Vector2 ninjaInitialOffset = new Vector2(-350, 228);
    private Vector2 titleInitialOffset = new Vector2(259, 696);

    private Vector2 ninjaOffset;
    private Vector2 titleOffset; 
    
    static final int menuEntryPadding = 5;
	
	public MainMenuScreen(Game game) {
		super(game);
		
		guiCam = new Camera2D(glGraphics, 800, 480);
		batcher = new SpriteBatcher(glGraphics, 10);
		
		ninjaOffset = ninjaInitialOffset;
        titleOffset = titleInitialOffset;
	}
	
	@Override
	public void update(float deltaTime) {
	
		// Cause the ninja and title text to appear gradually
        ninjaTimer += deltaTime;
        titleTimer += deltaTime;
        
        switch (ninjaState)
        {
            case Invisible:
                if (ninjaTimer >= ninjaAppearDelay)
                {
                    ninjaState = ElementState.Appearing;
                    ninjaTimer = 0.0f;
                }
                break;
            case Appearing:
                ninjaOffset = Vector2.mul(ninjaInitialOffset ,
                    (float)Math.pow(1 - ninjaTimer / ninjaAppearDuration, 2));

                if (ninjaTimer > ninjaAppearDuration)
                {
                    ninjaState = ElementState.Visible;
                }
                break;
            case Visible:
                // Nothing to do in this state
                break;
            default:
                break;
        }
        
        switch (titleState)
        {
            case Invisible:
                if (titleTimer >= titleAppearDelay)
                {
                    titleState = ElementState.Appearing;
                    titleTimer = 0.0f;
                }
                break;
            case Appearing:
                titleOffset = Vector2.mul(titleInitialOffset ,
                    (float)Math.pow(1 - titleTimer / titleAppearDuration, 2));

                if (titleTimer > titleAppearDuration)
                {
                    titleState = ElementState.Visible;
                }
                break;
            case Visible:
                // Nothing to do in this state
                break;
            default:
                break;
        }
	}
	
	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.titleScreenBackground);
		batcher.drawSprite(400, 240, 800, 480, Assets.titleScreenBackgroundRegion);
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
				
		
		batcher.beginBatch(Assets.title);
		batcher.drawSprite(titlePosition.x + titleOffset.x, titlePosition.y + titleOffset.y,
				518, 128, Assets.titleRegion);
		batcher.endBatch();
		
		batcher.beginBatch(Assets.ninja);
		batcher.drawSprite(ninjaPosition.x + ninjaOffset.x,  ninjaPosition.y + ninjaOffset.y, 
				308, 404, Assets.ninjaRegion);
		batcher.endBatch();
		
		float PositionY = 200.0f;	
		float PositionX = 520;
		Assets.startText.draw(batcher, PositionX, PositionY);
		PositionY -= Assets.startText.getHeight() + menuEntryPadding;
		
		PositionX = 520 ;
		Assets.highscoreText.draw(batcher, PositionX, PositionY);
		PositionY -= Assets.highscoreText.getHeight() + menuEntryPadding;
		
		PositionX = 520;
		Assets.exitText.draw(batcher, PositionX, PositionY);
		
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
	
	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
