package com.efzgames.framework.gl;

import com.efzgames.framework.math.Vector2;

import android.graphics.Point;


public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;
    
    final TextureRegion[] keyFrames;
    float frameChangeInterval;
    float frameChangeTimer;
    
    private Point frameSize;
    public Texture animationSheet;
    public int frameCount;
    
    private int frameIndex;
    
    public int getFrameWidth(){
    	return frameSize.x;
    }
    
    public int getFrameHeight(){
    	return frameSize.y;
    }
    
    public Vector2 visualCenter;
    public boolean isActive;
    public boolean isCyclic;
    
    
    public Animation(Texture animationSheet, Point frameDimensions, int frameCount, Vector2 visualCenter, boolean isCyclic,
    		float frameChangeInterval){
  
    	this.animationSheet = animationSheet;
    	
    	isActive = true;
    	frameSize = frameDimensions;
    	this.visualCenter = visualCenter;
    	this.frameCount = frameCount;
    	this.isCyclic = isCyclic;
    	this.frameChangeInterval = frameChangeInterval;
    	
    	keyFrames = new TextureRegion[frameCount];
    	
    	for(int i=0; i< frameCount; i++){
    		TextureRegion region = new TextureRegion(animationSheet,frameDimensions.x * i, 0,
    				frameDimensions.x, frameDimensions.y);
    		keyFrames[i] = region;
    	}
    }    

    
    public void update(float deltaTime) {
    	 if (isActive)
         {
    		 frameChangeTimer += deltaTime;
    		 // See if it is time to advance to the next frame
             if (frameChangeInterval > 0 && frameChangeTimer >= frameChangeInterval)
             {
                 frameChangeTimer = 0;              

                 frameIndex++;
                 if (frameIndex >= frameCount)
                 {
                     if (isCyclic)
                     {
                         frameIndex = 0; // Reset the animation
                     }
                     else
                     {
                         isActive = false;
                         frameIndex = frameCount - 1;
                     }
                 }
             }
         }		
	}
    

	public void present(float deltaTime, SpriteBatcher batcher , Vector2 position,  float rotation, Vector2 origin) {
	
		batcher.drawSprite(position.x +frameSize.x/2, position.y+frameSize.y/2, frameSize.x, frameSize.y, 
				rotation* Vector2.TO_DEGREES, keyFrames[frameIndex]);
	}
    
    public void playFromFrameIndex(int frameIndex){
        this.frameIndex = frameIndex;
        isActive = true;
    }
    
    public void SetFrameInterval(float interval){
    	frameChangeInterval = interval;
    }
}
