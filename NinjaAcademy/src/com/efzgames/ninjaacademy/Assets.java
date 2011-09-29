package com.efzgames.ninjaacademy;

import android.graphics.Color;
import android.graphics.Typeface;

import com.efzgames.framework.Music;
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
	public static Texture instructionsScreenBackground;
	public static Texture gamePlayBackground;
	
	public static Texture ninja;
	public static Texture title;
	public static Texture highscoreTitle;
	public static Texture room;
	
	public static TextureRegion titleScreenBackgroundRegion;
	public static TextureRegion highscoreScreenBackgroundRegion;
	public static TextureRegion instructionsScreenBackgroundRegion;
	public static TextureRegion gamePlayBackgroundRegion;
	
	public static TextureRegion ninjaRegion;
	public static TextureRegion titleRegion;
	public static TextureRegion highscoreTitleRegion;
	public static TextureRegion roomRegion;
	
	public static SpriteText startText;
	public static SpriteText highscoreText;
	public static SpriteText exitText;
	
	public static final int startTextWidth = 140;
	public static final int highscoreTextWidth = 320;
	public static final int exitTextWidth = 100;
	public static final int menuTextHeight = 60;
	
	public static Sound menuSelectionSound;
	public static Sound fuseSound;
	public static Sound swordSlashSound;
	public static Sound bambooSliceSound;
	public static Music ninjaAcademyMusic;
	
	public static Typeface moireFont;
	public static Typeface moireBoldFont;
	
	// Game Elements
	public static Texture emptyHeart;
	public static Texture fullHeart;
	public static TextureRegion emptyHeartRegion;
	public static TextureRegion fullHeartRegion;
	public static Texture bamboo;
	public static TextureRegion bambooRegion;
	public static Texture dynamite;
	public static TextureRegion dynamiteRegion;
	public static Texture slice;
	public static TextureRegion sliceRegion;
	public static Texture bottomSlice;
	public static Texture topSlice;
	public static Texture leftSlice;
	public static Texture rightSlice;
	public static TextureRegion bottomSliceRegion;
	public static TextureRegion topSliceRegion;
	public static TextureRegion leftSliceRegion;
	public static TextureRegion rightSliceRegione;
	
	public static void load(GLGame game) {

		titleScreenBackground = new Texture(game, "textures/backgrounds/titlescreenBG.png", false);
		highscoreScreenBackground = new Texture(game, "textures/backgrounds/highscoreBg.png", false);
		instructionsScreenBackground = new Texture(game, "textures/backgrounds/Instructions.png", false);
		gamePlayBackground = new Texture(game, "textures/backgrounds/gameplayBG.png", false);
		ninja = new Texture(game, "textures/ninja.png", false);
		title = new Texture(game, "textures/title.png", false);
		highscoreTitle = new Texture(game, "textures/highscore_title.png", false);
		room = new Texture(game, "textures/backgrounds/room.png", false);
	
		titleScreenBackgroundRegion = new TextureRegion(titleScreenBackground,0,0,800,480);
		highscoreScreenBackgroundRegion = new TextureRegion(highscoreScreenBackground,0,0,800,480);
		instructionsScreenBackgroundRegion = new TextureRegion(highscoreScreenBackground,0,0,800,480);
		gamePlayBackgroundRegion = new TextureRegion(gamePlayBackground,0,0,800,480);
		ninjaRegion = new TextureRegion(ninja,0,0,308,404);
		titleRegion = new TextureRegion(title,0,0,518,128);
		highscoreTitleRegion = new TextureRegion(highscoreTitle,0,0,286,49);
		roomRegion = new TextureRegion(room,0,0,800,480);
		
		moireFont = game.getFileIO().readFont("fonts/Moire-Regular.ttf");
		moireBoldFont = game.getFileIO().readFont("fonts/Moire-Bold.ttf");
		
		startText = new SpriteText(game, "Start", moireFont, 48, Color.WHITE, Color.BLACK, startTextWidth,
					menuTextHeight);
		highscoreText = new SpriteText(game, "High Score", moireFont, 48, Color.WHITE, Color.BLACK , 
				highscoreTextWidth, menuTextHeight);
		exitText = new SpriteText(game, "Exit", moireFont, 48, Color.WHITE, Color.BLACK,  exitTextWidth, 
				menuTextHeight);
		
		swordSlashSound = game.getAudio().newSound("audios/Sword_Slash.ogg");
		fuseSound = game.getAudio().newSound("audios/Fuse.ogg");
		menuSelectionSound = game.getAudio().newSound("audios/Menu_Selection.ogg");
		bambooSliceSound = game.getAudio().newSound("audios/Bamboo_Slice.ogg");
		ninjaAcademyMusic = game.getAudio().newMusic("audios/NinjAcademy_Music.ogg");
		Assets.ninjaAcademyMusic.setLooping(true);
		
		// Game Elements
		emptyHeart = new Texture(game, "textures/elements/emptyHeart.png", false);
		fullHeart = new Texture(game, "textures/elements/heart.png", false);
		emptyHeartRegion = new TextureRegion(emptyHeart,0,0,49,39);
		fullHeartRegion = new TextureRegion(fullHeart,0,0,49,39);
		bamboo = new Texture(game, "textures/elements/bamboo.png", false);
		bambooRegion = new TextureRegion(bamboo,0,0,41,184);
		dynamite = new Texture(game, "textures/elements/dynamite.png", false);
		dynamiteRegion = new TextureRegion(dynamite,0,0,54,117);
		slice = new Texture(game, "textures/elements/slice.png", false);
		sliceRegion = new TextureRegion(slice,0,0,14,81);
		bottomSlice = new Texture(game, "textures/elements/bottomSliceHorizontal.png", false);
		topSlice = new Texture(game, "textures/elements/topSliceHorizontal.png", false);
		leftSlice = new Texture(game, "textures/elements/leftSliceVertical.png", false);
		rightSlice = new Texture(game, "textures/elements/rightSliceVertical.png", false);
		bottomSliceRegion = new TextureRegion(slice,0,0,41,92);
		topSliceRegion = new TextureRegion(slice,0,0,41,92);
		leftSliceRegion = new TextureRegion(slice,0,0,20,184);
		rightSliceRegione = new TextureRegion(slice,0,0,20,184);
	}
	
	public static void reload() {
		titleScreenBackground.reload();
		highscoreScreenBackground.reload();
		instructionsScreenBackground.reload();
		gamePlayBackground.reload();
		ninja.reload();
		title.reload();
		room.reload();
		
		startText.reload();
		highscoreText.reload();
		exitText.reload();
		highscoreTitle.reload();
		
		// Game Elements
		emptyHeart.reload();
		fullHeart.reload();
		bamboo.reload();
		dynamite.reload();
		slice.reload();
		bottomSlice.reload();
		topSlice.reload();
		leftSlice.reload();
		rightSlice.reload();
	}
	
	public static void playSound(Sound sound) {		
			sound.play(1);
	}
	
}
