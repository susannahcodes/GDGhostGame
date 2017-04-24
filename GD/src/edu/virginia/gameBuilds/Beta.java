package edu.virginia.gameBuilds;

import edu.virginia.engine.display.Game;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import edu.virginia.engine.display.LongWallSprite;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.VertWallSprite;
import edu.virginia.engine.display.WallSprite;
import edu.virginia.engine.display.cherrySprite;
import edu.virginia.engine.display.enemySprite;
import edu.virginia.engine.display.fruitSprite;
import edu.virginia.engine.display.ghostSprite;
import edu.virginia.engine.display.healthBarSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.tween.Tween;
import edu.virginia.engine.tween.TweenEvent;
import edu.virginia.engine.tween.TweenJuggler;
import edu.virginia.engine.tween.TweenTransition;
import edu.virginia.engine.tween.TweenableParam;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.SoundManager;
import edu.virginia.lab1test.AStar;
import edu.virginia.lab1test.AStar.Cell;
import edu.virginia.lab1test.QuestManager;

public class Beta extends Game {
	
	/******* these rooms check when the enemy and ghost are in the same room. idk why it works auto for the green room 
	 * also sorry for naming them according to their colors since this is all gonna change.... */
	public Rectangle orangeRoom = new Rectangle(600, 197, 500, 303);
	public Rectangle blueRoom = new Rectangle(100, 500, 1000, 20);
	
	healthBarSprite healthBar = new healthBarSprite("healthBar");
	public int healthWidth = 0;
	public int foodCollected = 0;
	private boolean collected = true;
	private SoundManager soundManager;
	fruitSprite fruit = new fruitSprite("fruit");
	cherrySprite cherry = new cherrySprite("cherry");
	
	ghostSprite ghost = new ghostSprite("ghost");
	
	WallSprite wall = new WallSprite("testWall");
	WallSprite wall2 = new WallSprite("testWall2");
	WallSprite wall3 = new WallSprite("testWall3");
	WallSprite wall4 = new WallSprite("testWall4");
	VertWallSprite vwall = new VertWallSprite("vertWallOne");
	VertWallSprite vwall2 = new VertWallSprite("vertWallTwo");
	VertWallSprite vwall3 = new VertWallSprite("vertWallThree");
	
