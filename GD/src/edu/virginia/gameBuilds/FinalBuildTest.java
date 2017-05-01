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
	Rectangle doorwayView1 = new Rectangle(620, 550, 660, 100); // if the enemy
																// vertical
	// hallway,
	// ghost is
	// caught
	Rectangle doorwayView2 = new Rectangle(820, 225, 100, 700); // if the enemy
																// is not in the
																// top room, but
																// can "see"
																// into it from
																// top middle
																// rorom, ghost
																// is caught

	ArrayList<Rectangle> listOfRooms = new ArrayList<Rectangle>(Arrays.asList(
			topRoom, topMiddleRoom, leftMiddleRoom, rightMiddleRoom,
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
	boolean fruitCollectedL4 = false;
	boolean fruitCollectedL5 = false;
	boolean pearCollected = false;
	boolean pearCollectedL4 = false;
	boolean pearCollectedL5 = false;
	boolean pearCollectedL42 = false;
	boolean pearCollectedL52 = false;
	boolean strawberryCollectedL2 = false;
	boolean strawberryCollectedL3 = false;
	boolean strawberryCollectedL4 = false;
	boolean strawberryCollectedL5 = false;
	boolean strawberryCollectedL42 = false;
	boolean strawberryCollectedL52 = false;
	boolean cherryCollected = false;
	boolean bananaCollectedL2 = false;
	boolean bananaCollectedL3 = false;
	boolean bananaCollectedL4 = false;
	boolean bananaCollectedL5 = false;
	boolean fruitCollectedL2 = false;
	boolean fruitCollectedL3 = false;
	//boolean strawberryCollectedL2 = false;
	boolean cherryCollectedL2 = false;
	boolean cherryCollectedL3 = false;
	boolean cherryCollectedL4 = false;
	boolean cherryCollectedL5 = false;
	boolean cherryCollectedL32 = false;
	boolean cherryCollectedL42 = false;
	boolean cherryCollectedL52 = false;
	// boolean bananaCollectedL2 = false;

	fruitSprite fruit = new fruitSprite("fruit");
	fruitSprite fruitL2 = new fruitSprite("fruitL2");
	fruitSprite fruitL3 = new fruitSprite("fruitL3");
	fruitSprite fruitL4 = new fruitSprite("fruitL4");
	fruitSprite fruitL5 = new fruitSprite("fruitL5");
	cherrySprite cherry = new cherrySprite("cherry");
	cherrySprite cherryL2 = new cherrySprite("cherryL2");
	cherrySprite cherryL3 = new cherrySprite("cherryL3");
	cherrySprite cherryL4 = new cherrySprite("cherryL4");
	cherrySprite cherryL5 = new cherrySprite("cherryL5");
	cherrySprite cherryL32 = new cherrySprite("cherryL32");
	cherrySprite cherryL42 = new cherrySprite("cherryL42");
	cherrySprite cherryL52 = new cherrySprite("cherryL52");
	Sprite bananaL3 = new Sprite("bananaL3", "banana.png");
	Sprite bananaL4 = new Sprite("bananaL4", "banana.png");
	Sprite bananaL5 = new Sprite("bananaL5", "banana.png");
	Sprite pear = new Sprite("pear", "pear.png");
	Sprite pearL4 = new Sprite("pearL4", "pear.png");
	Sprite pearL5 = new Sprite("pearL5", "pear.png");
	Sprite pearL42 = new Sprite("pearL42", "pear.png");
	Sprite pearL52 = new Sprite("pearL52", "pear.png");
	Sprite grass = new Sprite("grass", "grass.jpg");
	Sprite grass2 = new Sprite("grass2", "grass.jpg");
	Sprite sky = new Sprite("sky", "sky.png");
	ghostSprite ghost = new ghostSprite("ghost");
	Sprite greyCarpet2 = new Sprite("greyCarpet", "greyCarpet.png");
	Sprite grass3 = new Sprite("grass2", "grass.jpg");
	Sprite strawberryL2 = new Sprite("strawberryL2", "strawberry.png");
	Sprite strawberryL3 = new Sprite("strawberryL3", "strawberry.png");
	Sprite strawberryL4 = new Sprite("strawberryL4", "strawberry.png");
	Sprite strawberryL5 = new Sprite("strawberryL5", "strawberry.png");
	Sprite strawberryL42 = new Sprite("strawberryL42", "strawberry.png");
	Sprite strawberryL52 = new Sprite("strawberryL52", "strawberry.png");
	Tween strawberryTweenL2 = new Tween(strawberryL2, new TweenTransition());
	Tween strawberryTweenL3 = new Tween(strawberryL3, new TweenTransition());
	Tween strawberryTweenL4 = new Tween(strawberryL4, new TweenTransition());
	Tween strawberryTweenL5 = new Tween(strawberryL5, new TweenTransition());
	Tween strawberryTweenL42 = new Tween(strawberryL42, new TweenTransition());
	Tween strawberryTweenL52 = new Tween(strawberryL52, new TweenTransition());
	Sprite bananaL2 = new Sprite("bananaL2", "banana.png");

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
	public boolean willPlayMusic = true;
	VertWallSprite longHallwayRightLevel2 = new VertWallSprite(
			"longHallwayRight"); // us
	VertWallSprite upperRightHallway = new VertWallSprite("upperRightHallway"); // use
																				// this
																				// wall's
																				// p
																				// //
																				// for
																				// collision
																				// detection
	VertWallSprite leftHouseTop = new VertWallSprite("leftHouseTop");
	VertWallSprite leftSideMiddleRoom = new VertWallSprite("leftSideMiddleRoom");
	Sprite l2Wall = new Sprite("l2Wall", "updownwall.png");
	Sprite redCarpet = new Sprite("redCarpet", "redCarpet.jpg");
	enemySprite enemy = new enemySprite("EnemyOne");

	/***** LIST OF WALLS ******/
	ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(
			lowerLeft, lowerRight, rightTop, leftBottom, rightBottom, vwall,
			wall2, wall4, vwall2, vwall3));

	Tween marioTween = new Tween(ghost, new TweenTransition());
	Tween fruitTween = new Tween(fruit, new TweenTransition());
	Tween pearTween = new Tween(pear, new TweenTransition());
	Tween pearTweenL4 = new Tween(pearL4, new TweenTransition());
	Tween pearTweenL5 = new Tween(pearL5, new TweenTransition());
	Tween pearTweenL42 = new Tween(pearL42, new TweenTransition());
	Tween pearTweenL52 = new Tween(pearL52, new TweenTransition());
	Tween cherryTween = new Tween(cherry, new TweenTransition());
	Tween fruitTweenL2 = new Tween(fruitL2, new TweenTransition());
	Tween fruitTweenL3 = new Tween(fruitL3, new TweenTransition());
	Tween fruitTweenL4 = new Tween(fruitL4, new TweenTransition());
	Tween fruitTweenL5 = new Tween(fruitL5, new TweenTransition());
	Tween cherryTweenL2 = new Tween(cherryL2, new TweenTransition());
	Tween bananaTweenL3 = new Tween(bananaL3, new TweenTransition());
	Tween cherryTweenL3 = new Tween(cherryL3, new TweenTransition());
	Tween cherryTweenL4 = new Tween(cherryL4, new TweenTransition());
	Tween cherryTweenL5 = new Tween(cherryL5, new TweenTransition());
	Tween cherryTweenL32 = new Tween(cherryL32, new TweenTransition());
	Tween cherryTweenL42 = new Tween(cherryL42, new TweenTransition());
	Tween cherryTweenL52 = new Tween(cherryL52, new TweenTransition());
	Tween bananaTweenL2 = new Tween(bananaL2, new TweenTransition());
	Tween bananaTweenL4 = new Tween(bananaL4, new TweenTransition());
	Tween bananaTweenL5 = new Tween(bananaL5, new TweenTransition());
	TweenJuggler juggler = new TweenJuggler();

	boolean trippedStrawberryL2 = false;
	boolean trippedStrawberryL3 = false;
	boolean trippedStrawberryL4 = false;
	boolean trippedStrawberryL5 = false;
	boolean trippedStrawberryL42 = false;
	boolean trippedStrawberryL52 = false;
	boolean trippedBananaL2 = false;
	boolean trippedBananaL3 = false;
	boolean trippedBananaL4 = false;
	boolean trippedBananaL5 = false;

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
	public boolean trippedPear = false;
	public boolean trippedPearL4 = false;
	public boolean trippedPearL5 = false;
	public boolean trippedPearL42 = false;
	public boolean trippedPearL52 = false;
	public boolean trippedCherryL2 = false;
	public boolean trippedCherryL4 = false;
	public boolean trippedCherryL5 = false;
	public boolean trippedFruitL2 = false;
	public boolean trippedFruitL3 = false;
	public boolean trippedFruitL4 = false;
	public boolean trippedFruitL5 = false;
	public boolean trippedCherryL3 = false;
	public boolean trippedCherryL32 = false;
	public boolean trippedCherryL42 = false;
	public boolean trippedCherryL52 = false;
	public boolean isLoading = true;

	/**** this code for the enemy's movement paths ***/

	int enemyMark1X = 250;
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
		// levelOne.add(gameOver);
		levelOne.add(ghost);
		levelOne.add(enemy);
		levelOne.add(health);
		levelOne.add(healthBar);
		levelOne.add(titleScreen);
		levelOne.add(loadingScreen);
		levelOne.add(gameOver);

		// levelTwo
		levelTwo.add(grass);
		levelTwo.add(grass2);
		levelTwo.add(grass3);
		levelTwo.add(woodFloor);
		levelTwo.add(woodFloor2);
		levelTwo.add(greyCarpet);
		levelTwo.add(table);
		levelTwo.add(beigeCarpet);
		levelTwo.add(bed);
		levelTwo.add(fruitL2);
		levelTwo.add(cherryL2);
		levelTwo.add(bananaL2);
		levelTwo.add(strawberryL2);

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
		// levelTwo.add(strawberry);
		levelTwo.add(menu2);
		levelTwo.add(health);
		levelTwo.add(healthBar);
		levelTwo.add(gameOver);

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
		levelThree.add(fruitL3);
		levelThree.add(pear);
		levelThree.add(cherryL3);
		levelThree.add(cherryL32);
		levelThree.add(strawberryL3);
		levelThree.add(bed);
		levelThree.add(bananaL3);
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
		levelThree.add(gameOver);

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
		levelFour.add(fruitL4);
		levelFour.add(cherryL4);
		levelFour.add(cherryL42);
		levelFour.add(strawberryL4);
		levelFour.add(strawberryL42);
		levelFour.add(bananaL4);
		levelFour.add(pearL4);
		levelFour.add(pearL42);
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
		levelFour.add(gameOver);

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
		levelFive.add(fruitL5);
		levelFive.add(cherryL5);
		levelFive.add(strawberryL5);
		levelFive.add(strawberryL52);
		levelFive.add(cherryL52);
		levelFive.add(bananaL5);
		levelFive.add(pearL5);
		levelFive.add(pearL52);
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
		levelFive.add(gameOver);

		this.getScenePanel().setBackground(Color.gray);

		// healthBar.setXPos(10);
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

		fruitL4.setXPos(500);
		fruitL4.setYPos(300 + 800);
		fruitL4.addEventListener(myQuestManager, null);

		fruitL5.setXPos(500);
		fruitL5.setYPos(300 + 800);
		fruitL5.addEventListener(myQuestManager, null);

		pear.setXPos(800);
		pear.setXScale(0.2);
		pear.setYScale(0.2);
		pear.setYPos(300 + 800 - 400);
		pear.addEventListener(myQuestManager, null);

		pearL4.setXPos(800);
		pearL4.setXScale(0.2);
		pearL4.setYScale(0.2);
		pearL4.setYPos(300 + 800 - 400);
		pearL4.addEventListener(myQuestManager, null);

		pearL5.setXPos(800);
		pearL5.setXScale(0.2);
		pearL5.setYScale(0.2);
		pearL5.setYPos(300 + 800 - 400);
		pearL5.addEventListener(myQuestManager, null);
		
		pearL42.setXPos(800);
		pearL42.setXScale(0.2);
		pearL42.setYScale(0.2);
		pearL42.setYPos(300 + 800 - 700);
		pearL42.addEventListener(myQuestManager, null);

		pearL52.setXPos(800);
		pearL52.setXScale(0.2);
		pearL52.setYScale(0.2);
		pearL52.setYPos(300 + 800 - 400);
		pearL52.addEventListener(myQuestManager, null);

		fruitL2.setXPos(500);
		fruitL2.setYPos(300 + 800);
		fruitL2.addEventListener(myQuestManager, null);

		fruitL3.setXPos(500);
		fruitL3.setYPos(300 + 800);
		fruitL3.addEventListener(myQuestManager, null);

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

		cherryL32.setXPos(850);
		cherryL32.setYPos(310 + 800);
		
		cherryL42.setXPos(850);
		cherryL42.setYPos(310 + 800);
		
		cherryL52.setXPos(850);
		cherryL52.setYPos(310 + 800);

		cherryL2.setXPos(710);
		cherryL2.setYPos(310 + 800);
		cherryL2.addEventListener(myQuestManager, null);

		cherryL3.setXPos(710);
		cherryL3.setYPos(310 + 800);
		cherryL3.addEventListener(myQuestManager, null);

		cherryL4.setXPos(710);
		cherryL4.setYPos(310 + 800);
		cherryL4.addEventListener(myQuestManager, null);

		cherryL5.setXPos(710);
		cherryL5.setYPos(310 + 800);
		cherryL5.addEventListener(myQuestManager, null);

		health.setXScale(1.94);
		l2Wall.setXPos(1062);
		l2Wall.setYPos(540);

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

		fruitTween.addEventListener(myQuestManager, null);
		fruitTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		pearTween.addEventListener(myQuestManager, null);
		pearTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		pearTweenL4.addEventListener(myQuestManager, null);
		pearTweenL4.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		pearTweenL5.addEventListener(myQuestManager, null);
		pearTweenL5.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		pearTweenL42.addEventListener(myQuestManager, null);
		pearTweenL42.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		pearTweenL52.addEventListener(myQuestManager, null);
		pearTweenL52.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		fruitTweenL2.addEventListener(myQuestManager, null);
		fruitTweenL2.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		fruitTweenL3.addEventListener(myQuestManager, null);
		fruitTweenL3.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		fruitTweenL4.addEventListener(myQuestManager, null);
		fruitTweenL4.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		fruitTweenL5.addEventListener(myQuestManager, null);
		fruitTweenL5.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTween.addEventListener(myQuestManager, null);
		cherryTween.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTweenL2.addEventListener(myQuestManager, null);
		cherryTweenL2.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTweenL3.addEventListener(myQuestManager, null);
		cherryTweenL3.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTweenL4.addEventListener(myQuestManager, null);
		cherryTweenL4.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTweenL5.addEventListener(myQuestManager, null);
		cherryTweenL5.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		cherryTweenL32.addEventListener(myQuestManager, null);
		cherryTweenL32.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		cherryTweenL42.addEventListener(myQuestManager, null);
		cherryTweenL42.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		cherryTweenL52.addEventListener(myQuestManager, null);
		cherryTweenL52.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		bananaL2.setXScale(0.18);
		bananaL2.setYScale(0.18);
		bananaL2.setXPos(300);
		bananaL2.setYPos(-100 + 1200);
		bananaL2.addEventListener(myQuestManager, null);
		bananaTweenL2.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		bananaL3.setXScale(0.18);
		bananaL3.setYScale(0.18);
		bananaL3.setXPos(300);
		bananaL3.setYPos(-100 + 1200);
		bananaL3.addEventListener(myQuestManager, null);
		bananaTweenL3.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		bananaL4.setXScale(0.18);
		bananaL4.setYScale(0.18);
		bananaL4.setXPos(300);
		bananaL4.setYPos(-100 + 1200);
		bananaL4.addEventListener(myQuestManager, null);
		bananaTweenL4.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		bananaL5.setXScale(0.18);
		bananaL5.setYScale(0.18);
		bananaL5.setXPos(300);
		bananaL5.setYPos(-100 + 1200);
		bananaL5.addEventListener(myQuestManager, null);
		bananaTweenL5.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		// titleScreen.setVisible(true);
		menu2.setVisible(false);
		menu3.setVisible(false);
		menu4.setVisible(false);
		menu5.setVisible(false);

		strawberryL2.setXScale(0.25);
		strawberryL2.setYScale(0.25);
		strawberryL2.setXPos(1170);
		strawberryL2.setYPos(300 + 800);
		strawberryL2.addEventListener(myQuestManager, null);

		strawberryL3.setXScale(0.25);
		strawberryL3.setYScale(0.25);
		strawberryL3.setXPos(1170);
		strawberryL3.setYPos(300 + 800);
		strawberryL3.addEventListener(myQuestManager, null);

		strawberryL4.setXScale(0.25);
		strawberryL4.setYScale(0.25);
		strawberryL4.setXPos(1170);
		strawberryL4.setYPos(300 + 800);
		strawberryL4.addEventListener(myQuestManager, null);
		
		strawberryL5.setXScale(0.25);
		strawberryL5.setYScale(0.25);
		strawberryL5.setXPos(1170);
		strawberryL5.setYPos(300 + 800);
		strawberryL5.addEventListener(myQuestManager, null);
		
		strawberryL42.setXScale(0.25);
		strawberryL42.setYScale(0.25);
		strawberryL42.setXPos(1170);
		strawberryL42.setYPos(300 + 400);
		strawberryL42.addEventListener(myQuestManager, null);
		
		strawberryL52.setXScale(0.25);
		strawberryL52.setYScale(0.25);
		strawberryL52.setXPos(1170);
		strawberryL52.setYPos(300 + 800);
		strawberryL52.addEventListener(myQuestManager, null);
		/***** SHIFTED DOWN BY 800 PIXELS ****/

		strawberryTweenL2.addEventListener(myQuestManager, null);
		strawberryTweenL2.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		strawberryTweenL3.addEventListener(myQuestManager, null);
		strawberryTweenL3.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		strawberryTweenL4.addEventListener(myQuestManager, null);
		strawberryTweenL4.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		strawberryTweenL5.addEventListener(myQuestManager, null);
		strawberryTweenL5.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		strawberryTweenL42.addEventListener(myQuestManager, null);
		strawberryTweenL42.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);
		
		strawberryTweenL52.addEventListener(myQuestManager, null);
		strawberryTweenL52.animate(TweenableParam.FADE_OUT, 1.0f, 0.0f, 6000);

		juggler.add(marioTween);
		juggler.add(fruitTween);
		juggler.add(fruitTweenL2);
		juggler.add(fruitTweenL3);
		juggler.add(fruitTweenL4);
		juggler.add(fruitTweenL5);
		juggler.add(cherryTween);
		juggler.add(cherryTweenL2);
		juggler.add(cherryTweenL3);
		juggler.add(cherryTweenL4);
		juggler.add(cherryTweenL5);
		juggler.add(cherryTweenL32);
		juggler.add(cherryTweenL42);
		juggler.add(cherryTweenL52);
		juggler.add(strawberryTweenL2);
		juggler.add(strawberryTweenL3);
		juggler.add(strawberryTweenL4);
		juggler.add(strawberryTweenL5);
		juggler.add(strawberryTweenL42);
		juggler.add(strawberryTweenL52);
		juggler.add(bananaTweenL3);
		juggler.add(bananaTweenL2);
		juggler.add(bananaTweenL4);
		juggler.add(bananaTweenL5);
		juggler.add(pearTween);
		juggler.add(pearTweenL4);
		juggler.add(pearTweenL5);
		juggler.add(pearTweenL42);
		juggler.add(pearTweenL52);

		gameOver.setVisible(false);

		try {
			soundManager = new SoundManager();
			soundManager.loadSoundEffect("caught", "resources/caught.wav");
			soundManager.loadSoundEffect("munch", "resources/munch.wav");
			soundManager.loadSoundEffect("gameOver", "resources/gameOver.wav");
			soundManager.loadMusic("bgroundmusic", "resources/bground.wav");
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
		System.out.println("Food Collected: " + foodCollected);
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

		healthBar.setXPos((ghost.getXPos() - VIEWPORT_SIZE_X / 2) + 10);
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

		if (isLoading == true) {
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

		if (isLoading == false) {
			loadingScreen.setVisible(false);
			if (willPlayMusic == true) {
				System.out.println("playing music");
				playMusic();
				willPlayMusic = false;
			}

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
		if (menu4.getXPos() > offsetMaxX) {
			menu4.setXPos(offsetMaxX);
		} else if (menu4.getXPos() < offsetMinX) {
			menu4.setXPos(offsetMinX);
		}
		if (menu4.getYPos() > offsetMaxY) {
			menu4.setYPos(offsetMaxY);
		} else if (menu4.getXPos() < offsetMinY) {
			menu4.setYPos(offsetMinY);
		}

		menu5.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		menu5.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (menu5.getXPos() > offsetMaxX) {
			menu5.setXPos(offsetMaxX);
		} else if (menu5.getXPos() < offsetMinX) {
			menu5.setXPos(offsetMinX);
		}
		if (menu5.getYPos() > offsetMaxY) {
			menu5.setYPos(offsetMaxY);
		} else if (menu5.getXPos() < offsetMinY) {
			menu5.setYPos(offsetMinY);
		}

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
								enemyMoveCounter0a += 2; // increases the
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
								enemyMoveCounter += 2; // increases the owner's
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
							enemyMoveCounter0b += 2; // increases the owner's
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
							enemyMoveCounter4 += 3; // makes the owner move
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
							enemyMoveCounter7 += 5; // makes the owner move
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
							enemyMoveCounter8 += 3; // makes the owner move
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

					juggler.nextFrame();

					for (Sprite wall : collDects) {

						if (ghost.collidesWith(wall) && ghostAbilities == false) {

							// System.out.println(wall.getId());
							collisionOccured = true;

							if (ghost.getRightHitBox().intersects(
									wall.getHitBox())) {
								// System.out.println("collision from the right");
								stopR = true;
							}

							if (ghost.getLeftHitBox().intersects(
									wall.getHitBox())) {
								// System.out.println("collision from the left");
								stopL = true;
							}

							if (ghost.getBottomHitBox().intersects(
									wall.getHitBox())) {
								// System.out.println("collision from the bottom");
								stopD = true;
							}

							if (ghost.getTopHitBox().intersects(
									wall.getHitBox())) {
								// System.out.println("collision from the top");
								stopU = true;
							}

							break;
						}
					}

					if (foodCollected == maxFood) {
						// healthWidth = 0;
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
						if (fruitCollected == true) {
							fruitTween.doTween(myQuestManager.tweenComplete);
						}
						if (fruitCollectedL2 == true) {
							fruitTweenL2.doTween(myQuestManager.tweenComplete);
						}

						if (fruitCollectedL3 == true) {
							fruitTweenL3.doTween(myQuestManager.tweenComplete);
						}
						if (fruitCollectedL4 == true) {
							fruitTweenL4.doTween(myQuestManager.tweenComplete);
						}

						if (fruitCollectedL5 == true) {
							fruitTweenL5.doTween(myQuestManager.tweenComplete);
						}

						if (cherryCollected == true) {
							cherryTween.doTween(myQuestManager.tweenComplete);
						}

						if (pearCollected == true) {
							pearTween.doTween(myQuestManager.tweenComplete);
						}

						if (pearCollectedL4 == true) {
							pearTweenL4.doTween(myQuestManager.tweenComplete);
						}

						if (pearCollectedL5 == true) {
							pearTweenL5.doTween(myQuestManager.tweenComplete);
						}
						if (pearCollectedL42 == true) {
							pearTweenL42.doTween(myQuestManager.tweenComplete);
						}

						if (pearCollectedL52 == true) {
							pearTweenL52.doTween(myQuestManager.tweenComplete);
						}
						if (cherryCollectedL2 == true) {
							cherryTweenL2.doTween(myQuestManager.tweenComplete);
						}

						if (cherryCollectedL3 == true) {
							cherryTweenL3.doTween(myQuestManager.tweenComplete);
						}
						if (cherryCollectedL4 == true) {
							cherryTweenL4.doTween(myQuestManager.tweenComplete);
						}

						if (cherryCollectedL5 == true) {
							cherryTweenL3.doTween(myQuestManager.tweenComplete);
						}

						if (cherryCollectedL32 == true) {
							cherryTweenL32
									.doTween(myQuestManager.tweenComplete);
						}
						if (cherryCollectedL42 == true) {
							cherryTweenL42
									.doTween(myQuestManager.tweenComplete);
						}
						if (cherryCollectedL52 == true) {
							cherryTweenL52
									.doTween(myQuestManager.tweenComplete);
						}
						if (strawberryCollectedL2 == true
								&& !gameOver.isVisible()) {
							strawberryTweenL2
									.doTween(myQuestManager.tweenComplete);
						}
						if (strawberryCollectedL3 == true
								&& !gameOver.isVisible()) {
							strawberryTweenL3
									.doTween(myQuestManager.tweenComplete);
						}
						if (strawberryCollectedL4 == true
								&& !gameOver.isVisible()) {
							strawberryTweenL4
									.doTween(myQuestManager.tweenComplete);
						}
						if (strawberryCollectedL5 == true
								&& !gameOver.isVisible()) {
							strawberryTweenL5
									.doTween(myQuestManager.tweenComplete);
						}
						
						if (strawberryCollectedL42 == true
								&& !gameOver.isVisible()) {
							strawberryTweenL42
									.doTween(myQuestManager.tweenComplete);
						}
						if (strawberryCollectedL52 == true
								&& !gameOver.isVisible()) {
							strawberryTweenL52
									.doTween(myQuestManager.tweenComplete);
						}
						if (bananaCollectedL2 == true) {
							System.out.println("tweening");
							bananaTweenL2.doTween(myQuestManager.tweenComplete);
						}
						if (bananaCollectedL3 == true) {
							bananaTweenL3.doTween(myQuestManager.tweenComplete);
						}
						if (bananaCollectedL4 == true) {
							System.out.println("tweening");
							bananaTweenL4.doTween(myQuestManager.tweenComplete);
						}
						if (bananaCollectedL5 == true) {
							bananaTweenL5.doTween(myQuestManager.tweenComplete);
						}
						
					}
					// questConfirm.setVisible(true);
				}

				if (pressedKeys.contains("S") && titleScreen.isVisible()) {
					System.out.println("got through menu and S pressed");
					titleScreen.setVisible(false);
					titleScreenActive = false;
				}

				if (pressedKeys.contains("R") && gameOver.isVisible()) {
					// reset doesnt reset
					gameOver.setVisible(false);
					if (atLevelOne == true) {
						fruitCollected = false;
						titleScreenActive = true;
					}
					if (atLevelTwo == true) {
						menu2Active = true;
					}
					if (atLevelThree == true) {
						menu3Active = true;
					}
					if (atLevelFour == true) {
						menu4Active = true;
					}
					if (atLevelFive == true) {
						menu5Active = true;
					}
					// {draw();}
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
						health.setXScale(health.getXScale() - 0.002);
						if (health.getXScale() < 0.1) {
							try {
								soundManager.playSoundEffect("gameOver");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameOver.setVisible(true);
							health.setXScale(1.9);
						}
						// System.out.println("enemy sees you");
						// System.out.println();
						if (gameOverB == false) {
							try {
								soundManager.playSoundEffect("caught");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameOverB = true;
							// System.out.println(room);
						}

					}
				}

				if (((ghost.collidesWith(bananaL2) && !(bananaL2 == null) && solidEnough == true))
						|| (((ghost.collidesWith(bananaL3)
								&& !(bananaL3 == null) && solidEnough == true)))) {
					if (atLevelTwo == true && trippedBananaL2 == false) {
						bananaL2.dispatchEvent(new Event(Event.COIN_PICKED_UP,
								bananaL2));
						bananaCollectedL2 = true;
						trippedBananaL2 = true;

						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected += 1;
						bananaTweenL2
								.dispatchEvent(new TweenEvent(
										TweenEvent.TWEEN_EVENT_COMPLETE,
										bananaTweenL2));

					}
					if (atLevelThree == true) {
						bananaL3.dispatchEvent(new Event(Event.COIN_PICKED_UP,
								bananaL3));
						if (trippedBananaL3 == false) {
							bananaCollectedL3 = true;
							trippedBananaL3 = true;
							try {
								soundManager.playSoundEffect("munch");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("collected banana");
							foodCollected += 1;
						}
						bananaTweenL3
								.dispatchEvent(new TweenEvent(
										TweenEvent.TWEEN_EVENT_COMPLETE,
										bananaTweenL3));
					}
					if (atLevelFour == true && trippedBananaL4 == false) {
						bananaL4.dispatchEvent(new Event(Event.COIN_PICKED_UP,
								bananaL4));
						bananaCollectedL4 = true;
						// health.setXScale(health.getXScale() + 0.95);
						trippedBananaL4 = true;
						System.out.println("caught banana 2");
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected += 1;
						bananaTweenL4.dispatchEvent(new TweenEvent(
								TweenEvent.TWEEN_EVENT_COMPLETE, bananaTweenL4));
					}
					if (atLevelFive == true && trippedBananaL5 == false) {
						bananaL5.dispatchEvent(new Event(Event.COIN_PICKED_UP,
								bananaL5));
						bananaCollectedL5 = true;
						// health.setXScale(health.getXScale() + 0.95);
						trippedBananaL5 = true;
						System.out.println("caught banana 2");
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						foodCollected += 1;
						bananaTweenL5.dispatchEvent(new TweenEvent(
								TweenEvent.TWEEN_EVENT_COMPLETE, bananaTweenL5));
					}

				}

			}

			if ((ghost.collidesWith(fruit) && solidEnough == true && fruit
					.isVisible())
					|| (ghost.collidesWith(fruitL2) && solidEnough == true && fruitL3
							.isVisible())
					|| (ghost.collidesWith(fruitL3) && solidEnough == true && fruitL3
							.isVisible())
					|| (ghost.collidesWith(fruitL4) && solidEnough == true && fruitL4
							.isVisible())
					|| (ghost.collidesWith(fruitL5) && solidEnough == true && fruitL5
							.isVisible())) {

				if (atLevelOne == true && !(gameOver.isVisible())
						&& trippedFruit == false) {

					System.out.println("Save me");
					fruit.dispatchEvent(new Event(Event.COIN_PICKED_UP, fruit));
					fruitCollected = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedFruit = true;
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println("collected fruit");
					foodCollected += 1;
					fruitTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTween));
				}
				if (atLevelTwo == true && trippedFruitL2 == false) {
					fruitL2.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							fruitL2));
					fruitCollectedL2 = true;
					trippedFruitL2 = true;
					System.out.println("caught fruit 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					fruitTweenL2.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTweenL2));
				}

				if (atLevelThree == true && trippedFruitL3 == false) {
					fruitL3.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							fruitL3));
					fruitCollectedL3 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedFruitL3 = true;
					System.out.println("caught fruit 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					fruitTweenL3.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTweenL3));
				}
				if (atLevelFour == true && trippedFruitL4 == false) {
					fruitL4.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							fruitL4));
					fruitCollectedL4 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedFruitL4 = true;
					System.out.println("caught fruit 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					fruitTweenL4.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTweenL4));
				}
				if (atLevelFive == true && trippedFruitL5 == false) {
					fruitL5.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							fruitL5));
					fruitCollectedL5 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedFruitL5 = true;
					System.out.println("caught fruit 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					fruitTweenL5.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTweenL5));
				}

			}
			if ((ghost.collidesWith(pear) && solidEnough == true && pear
					.isVisible())) {

				if (atLevelThree == true && !(gameOver.isVisible())
						&& trippedPear == false) {
					pear.dispatchEvent(new Event(Event.COIN_PICKED_UP, pear));
					pearCollected = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedPear = true;
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println("collected fruit");
					foodCollected += 1;
					pearTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, pearTween));
				}
				if (atLevelFour == true && !(gameOver.isVisible())
						&& trippedPearL4 == false) {
					pearL4.dispatchEvent(new Event(Event.COIN_PICKED_UP, pearL4));
					pearCollectedL4 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedPearL4 = true;
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println("collected fruit");
					foodCollected += 1;
					pearTweenL4.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, pearTweenL4));
				}
				if (atLevelFive == true && !(gameOver.isVisible())
						&& trippedPearL5 == false) {
					pearL5.dispatchEvent(new Event(Event.COIN_PICKED_UP, pearL5));
					pearCollectedL5 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedPearL5 = true;
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println("collected fruit");
					foodCollected += 1;
					pearTweenL5.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, pearTweenL5));
				}

			}
			if (((ghost.collidesWith(cherry) && solidEnough == true) && cherry
					.isVisible())
					|| ((ghost.collidesWith(cherryL2) && solidEnough == true) && cherryL2
							.isVisible())
					|| ((ghost.collidesWith(cherryL2) && solidEnough == true) && cherryL2
							.isVisible())) {

				if (atLevelOne == true && !(gameOver.isVisible())
						&& trippedCherry == false) {
					cherry.dispatchEvent(new Event(Event.COIN_PICKED_UP, cherry));
					cherryCollected = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherry = true;
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTween));
				}

				if (atLevelTwo == true && trippedCherryL2 == false) {
					cherryL2.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL2));
					cherryCollectedL2 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL2 = true;
					System.out.println("caught fruit 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL2.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL2));
				}

				if (atLevelThree == true && trippedCherryL3 == false) {
					cherryL3.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL3));
					cherryCollectedL3 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL3 = true;
					System.out.println("caught cherry3");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL3.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL3));
				}
				if (atLevelFour == true && trippedCherryL4 == false) {
					cherryL4.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL4));
					cherryCollectedL4 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL4 = true;
					System.out.println("caught cherry3");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL4.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL4));
				}
				if (atLevelFive == true && trippedCherryL5 == false) {
					cherryL5.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL5));
					cherryCollectedL5 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL5 = true;
					System.out.println("caught cherry3");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL5.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL5));
				}
			}
			
			if (((ghost.collidesWith(pearL42) && solidEnough == true)
					&& pearL42.isVisible()) || ((ghost.collidesWith(pearL52) && solidEnough == true)
							&& pearL52.isVisible())) {
				if (atLevelFour == true && trippedPearL42 == false) {
					pearL42.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							pearL42));
					pearCollectedL42 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedPearL42 = true;
					System.out.println("caught pear3 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					pearTweenL42.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, pearTweenL42));
				}
				if (atLevelFive == true && trippedPearL52 == false) {
					pearL52.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							pearL52));
					pearCollectedL52 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedPearL52 = true;
					System.out.println("caught pear5 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					pearTweenL52.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, pearTweenL52));
				}
			}
			
			if ((ghost.collidesWith(strawberryL42) && solidEnough == true)
					&& strawberryL42.isVisible()) {
				if (atLevelFour == true && trippedStrawberryL42 == false) {
					strawberryL42.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberryL42));
					strawberryCollectedL42 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedStrawberryL42 = true;
					System.out.println("caught strawberry3 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					strawberryTweenL42.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, strawberryTweenL42));
				}
				if (atLevelFive == true && trippedStrawberryL52 == false) {
					strawberryL52.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberryL52));
					strawberryCollectedL52 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedStrawberryL52 = true;
					System.out.println("caught strawberry5 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					strawberryTweenL52.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, strawberryTweenL52));
				}
			}
			
			if (((ghost.collidesWith(cherryL32) && solidEnough == true)
					&& cherryL32.isVisible()) || ((ghost.collidesWith(cherryL42) && solidEnough == true)
							&& cherryL42.isVisible()) || ((ghost.collidesWith(cherryL52) && solidEnough == true)
									&& cherryL52.isVisible())) {
				if (atLevelThree == true && trippedCherryL32 == false) {
					cherryL32.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL32));
					cherryCollectedL32 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL32 = true;
					System.out.println("caught cherry3 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL32.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL32));
				}
				if (atLevelFour == true && trippedCherryL42 == false) {
					cherryL42.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL42));
					cherryCollectedL42 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL42 = true;
					System.out.println("caught cherry3 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL42.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL42));
				}
				if (atLevelFive == true && trippedCherryL52 == false) {
					cherryL52.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							cherryL52));
					cherryCollectedL52 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedCherryL52 = true;
					System.out.println("caught cherry5 - 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					cherryTweenL52.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTweenL52));
				}
			}

			if (((ghost.collidesWith(strawberryL2) && !(strawberryL2 == null) && solidEnough == true))
					|| (((ghost.collidesWith(strawberryL3)
							&& !(bananaL3 == null) && solidEnough == true)))) {
				if (atLevelTwo == true && trippedStrawberryL2 == false) {
					strawberryL2.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberryL2));
					strawberryCollectedL2 = true;
					trippedStrawberryL2 = true;

					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					strawberryTweenL2
							.dispatchEvent(new TweenEvent(
									TweenEvent.TWEEN_EVENT_COMPLETE,
									strawberryTweenL2));

				}
				if (atLevelThree == true) {
					strawberryL3.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberryL3));
					if (trippedStrawberryL3 == false) {
						strawberryCollectedL3 = true;
						trippedStrawberryL3 = true;
						try {
							soundManager.playSoundEffect("munch");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("collected strawberry");
						foodCollected += 1;
					}
					strawberryTweenL3
							.dispatchEvent(new TweenEvent(
									TweenEvent.TWEEN_EVENT_COMPLETE,
									strawberryTweenL3));
				}
				if (atLevelFour == true && trippedStrawberryL4 == false) {
					strawberryL4.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberryL4));
					strawberryCollectedL4 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedStrawberryL4 = true;
					System.out.println("caught strawberry 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					strawberryTweenL4.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, strawberryTweenL4));
				}
				if (atLevelFive == true && trippedStrawberryL5 == false) {
					strawberryL5.dispatchEvent(new Event(Event.COIN_PICKED_UP,
							strawberryL5));
					strawberryCollectedL5 = true;
					// health.setXScale(health.getXScale() + 0.95);
					trippedStrawberryL5 = true;
					System.out.println("caught strawberry 2");
					try {
						soundManager.playSoundEffect("munch");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					foodCollected += 1;
					strawberryTweenL5.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, strawberryTweenL5));
				}

			}
	}
	}

	// System.out.println("X pos: " + ghost.getXPos() + " Y pos: "
	// + ghost.getYPos());

	/**
	 * Engine automatically invokes draw() every frame as well. If we want to
	 * make sure ghost gets drawn to the screen, we need to make sure to
	 * override this method and call ghost's draw method.
	 * */

	public void playMusic() {
		try {
			soundManager.playMusic("bgroundmusic");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void draw(Graphics g) {
		g.translate((int) -camera.getXPos(), (int) -camera.getYPos());
		super.draw(g);
		// if gameOver.isVisible //reset code here
		if (isLoading == true) {
			loadingScreen.setVisible(true);
			titleScreen.setVisible(false);
			// System.out.println("titleScreenDrawn" + " visibility: "
			// + titleScreen.isVisible());
			loadingScreen.draw(g);
		}
		if (titleScreenActive == true && isLoading == false) {
			titleScreen.setVisible(true);
			loadingScreen.setVisible(false);
			// System.out.println("titleScreenDrawn" + " visibility: "
			// + titleScreen.isVisible());
			titleScreen.draw(g);
		}

		if (atLevelOne == true) {
			// healthBar.setXPos(10);
			levelOne.draw(g);
			g.setColor(Color.white);
			// g.setFont(Font.arial);

			g.drawString("FruitCollected:" + foodCollected + "/" + maxFood,
					(int) ghost.getXPos(), (int) ghost.getYPos());
		}

		// else { levelTwo.draw(g);}

		if (atLevelTwo == true) {
			// System.out.println("entering level 2");
			if (menu2Active == true) {
				menu2.setVisible(true);
			} else {
				menu2.setVisible(false);
			}
			// healthBar.setXPos(10);
			levelTwo.draw(g);
			g.setColor(Color.white);
			g.drawString("FruitCollected:" + foodCollected + "/" + maxFood,
					(int) ghost.getXPos(), (int) ghost.getYPos());
		}
		if (atLevelThree == true) {
			if (menu3Active == true) {
				// System.out.println("about to put up menu for 3");
				menu3.setVisible(true);
			} else {
				menu3.setVisible(false);
			}
			// healthBar.setXPos(10);
			levelThree.draw(g);
			g.setColor(Color.white);
			g.drawString("FruitCollected:" + foodCollected + "/" + maxFood,
					(int) ghost.getXPos(), (int) ghost.getYPos());
		}
		if (atLevelFour == true) {
			if (menu4Active == true) {
				// System.out.println("about to put up menu for 4");
				menu4.setVisible(true);
			} else {
				menu4.setVisible(false);
			}
			// healthBar.setXPos(10);
			levelFour.draw(g);
			g.setColor(Color.white);
			g.drawString("FruitCollected:" + foodCollected + "/" + maxFood,
					(int) ghost.getXPos(), (int) ghost.getYPos());
		}
		if (atLevelFive == true) { // atLevelFive
			if (menu5Active == true) {
				menu5.setVisible(true);
			} else {
				menu5.setVisible(false);
			}
			// healthBar.setXPos(10);
			levelFive.draw(g);
			g.setColor(Color.white);
			g.drawString("FruitCollected:" + foodCollected + "/" + maxFood,
					(int) ghost.getXPos(), (int) ghost.getYPos());
		}

	}

	public void switchLevels() {
		if (atLevelOne == true) {
			foodCollected = 0;
			maxFood = 4;// supposed to be 4
			atLevelOne = false;
			collDects.remove(lowerRight);
			collDects.addAll(Arrays.asList(l2Wall, hallwayBottom,
					longHallwayRightLevel2, upperRightHallway,
					topHallwayLevelTwo));
			// ghost.setTrans(0.0f + deltaAlpha);
			ghost.setXPos(5);
			ghost.setYPos(780 - ghost.getScaledHeight() + 800);
			health.setXScale(1.94);
			atLevelTwo = true;
		} else if (atLevelTwo == true) {
			foodCollected = 0;
			maxFood = 6; // supposed to be 6
			atLevelTwo = false;
			// collDects.remove(topHallwayLevelTwo);
			collDects.remove(l2Wall);
			collDects.add(topHallwayLevelThree);
			collDects.add(leftSideMiddleRoom);
			listOfRooms.add(doorwayView1);
			// ghost.setTrans(0.0f + deltaAlpha);
			ghost.setXPos(5);
			ghost.setYPos(780 - ghost.getScaledHeight() + 800);
			health.setXScale(1.94);
			atLevelThree = true;
		} else if (atLevelThree == true) {
			// System.out.println("entering level 3(switchlevels)");
			foodCollected = 0;
			maxFood = 8;
			atLevelThree = false;
			collDects.remove(topHallwayLevelThree);
			collDects.addAll(Arrays.asList(longHallwayRight, topHallway,
					leftHouseTop, houseTop, topRoomBottom));
			// ghost.setTrans(0.0f + deltaAlpha);
			ghost.setXPos(5);
			ghost.setYPos(780 - ghost.getScaledHeight() + 800);
			health.setXScale(1.94);
			atLevelFour = true;
		} else if (atLevelFour == true) {
			// System.out.println("entering level 4");
			foodCollected = 0;
			maxFood = 10;
			atLevelFour = false;
			// ghost.setTrans(0.0f + deltaAlpha);
			ghost.setXPos(5);
			ghost.setYPos(780 - ghost.getScaledHeight() + 800);
			health.setXScale(1.94);
			atLevelFive = true;
		}
	}

	public static void main(String[] args) {

		Game beta = new FinalBuildTest();
		beta.start();
	}
}
