package com.efzgames.ninjaacademy;

import android.graphics.Color;
import android.graphics.Typeface;

import com.efzgames.framework.Sound;
import com.efzgames.framework.impl.GLGame;
import com.efzgames.framework.impl.SpriteText;
import com.efzgames.framework.gl.Texture;
import com.efzgames.framework.gl.TextureRegion;

public class Assets {
	public static Texture backgroundActivating;
	public static Texture backgroundBlank;
	public static Texture backgroundGamePlay;	
	public static Texture backgroundInstructions;
	public static Texture backgroundLoading;
	public static Texture backgroundResuming;
	public static Texture backgroundRoom;
	
	public static Texture highscoreScreenBackground;
	public static Texture titleScreenBackground;
	
	public static Texture ninja;
	public static Texture title;
	public static Texture highscoreTitle;
	
	public static TextureRegion titleScreenBackgroundRegion;
	public static TextureRegion highscoreScreenBackgroundRegion;
	public static TextureRegion ninjaRegion;
	public static TextureRegion titleRegion;
	public static TextureRegion highscoreTitleRegion;
	
	public static SpriteText startText;
	public static SpriteText highscoreText;
	public static SpriteText exitText;
	
	public static final int startTextWidth = 140;
	public static final int highscoreTextWidth = 320;
	public static final int exitTextWidth = 100;
	public static final int menuTextHeight = 60;
	
	public static Sound menuSelectionSound;
	
	public static Typeface moireFont;
	
	public static void load(GLGame game) {
//		backgroundActivating = new Texture(game, "Activating.png", true);
//		backgroundBlank = new Texture(game, "blank.png", true);
//		backgroundGamePlay = new Texture(game, "gameplayBG.png", true);
//		backgroundHighScore = new Texture(game, "highscoreBg.png", true);
//		backgroundInstructions = new Texture(game, "Instructions.png", true);
//		backgroundLoading = new Texture(game, "loading.png", true);
//		backgroundResuming = new Texture(game, "Resuming.png", true);
//		backgroundRoom = new Texture(game, "room.png", true);
		titleScreenBackground = new Texture(game, "textures/backgrounds/titlescreenBG.png", false);
		highscoreScreenBackground = new Texture(game, "textures/backgrounds/highscoreBg.png", false);
		ninja = new Texture(game, "textures/ninja.png", false);
		title = new Texture(game, "textures/title.png", false);
		highscoreTitle = new Texture(game, "textures/highscore_title.png", false);
	
		titleScreenBackgroundRegion = new TextureRegion(titleScreenBackground,0,0,800,480);
		highscoreScreenBackgroundRegion = new TextureRegion(highscoreScreenBackground,0,0,800,480);
		ninjaRegion = new TextureRegion(ninja,0,0,308,404);
		titleRegion = new TextureRegion(title,0,0,518,128);
		highscoreTitleRegion = new TextureRegion(highscoreTitle,0,0,286,49);
		
		moireFont = game.getFileIO().readFont("fonts/Moire-Regular.ttf");
		
		startText = new SpriteText(game, "Start", moireFont, 48, Color.WHITE, Color.BLACK, startTextWidth,
					menuTextHeight);
		highscoreText = new SpriteText(game, "High Score", moireFont, 48, Color.WHITE, Color.BLACK , 
				highscoreTextWidth, menuTextHeight);
		exitText = new SpriteText(game, "Exit", moireFont, 48, Color.WHITE, Color.BLACK,  exitTextWidth, 
				menuTextHeight);
		
		menuSelectionSound = game.getAudio().newSound("audios/Menu_Selection.ogg");
	}
	
	public static void reload() {
		titleScreenBackground.reload();
		ninja.reload();
		title.reload();
		
		startText.reload();
		highscoreText.reload();
		exitText.reload();
	}
	
	public static void playSound(Sound sound) {		
			sound.play(1);
	}
	
}