	VertWallSprite lowerLeft = new VertWallSprite("lowerLeft");
	VertWallSprite lowerRight = new VertWallSprite("lowerRight");
	LongWallSprite rightTop = new LongWallSprite("rightTop");
	LongWallSprite leftBottom = new LongWallSprite("leftBottom");
	LongWallSprite rightBottom = new LongWallSprite("rightBottom");
	//for collision detection
	//ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall, wall2,vwall,vwall2,wall3,wall4));
	ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall2,vwall,vwall2,wall4, lowerLeft, lowerRight, rightTop, leftBottom, rightBottom, vwall3));
	
	
	Sprite gameOver = new Sprite("gameOver", "gameOver.png");
	Sprite gameWon = new Sprite("gameWon", "gameWon.png");
	
	enemySprite enemy = new enemySprite("EnemyOne");
	
	Tween marioTween = new Tween(ghost, new TweenTransition() );
	Tween fruitTween = new Tween(fruit, new TweenTransition() );
	Tween cherryTween = new Tween(cherry, new TweenTransition());
	
	TweenJuggler juggler = new TweenJuggler();
	
	private GameClock clock;

	Rectangle marioBounds = new Rectangle();
	Rectangle coinBounds = new Rectangle();
	Rectangle wallBounds = new Rectangle();
	Rectangle VertwallBounds = new Rectangle();

	QuestManager myQuestManager = new QuestManager();

	private int dx = 4;
	private int dy = 4;
	
	
	private boolean ghostAbilities = false;
	private boolean collisionOccured = false;
	private boolean stopR = false;
	private boolean stopL = false;
	private boolean stopU = false;
	private boolean stopD = false;
	private boolean zPress = false;		// this controls the ghost abilities

	public boolean trippedCherry = false;
	public boolean trippedFruit = false;
	
	/**** this code for the enemy's movement paths ***/
	public int enemyMoveCounter = 1;
	public int enemyMoveCounter2 = 1;
	private boolean initializeRoute=true;
	
	private boolean path1Completed = false;
	
	public int room1x = 400;
	public int room1y = 275;

	
	public Rectangle room1 = new Rectangle();
	
	
	public int room2x = 800;
	public int room2y = 275;
	
	
	public Rectangle room2 = new Rectangle();
	public boolean gameOverB = false;
	
	public boolean gtr1 = true;
	public boolean room1SetUp = false;
	
	public boolean gtr2 = false;
	public boolean room2SetUp = false;
	
	public ArrayList<int[]> blockedList = new ArrayList<int[]>();
	
	private float deltaAlpha = (float) 0.1;		// controls how quickly the ghost becomes invisible/visible
	boolean transKeyTapped = false;			// needed to implement tapping of key
	boolean visibleKeyTapped = false;			// turns the ghost visible again
	
	
	
	ArrayList<Cell> path1 = new ArrayList<Cell>();
	ArrayList<Cell> fPath1 = new ArrayList<Cell>();
	
	ArrayList<Cell> path2 = new ArrayList<Cell>();
	ArrayList<Cell> fPath2 = new ArrayList<Cell>();
	
	
	

	public Beta() {
		
		super("Beta Build", 1200, 800);
		
		clock = new GameClock();
		
		this.getScenePanel().setBackground(Color.gray);
		healthBar.setXPos(10);
		//healthBar.setYPos(10);
		ghost.setTrans(0.0f);
		ghost.setXPos(3);
		ghost.setYPos(780-ghost.getScaledHeight());
		marioTween.doTween(true);
		marioTween.animate(TweenableParam.FADE_IN, 0.0f, 1.0f, 6000);	
		
		fruit.setXPos(500);
		fruit.setYPos(300);
		fruit.addEventListener(myQuestManager, null);
		
		cherry.setXPos(700);
		cherry.setYPos(300);
		cherry.addEventListener(myQuestManager, null);
		
		lowerLeft.setXPos(100);
		lowerLeft.setYPos(400);
		
		lowerRight.setXPos(1062);
		lowerRight.setYPos(400);
		
		rightTop.setXPos(600);
		rightTop.setYPos (156);
		
		leftBottom.setXPos(100);
		leftBottom.setYPos(700);
		
		rightBottom.setXPos(600);
		rightBottom.setYPos(700);
		
		
		wall2.setXPos(300);
		wall2.setYPos(500-vwall.getScaledHeight()-wall2.getScaledHeight());
		wall2.addEventListener(myQuestManager, null);
		
		wall4.setXPos(300+wall2.getScaledWidth());
		wall4.setYPos(500-vwall.getScaledHeight()-wall2.getScaledHeight());
		wall4.addEventListener(myQuestManager, null);
		
		vwall.setXPos(100);
		vwall.setYPos(500-vwall.getScaledHeight());
		vwall.addEventListener(myQuestManager, null);
		
		vwall2.setXPos(300+(2*wall2.getScaledWidth())-vwall.getScaledWidth());
		vwall2.setYPos(500-vwall.getScaledHeight());
		vwall2.addEventListener(myQuestManager, null);
		
		vwall3.setXPos(1062);
		//vwall3.setXPos(300+(2*wall2.getScaledWidth())-vwall.getScaledWidth());
		vwall3.setYPos(500-vwall.getScaledHeight());
		vwall3.addEventListener(myQuestManager, null);
		
		//wall.setXPos(300);
		//wall.setYPos(vwall.getYPos()+vwall.getScaledHeight());

		fruitTween.addEventListener(myQuestManager, null);
		//coinTween.animate(TweenableParam.POP_TO_CENTER, fruit.getYPos(), this.getScenePanel().getHeight()/2-(fruit.getScaledHeight()/2)-50, 6000);
		//coinTween.animate(TweenableParam.SWELL, fruit.getXScale(), .05, 6000);
		fruitTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		cherryTween.addEventListener(myQuestManager, null);
		//coinTween.animate(TweenableParam.POP_TO_CENTER, fruit.getYPos(), this.getScenePanel().getHeight()/2-(fruit.getScaledHeight()/2)-50, 6000);
		//coinTween.animate(TweenableParam.SWELL, fruit.getXScale(), .05, 6000);
		cherryTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		
		
		juggler.add(marioTween);
		juggler.add(fruitTween);
		juggler.add(cherryTween);
		
		gameOver.setXScale(1.5);
		gameOver.setYScale(1.5);
		gameOver.setXPos(170);
		gameOver.setYPos(300);
		gameOver.setVisible(false);
		
		
		try {
			soundManager = new SoundManager();
			soundManager.loadSoundEffect("caught", "resources/caught.wav");
			soundManager.loadSoundEffect("munch", "resources/munch.wav");
			soundManager.loadMusic("bgroundmusic", "resources/bground.wav");
			soundManager.playMusic("bgroundmusic");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		gameWon.setXScale(1.5);
		gameWon.setYScale(1.5);
		gameWon.setXPos(170);
		gameWon.setYPos(300);
		gameWon.setVisible(false);
		
		//enemy code
		enemy.setTrans(1.0f);
		enemy.setXPos(800);
		enemy.setYPos(100);
		
		//room rectangle
		room1.setBounds(300+vwall.getScaledHeight(),500-vwall.getScaledHeight(),(2*wall2.getScaledWidth()),vwall.getScaledHeight());
		
		//setting the list of blocked pixels the AI can't walk over, and figuring out a specific path
		
		 //{{300,95},{300,96},{300,97},{300,98},{300,99},{300,100},{300,101},{300,102},{300,103},{300,104},{300,105}};
		
//		//set the top wall of box to be blocked
//		int wstart = 500-vwall.getScaledHeight()-wall2.getScaledHeight();
//		for(int c = wstart-enemy.getScaledHeight(); c<=wstart+wall2.getScaledHeight();c++){	
//			for(int x = 300-enemy.getScaledWidth();x<=300+(wall2.getScaledWidth()*2);x++){
//				int[] e = new int[]{x,c};
//				blockedList.add(e);
//			}
//		}
//		//set the left vertical wall of the box to be blocked
//		for(int vy =500-vwall.getScaledHeight(); vy<=500; vy++){
//			for(int vx=300-enemy.getScaledWidth(); vx<= 300+vwall.getScaledWidth(); vx++){
//				int[] e = new int[]{vx,vy};
//				blockedList.add(e);
//			}
//		}
//		
//		//set the Right vertical wall of the box to be blocked
//				for(int vy2 =500-vwall.getScaledHeight(); vy2<=500; vy2++){
//					for(int vx2=300-enemy.getScaledWidth()+(2*wall2.getScaledWidth())-vwall.getScaledWidth(); vx2<= 300+(2*wall2.getScaledWidth()); vx2++){
//						int[] e = new int[]{vx2,vy2};
//						blockedList.add(e);		
//					}
//				}
		
		//path = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), (int)enemy.getXPos(), (int)enemy.getYPos(), room1x, room1y, blockedList);
//		path1 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), (int)enemy.getXPos(), (int)enemy.getYPos(), room1x, room1y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
//		int pLen = path1.size();
//		//int pLen2 = path.size();
//		int q=pLen-1;
//		//int q2=pLen2-1;
//		
//		/** prints the path **/
//		while(q>=0){
//			Cell temp = new Cell(path1.get(q).i,path1.get(q).j);
//			fPath1.add(temp);
//			q-=1;
//		}
		
//		while(q2>=0){
//			Cell temp = new Cell(path.get(q2).i,path.get(q2).j);
//			fPath2.add(temp);
//			q2-=1;
//		}
		
		//setting block list
		
		System.out.println(vwall.getScaledWidth());
		int wstart = 196-134;
		for(int c = wstart; c<=wstart+304+134;c++){	
			for(int x = 100 - vwall.getScaledWidth();x<=100;x++){
				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}
		
		int wstart2 = 197-134;
		for(int c = wstart2; c<=wstart2+304+134;c++){	
			for(int x = 600-76-vwall.getScaledWidth();x<=600;x++){
				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}
		
		int wstart3 = 197-134;
		for(int c = wstart3; c<=wstart2+304+134;c++){	
			for(int x = 1100-76;x<=1100+ vwall.getScaledWidth();x++){
				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}
		
		int wstart4 = 197-wall.getScaledHeight() - 134;
		for(int c = wstart4; c<=wstart4+wall2.getScaledHeight();c++){	
			for(int x = 350;x<=1176;x++){
				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}
		
		
		
		
		
		
		room1.setBounds(300+vwall.getScaledWidth(),500-vwall.getScaledHeight(),(2*wall2.getScaledWidth())-(2*vwall.getScaledWidth()),vwall.getScaledHeight());
		
		/*********** ATTEMPTING TO ADD SECOND PATH TO ENEMY *****************/
		
//		path2 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), (int)enemy.getXPos(), (int)enemy.getYPos(), room2x, room2y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
//		int p2Len = path2.size();
//		int q2 = p2Len-1;
//		
//		// prints the path 
//		while(q2>=0){
//			Cell temp = new Cell(path2.get(q2).i,path2.get(2).j);
//			fPath2.add(temp);
//			q2 -=1;
//		}
		room2.setBounds(300+vwall.getScaledWidth(),500-vwall.getScaledHeight(),(2*wall2.getScaledWidth())-(2*vwall.getScaledWidth()),vwall.getScaledHeight());		// ahhh idk what im doing
		
		/*********** end of second path for enemy code *********/
		
		//initializeRoute = false;
	}
	
	public void switchPath(){
		if(gtr1 == true){
			gtr1 = false;
			room1SetUp = false;
			gtr2 = true;
		}
		
		if(gtr2 == true){
			
			gtr2 = false;
			room2SetUp = false;
			gtr1 = true;
		}
	}

	public void update(ArrayList<String> pressedKeys) {

		/** we must include a way to check if the walls are not null before they are drawn
		 * 	it doesn't truly affect the game but it gives us a lot of error warnings in the console
		 * but it seems to make the drawing of the objects to the screen slow...might want to ask Floryan
		 */
		
		super.update(pressedKeys);

		if (ghost != null) {
			if (fruit != null) {
				if ( enemy != null ) {
			
					if(gtr1 == true){
	
						if(room1SetUp==false){
							ArrayList<Cell> pathT = new ArrayList<Cell>();
							ArrayList<Cell> fPathT = new ArrayList<Cell>();
							
							path1 = pathT;
							fPath1 = fPathT;
							
							path1 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), (int)enemy.getXPos(), (int)enemy.getYPos(), room1x, room1y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
							int pLen = path1.size();
							
							int q=pLen-1;
							
							
							/** prints the path **/
							 
							
							while(q>=0){
								Cell temp = new Cell(path1.get(q).i,path1.get(q).j);
								fPath1.add(temp);
								q-=1;
							}
							//System.out.println("initial size: " + fPath1.size());
							
							room1SetUp = true;
							
					}
						
						if(enemyMoveCounter<fPath1.size()){
							Cell moveTo = fPath1.get(enemyMoveCounter);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							//enemyMoveCounter+=1;
							enemyMoveCounter+=5;		// increases the owner's speed
						}
						
						if(enemyMoveCounter>=fPath1.size()){
//							System.out.println("Count is over 700");
//							System.out.println("count: " + enemyMoveCounter);
//							System.out.println("fPath size: " + fPath1.size());
							//path1Completed = true;
							
							enemyMoveCounter=1;
							//switchPath();
							gtr1 = false;
							room1SetUp = false;
							gtr2 = true;
							
							
						}
						
					}

					if(gtr2 == true){
						
						
						if(room2SetUp==false){
							ArrayList<Cell> pathT = new ArrayList<Cell>();
							ArrayList<Cell> fPathT = new ArrayList<Cell>();
							
							path2 = pathT;
							fPath2 = fPathT;
							
							path2 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), (int)enemy.getXPos(), (int)enemy.getYPos(), room2x, room2y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
							int pLen = path2.size();
							
							int q=pLen-1;
							
							
							/** prints the path **/
							
							while(q>=0){
								Cell temp = new Cell(path2.get(q).i,path2.get(q).j);
								fPath2.add(temp);
								q-=1;
							}
							room2SetUp = true;
							
					}
						
						if(enemyMoveCounter2<fPath2.size()){
							Cell moveTo = fPath2.get(enemyMoveCounter2);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							//enemyMoveCounter2+=1;
							enemyMoveCounter2+=5;		// makes the owner move faster
						}
						if(enemyMoveCounter2>=fPath2.size()){
							
							
							enemyMoveCounter2=1;
							gtr2 = false;
							room2SetUp = false;
							gtr1 = true;
						}
						
					}
					
					juggler.nextFrame();
					
//					if (enemy.getYPos() == room1y-15 && enemy.getXPos() == room1x) {
//						path1Completed = true;
//						//System.out.println("PATH ONE COMPLETED");
//					}
										
					for(Sprite wall : collDects){			// does code have ability to cycle through every wall object in 1/60 of a second?

						if(ghost.collidesWith(wall) && ghostAbilities==false){
							collisionOccured = true;
							double pcenterx = ghost.getHitBox().getCenterX();
							double pcentery = ghost.getHitBox().getCenterY();

							double wcenterx = wall.getHitBox().getCenterX();
							double wcentery = wall.getHitBox().getCenterY();
							
							double marioT = ghost.getYPos();
							
							//double marioB = (ghost.getYPos()+ ghost.getUnscaledHeight());
							double marioB = (ghost.getYPos()+ ghost.getScaledHeight());		//changed from unscaled to scaled
							
							double wallT = wall.getYPos();
							
							//double wallB = (wall.getYPos()+ wall.getUnscaledHeight());
							double wallB = (wall.getYPos()+ wall.getScaledHeight());				//changed from unscaled to scaled

							//double marioR = ghost.getXPos() + ghost.getUnscaledWidth();
							double marioR = ghost.getXPos() + ghost.getScaledWidth();			//changed from unscaled to scaled
							
							double marioL = ghost.getXPos();
							
							//double wallR = wall.getXPos() + wall.getUnscaledWidth();
							double wallR = wall.getXPos() + wall.getScaledWidth();		//changed from unscaled to scaled
							
							double wallL = wall.getXPos();

							if(marioR - wallL >=0 && marioL<wcenterx){		
								stopR = true;
							}
							if(wallR-marioL>=0 && marioL>wcenterx){
								stopL=true;
							}
							if(marioB - wallT>=0 && marioB<wallB){
								stopD = true;
							}
							
							if(wallB-marioT>=0 && marioT>wallT){
								//stopU = true;
							}
							
							// ATTEMPTING TO FIX COLLISION
							if(wallB-marioT>=0 && marioT>wallT){
								if ( !(wallL >= marioR) ||  !(wallR <= marioL) ) {
									stopU = true;
								}
							}
							
							/*** experimental fixes to the ghost "sticking on walls (that so far were unsuccessful...) ***/
							/*if(wallB-marioT>=0 && marioT>wallT){
								if (!stopR && !stopD) {
									stopU = true;
								}
							}*/
							/*
							if ( wallB - marioT >= 0 ) {									// check to see if collision is occurring when ghost wants to go up
								if ( (wallL >= marioL && wallR <= marioR) || (wallL <= marioL && wallR >= marioR) ) {	// if wall is directly above ghost, stop movement
									stopU = true;
								}
							}*/
							/***************/
							
							break;		// what does this break do?
						}
					}
					

					//enemy in the same room as player detection
					if(enemy.getHitBox().intersects(room1) && ghost.getHitBox().intersects(room1)&&ghostAbilities==false){
						//System.out.println("ENEMY FOUND YOU! GAME OVER");
						if (!gameWon.isVisible() && gameOverB == false) {
							try {
								soundManager.playSoundEffect("caught");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						gameOver.setVisible(true);
						gameOverB = true;
						}
					}
					
					if ( orangeRoom.intersects(enemy.getHitBox()) && orangeRoom.intersects(ghost.getHitBox()) && !ghostAbilities) {
						//System.out.println("ENEMY FOUND YOU! GAME OVER");
						if (!gameWon.isVisible() && gameOverB == false) {
							try {
								soundManager.playSoundEffect("caught");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameOver.setVisible(true);
							gameOverB = true;
						}
					}
					
					//xSystem.out.println("ghost in room " + blueRoom.intersects(ghost.getHitBox()));
					if ( blueRoom.intersects(enemy.getHitBox()) && blueRoom.intersects(ghost.getHitBox()) && !ghostAbilities) {
						//System.out.println("ENEMY FOUND YOU! GAME OVER");
						if (!gameWon.isVisible() && gameOverB == false) {
							try {
								soundManager.playSoundEffect("caught");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameOver.setVisible(true);
							gameOverB = true;
						}
					}
					
					if (foodCollected == 2) {

						//System.out.println("A winner is you");
						//winner.setVisible(true);

						//System.out.println("A winner is you");
						if (!gameOver.isVisible()) {
						gameWon.setVisible(true);}

					}

					if(collisionOccured == false){
						stopL =false;
						stopU =false;
						stopR =false;
						stopD =false;	
					}

					if (collisionOccured == true){
						collisionOccured = false;
					}

					if (ghostAbilities==true){
						stopL =false;
						stopU =false;
						stopR =false;
						stopD =false;
					}


					if (myQuestManager.questCompleted) {
						if (collected == true) {
							
							fruitTween.doTween(myQuestManager.tweenComplete);
						}
						else cherryTween.doTween(myQuestManager.tweenComplete);
					
						//questConfirm.setVisible(true);		
					}
					
					if (pressedKeys.contains("Q")) {
						System.exit(0);
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(38)) ) {
						if ( !(ghost.getYPos() - dy < -20) && stopU==false)
							ghost.setYPos(ghost.getYPos()-dy);
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
						if ( !(ghost.getYPos() + dy > this.getScenePanel().getHeight() - 20) && stopD==false)
							ghost.setYPos(ghost.getYPos() + dy);
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
						if ( !(ghost.getXPos() + dx > this.getScenePanel().getWidth() - 60) && stopR==false)
							ghost.setXPos(ghost.getXPos() + dx);
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
						if ( !(ghost.getXPos() - dx < -20) && stopL==false)
							ghost.setXPos(ghost.getXPos() - dx);
					}
					
					/******* this allows the ghost to become invisible *****/
					/*
					if (pressedKeys.contains(KeyEvent.getKeyText(71))) {
						if(zPress == false){
							zPress = true;

							if(ghostAbilities==false ){
								float newAlpha = (float) (0.2);
								ghost.setTrans(newAlpha);
							}
							
							if (ghostAbilities==true ) {
								float newAlpha = (float) (0.9f);
								ghost.setTrans(newAlpha);
							}
							ghostAbilities = !ghostAbilities;
						}
					}
					
					if (pressedKeys.contains(KeyEvent.getKeyText(71))==false){
						zPress = false;
					}	
					*/
					
					/******************* TAPPING AND VISIBILITY ****************/
					if (!transKeyTapped && pressedKeys.contains(KeyEvent.getKeyText(88))) {		//checks to see if key has been tapped.
						if ( ghost.getTrans()-deltaAlpha > 0.0f ) {
							ghost.setTrans(ghost.getTrans()-deltaAlpha);
						}	
						transKeyTapped = true;
					}
					
					if ( !pressedKeys.contains(KeyEvent.getKeyText(88)) ) {												// if it has been tapped, set it to be true and make slightly trans								
						transKeyTapped = false;
					}
					
					if ( !visibleKeyTapped && pressedKeys.contains(KeyEvent.getKeyText(90))) {
						if ( ghost.getTrans()+deltaAlpha < 1.0f ) {
							ghost.setTrans(ghost.getTrans()+deltaAlpha);
						}
						visibleKeyTapped = true;
					}
					
					if (!pressedKeys.contains(KeyEvent.getKeyText(90)) ) {
						visibleKeyTapped = false;
					}
					
					// NEED TO FIX THIS SO THERE IS AN IN-BETWEEN STATE WHERE THE GHOST CAN NOT FLOAT
					// THROUGH WALLS NOR CAN IT COLLECT FRUIT:
					if ( ghost.getTrans() >=  1.0f - deltaAlpha) {
						ghostAbilities = false;
					}
					
					if ( ghost.getTrans() <=  0.0f + deltaAlpha) {
						ghostAbilities = true;
					}

					/***********************************/


					if ((ghost.collidesWith(fruit)&& ghostAbilities==false)) {
						fruit.dispatchEvent(new Event(Event.COIN_PICKED_UP, fruit));
						collected = true;
						
						if (healthWidth < 340 && trippedFruit == false) {
						healthWidth += 170;
						trippedFruit = true;
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected +=1;
						}
						
						fruitTween.dispatchEvent(new TweenEvent(TweenEvent.TWEEN_EVENT_COMPLETE, fruitTween));
					}
					if ((ghost.collidesWith(cherry) && ghostAbilities == false)) {
						cherry.dispatchEvent(new Event(Event.COIN_PICKED_UP, cherry));
						if (healthWidth < 340 && trippedCherry == false) {
							healthWidth += 170; 
							trippedCherry = true;
							try {
								soundManager.playSoundEffect("munch");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							foodCollected +=1;
							}
						collected = false;
							cherryTween.dispatchEvent(new TweenEvent(TweenEvent.TWEEN_EVENT_COMPLETE, cherryTween));
							//makes orange tween even though it's the cherry that's being overlapped
						}
					

				}

			}
		}
	}
	
	

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure ghost gets drawn to
	 * the screen, we need to make sure to override this method and call ghost's draw method.
	 * */
	@Override
	public void draw(Graphics g) {
		super.draw(g);

		g.setColor(Color.blue);
		g.drawRect (100, 500, 1000, 20);
		g.fillRect(100, 500, 1000, 200);
		g.setColor(Color.green);//entryway
		g.drawRect (100, 197, 500, 303);
		g.fillRect(100, 197, 500, 303);
		g.setColor(Color.orange);//leftroom
		g.drawRect (600, 197, 500, 303);
		g.fillRect(600, 197, 500, 303);
		if (fruit != null) {
			fruit.draw(g);
			//wall.draw(g);
			wall2.draw(g);
			//wall3.draw(g);
			wall4.draw(g);
			vwall.draw(g);
			vwall2.draw(g);
			vwall3.draw(g);
		}


		if (fruit != null) {
			fruit.draw(g);

		}
		
		if (cherry != null) {
			cherry.draw(g);
		}
		
		g.setColor(Color.red);
		g.fillRect(20, 30, healthWidth, 22);
		healthBar.draw(g);
		
		
		if (ghost != null) {
			ghost.draw(g);
		}
		
		if (enemy != null) {
			enemy.draw(g);
		}
		if (lowerLeft != null && lowerRight != null && rightTop != null && leftBottom != null) {
			lowerLeft.draw(g);
			lowerRight.draw(g);
			rightTop.draw(g);
			leftBottom.draw(g);
			rightBottom.draw(g);
		}

		
		for ( Sprite wall : collDects ) {
			if ( wall != null ) {
				wall.draw(g);
			}
		}
		
		if (gameWon != null) {
			gameWon.draw(g);
		}
		
		if (gameOver != null) {
			gameOver.draw(g);
		}
	}

	public static void main(String[] args) {

		Beta game = new Beta();
		game.start();
	}
}


