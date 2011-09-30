package com.efzgames.ninjaacademy;

import com.efzgames.framework.math.Vector2;

public class GameConstants {
	public static final int viewPortWidth = 800;
	public static final int viewPortHeight = 480;

	public static final Vector2 launchAcceleration = new Vector2(0, -500);
	public static final Vector2 fallingTargetLaunchAcceleration = new Vector2(0, -50);
	public static final float offScreenYCoordinate = -250;

	public static final float swordSlashFadeDuration = 0.150f;

	public static final float targetRadius = 100;
	public static final float targetSpeed = 75;

	public static final Vector2 upperTargetAreaTopLeft = new Vector2(208, 480- 169);
	public static final Vector2 middleTargetAreaTopLeft = new Vector2(208,  480- 247);
	public static final Vector2 lowerTargetAreaTopLeft = new Vector2(208,  480- 320);

	public static final Vector2 upperTargetAreaBottomRight = new Vector2(591,  480- 226);
	public static final Vector2 middleTargetAreaBottomRight = new Vector2(591,	 480-298);
	public static final Vector2 lowerTargetAreaBottomRight = new Vector2(591,  480-370);

	public static final Vector2 upperTargetOrigin = new Vector2(750,
			480 - 200 - 29);
	public static final Vector2 upperTargetDestination = new Vector2(50,
			480 - 200 - 29);
	public static final Vector2 middleTargetOrigin = new Vector2(50,
			480 - 273 - 29);
	public static final Vector2 middleTargetDestination = new Vector2(750,
			480 - 273 - 29);
	public static final Vector2 lowerTargetOrigin = new Vector2(750,
			480 - 344 - 29);
	public static final Vector2 lowerTargetDestination = new Vector2(50,
			480 - 344 - 29);

	public static final Vector2 throwingStarOrigin = new Vector2(400, 480 - 510);
	public static final float throwingStarFlightDuration = 0.250f;
	public static float throwingStarEndScale = 0.25f;
}
