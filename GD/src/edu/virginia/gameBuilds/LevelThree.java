package edu.virginia.gameBuilds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
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
import edu.virginia.lab1test.QuestManager;
import edu.virginia.lab1test.AStar.Cell;

class LevelThree extends Game {
	
	/******* these rooms check when the enemy and ghost are in the same room. idk why it works auto for the green room 
	 * also sorry for naming them according to their colors since this is all gonna change.... */
	Rectangle orangeRoom = new Rectangle(600, 197, 500, 303);
	Rectangle blueRoom = new Rectangle(100, 500, 1000, 20);
	healthBarSprite healthBar = new healthBarSprite("healthBar");
	 int healthWidth = 0;
	 int foodCollected = 0;
	 boolean collected = true;
	 SoundManager soundManager;
	fruitSprite fruit = new fruitSprite("fruit");
	cherrySprite cherry = new cherrySprite("cherry");
	Sprite grass = new Sprite("grass", "grass.jpg");
	Sprite grass2 = new Sprite("grass2", "grass.jpg");
	//Sprite sky = new Sprite("sky", "sky.png");
	ghostSprite ghost = new ghostSprite("ghost");
	private DisplayObjectContainer camera = new DisplayObjectContainer("Camera", null);
	
	WallSprite wall = new WallSprite("testWall");
	WallSprite wall2 = new WallSprite("testWall2");
	WallSprite wall3 = new WallSprite("testWall3");
	WallSprite wall4 = new WallSprite("testWall4");
	WallSprite topHallway = new WallSprite("topHallway"); 
	WallSprite hallwayBottom = new WallSprite("hallwayBottom");
	WallSprite houseTop = new WallSprite("houseTop");
	Sprite woodFloor2 = new Sprite ("wood", "wood.jpg");
	Sprite redCarpet = new Sprite ("redCarpet", "redCarpet.jpg");
	

	VertWallSprite vwall = new VertWallSprite("vertWallOne");
	VertWallSprite vwall2 = new VertWallSprite("vertWallTwo");
	VertWallSprite vwall3 = new VertWallSprite("vertWallThree");
	VertWallSprite longHallwayRight = new VertWallSprite("longHallwayRight");
	VertWallSprite upperRightHallway = new VertWallSprite("upperRightHallway");
	VertWallSprite leftHouseTop = new VertWallSprite("leftHouseTop");
	VertWallSprite leftSideMiddleRoom = new VertWallSprite("leftSideMiddleRoom");
	Sprite grass3 = new Sprite("grass2", "grass.jpg");
	
	WallSprite topRoomBottom = new WallSprite ("topRoomBottom");
	
	VertWallSprite lowerLeft = new VertWallSprite("lowerLeft");
	LongWallSprite rightTop = new LongWallSprite("rightTop");
	LongWallSprite leftBottom = new LongWallSprite("leftBottom");
	LongWallSprite rightBottom = new LongWallSprite("rightBottom");
	//for collision detection
	//ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall, wall2,vwall,vwall2,wall3,wall4));
	ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall2,vwall,vwall2,wall4, lowerLeft, rightTop, leftBottom, rightBottom, vwall3));
	
	
	Sprite gameOver = new Sprite("gameOver", "gameOver.png");
	Sprite gameWon = new Sprite("gameWon", "gameWon.png");
	Sprite woodFloor = new Sprite ("wood", "wood.jpg");
	Sprite table = new Sprite ("table", "table.png");
	Sprite greyCarpet = new Sprite ("greyCarpet", "greyCarpet.png");
	Sprite beigeCarpet = new Sprite ("beigeCarpet", "beigeCarpet.jpg");
	
	enemySprite enemy = new enemySprite("EnemyOne");
	
	//Tween marioTween = new Tween(ghost, new TweenTransition() );
	Tween fruitTween = new Tween(fruit, new TweenTransition() );
	Tween cherryTween = new Tween(cherry, new TweenTransition());
	
	TweenJuggler juggler = new TweenJuggler();
	
	GameClock clock;

	Rectangle marioBounds = new Rectangle();
	Rectangle coinBounds = new Rectangle();
	Rectangle wallBounds = new Rectangle();
	Rectangle VertwallBounds = new Rectangle();

	QuestManager myQuestManager = new QuestManager();

	int dx = 4;
	int dy = 4;
	
	boolean ghostAbilities = false;			// when true, the ghost can float thru walls and hide from the owner, but NOT pick up fruit
	boolean solidEnough = false;				// when true, the ghost can pick up fruit but NOT float thru walls nor hide
	
	boolean collisionOccured = false;
	boolean stopR = false;
	boolean stopL = false;
	boolean stopU = false;
	boolean stopD = false;
	boolean zPress = false;		// this controls the ghost abilities

