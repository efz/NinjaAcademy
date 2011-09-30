package com.efzgames.ninjaacademy.elements;

import com.efzgames.framework.gl.SpriteBatcher;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.screens.GameScreen;

public class SwordSlash extends TexturedDrawableGameComponent{

	enum State
    {
        Static,
        Appearing,
        Fading
    }
	
	private final Vector2 textureOrigin = new Vector2(6, 75);
	
	private Vector2 source;
	 
	private State state = State.Appearing;	
	private float fadeDuration = 0.5f;
	private float growthDuration = 0.1f;
	private float timer = 0;
	
	private float desiredScale;
	private float alpha = 1;
	
	private float rotation;
	
	private Vector2 scaleVector = new Vector2(1, 1);
	
	private Vector2 unitY = new Vector2(0,-1);
	
	public synchronized float getStretch(){
		return scaleVector.y;
	}
	
	public synchronized void setStretch(float value){
		scaleVector.y = value;
	}
	
	public SwordSlash(GLGame glGame, GameScreen gameScreen, Texture texture,
			TextureRegion textureRegion) {
		super(glGame, gameScreen, texture, textureRegion);
	}

	@Override
	public synchronized void update(float deltaTime) {
		  timer += deltaTime;

          switch (state)
          {
              case Static:
                  // No update required in this case
                  break;
              case Appearing:
                  // Cause the slash to grow
                  setStretch((float)(desiredScale * timer / growthDuration));

                  if (timer >= growthDuration)
                  {
                      fade(fadeDuration);
                  }
                  break;
              case Fading:
                  // Cause the slash to fade, and ultimately vanish
                  alpha = (float)(1 - timer / fadeDuration);

                  if (timer >= fadeDuration)
                  {
                      isEnabled = false;
                      isVisible = false;
                  }
                  break;
              default:
                  break;
          }
		
	}

	@Override
	public synchronized void present(float deltaTime, SpriteBatcher batcher) {
	    batcher.setAlpha(alpha);   
		batcher.beginBatch(texture);
//		batcher.drawSprite(source.x   , source.y , texture.width, texture.height,
//				rotation* Vector2.TO_DEGREES,textureOrigin.x, textureOrigin.y ,scaleVector.x, scaleVector.y ,  textureRegion);
		
		batcher.drawSprite(source.x   , source.y , texture.width * scaleVector.x, texture.height * scaleVector.y,
				rotation* Vector2.TO_DEGREES,  textureRegion);
		batcher.endBatch();
		batcher.setAlpha(1.0f);
	}
	
	 public synchronized void fade(float fadeDuration)
     {
         timer = 0;

         this.fadeDuration = fadeDuration;

         state = State.Fading;
     }
	
	 public synchronized void reset()
     {
         // Make the slash active and visible
         alpha = 1;
         isEnabled = true;
         isVisible = true;
     }
	 
	 public synchronized void positionSlash(Vector2 source, Vector2 destination)
     {
         state = State.Static;
         this.source = source;

         initializeSlashForCoordinates(source, destination);

         setStretch( desiredScale);
     }
	 
	 public synchronized void initializeSlashForCoordinates(Vector2 source, Vector2 destination)
     {            
         // Find the scale required to properly display the slash
         desiredScale = Vector2.sub(source , destination).len() / getBoundingHeight();

         // Calculate the required rotation for the sword slash (flip the Y as the screen's Y-axis is flipped)
         Vector2 desiredDirectionUnitVector = Vector2.sub(source , destination);
         //desiredDirectionUnitVector.y = -desiredDirectionUnitVector.y;
         desiredDirectionUnitVector.nor();

         rotation = (float)Math.acos(Vector2.dot(desiredDirectionUnitVector, unitY));

         if (desiredDirectionUnitVector.x < 0)
         {
             rotation = -rotation;
         }
     }

}
