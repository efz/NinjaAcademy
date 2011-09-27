package com.efzgames.ninjaacademy.elements;

public abstract class GameComponent {
	
	public boolean IsEnabled = false;
	public boolean IsVisible = false;
	
	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime);
}