//	public boolean trippedCherry = false;
//	public boolean trippedFruit = false;
	boolean trippedCherry = false;
	boolean trippedFruit = false;
	
	/**** this code for the enemy's movement paths ***/
//	public int enemyMoveCounter = 1;
//	public int enemyMoveCounter2 = 1;
//	private boolean initializeRoute=true;
//	
//	private boolean path1Completed = false;
//	
//	public int room1x = 400;
//	public int room1y = 275;
//
//	
//	public Rectangle room1 = new Rectangle();
//	
//	
//	public int room2x = 800;
//	public int room2y = 275;
//	
//	
//	public Rectangle room2 = new Rectangle();
//	public boolean gameOverB = false;
//	
//	public boolean gtr1 = true;
//	public boolean room1SetUp = false;
//	
//	public boolean gtr2 = false;
//	public boolean room2SetUp = false;
//	
//	public ArrayList<int[]> blockedList = new ArrayList<int[]>();
//	
//	private float deltaAlpha = (float) 0.1;		// controls how quickly the ghost becomes invisible/visible
//	boolean transKeyTapped = false;			// needed to implement tapping of key
//	boolean visibleKeyTapped = false;			// turns the ghost visible again
	
	
	
	
	
	int enemyMoveCounter = 1;
	int enemyMoveCounter2 = 1;
	int enemyMoveCounter3 = 1;
	int enemyMoveCounter4 = 1;
	boolean initializeRoute=true;
	
	boolean path1Completed = false;
	
	 int room1x = 400;
	int room1y = 275;

	
	Rectangle room1 = new Rectangle();
	
	
	int room2x = 1000;
	int room2y = 550;
	
	
	Rectangle room2 = new Rectangle();
	boolean gameOverB = false;
	
	boolean gtr1 = true;
	boolean room1SetUp = false;
	
	boolean gtr2 = false;
	boolean room2SetUp = false;
	
	
	int room3x = 800;
	int room3y = 330;
	
	boolean gtr3 = false;
	boolean gtr4 = false;
	
	
	ArrayList<int[]> blockedList = new ArrayList<int[]>();
	
	float deltaAlpha = (float) 0.1;		// controls how quickly the ghost becomes invisible/visible
	boolean transKeyTapped = false;			// needed to implement tapping of key
	boolean visibleKeyTapped = false;
	
	
	
	
	
	ArrayList<Cell> path1 = new ArrayList<Cell>();
	ArrayList<Cell> fPath1 = new ArrayList<Cell>();
	
	ArrayList<Cell> path2 = new ArrayList<Cell>();
	ArrayList<Cell> fPath2 = new ArrayList<Cell>();
	
	ArrayList<Cell> path3 = new ArrayList<Cell>();
	ArrayList<Cell> fPath3 = new ArrayList<Cell>();
	
	ArrayList<Cell> path4 = new ArrayList<Cell>();
	ArrayList<Cell> fPath4 = new ArrayList<Cell>();
	
	LevelThree(String gameId, int width, int height) {
		super("Level Build", 1200, 800);
		
		clock = new GameClock();
		
		this.getScenePanel().setBackground(Color.gray);
		healthBar.setXPos(10);
		//healthBar.setYPos(10);
		ghost.setTrans(0.0f);
		ghost.setXPos(3);
		grass.setYPos(0);
		grass.setXPos(0);
		grass2.setYPos(-800);
		grass3.setYPos(-1400);
		
		woodFloor2.setXPos(1060);
		woodFloor2.setYPos(-300);
		//woodFloor2.setRotation(1.57);
		woodFloor2.setXScale(0.9);
		woodFloor2.setYScale(2.4);
		
		

		redCarpet.setXPos(600);
		redCarpet.setYPos(-300);
		redCarpet.setYScale(1.53);
		redCarpet.setXScale(1.53);
		
		
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
		//marioTween.doTween(true);
		//marioTween.animate(TweenableParam.FADE_IN, 0.0f, 1.0f, 6000);	
		
		fruit.setXPos(500);
		fruit.setYPos(300);
		fruit.addEventListener(myQuestManager, null);
		
		cherry.setXPos(710);
		cherry.setYPos(310);
		cherry.addEventListener(myQuestManager, null);
		
		lowerLeft.setXPos(100);
		lowerLeft.setYPos(400);

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

		
		
		
/**NEW WALLS HERE ************/		
		longHallwayRight.setXPos(1300); //extended for level 3
		longHallwayRight.setYPos(-650);
		longHallwayRight.setYScale(8.67);
		
		topHallway.setXPos(957);
		topHallway.setYPos(-300);
		topHallway.setXScale(2.5);
		
		upperRightHallway.setXPos(1063);
		upperRightHallway.setYPos(-150);
		upperRightHallway.setYScale(2);	
		
		hallwayBottom.setXPos(1063);
		hallwayBottom.setYPos(700);
		hallwayBottom.setXScale(1.7);
		
		houseTop.setXPos(100);
		houseTop.setYPos(-650);
		houseTop.setXScale(8);
		
		leftHouseTop.setXPos(100);
		leftHouseTop.setYPos(-650);
		leftHouseTop.setYScale(2.37);
		
		leftSideMiddleRoom.setXPos(567);
		leftSideMiddleRoom.setYPos(-270);
		leftSideMiddleRoom.setYScale(2.8);
		
		topRoomBottom.setXPos(100);
		topRoomBottom.setYPos(-300);
		topRoomBottom.setXScale(4.7);
		
		
		
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
		
		
		
		//juggler.add(marioTween);
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
		enemy.setXPos(room2x);
		enemy.setYPos(room2y-15);
		
		//room rectangle
		room1.setBounds(300+vwall.getScaledHeight(),500-vwall.getScaledHeight(),(2*wall2.getScaledWidth()),vwall.getScaledHeight());

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
		
		/*********** end of second path for enemy code *********/
		
		
		
		
		
		
		path1 = AStar.test(1, 2000, 2000, room2x, room2y-15, room1x, room1y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
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
		
		path3 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), room2x, room2y-15, room3x, room3y, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
		int pLen3 = path3.size();
		
		int q3=pLen3-1;
		
		
		/** prints the path **/
		
		while(q3>=0){
			Cell temp = new Cell(path3.get(q3).i,path3.get(q3).j);
			fPath3.add(temp);
			q3-=1;
		}
		
		path4 = AStar.test(1, this.getScenePanel().getWidth(), this.getScenePanel().getHeight(), room3x, room3y,  room2x, room2y-15, blockedList);		// made room1y -> room1y-15 because enemy wasn't fully in the room
		int pLen4 = path4.size();
		
		int q4=pLen3-1;
		
		
		/** prints the path **/
		
		while(q4>=0){
			Cell temp = new Cell(path4.get(q4).i,path4.get(q4).j);
			fPath4.add(temp);
			q4-=1;
		}
		
		
		//initializeRoute = false;
	}
	
