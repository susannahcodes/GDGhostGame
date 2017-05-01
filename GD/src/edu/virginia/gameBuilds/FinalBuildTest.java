package edu.virginia.gameBuilds;

import edu.virginia.engine.display.Game;

import java.awt.Color;
import java.awt.Graphics;
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
import edu.virginia.gameBuilds.LevelTwo;

public class FinalBuildTest extends Game {

	public DisplayObjectContainer levelOne = new DisplayObjectContainer(
			"LevelOne", "health.png");
	public DisplayObjectContainer levelTwo = new DisplayObjectContainer(
			"LevelTwo", "health.png");
	public DisplayObjectContainer levelThree = new DisplayObjectContainer(
			"LevelThree", "health.png");
	public DisplayObjectContainer levelFour = new DisplayObjectContainer(
			"LevelFour", "health.png");
	public DisplayObjectContainer levelFive = new DisplayObjectContainer(
			"LevelFive", "health.png");

	/******* these rooms check when the enemy and ghost are in the same room */
	Rectangle topRoom = new Rectangle(140, 175, 1160, 315);
	Rectangle topMiddleRoom = new Rectangle(620, 550, 435, 400);
	Rectangle leftMiddleRoom = new Rectangle(135, 1015, 400, 465);
	Rectangle rightMiddleRoom = new Rectangle(625, 1015, 420, 465);
	Rectangle verticalHallway = new Rectangle(1110, 550, 180, 940);
	Rectangle bottomRoom = new Rectangle(130, 1310, 1160, 180); 	
	Rectangle doorwayView1 = new Rectangle(620, 550, 660, 100);		//if the enemy is not in the top middle room, but can "see" into it from the vertical hallway, ghost is caught 
	Rectangle doorwayView2 = new Rectangle(820, 225, 100, 700);		//if the enemy is not in the top room, but can "see" into it from top middle rorom, ghost is caught 
	
	ArrayList<Rectangle> listOfRooms = new ArrayList<Rectangle>(Arrays.asList(topRoom, topMiddleRoom, leftMiddleRoom, rightMiddleRoom, 
																										verticalHallway, bottomRoom, doorwayView2));

	healthBarSprite healthBar = new healthBarSprite("healthBar");
	Sprite health = new Sprite("health", "health.png");
	public int healthWidth = 0;
	public int foodCollected = 0;
	private boolean collected = true;
	private SoundManager soundManager;
	private DisplayObjectContainer camera = new DisplayObjectContainer(
			"Camera", null);

	boolean fruitCollected = false;
	boolean strawberryCollected = false;
	boolean cherryCollected = false;
	boolean bananaCollected = false;

	fruitSprite fruit = new fruitSprite("fruit");
	cherrySprite cherry = new cherrySprite("cherry");
	Sprite grass = new Sprite("grass", "grass.jpg");
	Sprite grass2 = new Sprite("grass2", "grass.jpg");
	Sprite sky = new Sprite("sky", "sky.png");
	ghostSprite ghost = new ghostSprite("ghost");
	Sprite greyCarpet2 = new Sprite("greyCarpet", "greyCarpet.png");
	Sprite grass3 = new Sprite("grass2", "grass.jpg");
	Sprite strawberry = new Sprite("strawberry", "strawberry.png");
	Tween strawberryTween = new Tween(strawberry, new TweenTransition());
	Sprite banana = new Sprite("banana", "banana.png");
	Tween bananaTween = new Tween(banana, new TweenTransition());
	// public boolean levelOne = true;

	WallSprite wall = new WallSprite("testWall");
	WallSprite wall2 = new WallSprite("testWall2");
	WallSprite wall3 = new WallSprite("testWall3");
	WallSprite wall4 = new WallSprite("testWall4");
	// VertWallSprite vwall6L2 = new VertWallSprite("vertWallOne");
	VertWallSprite vwall = new VertWallSprite("vertWallOne");
	VertWallSprite vwall2 = new VertWallSprite("vertWallTwo");
	VertWallSprite vwall3 = new VertWallSprite("vertWallThree");
	// VertWallSprite leftHouseTop = new VertWallSprite("leftHouseTop");
	VertWallSprite lowerLeft = new VertWallSprite("lowerLeft");
	VertWallSprite lowerRight = new VertWallSprite("lowerRight");
	LongWallSprite rightTop = new LongWallSprite("rightTop");
	LongWallSprite leftBottom = new LongWallSprite("leftBottom");
	LongWallSprite rightBottom = new LongWallSprite("rightBottom");
	// for collision detection
	// ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall,
	// wall2,vwall,vwall2,wall3,wall4));

	Sprite gameOver = new Sprite("gameOver", "gameOver.png");
	Sprite woodFloor = new Sprite("wood", "wood.jpg");
	Sprite table = new Sprite("table", "table.png");
	Sprite greyCarpet = new Sprite("greyCarpet", "greyCarpet.png");
	Sprite beigeCarpet = new Sprite("beigeCarpet", "beigeCarpet.jpg");
	Sprite bed = new Sprite("bed", "bed.png");

	WallSprite topRoomBottom = new WallSprite("topRoomBottom");
	WallSprite topHallway = new WallSprite("topHallway");
	WallSprite topHallwayLevelTwo = new WallSprite("topHallway");
	WallSprite topHallwayLevelThree = new WallSprite("topHallway");
	WallSprite hallwayBottom = new WallSprite("hallwayBottom");
	WallSprite houseTop = new WallSprite("houseTop");
	Sprite woodFloor2 = new Sprite("wood", "wood.jpg");
	VertWallSprite longHallwayRight = new VertWallSprite("longHallwayRight");
	// use this wall's
	Sprite titleScreen = new Sprite("titleScreen", "titleScreen.png");
	Sprite loadingScreen = new Sprite("loadingScreen", "loadingScreen.png");
	Sprite menu2 = new Sprite("menu2", "menu2.png");
	Sprite menu3 = new Sprite("menu3", "menu3.png");
	Sprite menu4 = new Sprite("menu4", "menu4.png");
	Sprite menu5 = new Sprite("menu5", "menu5.png");
	public boolean titleScreenActive = true;
	public boolean menu2Active = true;
	public boolean menu3Active = true;
	public boolean menu4Active = true;
	public boolean menu5Active = true;
	VertWallSprite longHallwayRightLevel2 = new VertWallSprite(
			"longHallwayRight"); // us
	VertWallSprite upperRightHallway = new VertWallSprite("upperRightHallway"); // use
																				// this
																				// wall's
																				// pos
																				// for
																				// collision
																				// detection
	VertWallSprite leftHouseTop = new VertWallSprite("leftHouseTop");
	VertWallSprite leftSideMiddleRoom = new VertWallSprite("leftSideMiddleRoom");
	Sprite l2Wall = new Sprite("l2Wall", "updownwall.png");
	Sprite redCarpet = new Sprite("redCarpet", "redCarpet.jpg");
	enemySprite enemy = new enemySprite("EnemyOne");
	
