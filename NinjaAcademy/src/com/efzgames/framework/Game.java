package com.efzgames.framework;

import com.efzgames.framework.impl.GLGraphics;

public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();
    
    public GLGraphics getGLGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();

	public void exit();
}