//	public void switchPath(){
//		if(gtr1 == true){
//			gtr1 = false;
//			room1SetUp = false;
//			gtr2 = true;
//		}
//		
//		if(gtr2 == true){
//			
//			gtr2 = false;
//			room2SetUp = false;
//			gtr1 = true;
//		}
//	}

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
							gtr3 = false;
							
							
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
							
							gtr3 = false;
						}
						
					}
					
					if(gtr3 == true){
						
						
						
						if(enemyMoveCounter3<fPath3.size()){
							Cell moveTo = fPath3.get(enemyMoveCounter3);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goBackward(true);			// THIS IS WHERE TO TELL THE SPRITE TO CHANGE DIRECTIONS
							enemyMoveCounter3+=1;		// makes the owner move faster
						}
						if(enemyMoveCounter3>=fPath3.size()){
							
							
							enemyMoveCounter3=1;
							gtr2 = false;

							gtr1 = false;
							
							gtr3 = true;
						}
						
					}
					
					if(gtr4 == true){
						
						
						
						if(enemyMoveCounter4<fPath4.size()){
							Cell moveTo = fPath4.get(enemyMoveCounter4);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goBackward(true);			// THIS IS WHERE TO TELL THE SPRITE TO CHANGE DIRECTIONS
							enemyMoveCounter4+=1;		// makes the owner move faster
						}
						if(enemyMoveCounter4>=fPath4.size()){
							
							
							enemyMoveCounter4=1;
							gtr2 = false;

							gtr1 = true;
							
							gtr3 = false;
							
						}
						
					}
					
					juggler.nextFrame();
					