	/***** LIST OF WALLS ******/
	//ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall2,vwall,vwall2,wall4, lowerLeft, rightTop, leftBottom, rightBottom, vwall3, topHallway, hallwayBottom, leftSideMiddleRoom, 
			//longHallwayRight, upperRightHallway, wall2, vwall3, leftHouseTop, leftSideMiddleRoom,houseTop,topRoomBottom));
	
	ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(lowerLeft, lowerRight, rightTop, leftBottom, rightBottom, vwall, wall2, wall4, vwall2, vwall3));

	Tween marioTween = new Tween(ghost, new TweenTransition());
	Tween fruitTween = new Tween(fruit, new TweenTransition());
	Tween cherryTween = new Tween(cherry, new TweenTransition());

	TweenJuggler juggler = new TweenJuggler();

	boolean trippedStrawberry = false;
	boolean trippedBanana = false;

	private GameClock clock;

	Rectangle marioBounds = new Rectangle();
	Rectangle coinBounds = new Rectangle();
	Rectangle wallBounds = new Rectangle();
	Rectangle VertwallBounds = new Rectangle();

	QuestManager myQuestManager = new QuestManager();

	private int dx = 4;
	private int dy = 4;

	private boolean ghostAbilities = false; // when true, the ghost can float
											// thru walls and hide from the
											// owner, but NOT pick up fruit
	private boolean solidEnough = false; // when true, the ghost can pick up
											// fruit but NOT float thru walls
											// nor hide

	private boolean collisionOccured = false;
	private boolean stopR = false;
	private boolean stopL = false;
	private boolean stopU = false;
	private boolean stopD = false;
	private boolean zPress = false; // this controls the ghost abilities

	public boolean trippedCherry = false;
	public boolean trippedFruit = false;
	
	public boolean isLoading = true;

	/**** this code for the enemy's movement paths ***/

	int enemyMark1X = 350;
	int enemyMark1Y = 1300;

	int enemyMark2X = 1135;
	int enemyMark2Y = 1300;

	int enemyMark3X = 1135;
	int enemyMark3Y = 520;

	int enemyMark4X = 790;
	int enemyMark4Y = 520;

	int enemyMark5X = 790;
	int enemyMark5Y = 325;

	int enemyMoveCounter0a = 1; // this is for the first level ONLY. enemy just
								// walks back and forth in bottom room
	int enemyMoveCounter0b = 1; // this is for the first level ONLY. enemy just
								// walks back and forth in bottom room

	int enemyMoveCounter = 1;
	int enemyMoveCounter2 = 1;
	int enemyMoveCounter3 = 1;
	int enemyMoveCounter4 = 1;
	int enemyMoveCounter5 = 1;
	int enemyMoveCounter6 = 1;
	int enemyMoveCounter7 = 1;
	int enemyMoveCounter8 = 1;

	// boolean gtr0a = true; // this is for the first level ONLY. enemy just
	// walks back and forth in bottom room
	boolean gtr0b = false; // this is for the first level ONLY. enemy just walks
							// back and forth in bottom room

	boolean gtr1 = true;
	boolean gtr2 = false;
	boolean gtr3 = false;
	boolean gtr4 = false;
	boolean gtr5 = false;
	boolean gtr6 = false;
	boolean gtr7 = false;
	boolean gtr8 = false;

	public ArrayList<int[]> blockedList = new ArrayList<int[]>();

	public boolean gameOverB = false;

	private float deltaAlpha = (float) 0.1; // controls how quickly the ghost
											// becomes invisible/visible
	boolean transKeyTapped = false; // needed to implement tapping of key
	boolean visibleKeyTapped = false; // turns the ghost visible again
	public int maxFood = 2;

	ArrayList<Cell> levelOnePath1 = new ArrayList<Cell>(); // this is for the
															// first level ONLY.
															// enemy just walks
															// back and forth in
															// bottom room
	ArrayList<Cell> fLevelOnePath1 = new ArrayList<Cell>(); // this is for the
															// first level ONLY.
															// enemy just walks
															// back and forth in
															// bottom room

	ArrayList<Cell> path1 = new ArrayList<Cell>();
	ArrayList<Cell> fPath1 = new ArrayList<Cell>();

	ArrayList<Cell> path2 = new ArrayList<Cell>();
	ArrayList<Cell> fPath2 = new ArrayList<Cell>();

	ArrayList<Cell> path3 = new ArrayList<Cell>();
	ArrayList<Cell> fPath3 = new ArrayList<Cell>();

	ArrayList<Cell> path4 = new ArrayList<Cell>();
	ArrayList<Cell> fPath4 = new ArrayList<Cell>();

	// fields necessary for level switching

	// add a new boolean for each new level
	public boolean atLevelOne = true;
	public boolean atLevelTwo = false;
	public boolean atLevelThree = false;
	public boolean atLevelFour = false;
	public boolean atLevelFive = false;
	public Game currentGame;

	public FinalBuildTest() {

		super("Final Build", 1200, 800);

		clock = new GameClock();

		// LevelOne
		levelOne.add(grass);
		levelOne.add(grass2);
		levelOne.add(grass3);
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
		levelOne.add(gameOver);
		levelOne.add(ghost);
		levelOne.add(enemy);
		levelOne.add(health);
		levelOne.add(healthBar);
		levelOne.add(titleScreen);
		levelOne.add(loadingScreen);

		// levelTwo
		levelTwo.add(grass);
		levelTwo.add(grass2);
		levelTwo.add(grass3);
		levelTwo.add(woodFloor);
		levelTwo.add(woodFloor2);
		levelTwo.add(greyCarpet);
		levelTwo.add(table);
		levelTwo.add(beigeCarpet);
		levelTwo.add(fruit);
		levelTwo.add(cherry);
		levelTwo.add(bed);
		levelTwo.add(lowerLeft);
		// levelTwo.add(lowerRight);
		levelTwo.add(rightTop);
		levelTwo.add(leftBottom);
		levelTwo.add(rightBottom);
		levelTwo.add(vwall);
		levelTwo.add(wall2);
		levelTwo.add(wall4);
		levelTwo.add(vwall2);
		levelTwo.add(l2Wall);
		levelTwo.add(vwall3);
		levelTwo.add(gameOver);
		levelTwo.add(ghost);
		levelTwo.add(enemy);
		levelTwo.add(longHallwayRightLevel2);
		levelTwo.add(topHallwayLevelTwo);
		levelTwo.add(upperRightHallway);
		levelTwo.add(hallwayBottom);
		levelTwo.add(strawberry);
		levelTwo.add(menu2);
		levelTwo.add(health);
		levelTwo.add(healthBar);

		// levelThree
		levelThree.add(grass);
		levelThree.add(grass2);
		levelThree.add(grass3);
		levelThree.add(woodFloor);
		levelThree.add(woodFloor2);
		levelThree.add(greyCarpet);
		levelThree.add(redCarpet);
		levelThree.add(table);
		levelThree.add(beigeCarpet);
		levelThree.add(fruit);
		levelThree.add(cherry);
		levelThree.add(banana);
		levelThree.add(bed);
		levelThree.add(lowerLeft);
		// levelThree.add(lowerRight);
		levelThree.add(rightTop);
		levelThree.add(leftBottom);
		levelThree.add(rightBottom);
		levelThree.add(vwall);
		levelThree.add(wall2);
		levelThree.add(wall4);
		levelThree.add(vwall2);
		levelThree.add(vwall3);
		levelThree.add(gameOver);
		levelThree.add(ghost);
		levelThree.add(enemy);
		levelThree.add(longHallwayRightLevel2);
		levelThree.add(leftSideMiddleRoom);
		levelThree.add(topHallwayLevelThree);
		levelThree.add(upperRightHallway);
		levelThree.add(hallwayBottom);
		levelThree.add(menu3);
		levelThree.add(health);
		levelThree.add(healthBar);

		// levelFour
		levelFour.add(grass);
		levelFour.add(grass2);
		levelFour.add(grass3);
		levelFour.add(woodFloor);
		levelFour.add(woodFloor2);
		levelFour.add(greyCarpet);
		levelFour.add(greyCarpet2);
		levelFour.add(redCarpet);
		levelFour.add(table);
		levelFour.add(beigeCarpet);
		levelFour.add(fruit);
		levelFour.add(cherry);
		levelFour.add(bed);
		levelFour.add(lowerLeft);
		levelFour.add(rightTop);
		levelFour.add(leftBottom);
		levelFour.add(rightBottom);
		levelFour.add(vwall);
		levelFour.add(wall2);
		levelFour.add(wall4);
		levelFour.add(vwall2);
		levelFour.add(vwall3);
		levelFour.add(gameOver);
		levelFour.add(ghost);
		levelFour.add(enemy);
		levelFour.add(longHallwayRight);
		levelFour.add(leftSideMiddleRoom);
		levelFour.add(topHallway);
		levelFour.add(upperRightHallway);
		levelFour.add(hallwayBottom);
		levelFour.add(leftHouseTop);
		levelFour.add(houseTop);
		levelFour.add(topRoomBottom);
		levelFour.add(menu4);
		levelFour.add(health);
		levelFour.add(healthBar);

		// levelFive
		levelFive.add(grass);
		levelFive.add(grass2);
		levelFive.add(grass3);
		levelFive.add(woodFloor);
		levelFive.add(woodFloor2);
		levelFive.add(greyCarpet);
		levelFive.add(greyCarpet2);
		levelFive.add(redCarpet);
		levelFive.add(table);
		levelFive.add(beigeCarpet);
		levelFive.add(fruit);
		levelFive.add(cherry);
		levelFive.add(bed);
		levelFive.add(lowerLeft);
		levelFive.add(rightTop);
		levelFive.add(leftBottom);
		levelFive.add(rightBottom);
		levelFive.add(vwall);
		levelFive.add(wall2);
		levelFive.add(wall4);
		levelFive.add(vwall2);
		levelFive.add(vwall3);
		levelFive.add(gameOver);
		levelFive.add(ghost);
		levelFive.add(enemy);
		levelFive.add(longHallwayRight);
		levelFive.add(leftSideMiddleRoom);
		levelFive.add(topHallway);
		levelFive.add(upperRightHallway);
		levelFive.add(hallwayBottom);
		levelFive.add(leftHouseTop);
		levelFive.add(houseTop);
		levelFive.add(topRoomBottom);
		levelFive.add(menu5);
		levelFive.add(health);
		levelFive.add(healthBar);

		this.getScenePanel().setBackground(Color.gray);

		healthBar.setXPos(10);
		// healthBar.setYPos(10);
		ghost.setTrans(0.0f);
		ghost.setXPos(3);
		grass.setYPos(800);
		grass.setXPos(0);
		grass2.setYPos(-800 + 800);
		grass3.setYPos(-1400 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/

		// sky.setYPos(0);

		woodFloor.setXPos(250);
		woodFloor.setYPos(450 + 800);
		woodFloor.setRotation(1.57);
		woodFloor.setXScale(2.3);
		woodFloor.setYScale(0.7);

		topRoomBottom.setXPos(100);
		topRoomBottom.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		topRoomBottom.setXScale(4.7);

		greyCarpet.setXPos(600);
		greyCarpet.setYPos(180 + 800);
		greyCarpet.setYScale(0.67);

		greyCarpet2.setXPos(100);
		greyCarpet2.setYPos(-650 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		greyCarpet2.setXScale(2.55);
		greyCarpet2.setYScale(0.75);

		beigeCarpet.setXPos(100);
		beigeCarpet.setYPos(195 + 800);
		beigeCarpet.setYScale(0.93);
		beigeCarpet.setXScale(1.2);
		// beigeCarpet.setYScale(0.67);

		table.setXPos(700);
		table.setYPos(325 + 800);
		table.setXScale(0.035);
		table.setYScale(0.035);

		ghost.setYPos(780 - ghost.getScaledHeight() + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		marioTween.doTween(true);
		marioTween.animate(TweenableParam.FADE_IN, 0.0f, 1.0f, 6000);

		fruit.setXPos(500);
		fruit.setYPos(300 + 800);
		fruit.addEventListener(myQuestManager, null);

		woodFloor2.setXPos(1060);
		woodFloor2.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		// woodFloor2.setRotation(1.57);
		woodFloor2.setXScale(0.9);
		woodFloor2.setYScale(2.4);

		bed.setXPos(130);
		bed.setYPos(300 + 800);
		bed.setXScale(0.4);
		bed.setYScale(0.4);

		redCarpet.setXPos(600);
		redCarpet.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		redCarpet.setYScale(1.53);
		redCarpet.setXScale(1.53);

		cherry.setXPos(710);
		cherry.setYPos(310 + 800);
		cherry.addEventListener(myQuestManager, null);

		health.setXScale(0.004);
		l2Wall.setYPos(-300 + 800);
		l2Wall.setYPos(1300);

		lowerLeft.setXPos(100);
		lowerLeft.setYPos(400 + 800);

		lowerRight.setXPos(1062);
		lowerRight.setYPos(400 + 800);

		rightTop.setXPos(600);
		rightTop.setYPos(156 + 800);

		leftBottom.setXPos(100);
		leftBottom.setYPos(700 + 800);

		rightBottom.setXPos(600);
		rightBottom.setYPos(700 + 800);

		wall2.setXPos(300);
		wall2.setYPos(500 - vwall.getScaledHeight() - wall2.getScaledHeight()
				+ 800);
		wall2.addEventListener(myQuestManager, null);

		wall4.setXPos(300 + wall2.getScaledWidth());
		wall4.setYPos(500 - vwall.getScaledHeight() - wall2.getScaledHeight()
				+ 800);
		wall4.addEventListener(myQuestManager, null);

		vwall.setXPos(100);
		vwall.setYPos(500 - vwall.getScaledHeight() + 800);
		vwall.addEventListener(myQuestManager, null);

		vwall2.setXPos(300 + (2 * wall2.getScaledWidth())
				- vwall.getScaledWidth());
		System.out.println("vwall2 x pos: " + vwall2.getXPos());
		vwall2.setYPos(500 - vwall.getScaledHeight() + 800);
		vwall2.addEventListener(myQuestManager, null);

		vwall3.setXPos(1062);
		// vwall3.setXPos(300+(2*wall2.getScaledWidth())-vwall.getScaledWidth());
		vwall3.setYPos(500 - vwall.getScaledHeight() + 800);
		vwall3.addEventListener(myQuestManager, null);

		longHallwayRight.setXPos(1300); // extended for level 3
		longHallwayRight.setYPos(-650 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		longHallwayRight.setYScale(8.67);

		longHallwayRightLevel2.setXPos(1300); // extended for level 3
		longHallwayRightLevel2.setYPos(-650 + 800 + 360);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		longHallwayRightLevel2.setYScale(6.45);

		topHallway.setXPos(957);
		topHallway.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		topHallway.setXScale(2.5);

		topHallwayLevelTwo.setXPos(1060);
		topHallwayLevelTwo.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		topHallwayLevelTwo.setXScale(1.8);

		topHallwayLevelThree.setXPos(570);
		topHallwayLevelThree.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		topHallwayLevelThree.setXScale(5.05);

		upperRightHallway.setXPos(1063);
		upperRightHallway.setYPos(-150 + 800 + 50);
		/***** SHIFTED DOWN BY 800 PIXELS AND MOVED DOWN FOR THE ENEMY ****/
		upperRightHallway.setYScale(2);

		houseTop.setXPos(100);
		houseTop.setYPos(-650 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		houseTop.setXScale(8);

		hallwayBottom.setXPos(1063);
		hallwayBottom.setYPos(700 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		hallwayBottom.setXScale(1.7);

		houseTop.setXPos(100);
		houseTop.setYPos(-650 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		houseTop.setXScale(8);

		leftHouseTop.setXPos(100);
		leftHouseTop.setYPos(-650 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		leftHouseTop.setYScale(2.37);

		leftSideMiddleRoom.setXPos(567);
		leftSideMiddleRoom.setYPos(-270 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		leftSideMiddleRoom.setYScale(2.8);

		topRoomBottom.setXPos(100);
		topRoomBottom.setYPos(-300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		topRoomBottom.setXScale(4.7);

		// wall.setXPos(300);
		// wall.setYPos(vwall.getYPos()+vwall.getScaledHeight());

		fruitTween.addEventListener(myQuestManager, null);
		fruitTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTween.addEventListener(myQuestManager, null);
		cherryTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		banana.setXScale(0.25);
		banana.setYScale(0.25);
		banana.setXPos(730);
		banana.setYPos(-100 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		banana.addEventListener(myQuestManager, null);

		// titleScreen.setVisible(true);
		menu2.setVisible(false);
		menu3.setVisible(false);
		menu4.setVisible(false);
		menu5.setVisible(false);

		strawberry.setXScale(0.25);
		strawberry.setYScale(0.25);
		strawberry.setXPos(1170);
		strawberry.setYPos(300 + 800);
		/***** SHIFTED DOWN BY 800 PIXELS ****/
		strawberry.addEventListener(myQuestManager, null);

		strawberryTween.addEventListener(myQuestManager, null);
		strawberryTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		bananaTween.addEventListener(myQuestManager, null);
		bananaTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		juggler.add(marioTween);
		juggler.add(fruitTween);
		juggler.add(cherryTween);
		juggler.add(strawberryTween);
		juggler.add(bananaTween);

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

		// enemy code
		enemy.setTrans(1.0f);
		enemy.setXPos(350);
		enemy.setYPos(1300);

		/****************************** Creates the path **********************/

		System.out.println("Called levelOnePath1");

		levelOnePath1 = AStar.test(1, 4000, 4000, enemyMark1X, enemyMark1Y,
				enemyMark2X - 300, enemyMark2Y, blockedList);
		int pLen0 = levelOnePath1.size();

		int q0 = pLen0 - 1;

		while (q0 >= 0) {
			Cell temp = new Cell(levelOnePath1.get(q0).i,
					levelOnePath1.get(q0).j);
			fLevelOnePath1.add(temp);
			q0 -= 1;
		}

		System.out.println("Called 1");
		path1 = AStar.test(1, 4000, 4000, enemyMark1X, enemyMark1Y,
				enemyMark2X, enemyMark2Y, blockedList);
		int pLen = path1.size();

		int q = pLen - 1;

		/** prints the path **/
		while (q >= 0) {
			Cell temp = new Cell(path1.get(q).i, path1.get(q).j);
			fPath1.add(temp);
			q -= 1;
		}

		System.out.println("Called 2");
		path2 = AStar.test(1, 4000, 4000, enemyMark2X, enemyMark2Y,
				enemyMark3X, enemyMark3Y, blockedList);
		int pLen2 = path2.size();

		int q2 = pLen2 - 1;

		/** prints the path **/

		while (q2 >= 0) {
			Cell temp = new Cell(path2.get(q2).i, path2.get(q2).j);
			fPath2.add(temp);
			q2 -= 1;
		}

		System.out.println("Called 3");
		path3 = AStar.test(1, 4000, 4000, enemyMark3X, enemyMark3Y,
				enemyMark4X, enemyMark4Y, blockedList);
		int pLen3 = path3.size();

		int q3 = pLen3 - 1;

		/** prints the path **/

		while (q3 >= 0) {
			Cell temp = new Cell(path3.get(q3).i, path3.get(q3).j);
			fPath3.add(temp);
			q3 -= 1;
		}

		System.out.println("Called 4");

		path4 = AStar.test(1, 4000, 4000, enemyMark4X, enemyMark4Y,
				enemyMark5X, enemyMark5Y, blockedList);

		System.out.println("finished path 4");
		int pLen4 = path4.size();

		int q4 = pLen4 - 1;

		/** prints the path **/

		while (q4 >= 0) {
			Cell temp = new Cell(path4.get(q4).i, path4.get(q4).j);
			fPath4.add(temp);
			q4 -= 1;
		}
		
		isLoading = false;

	}

	public void update(ArrayList<String> pressedKeys) {

		/**
		 * we must include a way to check if the walls are not null before they
		 * are drawn it doesn't truly affect the game but it gives us a lot of
		 * error warnings in the console but it seems to make the drawing of the
		 * objects to the screen slow...might want to ask Floryan
		 */

		camera.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		camera.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (camera.getXPos() > offsetMaxX) {
			camera.setXPos(offsetMaxX);
		} else if (camera.getXPos() < offsetMinX) {
			camera.setXPos(offsetMinX);
		}
		if (camera.getYPos() > offsetMaxY) {
			camera.setYPos(offsetMaxY);
		} else if (camera.getXPos() < offsetMinY) {
			camera.setYPos(offsetMinY);
		}

		health.setXPos((ghost.getXPos() - (VIEWPORT_SIZE_X / 2)));
		health.setYPos((ghost.getYPos() - VIEWPORT_SIZE_Y / 2));
		if (health.getXPos() > offsetMaxX) {
			health.setXPos(offsetMaxX);
		} else if (health.getXPos() < offsetMinX) {
			health.setXPos(offsetMinX);
		}
		if (health.getYPos() > offsetMaxY) {
			health.setYPos(offsetMaxY);
		} else if (health.getXPos() < offsetMinY) {
			health.setYPos(offsetMinY);
		}
		super.update(pressedKeys);

		healthBar.setXPos((ghost.getXPos() - VIEWPORT_SIZE_X / 2));
		healthBar.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (healthBar.getXPos() > offsetMaxX) {
			healthBar.setXPos(offsetMaxX);
		} else if (healthBar.getXPos() < offsetMinX) {
			healthBar.setXPos(offsetMinX);
		}
		if (healthBar.getYPos() > offsetMaxY) {
			healthBar.setYPos(offsetMaxY);
		} else if (healthBar.getXPos() < offsetMinY) {
			healthBar.setYPos(offsetMinY);
		}
		super.update(pressedKeys);

		gameOver.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		gameOver.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (gameOver.getXPos() > offsetMaxX) {
			gameOver.setXPos(offsetMaxX);
		} else if (gameOver.getXPos() < offsetMinX) {
			gameOver.setXPos(offsetMinX);
		}
		if (gameOver.getYPos() > offsetMaxY) {
			gameOver.setYPos(offsetMaxY);
		} else if (gameOver.getXPos() < offsetMinY) {
			gameOver.setYPos(offsetMinY);
		}
		super.update(pressedKeys);

		menu2.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		menu2.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (menu2.getXPos() > offsetMaxX) {
			menu2.setXPos(offsetMaxX);
		} else if (menu2.getXPos() < offsetMinX) {
			menu2.setXPos(offsetMinX);
		}
		if (menu2.getYPos() > offsetMaxY) {
			menu2.setYPos(offsetMaxY);
		} else if (menu2.getXPos() < offsetMinY) {
			menu2.setYPos(offsetMinY);
		}
		
		
		
		if(isLoading==true){
			loadingScreen.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
			loadingScreen.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
			if (loadingScreen.getXPos() > offsetMaxX) {
				loadingScreen.setXPos(offsetMaxX);
			} else if (loadingScreen.getXPos() < offsetMinX) {
				loadingScreen.setXPos(offsetMinX);
			}
			if (loadingScreen.getYPos() > offsetMaxY) {
				loadingScreen.setYPos(offsetMaxY);
			} else if (loadingScreen.getXPos() < offsetMinY) {
				loadingScreen.setYPos(offsetMinY);
			}
		}
		
		if(isLoading==false){
			loadingScreen.setVisible(false);
			titleScreen.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
			titleScreen.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
			if (titleScreen.getXPos() > offsetMaxX) {
				titleScreen.setXPos(offsetMaxX);
			} else if (titleScreen.getXPos() < offsetMinX) {
				titleScreen.setXPos(offsetMinX);
			}
			if (titleScreen.getYPos() > offsetMaxY) {
				titleScreen.setYPos(offsetMaxY);
			} else if (titleScreen.getXPos() < offsetMinY) {
				titleScreen.setYPos(offsetMinY);
			}
		}

		menu3.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		menu3.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (menu3.getXPos() > offsetMaxX) {
			menu3.setXPos(offsetMaxX);
		} else if (menu3.getXPos() < offsetMinX) {
			menu3.setXPos(offsetMinX);
		}
		if (menu3.getYPos() > offsetMaxY) {
			menu3.setYPos(offsetMaxY);
		} else if (menu3.getXPos() < offsetMinY) {
			menu3.setYPos(offsetMinY);
		}

		menu4.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		menu4.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);

		menu5.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		menu5.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);

		camera.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		camera.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (camera.getXPos() > offsetMaxX) {
			camera.setXPos(offsetMaxX);
		} else if (camera.getXPos() < offsetMinX) {
			camera.setXPos(offsetMinX);
		}
		if (camera.getYPos() > offsetMaxY) {
			camera.setYPos(offsetMaxY);
		} else if (camera.getXPos() < offsetMinY) {
			camera.setYPos(offsetMinY);
		}
		super.update(pressedKeys);
		super.update(pressedKeys);

		if (ghost != null) {
			if (fruit != null) {
				if (enemy != null) {

					if (gtr1 == true) {

						if (atLevelOne) { // so if we are in level one, the
											// enemy will go to a certain mark
							if (enemyMoveCounter0a < fLevelOnePath1.size()) {
								Cell moveTo = fLevelOnePath1
										.get(enemyMoveCounter0a);
								int xm = moveTo.i;
								int ym = moveTo.j;
								enemy.setXPos(xm);
								enemy.setYPos(ym);
								enemy.goBackward(true); // THIS IS WHERE TO TELL
														// THE SPRITE TO CHANGE
														// DIRECTIONS
								// enemyMoveCounter+=1;
								enemyMoveCounter0a += 5; // increases the
															// owner's speed
							}

							if (enemyMoveCounter0a >= fLevelOnePath1.size()) {
								enemyMoveCounter0a = 1;
								gtr0b = true;
								gtr1 = false;
							}
						}

						else {
							if (enemyMoveCounter < fPath1.size()) {
								Cell moveTo = fPath1.get(enemyMoveCounter);
								int xm = moveTo.i;
								int ym = moveTo.j;
								enemy.setXPos(xm);
								enemy.setYPos(ym);
								enemy.goBackward(true); // THIS IS WHERE TO TELL
														// THE SPRITE TO CHANGE
														// DIRECTIONS
								// enemyMoveCounter+=1;
								enemyMoveCounter += 5; // increases the owner's
														// speed
							}

							if (enemyMoveCounter >= fPath1.size()) {
								enemyMoveCounter = 1;
								gtr1 = false;
								gtr2 = true;
								gtr3 = false;
								gtr4 = false;
							}
						}
					}

					if (gtr0b) {
						if (enemyMoveCounter0b < levelOnePath1.size()) {
							Cell moveTo = levelOnePath1.get(enemyMoveCounter0b);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);

							enemy.goForward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							// enemyMoveCounter+=1;
							enemyMoveCounter0b += 5; // increases the owner's
														// speed
						}

						if (enemyMoveCounter0b >= levelOnePath1.size()) {
							enemyMoveCounter0b = 1;
							gtr1 = true;
							gtr0b = false;
						}
					}

					if (gtr2 == true) {
						if (enemyMoveCounter2 < fPath2.size()) {
							Cell moveTo = fPath2.get(enemyMoveCounter2);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true); // THIS IS WHERE TO TELL THE
							// SPRITE TO CHANGE
							// DIRECTIONS
							enemyMoveCounter2 += 5; // makes the owner move
							// faster
						}

						if (enemyMoveCounter2 >= fPath2.size()) {
							enemyMoveCounter2 = 1;

							if (atLevelTwo) {
								gtr1 = false;
								gtr2 = false;
								gtr3 = false;
								gtr4 = false;
								gtr7 = true;
							}

							if (atLevelThree || atLevelFour || atLevelFive) {
								gtr2 = false;
								gtr1 = false;
								gtr3 = true;
								gtr4 = false;
							}
						}
					}

					if (gtr3 == true) {
						if (enemyMoveCounter3 < fPath3.size()) {
							Cell moveTo = fPath3.get(enemyMoveCounter3);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true);
							enemyMoveCounter3 += 3;
						}
						if (enemyMoveCounter3 >= fPath3.size()) {
							enemyMoveCounter3 = 1;
							if (atLevelThree) {
								gtr2 = false;
								gtr1 = false;
								gtr3 = false;
								gtr4 = false;
								gtr6 = true;
							}

							if (atLevelFour || atLevelFive) {
								gtr2 = false;
								gtr1 = false;
								gtr3 = false;
								gtr4 = true;
								gtr6 = false;
							}
						}
					}

					if (gtr4 == true) {
						if (enemyMoveCounter4 < fPath4.size()) {
							Cell moveTo = fPath4.get(enemyMoveCounter4);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							enemyMoveCounter4 += 5; // makes the owner move
													// faster
						}

						if (enemyMoveCounter4 >= fPath4.size()) {
							enemyMoveCounter4 = 1;
							gtr2 = false;
							gtr1 = false;
							gtr3 = false;
							gtr4 = false;
							gtr5 = true;
						}
					}

					if (gtr5 == true) {
						if (enemyMoveCounter5 < path4.size()) {
							Cell moveTo = path4.get(enemyMoveCounter5);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goBackward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							enemyMoveCounter5 += 3; // makes the owner move
													// faster
						}

						if (enemyMoveCounter5 >= path4.size()) {
							enemyMoveCounter5 = 1;
							gtr2 = false;
							gtr1 = false;
							gtr3 = false;
							gtr4 = false;
							gtr5 = false;
							gtr6 = true;
						}
					}

					if (gtr6 == true) {
						if (enemyMoveCounter6 < path3.size()) {
							Cell moveTo = path3.get(enemyMoveCounter6);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goBackward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							enemyMoveCounter6 += 5; // makes the owner move
													// faster
						}
						if (enemyMoveCounter6 >= path3.size()) {
							enemyMoveCounter6 = 1;
							gtr2 = false;
							gtr1 = false;
							gtr3 = false;
							gtr4 = false;
							gtr5 = false;
							gtr6 = false;
							gtr7 = true;
						}
					}

					if (gtr7 == true) {
						if (enemyMoveCounter7 < path2.size()) {
							Cell moveTo = path2.get(enemyMoveCounter7);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							enemyMoveCounter7 += 3; // makes the owner move
													// faster
						}

						if (enemyMoveCounter7 >= path2.size()) {
							enemyMoveCounter7 = 1;
							gtr1 = false;
							gtr2 = false;
							gtr3 = false;
							gtr4 = false;
							gtr5 = false;
							gtr6 = false;
							gtr7 = false;
							gtr8 = true;
						}
					}

					if (gtr8 == true) {
						if (enemyMoveCounter8 < path1.size()) {
							Cell moveTo = path1.get(enemyMoveCounter8);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							enemyMoveCounter8 += 5; // makes the owner move
													// faster
						}

						if (enemyMoveCounter8 >= path1.size()) {
							enemyMoveCounter8 = 1;
							gtr1 = true;
							gtr2 = false;
							gtr3 = false;
							gtr4 = false;
							gtr5 = false;
							gtr6 = false;
							gtr7 = false;
							gtr8 = false;
						}
					}

					/*************** END OFENEMY'S PATHS CODE *****************/

					/*
					 * if (gtr1 == true) {
					 * 
					 * if (enemyMoveCounter < fPath1.size()) { Cell moveTo =
					 * fPath1.get(enemyMoveCounter); int xm = moveTo.i; int ym =
					 * moveTo.j; enemy.setXPos(xm); enemy.setYPos(ym);
					 * enemy.goForward(true); enemyMoveCounter += 1; }
					 * 
					 * if (enemyMoveCounter >= fPath1.size()) { enemyMoveCounter
					 * = 1; gtr1 = false; gtr2 = true;
					 * 
					 * }
					 * 
					 * }
					 * 
					 * if (gtr2 == true) {
					 * 
					 * if (enemyMoveCounter2 < fPath2.size()) { Cell moveTo =
					 * fPath2.get(enemyMoveCounter2); int xm = moveTo.i; int ym
					 * = moveTo.j; enemy.setXPos(xm); enemy.setYPos(ym);
					 * enemy.goBackward(true); enemyMoveCounter2 += 1; }
					 * 
					 * if (enemyMoveCounter2 >= fPath2.size()) {
					 * 
					 * enemyMoveCounter2 = 1; gtr2 = false;
					 * 
					 * gtr1 = true; } }
					 */

					juggler.nextFrame();
					
					/*if ( atLevelOne ) {
						for (Sprite wall : collDectsL1) { 

							if (ghost.collidesWith(wall) && ghostAbilities == false) {

								collisionOccured = true;

								if (ghost.getRightHitBox().intersects(
										wall.getHitBox())) {
									stopR = true;
								}

								if (ghost.getLeftHitBox().intersects(
										wall.getHitBox())) {
									stopL = true;
								}

								if (ghost.getBottomHitBox().intersects(
										wall.getHitBox())) {
									stopD = true;
								}

								if (ghost.getTopHitBox().intersects(
										wall.getHitBox())) {
									stopU = true;
								}

								break; 
							}
						}
					}*/


					for (Sprite wall : collDects) { 

						if (ghost.collidesWith(wall) && ghostAbilities == false) {
							
							System.out.println(wall.getId());
							collisionOccured = true;

							if (ghost.getRightHitBox().intersects(wall.getHitBox())) {
								System.out.println("collision from the right");
								stopR = true;
							}

							if (ghost.getLeftHitBox().intersects(wall.getHitBox())) {
								System.out.println("collision from the left");
								stopL = true;
							}

							if (ghost.getBottomHitBox().intersects(wall.getHitBox())) {
								System.out.println("collision from the bottom");
								stopD = true;
							}

							if (ghost.getTopHitBox().intersects(wall.getHitBox())) {
								System.out.println("collision from the top");
								stopU = true;
							}

							break; 
						}
					}



					if (foodCollected == maxFood) {
						healthWidth = 0;
						if (atLevelFive == false) {
							switchLevels();
						}
						// else { //display you've won sprite}
					}

					if (collisionOccured == false) {
						stopL = false;
						stopU = false;
						stopR = false;
						stopD = false;
					}

					if (collisionOccured == true) {
						collisionOccured = false;
					}

					if (ghostAbilities == true) {
						stopL = false;
						stopU = false;
						stopR = false;
						stopD = false;
					}

					if (myQuestManager.questCompleted) {
						if (fruitCollected == true && !gameOver.isVisible()) {
							fruitTween.doTween(myQuestManager.tweenComplete);
						} 
						
						else if (cherryCollected == true) {cherryTween.doTween(myQuestManager.tweenComplete);}
						else if (strawberryCollected == true
								&& !gameOver.isVisible()) {
							strawberryTween.doTween(myQuestManager.tweenComplete);
						} else if (bananaCollected == true) {
							bananaTween.doTween(myQuestManager.tweenComplete);
						}
					}
					// questConfirm.setVisible(true);
				}

				if (pressedKeys.contains("S") && titleScreen.isVisible()) {
					System.out.println("got through menu and S pressed");
					titleScreen.setVisible(false);
					titleScreenActive = false;
				}

				if ((pressedKeys.contains("N") && menu2.isVisible())
						|| (pressedKeys.contains("N") && menu3.isVisible())
						|| (pressedKeys.contains("N") && menu4.isVisible())
						|| (pressedKeys.contains("N") && menu5.isVisible())) {

					if (menu2.isVisible()) {
						menu2.setVisible(false);
						menu2Active = false;
					}

					if (menu3.isVisible()) {
						menu3.setVisible(false);
						menu3Active = false;
					}
					if (menu4.isVisible()) {
						menu4.setVisible(false);
						menu4Active = false;
					}
					if (menu5.isVisible()) {
						menu5.setVisible(false);
						menu5Active = false;
					}

				}
				if (pressedKeys.contains("Q")) {
					System.exit(0);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(38))) {
					if (stopU == false && !(menu2.isVisible())
							&& !(menu3.isVisible()) && !(menu4.isVisible())
							&& !(menu5.isVisible())
							&& !(titleScreen.isVisible()))
						ghost.setYPos(ghost.getYPos() - dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
					if (stopD == false && !(menu2.isVisible())
							&& !(menu3.isVisible()) && !(menu4.isVisible())
							&& !(menu5.isVisible())
							&& !(titleScreen.isVisible()))
						ghost.setYPos(ghost.getYPos() + dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
					if (stopR == false && !(menu2.isVisible())
							&& !(menu3.isVisible()) && !(menu4.isVisible())
							&& !(menu5.isVisible())
							&& !(titleScreen.isVisible()))
						ghost.setXPos(ghost.getXPos() + dx);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
					if (stopL == false && !(menu2.isVisible())
							&& !(menu3.isVisible()) && !(menu4.isVisible())
							&& !(menu5.isVisible())
							&& !(titleScreen.isVisible()))
						ghost.setXPos(ghost.getXPos() - dx);
				}

				/******************* TAPPING AND VISIBILITY ****************/
				if (!transKeyTapped
						&& pressedKeys.contains(KeyEvent.getKeyText(88))) {
					if (ghost.getTrans() - deltaAlpha > 0.0f) {
						ghost.setTrans(ghost.getTrans() - deltaAlpha);
					}
					transKeyTapped = true;
				}

				if (!pressedKeys.contains(KeyEvent.getKeyText(88))) {
					transKeyTapped = false;
				}

				if (!visibleKeyTapped
						&& pressedKeys.contains(KeyEvent.getKeyText(90))) {
					if (ghost.getTrans() + deltaAlpha < 1.0f) {
						ghost.setTrans(ghost.getTrans() + deltaAlpha);
					}
					visibleKeyTapped = true;
				}

				if (!pressedKeys.contains(KeyEvent.getKeyText(90))) {
					visibleKeyTapped = false;
				}

				if (ghost.getTrans() >= 1.0f - deltaAlpha) {
					ghostAbilities = false;
					solidEnough = true;
				}

				if ((0.0f + deltaAlpha < ghost.getTrans())
						&& (ghost.getTrans() < 1.0f - deltaAlpha)) {
					ghostAbilities = false;
					solidEnough = false;
				}

				if (ghost.getTrans() <= 0.0f + deltaAlpha) {
					ghostAbilities = true;
					solidEnough = false;
				}

				/********* ENEMY COLLISION DETECTION ******/

				for (Rectangle room : listOfRooms) {

					if (enemy.getHitBox().intersects(room)
							&& ghost.getHitBox().intersects(room)
							&& !ghostAbilities) {
						if (gameOverB == false) {
							try {
								soundManager.playSoundEffect("caught");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameOver.setVisible(true);
							gameOverB = true;
							// System.out.println(room);
						}
					}
				}

				if ((ghost.collidesWith(banana) && solidEnough == true && !gameOver
						.isVisible())) {
					banana.dispatchEvent(new Event(Event.COIN_PICKED_UP, banana));
					if (trippedBanana == false) {
						if (atLevelOne) {
							health.setXScale(health.getXScale() + 2);
						}
						// health.setXScale(health.getXScale() + 0.4);
						bananaCollected = true;
						trippedBanana = true;
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected += 1;
					}
					collected = false;
					bananaTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, bananaTween));
					// makes orange tween even though it's the cherry that's
					// being overlapped
				}

				if ((ghost.collidesWith(fruit) && solidEnough == true && !gameOver
						.isVisible())) {
					fruit.dispatchEvent(new Event(Event.COIN_PICKED_UP, fruit));
					fruitCollected = true;

					if (atLevelOne == true && health.getXScale() < 4
							&& trippedFruit == false) {
						health.setXScale(health.getXScale() + 0.95);
						trippedFruit = true;
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected += 1;
					}

					fruitTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTween));
				}

				if ((ghost.collidesWith(cherry) && solidEnough == true && !gameOver
						.isVisible())) {
					cherry.dispatchEvent(new Event(Event.COIN_PICKED_UP, cherry));
					cherryCollected = true;

					if (atLevelOne == true && health.getXScale() < 4
							&& trippedCherry == false) {
						health.setXScale(health.getXScale() + 0.95);
						trippedCherry = true;
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected += 1;
					}

					cherryTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTween));
				}

				if ((ghost.collidesWith(strawberry) && solidEnough == true && !gameOver
						.isVisible())) {
					strawberry.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberry));
					if (trippedStrawberry == false) {
						// health.setXScale(health.getXScale() + 0.4);
						strawberryCollected = true;
						trippedStrawberry = true;
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("foodcollected: " + foodCollected);
						System.out.println("maxFood: " + maxFood);
						foodCollected += 1;
					}
					collected = false;
					strawberryTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, strawberryTween));
					// makes orange tween even though it's the cherry that's
					// being overlapped
				}

			}
		}
	}

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to
	 * make sure ghost gets drawn to the screen, we need to make sure to
	 * override this method and call ghost's draw method.
	 * */
	@Override
	public void draw(Graphics g) {
		g.translate((int) -camera.getXPos(), (int) -camera.getYPos());
		super.draw(g);
		if (isLoading == true) {
			loadingScreen.setVisible(true);
			titleScreen.setVisible(false);
			//System.out.println("titleScreenDrawn" + " visibility: "
					//+ titleScreen.isVisible());
			loadingScreen.draw(g);
		}
		if (titleScreenActive == true&& isLoading==false) {
			titleScreen.setVisible(true);
			loadingScreen.setVisible(false);
			System.out.println("titleScreenDrawn" + " visibility: "
					+ titleScreen.isVisible());
			titleScreen.draw(g);
		}

		if (atLevelOne == true) {
			levelOne.draw(g);
			g.setColor(Color.red);
			g.fillRect(20, 30, healthWidth, 22);
			healthBar.draw(g);
		}

		// else { levelTwo.draw(g);}

		if (atLevelTwo == true) {
			// System.out.println("entering level 2");
			if (menu2Active == true) {
				menu2.setVisible(true);
			} else {
				menu2.setVisible(false);
			}
			levelTwo.draw(g);
		}
		if (atLevelThree == true) {
			if (menu3Active == true) {
				// System.out.println("about to put up menu for 3");
				menu3.setVisible(true);
			} else {
				menu3.setVisible(false);
			}
			levelThree.draw(g);
		}
		if (atLevelFour == true) {
			if (menu4Active == true) {
				// System.out.println("about to put up menu for 4");
				menu4.setVisible(true);
			} else {
				menu4.setVisible(false);
			}
			levelFour.draw(g);
		}
		if (atLevelFive == true) { // atLevelFive
			if (menu5Active == true) {
				menu5.setVisible(true);
			} else {
				menu5.setVisible(false);
			}
			levelFive.draw(g);
		}

	}

	// Level Switching code
	// add a new if statment for each new level
	public void switchLevels() {
		if (atLevelOne == true) {
			foodCollected = 0;
			maxFood = 1;// supposed to be 4
			atLevelOne = false;
			collDects.remove(lowerRight);
			collDects.addAll(Arrays.asList(l2Wall, hallwayBottom, longHallwayRightLevel2, upperRightHallway, topHallwayLevelTwo));
			atLevelTwo = true;
		} else if (atLevelTwo == true) {
			// System.out.println("entering level 2 (switchlevels)");
			foodCollected = 0;
			maxFood = 1; // supposed to be 6
			atLevelTwo = false;
			//collDects.remove(topHallwayLevelTwo);
			collDects.add(topHallwayLevelThree);
			collDects.add(leftSideMiddleRoom);
			listOfRooms.add(doorwayView1);
			atLevelThree = true;
		} else if (atLevelThree == true) {
			// System.out.println("entering level 3(switchlevels)");
			foodCollected = 0;
			maxFood = 8;
			atLevelThree = false;
			collDects.remove(topHallwayLevelThree);
			collDects.addAll(Arrays.asList(longHallwayRight, topHallway, leftHouseTop, houseTop, topRoomBottom));
			atLevelFour = true;
		} else if (atLevelFour == true) {
			// System.out.println("entering level 4");
			foodCollected = 0;
			maxFood = 10;
			atLevelFour = false;
			atLevelFive = true;
		}
	}

	public static void main(String[] args) {
		
		Game beta = new FinalBuildTest();
		beta.start();
	}
}
