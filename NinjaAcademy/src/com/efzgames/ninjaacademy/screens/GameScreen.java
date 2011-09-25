package com.efzgames.ninjaacademy.screens;



public abstract class GameScreen {

	 private boolean isPopup = false;
	 
	 protected void setIsPopUp(boolean value){
		 isPopup = value;
	 }
	 
	 public boolean getIsPopUp(){
		 return isPopup;
	 }
	
	private float transitionOnTime = 0;
	
	 protected void setTransitionOnTime(float value){
		 transitionOnTime = value;
	 }
	 
	 public float getTransitionOnTime(){
		 return transitionOnTime;
	 }
	
	private float transitionOffTime = 0;
	
	protected void setTransitionOffTime(float value){
		 transitionOffTime = value;
	 }
	 
	 public float getTransitionOffTime(){
		 return transitionOffTime;
	 }
	
	private float transitionPosition = 1;
	
	protected void setTransitionPosition(float value){
		transitionPosition = value;
	 }
	 
	 public float getTransitionPosition(){
		 return transitionPosition;
	 }
	
	
	 public float getTransitionAlpha(){
		 return 1- transitionPosition;
	 }
	 
	 private ScreenState screenState = ScreenState.TransitionOn;
	 
	 protected void SetScreenState(ScreenState value){
		 screenState = value;
	 }
	 
	 public ScreenState GetScreenState(){
		 return screenState;
	 }
	 
	 private boolean isExiting = false;
	 
	 protected void setIsExiting(boolean value){
		 isExiting = value;
	 }
	 
	 public boolean getIsExiting(){
		 return isExiting;
	 }
	 
	 private boolean otherScreenHasFocus;
	 
	 public boolean isActive(){        
         return !otherScreenHasFocus &&  (screenState == ScreenState.Active);       
     }
	 
	 private ScreenManager screenManager;
	 
	 void setScreenManager(ScreenManager value) {
		 screenManager = value;
	 }
	 
	 
	 
}