//					if (enemy.getYPos() == room1y-15 && enemy.getXPos() == room1x) {
//						path1Completed = true;
//						//System.out.println("PATH ONE COMPLETED");
//					}
										
					for(Sprite wall : collDects){			// does code have ability to cycle through every wall object in 1/60 of a second?

						if(ghost.collidesWith(wall) && ghostAbilities==false){

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

						//System.out.println("A winner is you");
						//winner.setVisible(true);

						//System.out.println("A winner is you");
						if (!gameOver.isVisible()) {
						gameWon.setVisible(true);}
						
						//pause system here?
						
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
						if ( stopU==false)
							{ghost.setYPos(ghost.getYPos()-dy);
							System.out.println("down is: " + ghost.getYPos());}
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
						if (stopD==false) {
							ghost.setYPos(ghost.getYPos() + dy);
							System.out.println("up is: " + ghost.getYPos()); }
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
						if (stopR==false) {
							ghost.setXPos(ghost.getXPos() + dx);
							System.out.println("right is: " + ghost.getXPos());}
					}

					if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
						if ( stopL==false)
							{ghost.setXPos(ghost.getXPos() - dx);
							System.out.println("left is: " + ghost.getXPos());}
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
		g.translate((int)-camera.getXPos(), (int)-camera.getYPos());
		super.draw(g);
		if (grass != null && grass2 != null) {
			grass.draw(g);
			grass2.draw(g);
			grass3.draw(g);
		}

		if (woodFloor != null && greyCarpet != null && beigeCarpet != null && table != null && redCarpet != null && woodFloor2 != null) {
			woodFloor.draw(g);
			greyCarpet.draw(g);
			beigeCarpet.draw(g);
			redCarpet.draw(g);
			woodFloor2.draw(g);
			table.draw(g);
		}
//		g.setColor(Color.blue);
//		g.fillRect (1100, -300, 230, 1000);
//		g.setColor(Color.green);//entryway
//		g.fillRect(600, -300, 500, 480);
//		g.setColor(Color.orange);//leftroom
		//g.drawRect (600, 197, 500, 303);
		//g.fillRect(600, 197, 500, 303);
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
		if (lowerLeft != null && rightTop != null && leftBottom != null && longHallwayRight != null && topHallway != null && upperRightHallway != null && hallwayBottom != null && houseTop != null && leftHouseTop != null && topRoomBottom != null && leftSideMiddleRoom != null) {
			lowerLeft.draw(g);
			rightTop.draw(g);
			leftBottom.draw(g);
			rightBottom.draw(g);
			longHallwayRight.draw(g);
			topHallway.draw(g);
			upperRightHallway.draw(g);
			hallwayBottom.draw(g);
			houseTop.draw(g);
			leftHouseTop.draw(g);
			topRoomBottom.draw(g);
			leftSideMiddleRoom.draw(g);
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

	//Level Switching code
		//add a new if statment for each new level
		public void switchLevels(){
			if(Beta.atLevelOne == true){
				System.out.println("it should be working");
				Beta.currentGame.exitGame();
				Beta.atLevelOne = false;
				Beta.atLevelTwo = true;
				//Game game = new LevelTwo();
				//Beta.currentGame = game;
				Beta.currentGame.start();
				
			}
			
			if(Beta.atLevelTwo == true){
				System.out.println("level 3 entered");
				Beta.currentGame.exitGame();
				Beta.atLevelThree = true;
				Beta.atLevelTwo = false;
				Game game = new LevelThree("LevelThree", 1200, 800);
				//Game game = new Beta();
				Beta.currentGame = game;
				Beta.currentGame.start();
				
			}
		}
		

		

		public static void main(String[] args) {

	
			//Game game = new Beta();
			LevelThree level2 = new LevelThree("Test", 1200, 800);
			Beta.currentGame = level2;
			
			Beta.currentGame.start();
		}
	

//private GameClock clock2;
}

