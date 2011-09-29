package com.efzgames.ninjaacademy;

import com.efzgames.framework.math.Vector2;

public class GameConstants {
	public static final int viewPortWidth = 800;
	public static final int viewPortHeight = 480;
	
	public static final Vector2 launchAcceleration = new Vector2(0, -500);
	public static final float offScreenYCoordinate = -250;
	
	public static final float swordSlashFadeDuration = 0.150f;
	
	public static final float targetRadius = 28;
	public static final float targetSpeed = 75;
	
	public static final Vector2 upperTargetOrigin = new Vector2(750, 480-200-29);
    public static final Vector2 upperTargetDestination = new Vector2(50, 480-200-29);
    public static final Vector2 middleTargetOrigin = new Vector2(50, 480-273-29);
    public static final Vector2 middleTargetDestination = new Vector2(750, 480-273-29);
    public static final Vector2 lowerTargetOrigin = new Vector2(750, 480-344-29);
    public static final Vector2 lowerTargetDestination = new Vector2(50,480-344-29);
}
