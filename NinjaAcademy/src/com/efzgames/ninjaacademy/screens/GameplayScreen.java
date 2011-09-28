package com.efzgames.ninjaacademy.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;
import android.util.FloatMath;

import com.efzgames.framework.Game;
import com.efzgames.framework.Input.TouchEvent;
import com.efzgames.framework.gl.Animation;
import com.efzgames.framework.math.Vector2;
import com.efzgames.ninjaacademy.Assets;
import com.efzgames.ninjaacademy.GameConstants;
import com.efzgames.ninjaacademy.GameConfiguration;
import com.efzgames.ninjaacademy.GamePhase;
import com.efzgames.ninjaacademy.elements.EventHandler;
import com.efzgames.ninjaacademy.elements.GameComponent;
import com.efzgames.ninjaacademy.elements.HitPointsComponent;
import com.efzgames.ninjaacademy.elements.LaunchedComponent;
import com.efzgames.ninjaacademy.elements.ScoreComponent;
import com.efzgames.ninjaacademy.elements.SwordSlash;
import com.efzgames.ninjaacademy.NinjaAcademy;

public class GameplayScreen  extends GameScreen {
	
	static final float closeDragDistance = 25;


    static final float audibleDragDistance = 10;
     
	final float swordSlashCheckInterval = 0.100f;
    private float swordSlashCheckTimer;
    
	 // Constants
    private static final int maxThrowingStars = 10;
    private static final int maxSwordSlashes = 3;
    private static final int maxConveyerTargets = 10;
    private static final int maxFallingTargets = 5;
    private static final int maxGoldTargets = 5;
    private static final int maxFallingGoldTargets = 2;        
    private static final int maxBamboos = 6;
    private static final int maxDynamites = 3;
    private static final int maxBambooSlices = 5;
    
    private ArrayList<LaunchedComponent> inAirBambooComponents;
    private float bambooTimer = 0;
    private ArrayList<LaunchedComponent> inAirDynamiteComponents;
    
    private Stack<LaunchedComponent> bambooComponents;
    private Stack<LaunchedComponent> dynamiteComponents;
	
	private HitPointsComponent hitPointsComponent;
	private ScoreComponent scoreComponent;
	
	// Configuration related variables
    private GamePhase currentPhase;
    private float configurationPhaseTimer;
    private float dynamiteTimer;
    

    //Stack<Target> upperTargetComponents;
    private float upperTargetTimer;
    //List<Target> upperTargetsInMotion;

    //Stack<Target> middleTargetComponents;
    private float middleTargetTimer;
    //List<Target> middleTargetsInMotion;

    //Stack<Target> lowerTargetComponents;
    private float lowerTargetTimer;
    //List<Target> lowerTargetsInMotion;
    
    int swordSlashIndex = 0;
    SwordSlash[] swordSlashComponents;
    Vector2 swordSlashOrigin;
    SwordSlash activeSwordSlash;
    
   // Gesture recognition related variables
   private Vector2 dragPosition = null;

    
    Random random;
    
    public int gamePhasesPassed;
	
	public GameplayScreen(Game game) {
		super(game);
		
		random = new Random();
		
		gamePhasesPassed = -1;
        switchConfigurationPhase();
        
		createHUDComponents();
		createSwordSlashes();
		createLaunchedComponents();
	}
	
	private void handleDrag(TouchEvent gesture)
    {
        float dragDistance = 0;

        // Handle a new drag sequence
        if (dragPosition == null)
        {
            swordSlashOrigin = new Vector2(gesture.x * this.inputScaleX, GameConstants.viewPortHeight - gesture.y * this.inputScaleY) ;
            swordSlashCheckTimer = 0;
            activeSwordSlash = getSwordSlash();
            activeSwordSlash.setStretch(0);
            activeSwordSlash.reset();
        }
        else
        {
            dragDistance = FloatMath.sqrt((gesture.x * this.inputScaleX - dragPosition.x) *(gesture.x * this.inputScaleX - dragPosition.x)
            		+((GameConstants.viewPortHeight - gesture.y * this.inputScaleY) - dragPosition.y)*
            		((GameConstants.viewPortHeight -gesture.y * this.inputScaleY) - dragPosition.y));

            // Reset the sword slash timer for each significant drag
            if (dragDistance > closeDragDistance)
            {
                swordSlashCheckTimer = 0;
            }
        }

        dragPosition = new Vector2(gesture.x * this.inputScaleX, GameConstants.viewPortHeight - gesture.y * this.inputScaleY);

        activeSwordSlash.positionSlash(swordSlashOrigin, dragPosition);

        if (!sliceComponents(swordSlashOrigin, dragPosition) && dragDistance > audibleDragDistance)
        {
            Assets.playSound(Assets.swordSlashSound);
        }
    }
	
