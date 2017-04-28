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

import edu.virginia.engine.display.DisplayObjectContainer;
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
	public DisplayObjectContainer levelOne = new DisplayObjectContainer("LevelOne", "health.png");
	/******* these rooms check when the enemy and ghost are in the same room. idk why it works auto for the green room 
	 * also sorry for naming them according to their colors since this is all gonna change.... */
	public Rectangle orangeRoom = new Rectangle(620, 180, 460, 260);
	public Rectangle blueRoom = new Rectangle(120, 520, 960, 20);
	healthBarSprite healthBar = new healthBarSprite("healthBar");
	public int healthWidth = 0;
	public int foodCollected = 0;
	private boolean collected = true;
	private SoundManager soundManager;
	private DisplayObjectContainer camera = new DisplayObjectContainer("Camera", null);

	fruitSprite fruit = new fruitSprite("fruit");
	cherrySprite cherry = new cherrySprite("cherry");
	Sprite grass = new Sprite("grass", "grass.jpg");
	Sprite sky = new Sprite("sky", "sky.png");
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
	Sprite woodFloor = new Sprite ("wood", "wood.jpg");
	Sprite table = new Sprite ("table", "table.png");
	Sprite greyCarpet = new Sprite ("greyCarpet", "greyCarpet.png");
	Sprite beigeCarpet = new Sprite ("beigeCarpet", "beigeCarpet.jpg");
	Sprite bed = new Sprite ("bed", "bed.png");
	
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
	
	private boolean ghostAbilities = false;			// when true, the ghost can float thru walls and hide from the owner, but NOT pick up fruit
	private boolean solidEnough = false;				// when true, the ghost can pick up fruit but NOT float thru walls nor hide
	
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
	
	public int room1x = 300;
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
	
	
	
	//fields necessary for level switching

			//add a new boolean for each new level
			public static boolean atLevelOne = true;
			public static boolean atLevelTwo = false;
			public static boolean atLevelThree = false;
			public static Game currentGame;
	


	public Beta() {
		
		super("Beta Build", 1200, 800);
		
		clock = new GameClock();
		
		levelOne.add(grass);
		//levelOne.add(ghost);
		//levelOne.add(enemy);
		levelOne.add(woodFloor);
		levelOne.add(greyCarpet);
		levelOne.add(table);
		levelOne.add(beigeCarpet);
		levelOne.add(fruit);
		levelOne.add(cherry);
		levelOne.add(bed);
		levelOne.add(lowerLeft);
		levelOne.add(lowerRight);
		levelOne.add(rightTop);
		levelOne.add(leftBottom);
		levelOne.add(rightBottom);
		levelOne.add(vwall);
		levelOne.add(wall2);
		levelOne.add(wall4);
		levelOne.add(vwall2);
		levelOne.add(vwall3);
		levelOne.add(gameWon);
		levelOne.add(gameOver);
		
		
		this.getScenePanel().setBackground(Color.gray);
		healthBar.setXPos(10);
		//healthBar.setYPos(10);
		ghost.setTrans(0.0f);
		ghost.setXPos(3);
		grass.setYPos(0);
		grass.setXPos(0);
		//sky.setYPos(0);
		
		woodFloor.setXPos(250);
		woodFloor.setYPos(450);
		woodFloor.setRotation(1.57);
		woodFloor.setXScale(2.3);
		woodFloor.setYScale(0.7);
		
		greyCarpet.setXPos(600);
		greyCarpet.setYPos(180);
		greyCarpet.setYScale(0.67);
		
		beigeCarpet.setXPos(100);
		beigeCarpet.setYPos(195);
		beigeCarpet.setYScale(0.93);
		beigeCarpet.setXScale(1.2);
		//beigeCarpet.setYScale(0.67);
		
		table.setXPos(700);
		table.setYPos(325);
		table.setXScale(0.035);
		table.setYScale(0.035);
		
		ghost.setYPos(780-ghost.getScaledHeight());
		marioTween.doTween(true);
		marioTween.animate(TweenableParam.FADE_IN, 0.0f, 1.0f, 6000);	
		
		fruit.setXPos(500);
		fruit.setYPos(300);
		fruit.addEventListener(myQuestManager, null);
		
		bed.setXPos(130);
		bed.setYPos(300);
		bed.setXScale(0.4);
		bed.setYScale(0.4);
		
		cherry.setXPos(710);
		cherry.setYPos(310);
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
		System.out.println("vwall2 x pos: " + vwall2.getXPos());
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
		//changed this to start in room 1
//		enemy.setXPos(800);
//		enemy.setYPos(100);
		
		enemy.setXPos(room2x);
		enemy.setYPos(room2y-15);

		//
		
		
		//room rectangle
		room1.setBounds(300+vwall.getScaledHeight()+20,500-vwall.getScaledHeight()-20,275,265);
		//System.out.println("room1 rectangle: " + room1);

		System.out.println(vwall.getScaledWidth());
		int wstart = 196-85;
		//int wstart = (int) (vwall.getYPos() - enemy.getHitBox().getHeight());
		for(int c = wstart; c<=wstart+vwall.getScaledHeight()+45;c++){	
			for(int x = 100 - vwall.getScaledWidth(); x<=100; x++){
				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}

		int wstart2 = 196-85;
		//int wstart2 = (int) (vwall.getYPos() - enemy.getHitBox().getHeight());
		for(int c = wstart2; c<=wstart2+vwall.getScaledHeight()+45;c++){	
			for(int x = (int) (530-enemy.getHitBox().getWidth()-vwall.getScaledWidth()); x<=600; x++){

				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}
		int wstart3 = 196-85;
		//int wstart3 = 197-134;
		//for(int c = wstart3; c<=wstart2+304+134;c++){	
		for(int c = wstart3; c<=wstart2+304+45;c++){	
			for(int x = 1100-76;x<=1100+ vwall.getScaledWidth();x++){
				int[] e = new int[]{x,c};
				blockedList.add(e);
			}
		}
		
		int wstart4 = 197-wall.getScaledHeight() - 85;
		for(int c = wstart4; c<=wstart4+wall2.getScaledHeight();c++){	
			for(int x = 350; x<=1176; x++){
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
		
		
		//path one
		///////////////////////////////////////////////////////
		path1 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), room2x, room2y-15, room1x, room1y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
		int pLen = path1.size();
		
		int q=pLen-1;
		
		
		/** prints the path **/
		 
		
		while(q>=0){
			Cell temp = new Cell(path1.get(q).i,path1.get(q).j);
			fPath1.add(temp);
			q-=1;
		}
		
		//////////////////////////////////////////////////////////
		
		
		//path2
		/////////////////////////////////////////////////
		path2 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), room1x, room1y-15, room2x, room2y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
		int pLen2 = path2.size();
		
		int q2=pLen2-1;
		
		
		/** prints the path **/
		
		while(q2>=0){
			Cell temp = new Cell(path2.get(q2).i,path2.get(q2).j);
			fPath2.add(temp);
			q2-=1;
		}
		/////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*********** end of second path for enemy code *********/
		
		
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

		camera.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		camera.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (camera.getXPos() > offsetMaxX){camera.setXPos(offsetMaxX);}
		else if (camera.getXPos() < offsetMinX) {camera.setXPos(offsetMinX);}
		if (camera.getYPos() > offsetMaxY) {camera.setYPos(offsetMaxY);}
		else if (camera.getXPos() < offsetMinY){camera.setYPos(offsetMinY);}
		super.update(pressedKeys);

		if (ghost != null) {
			if (fruit != null) {
				if ( enemy != null ) {
			
					if(gtr1 == true){
	
						
						if(enemyMoveCounter<fPath1.size()){
							Cell moveTo = fPath1.get(enemyMoveCounter);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true);			// THIS IS WHERE TO TELL THE SPRITE TO CHANGE DIRECTIONS
							//enemyMoveCounter+=1;
							enemyMoveCounter+=1;		// increases the owner's speed
						}
						
						if(enemyMoveCounter>=fPath1.size()){
//							System.out.println("Count is over 700");
//							System.out.println("count: " + enemyMoveCounter);
//							System.out.println("fPath size: " + fPath1.size());
							//path1Completed = true;
							
							enemyMoveCounter=1;
							//switchPath();
							gtr1 = false;
							gtr2 = true;
							
							
						}
						
					}

					if(gtr2 == true){
						
						
						
						if(enemyMoveCounter2<fPath2.size()){
							Cell moveTo = fPath2.get(enemyMoveCounter2);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goBackward(true);			// THIS IS WHERE TO TELL THE SPRITE TO CHANGE DIRECTIONS
							enemyMoveCounter2+=1;		// makes the owner move faster
						}
						if(enemyMoveCounter2>=fPath2.size()){
							
							
							enemyMoveCounter2=1;
							gtr2 = false;

							gtr1 = true;
						}
						
					}
					
					juggler.nextFrame();
					
//					if (enemy.getYPos() == room1y-15 && enemy.getXPos() == room1x) {
//						path1Completed = true;
//						//System.out.println("PATH ONE COMPLETED");
//					}
										
					for (Sprite wall : collDects ) {			// does code have ability to cycle through every wall object in 1/60 of a second?
						
						if(ghost.collidesWith(wall) && ghostAbilities==false){
							
							collisionOccured = true;
							
							if (ghost.getRightHitBox().intersects(wall.getHitBox())) {
								stopR = true;
							}
							
							if(ghost.getLeftHitBox().intersects(wall.getHitBox())){
								stopL=true;
							}
							

							if ( ghost.getBottomHitBox().intersects(wall.getHitBox()) ) {
								stopD = true;
							}

							if ( ghost.getTopHitBox().intersects(wall.getHitBox()) ) {
								stopU = true;
							}

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
						if (!gameOver.isVisible()) {
						gameWon.setVisible(true);}
						healthWidth = 0;
						switchLevels();

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
						if (collected == true && !gameOver.isVisible() ) {
							
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
					
					if ( ghost.getTrans() >=  1.0f - deltaAlpha) {
						ghostAbilities = false;
						solidEnough = true;
					}
					
					if ( (0.0f + deltaAlpha < ghost.getTrans()) && (ghost.getTrans() < 1.0f - deltaAlpha) ) {
						ghostAbilities = false;
						solidEnough = false;
					}
					
					if ( ghost.getTrans() <=  0.0f + deltaAlpha) {
						ghostAbilities = true;
						solidEnough = false;
					}


					/***********************************/


					if ((ghost.collidesWith(fruit)&& solidEnough==true && !gameOver.isVisible())) {
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
					if ((ghost.collidesWith(cherry) && solidEnough == true  && !gameOver.isVisible())) {
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
		levelOne.draw(g);
//		if (grass != null ) {
//			grass.draw(g);
//			
//		}
//
//		if (woodFloor != null && greyCarpet != null && beigeCarpet != null && table != null) {
//			woodFloor.draw(g);
//			greyCarpet.draw(g);
//			beigeCarpet.draw(g);
//			table.draw(g);
//			bed.draw(g);
//		}
//		if (fruit != null) {
//			fruit.draw(g);
//			//wall.draw(g);
//			wall2.draw(g);
//			//wall3.draw(g);
//			wall4.draw(g);
//			vwall.draw(g);
//			vwall2.draw(g);
//			vwall3.draw(g);
//		}
//
//
//		if (fruit != null) {
//			fruit.draw(g);
//
//		}
//		
//		if (cherry != null) {
//			cherry.draw(g);
//		}
//		
//		g.setColor(Color.red);
//		g.fillRect(20, 30, healthWidth, 22);
//		healthBar.draw(g);
//		
//		
		if (ghost != null) {
			ghost.draw(g);
		}
		
		if (enemy != null) {
			enemy.draw(g);
		}
//		if (lowerLeft != null && lowerRight != null && rightTop != null && leftBottom != null) {
//			lowerLeft.draw(g);
//			lowerRight.draw(g);
//			rightTop.draw(g);
//			leftBottom.draw(g);
//			rightBottom.draw(g);
//		}

		
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

	//Level Switching code
		//add a new if statment for each new level
		public void switchLevels(){
				System.out.println("Switch to Level2");
				currentGame.exitGame();
				Game game = new LevelTwo("Level 2", 1200, 800);
				//currentGame = game;
				currentGame.start();
				
			
			
		}

		

		public static void main(String[] args) {

			//Game game = new Beta();
			Game beta = new Beta();
			currentGame = beta;
			currentGame.start();
		}
}



