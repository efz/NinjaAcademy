package com.efzgames.ninjaacademy;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.efzgames.ninjaacademy.Assets;
import com.efzgames.ninjaacademy.elements.GameComponent;
import com.efzgames.ninjaacademy.screens.MainMenuScreen;
import com.efzgames.framework.Screen;
import com.efzgames.framework.impl.GLGame;

public class NinjaAcademy extends GLGame{
	
	public List<GameComponent> components = new ArrayList<GameComponent>();
	
	boolean firstTimeCreate = true;
	
	@Override
	public Screen getStartScreen() {
		return new MainMenuScreen(this);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate) {		
			Assets.load(this);
			firstTimeCreate = false;
		} else {
			Assets.reload();
			
		}
		Assets.ninjaAcademyMusic.play();
	}

	@Override
	public void onPause() {
		super.onPause();	
		Assets.ninjaAcademyMusic.stop();
	}
	
	
}