	 private boolean sliceComponents(Vector2 origin, Vector2 destination)
     {
         return false;          
     }
	
	 private SwordSlash getSwordSlash()
     {
         SwordSlash result = swordSlashComponents[swordSlashIndex++];

         if (swordSlashIndex >= maxSwordSlashes)
         {
             swordSlashIndex = 0;
         }

         return result;
     }
	
	@Override
	public void update(float deltaTime) {
		
		List<TouchEvent> events = game.getInput().getTouchEvents();
		int len = events.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = events.get(i);
			if (event.type == TouchEvent.TOUCH_DRAGGED){
				handleDrag(event);				
			}
		}
	
		for(GameComponent comp: ((NinjaAcademy)game).components){
			if(!comp.isEnabled)
				continue;
			
			comp.update(deltaTime);
		}
		
		// Make the currently displayed sword slash fade if necessary 
        if (dragPosition != null)
        {                                
            swordSlashCheckTimer += deltaTime;

            if (swordSlashCheckTimer >= swordSlashCheckInterval)
            {
                activeSwordSlash.fade(GameConstants.swordSlashFadeDuration);

                dragPosition = null;
            }
        }
		
		manageGamePhase(deltaTime);
	}
	
	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.gamePlayBackground);
		batcher.drawSprite(400, 240, 800, 480,
				Assets.gamePlayBackgroundRegion);
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.beginBatch(Assets.room);
		batcher.drawSprite(400, 240, 800, 480,
				Assets.roomRegion);
		batcher.endBatch();
		
		hitPointsComponent.present(deltaTime, batcher);
		scoreComponent.present(deltaTime, batcher);
		
		for(GameComponent comp: ((NinjaAcademy)game).components){
			if(!comp.isVisible)
				continue;
			
			comp.present(deltaTime, batcher);
		}
		
		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
	
	  /// <summary>
    /// Creates the components used to display the game HUD.
    /// </summary>
    private void createHUDComponents(){
    	// Create the component for displaying hit points
        hitPointsComponent = new HitPointsComponent(glGame);        
        hitPointsComponent.totalHitPoints = GameConfiguration.playerLives;
        hitPointsComponent.currentHitPoints = GameConfiguration.playerLives;   
        
        scoreComponent = new ScoreComponent(glGame);
        scoreComponent.score =0;
    }
    
    /// <summary>
    /// Creates the bamboo, bomb and dynamite components and adds them to the game's component list. All 
    /// components are initially disabled and invisible.
    /// </summary>
    private void createLaunchedComponents() {
    	inAirBambooComponents = new ArrayList<LaunchedComponent>(maxBamboos);
        inAirDynamiteComponents = new ArrayList<LaunchedComponent>(maxDynamites);

        // Create bamboos
        bambooComponents = new Stack<LaunchedComponent>();
        
        for (int i = 0; i < maxBamboos; i++)
        {
            LaunchedComponent bamboo = new LaunchedComponent(glGame, this, Assets.bamboo, Assets.bambooRegion);
            bamboo.isVisible = false;
            bamboo.isEnabled = false;
            bamboo.notifyHeight = GameConstants.offScreenYCoordinate;        

            bamboo.droppedPastHeight = new EventHandler(){
            	@Override
            	public void onEvent(GameComponent source){
            		bambooDroppedOutOfScreen((LaunchedComponent)source);
            	}
            };

            bambooComponents.push(bamboo);

            ((NinjaAcademy)game).components.add(bamboo);
        }
        
        // Create dynamites
        dynamiteComponents = new Stack<LaunchedComponent>();

        for (int i = 0; i < maxDynamites; i++)
        {
            LaunchedComponent dynamite = new LaunchedComponent(glGame, this,  new Animation(Assets.dynamite, new Point(27,117), 2
            		, new Vector2(9, 69), true, 0.250f));
            dynamite.isVisible = false;
            dynamite.isEnabled = false;
            dynamite.notifyHeight = GameConstants.offScreenYCoordinate;
       
            dynamite.droppedPastHeight = new EventHandler(){
            	@Override
            	public void onEvent(GameComponent source){
            		dynamiteDroppedOutOfScreen((LaunchedComponent)source);
            	}
            };         

            dynamiteComponents.push(dynamite);

            ((NinjaAcademy)game).components.add(dynamite);
        }
    	    	
    }
    
 
    void bambooDroppedOutOfScreen(LaunchedComponent bamboo)
    {
        bamboo.isEnabled = false;
        bamboo.isVisible = false;

        bambooComponents.push(bamboo);
        inAirBambooComponents.remove(bamboo);

        int hitPoints = Math.max(getHitPoints() - 1, 0);
        setHitPoints(hitPoints);

        if (hitPoints == 0)
        {
            //MarkGameOver();
        }
    }
    
    void dynamiteDroppedOutOfScreen(LaunchedComponent dynamite)
    {    
        dynamite.isEnabled = false;
        dynamite.isVisible = false;

        dynamiteComponents.push(dynamite);
        inAirDynamiteComponents.remove(dynamite);
    }
    
    public int getHitPoints(){
    	return this.hitPointsComponent.currentHitPoints;
    }
    
    public void setHitPoints(int value){
    	this.hitPointsComponent.currentHitPoints = value;
    }
    
    private void manageGamePhase(float gameTime){
    
    	// Keep track of the phase's progress
        configurationPhaseTimer += gameTime;
        
        // Move to the next game phase if necessary
        if (currentPhase.duration >= 0 && configurationPhaseTimer >= currentPhase.duration)
        {
            switchConfigurationPhase();
        }
        
    	managePhaseBamboos(gameTime);
    	managePhaseDynamites(gameTime);
    }
    
    private void managePhaseBamboos(float gameTime){
    	bambooTimer += gameTime;
    	
    	 if (bambooTimer >= currentPhase.bambooAppearanceInterval)
         {
             bambooTimer = 0;

             if (bambooComponents.size() > 0 && random.nextDouble() <= currentPhase.bambooAppearanceProbablity)
             {
                 LaunchedComponent launchedBamboo = bambooComponents.pop();
                 inAirBambooComponents.add(launchedBamboo);

                 Vector2 launchSpeed = getLaunchSpeed();

                 launchedBamboo.Launch(getLaunchPosition(), launchSpeed, GameConstants.launchAcceleration,
                		 getLaunchRotation(launchSpeed));
                 launchedBamboo.isEnabled = true;
                 launchedBamboo.isVisible = true;
             }
         }
    }
    
    private void managePhaseDynamites(float gameTime)
    {
        dynamiteTimer +=gameTime;

        if (dynamiteTimer >= currentPhase.dynamiteAppearanceInterval)
        {
            dynamiteTimer = 0;

            if (dynamiteComponents.size() > 0 && random.nextDouble() <= currentPhase.dynamiteAppearanceProbablity)
            {
                int dynamiteAmount = getDynamiteAmount();
                dynamiteAmount = Math.min(dynamiteAmount, dynamiteComponents.size());

                Assets.playSound(Assets.fuseSound);

                for (int i = 0; i < dynamiteAmount; i++)
                {
                    LaunchedComponent launchedDynamite = dynamiteComponents.pop();
                    inAirDynamiteComponents.add(launchedDynamite);

                    Vector2 launchSpeed = getLaunchSpeed();

                    launchedDynamite.Launch(getLaunchPosition(), launchSpeed, GameConstants.launchAcceleration,
                        getLaunchRotation(launchSpeed));
                    launchedDynamite.isEnabled = true;
                    launchedDynamite.isVisible = true;
                }
            }
        }
    }
    
    private int getDynamiteAmount()
    {
        double randomNumber = random.nextDouble();

        double totalProbability = 0;

        for (int i = 0; i < currentPhase.dynamiteAmountProbabilities.length; i++)
        {
            totalProbability += currentPhase.dynamiteAmountProbabilities[i];
            if (randomNumber <= totalProbability)
            {
                return i + 1;
            }
        }

        return currentPhase.dynamiteAmountProbabilities.length;
    }
    
    private Vector2 getLaunchSpeed()
    {
        return new Vector2(-150 + (float)(random.nextDouble() * 300), -(-500 - (float)(random.nextDouble() * 150)));
    }
    
    private Vector2 getLaunchPosition()
    {
        return new Vector2(300 + (float)(random.nextDouble() * 200), -(500 - GameConstants.viewPortHeight));
    }
    
    private float getLaunchRotation(Vector2 launchSpeed)
    {
        return 5 * launchSpeed.x / 150;
    }
    
    public void switchConfigurationPhase(){
        if (GameConfiguration.phases.size() > gamePhasesPassed)
        {
            gamePhasesPassed++;

            currentPhase = GameConfiguration.phases.get(gamePhasesPassed);

            bambooTimer = 0;
            dynamiteTimer = 0;
            lowerTargetTimer = 0;
            middleTargetTimer =0;
            upperTargetTimer = 0;                
        }
        else
        {
           // MarkGameOver();
        }
    }
    
    
    private void createSwordSlashes()
    {
        swordSlashComponents = new SwordSlash[maxThrowingStars];

        for (int i = 0; i < maxSwordSlashes; i++)
        {
            SwordSlash swordSlash = new SwordSlash(glGame, this, Assets.slice, Assets.sliceRegion);
           
            swordSlash.isVisible = false;
            swordSlash.isEnabled = false;

            swordSlashComponents[i] = swordSlash;

            ((NinjaAcademy)game).components.add(swordSlash);
        }
    }
}
