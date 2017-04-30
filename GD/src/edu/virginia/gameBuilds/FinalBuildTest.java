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
	// Rectangle topRoom = new Rectangle(140, 175, 1160, 315);
	// Rectangle topMiddleRoom = new Rectangle(620, 550, 435, 400);
	Rectangle leftMiddleRoom = new Rectangle(135, 215, 400, 465);
	Rectangle rightMiddleRoom = new Rectangle(625, 215, 420, 465);
	// Rectangle verticalHallway = new Rectangle(1110, 550, 180, 940);
	Rectangle bottomRoom = new Rectangle(130, 510, 900, 180);
	// Rectangle doorwayView1 = new Rectangle(620, 550, 660, 100); //if the
	// enemy is not in the top middle room, but can "see" into it from the
	// vertical hallway, ghost is caught
	// Rectangle doorwayView2 = new Rectangle(820, 225, 100, 700); //if the
	// enemy is not in the top room, but can "see" into it from top middle
	// rorom, ghost is caught

	ArrayList<Rectangle> listOfRooms = new ArrayList<Rectangle>(Arrays.asList(
			leftMiddleRoom, rightMiddleRoom, bottomRoom));

	healthBarSprite healthBar = new healthBarSprite("healthBar");
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
	ArrayList<Sprite> collDects = new ArrayList<Sprite>(Arrays.asList(wall2,
			vwall, vwall2, wall4, lowerLeft, lowerRight, rightTop, leftBottom,
			rightBottom, vwall3));

	Sprite gameOver = new Sprite("gameOver", "gameOver.png");
	Sprite gameWon = new Sprite("gameWon", "gameWon.png");
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
	VertWallSprite vwall6L2 = new VertWallSprite("vwall6L2"); // use this wall's
																// pos for
																// collision
																// detection
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
	Sprite redCarpet = new Sprite("redCarpet", "redCarpet.jpg");
	enemySprite enemy = new enemySprite("EnemyOne");

	Tween marioTween = new Tween(ghost, new TweenTransition());
	Tween fruitTween = new Tween(fruit, new TweenTransition());
	Tween cherryTween = new Tween(cherry, new TweenTransition());

	TweenJuggler juggler = new TweenJuggler();

	// boolean trippedCherry = false;
	boolean trippedStrawberry = false;
	// boolean trippedFruit = false;
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

	/**** this code for the enemy's movement paths ***/
	public int enemyMoveCounter = 1;
	public int enemyMoveCounter2 = 1;
	private boolean initializeRoute = true;

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

	private float deltaAlpha = (float) 0.1; // controls how quickly the ghost
											// becomes invisible/visible
	boolean transKeyTapped = false; // needed to implement tapping of key
	boolean visibleKeyTapped = false; // turns the ghost visible again
	public int maxFood = 2;
	ArrayList<Cell> path1 = new ArrayList<Cell>();
	ArrayList<Cell> fPath1 = new ArrayList<Cell>();

	ArrayList<Cell> path2 = new ArrayList<Cell>();
	ArrayList<Cell> fPath2 = new ArrayList<Cell>();

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
		// levelOne.add(gameWon);
		levelOne.add(gameOver);
		levelOne.add(ghost);
		levelOne.add(enemy);

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
		// LevelTwo.add(vwall6L2);
		levelTwo.add(vwall3);
		levelTwo.add(gameWon);
		levelTwo.add(gameOver);
		levelTwo.add(ghost);
		levelTwo.add(enemy);
		levelTwo.add(longHallwayRightLevel2);
		// levelTwo.add(leftSideMiddleRoom);
		levelTwo.add(topHallwayLevelTwo);
		levelTwo.add(upperRightHallway);
		levelTwo.add(hallwayBottom);
		levelTwo.add(strawberry);

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
		// LevelThree.add(vwall6L2);
		levelThree.add(vwall3);
		levelThree.add(gameWon);
		levelThree.add(gameOver);
		levelThree.add(ghost);
		levelThree.add(enemy);
		levelThree.add(longHallwayRightLevel2);
		levelThree.add(leftSideMiddleRoom);
		levelThree.add(topHallwayLevelThree);
		levelThree.add(upperRightHallway);
		levelThree.add(hallwayBottom);

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
		// levelFour.add(lowerRight);
		levelFour.add(rightTop);
		levelFour.add(leftBottom);
		levelFour.add(rightBottom);
		levelFour.add(vwall);
		levelFour.add(wall2);
		levelFour.add(wall4);
		levelFour.add(vwall2);
		// LevelThree.add(vwall6L2);
		levelFour.add(vwall3);
		levelFour.add(gameWon);
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
		// levelFive.add(lowerRight);
		levelFive.add(rightTop);
		levelFive.add(leftBottom);
		levelFive.add(rightBottom);
		levelFive.add(vwall);
		levelFive.add(wall2);
		levelFive.add(wall4);
		levelFive.add(vwall2);
		// LevelThree.add(vwall6L2);
		levelFive.add(vwall3);
		levelFive.add(gameWon);
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

		ghost.setYPos(780 - ghost.getScaledHeight());
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

		vwall6L2.setYPos(-300 + 800);
		vwall6L2.setYPos(1300);

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

		enemy.setXPos(room2x);
		enemy.setYPos(room2y - 15 + 800);

		//

		// room rectangle
		room1.setBounds(300 + vwall.getScaledHeight() + 20,
				500 - vwall.getScaledHeight() - 20, 275, 265);
		// System.out.println("room1 rectangle: " + room1);

		System.out.println(vwall.getScaledWidth());
		int wstart = 196 - 85;
		// int wstart = (int) (vwall.getYPos() - enemy.getHitBox().getHeight());
		for (int c = wstart; c <= wstart + vwall.getScaledHeight() + 45; c++) {
			for (int x = 100 - vwall.getScaledWidth(); x <= 100; x++) {
				int[] e = new int[] { x, c };
				blockedList.add(e);
			}
		}

		int wstart2 = 196 - 85;
		// int wstart2 = (int) (vwall.getYPos() -
		// enemy.getHitBox().getHeight());
		for (int c = wstart2; c <= wstart2 + vwall.getScaledHeight() + 45; c++) {
			for (int x = (int) (530 - enemy.getHitBox().getWidth() - vwall
					.getScaledWidth()); x <= 600; x++) {

				int[] e = new int[] { x, c };
				blockedList.add(e);
			}
		}
		int wstart3 = 196 - 85;
		// int wstart3 = 197-134;
		// for(int c = wstart3; c<=wstart2+304+134;c++){
		for (int c = wstart3; c <= wstart2 + 304 + 45; c++) {
			for (int x = 1100 - 76; x <= 1100 + vwall.getScaledWidth(); x++) {
				int[] e = new int[] { x, c };
				blockedList.add(e);
			}
		}

		int wstart4 = 197 - wall.getScaledHeight() - 85;
		for (int c = wstart4; c <= wstart4 + wall2.getScaledHeight(); c++) {
			for (int x = 350; x <= 1176; x++) {
				int[] e = new int[] { x, c };
				blockedList.add(e);
			}
		}

		room1.setBounds(300 + vwall.getScaledWidth(),
				500 - vwall.getScaledHeight(), (2 * wall2.getScaledWidth())
						- (2 * vwall.getScaledWidth()), vwall.getScaledHeight());

		/*********** ATTEMPTING TO ADD SECOND PATH TO ENEMY *****************/

		room2.setBounds(300 + vwall.getScaledWidth(),
				500 - vwall.getScaledHeight(), (2 * wall2.getScaledWidth())
						- (2 * vwall.getScaledWidth()), vwall.getScaledHeight()); // ahhh
																					// idk
																					// what
																					// im
																					// doing

		// path one
		// /////////////////////////////////////////////////////
		path1 = AStar.test(1, this.getScenePanel().getWidth(), this
				.getScenePanel().getHeight(), room2x, room2y - 15, room1x,
				room1y - 15, blockedList); // made room1y -> room1y-15 because
											// enemy wasn't fully in the room
		int pLen = path1.size();

		int q = pLen - 1;

		/** prints the path **/

		while (q >= 0) {
			Cell temp = new Cell(path1.get(q).i, path1.get(q).j);
			fPath1.add(temp);
			q -= 1;
		}

		// ////////////////////////////////////////////////////////

		// path2
		// ///////////////////////////////////////////////
		path2 = AStar.test(1, this.getScenePanel().getWidth(), this
				.getScenePanel().getHeight(), room1x, room1y - 15, room2x,
				room2y - 15, blockedList); // made room1y -> room1y-15 because
											// enemy wasn't fully in the room
		int pLen2 = path2.size();

		int q2 = pLen2 - 1;

		/** prints the path **/

		while (q2 >= 0) {
			Cell temp = new Cell(path2.get(q2).i, path2.get(q2).j);
			fPath2.add(temp);
			q2 -= 1;
		}
		// ///////////////////////////////////////

		/*********** end of second path for enemy code *********/

	}

	public void switchPath() {
		if (gtr1 == true) {
			gtr1 = false;
			room1SetUp = false;
			gtr2 = true;
		}

		if (gtr2 == true) {

			gtr2 = false;
			room2SetUp = false;
			gtr1 = true;
		}
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

		gameWon.setXPos(ghost.getXPos() - VIEWPORT_SIZE_X / 2);
		gameWon.setYPos(ghost.getYPos() - VIEWPORT_SIZE_Y / 2);
		if (gameWon.getXPos() > offsetMaxX) {
			gameWon.setXPos(offsetMaxX);
		} else if (gameWon.getXPos() < offsetMinX) {
			gameWon.setXPos(offsetMinX);
		}
		if (gameWon.getYPos() > offsetMaxY) {
			gameWon.setYPos(offsetMaxY);
		} else if (gameWon.getXPos() < offsetMinY) {
			gameWon.setYPos(offsetMinY);
		}
		super.update(pressedKeys);

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

						if (enemyMoveCounter < fPath1.size()) {
							Cell moveTo = fPath1.get(enemyMoveCounter);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goForward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							// enemyMoveCounter+=1;
							enemyMoveCounter += 1; // increases the owner's
													// speed
						}

						if (enemyMoveCounter >= fPath1.size()) {
							enemyMoveCounter = 1;
							// switchPath();
							gtr1 = false;
							gtr2 = true;

						}

					}

					if (gtr2 == true) {

						if (enemyMoveCounter2 < fPath2.size()) {
							Cell moveTo = fPath2.get(enemyMoveCounter2);
							int xm = moveTo.i;
							int ym = moveTo.j;
							enemy.setXPos(xm);
							enemy.setYPos(ym);
							enemy.goBackward(true); // THIS IS WHERE TO TELL THE
													// SPRITE TO CHANGE
													// DIRECTIONS
							enemyMoveCounter2 += 1; // makes the owner move
													// faster
						}
						if (enemyMoveCounter2 >= fPath2.size()) {

							enemyMoveCounter2 = 1;
							gtr2 = false;

							gtr1 = true;
						}

					}

					juggler.nextFrame();

					for (Sprite wall : collDects) { // does code have ability to
													// cycle through every wall
													// object in 1/60 of a
													// second?

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

							break; // what does this break do?
						}
					}

					if (foodCollected == maxFood) {
						if (!gameOver.isVisible()) {
							// gameWon.setVisible(true);
						}
						healthWidth = 0;
						System.out.println("swtichinglevels");
						switchLevels();

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
						if (collected == true && !gameOver.isVisible()) {

							fruitTween.doTween(myQuestManager.tweenComplete);
						} else if (cherryCollected == true
								&& !gameOver.isVisible()) {
							cherryTween.doTween(myQuestManager.tweenComplete);
						} else if (strawberryCollected == true
								&& !gameOver.isVisible()) {
							strawberryTween
									.doTween(myQuestManager.tweenComplete);
						} else if (bananaCollected == true) {
							bananaTween.doTween(myQuestManager.tweenComplete);
						}
					}
					// questConfirm.setVisible(true);
				}

				if (pressedKeys.contains("Q")) {
					System.exit(0);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(38))) {
					if (stopU == false)
						ghost.setYPos(ghost.getYPos() - dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(40))) {
					if (stopD == false)
						ghost.setYPos(ghost.getYPos() + dy);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
					if (stopR == false)
						ghost.setXPos(ghost.getXPos() + dx);
				}

				if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
					if (stopL == false)
						ghost.setXPos(ghost.getXPos() - dx);
				}

				/******************* TAPPING AND VISIBILITY ****************/
				if (!transKeyTapped
						&& pressedKeys.contains(KeyEvent.getKeyText(88))) { // checks
																			// to
																			// see
																			// if
																			// key
																			// has
																			// been
																			// tapped.
					if (ghost.getTrans() - deltaAlpha > 0.0f) {
						ghost.setTrans(ghost.getTrans() - deltaAlpha);
					}
					transKeyTapped = true;
				}

				if (!pressedKeys.contains(KeyEvent.getKeyText(88))) { // if
																		// it
																		// has
																		// been
																		// tapped,
																		// set
																		// it
																		// to
																		// be
																		// true
																		// and
																		// make
																		// slightly
																		// trans
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
					// System.out.println("enemy hitbox intersects room?: "
					// + enemy.getHitBox().intersects(room) +
					// " ghost hitbox intersects room?: " +
					// ghost.getHitBox().intersects(room) +
					// " ghost Abilitites?: " + ghostAbilities);
					// System.out.println();
					if (enemy.getHitBox().intersects(room)
							&& ghost.getHitBox().intersects(room)
							&& !ghostAbilities) {
						if (!gameWon.isVisible() && gameOverB == false) {
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
						foodCollected += 1;
					}

					fruitTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, fruitTween));
				}
				if ((ghost.collidesWith(cherry) && solidEnough == true && !gameOver
						.isVisible())) {
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
						foodCollected += 1;
					}
					collected = false;
					cherryTween.dispatchEvent(new TweenEvent(
							TweenEvent.TWEEN_EVENT_COMPLETE, cherryTween));
					// makes orange tween even though it's the cherry that's
					// being overlapped
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
		if (atLevelOne == true) {
			levelOne.draw(g);
			g.setColor(Color.red);
			g.fillRect(20, 30, healthWidth, 22);
			healthBar.draw(g);

			for (Sprite wall : collDects) {
				if (wall != null) {
					wall.draw(g);
				}
			}
		}

		// else { levelTwo.draw(g);}

		if (atLevelTwo == true) {
			System.out.println("entering level 2");
			levelTwo.draw(g);
		}
		if (atLevelThree == true) {
			System.out.println("entering level 3");
			levelThree.draw(g);
		}
		if (atLevelFour == true) {
			System.out.println("entering level 4");
			levelFour.draw(g);
		}
		if (atLevelFive == true) { // atLevelFive
			System.out.println("entering final level");
			levelFive.draw(g);
		}

	}

	// Level Switching code
	// add a new if statment for each new level
	public void switchLevels() {
		if (atLevelOne == true) {
			foodCollected = 0;
			maxFood = 1;//supposed to be 4
			atLevelOne = false;
			atLevelTwo = true;
		} else if (atLevelTwo == true) {
			System.out.println("entering level 2");
			foodCollected = 0;
			maxFood = 1; //supposed to be 6
			atLevelTwo = false;
			atLevelThree = true;
		} else if (atLevelThree == true) {
			System.out.println("entering level 3");
			foodCollected = 0;
			maxFood = 8;
			atLevelThree = false;
			atLevelFour = true;
		} else if (atLevelFour == true) {
			System.out.println("entering level 4");
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
