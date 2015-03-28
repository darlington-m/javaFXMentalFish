import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main class for the MentalFish games. Initialises a Stage and runs and
 * instance of AnimationTimer.
 * 
 * @author Darlington Moyo
 *
 */
public class AssignmentTemplate extends Application {

	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Pane ui, scorePane, instrPane;
	private Player player;
	//private Bonus bonus;
	private Bubble bub = new Bubble(100, 150, 0);
	private Bubble bub2 = new Bubble(700, 300, 13);
	private static Button startButton;
	private static Button saveBtn;
	private AudioClip themeMusic, end;
	private static TabPane root;
	private Tab tab1;
	private static Tab tab2;
	private Tab tab3;
	private FlowPane flowP;
	private Text scoreText, questText, timerText;
	private static Text gameSecsText;
	private boolean addNewQuest, addNewRandomObjs = true;
	private static int score, gameSecs = 90;
	private int questNum;
	private static int secs;
	private ArrayList<QuestionProduct> questions;
	private QuestionProduct rand1, quest, rand2;
	private Timer secsTimer, gameSecsTimer;
	private static TextField playerName;
	private static ListView<String> lview = new ListView<String>();
	private static ScoreLibrary scoreLib = new ScoreLibrary();
	private Factory f = new Factory();

	/**
	 * Event handler for changing direction of the player object depending on
	 * which direction key is pressed. Sets the "move" value for player which in
	 * turn is used to call the appropriate MoveIfCommand execute method.
	 */
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent keyEvent) {
			// TODO Auto-generated method stub

			if (keyEvent.getCode() == KeyCode.D) {
				player.setMove("right");
			} else if (keyEvent.getCode() == KeyCode.A) {
				player.setMove("left");
			} else if (keyEvent.getCode() == KeyCode.S) {
				player.setMove("down");
			} else if (keyEvent.getCode() == KeyCode.W) {
				player.setMove("up");
			}
		}
	};

	/**
	 * Main animation timer from the game. Loops through the questions ArrayList
	 * calling random questions that are drawn on the canvas for each round of
	 * questions. When a collision is detected between the player object and the
	 * right answer object, the timer moves to the next question until the player
	 * runs out of time.
	 */
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long arg0) {
			// TODO Auto-generated method stub

			checkGameEnd();
			scoreText.setText(String.valueOf(score));
			gc.drawImage(
					new Image(AssignmentTemplate.class.getResource(
							"resources/bg.png").toExternalForm()), 0, 0);
			timerText.setText(String.valueOf(secs));
			gameSecsText.setText(String.format("%02d:%02d",
					(gameSecs % 3600) / 60, (gameSecs % 60)));

			player.move();
			gc.drawImage(player.getImage(), player.r.getX(), player.r.getY());

			gc.drawImage(bub.image, bub.r.getX(), bub.r.getY());
			gc.drawImage(bub2.image, bub2.r.getX(), bub2.r.getY());
			bub.animCycle++;
			bub2.animCycle++;
			bub.drawBubbles();
			bub2.drawBubbles();

			if (addNewRandomObjs) {

				quest = questions.get(questNum);
				rand1 = questions.get(getRandom(0, 48));
				rand2 = questions.get(getRandom(0, 48));

				if (rand1.getAnswerValue() == quest.getAnswerValue()) {
					rand1 = questions.get(getRandom(0, 48));
				}
				if (rand2.getAnswerValue() == quest.getAnswerValue()
						|| rand2.getAnswerValue() == rand1.getAnswerValue()) {
					rand2 = questions.get(getRandom(0, 48));
				}

				addNewRandomObjs = false;
			}

			gc.drawImage(rand1.image, rand1.r.getX(), rand1.r.getY());
			gc.drawImage(rand2.image, rand2.r.getX(), rand2.r.getY());
			gc.drawImage(quest.image, quest.r.getX(), quest.r.getY());
			questText.setText(quest.getQuestion());

			if (addNewQuest) {
				addNewQuest = false;
				secs = 8;
			}

			// if (questsAnswered % 5 == 0) {
			// gc.drawImage(bonus.image, bonus.r.getX(), bonus.r.getY());
			// }
			//
			// if (bonus.r.getBoundsInParent().intersects(
			// player.r.getBoundsInParent())) {
			//
			// score += bonus.getPointsValue();
			//
			// }

			if (player.r.getBoundsInParent().intersects(
					quest.r.getBoundsInParent())) {

				score += quest.getPointsValue();
				score += secs;
				addNewQuest = true;
				addNewRandomObjs = true;
				changeQuestNum();
			}

			if (secs == 0) {
				score -= 20;
				scoreText.setText(String.valueOf(score));
				addNewRandomObjs = true;
				addNewQuest = true;
				changeQuestNum();
			}

			player.setImageMoveNum(player.getImageMoveNum() + 1);
		}
	};

	/**
	 * AssignmentTemplate constructor. Initialises some static field values.
	 */
	public AssignmentTemplate() {

		addQuestions();
		this.questNum = getRandom(0, questions.size());
		player = Player.getInstance(400, -200);
		//bonus = Bonus.getInstance(700, 300);
		player.setMove("down");
		openScoresFromFile();
		showList();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {

		// TODO Auto-generated method stub
		stage.setTitle("Software Architectures � Darlington Moyo");
		root = new TabPane();
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.show();

		scene.getStylesheets().addAll(
				this.getClass().getResource("resources/style.css")
						.toExternalForm());
		scene.setOnKeyPressed(keyHandler);

		flowP = new FlowPane();
		tab1 = new Tab();
		tab1.setText("Hungry Fish");
		tab1.setClosable(false);
		tab1.setContent(flowP);

		ui = new Pane();
		ui.setPrefSize(800, 71);
		ui.setLayoutX(0);
		ui.setStyle("-fx-background-color: #3399cc");

		canvas = new Canvas(800, 500);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(
				new Image(AssignmentTemplate.class.getResource(
						"resources/bg.png").toExternalForm()), 0, 0);
		flowP.getChildren().addAll(ui, canvas);

		tab2 = new Tab();
		tab2.setText("View Scores");
		tab2.setClosable(false);

		lview.setPrefWidth(400);
		lview.setPrefHeight(511);
		lview.setLayoutX(200);
		lview.setLayoutY(30);

		scorePane = new Pane();
		scorePane.setLayoutX(0);
		scorePane.setLayoutY(0);
		scorePane.setId("scorePane");

		tab2.setContent(scorePane);
		scorePane.getChildren().add(lview);

		instrPane = new Pane();
		instrPane.setLayoutX(0);
		instrPane.setLayoutY(0);
		instrPane.setId("instrPane");

		tab3 = new Tab();
		tab3.setText("Instructions");
		tab3.setClosable(false);
		tab3.setContent(instrPane);

		root.getTabs().addAll(tab1, tab2, tab3);

		ui.getChildren().addAll(questBoxTitle(), questBox(), scoreBoxTitle(),
				scoreBox(), timerBoxTitle(), timerBox(), startButton(),
				gameTimeBox());

		/*
		 * Event listener for the start button. Depending on the stage of the
		 * game, button can start a , pause or resume a game.
		 */
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				themeMusic = new AudioClip(AssignmentTemplate.class
						.getResource("resources/theme.mp3").toExternalForm());
				if (startButton.getText() == "Pause Game") {
					themeMusic.stop();
					timer.stop();
					startButton.setText("Resume Game");
					secsTimer.cancel();
					gameSecsTimer.cancel();
				} else if (startButton.getText() == "Resume Game") {
					themeMusic.play();
					startButton.setText("Pause Game");
					secsTimer = new Timer();
					secsTimer.schedule(new MoveTimer(), 0, 1000);
					gameSecsTimer = new Timer();
					gameSecsTimer.schedule(new GameTimer(), 0, 1000);
					timer.start();
				} else {
					themeMusic.play();
					startButton.setText("Pause Game");
					secs = 8;
					gameSecs = 90;
					secsTimer = new Timer();
					secsTimer.schedule(new MoveTimer(), 0, 1000);
					gameSecsTimer = new Timer();
					gameSecsTimer.schedule(new GameTimer(), 0, 1000);
					timer.start();
				}

			}
		});

		/*
		 * Event listener for closing the games windows. Terminates all
		 * processes when game is exited.
		 */
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

	}

	/**
	 * Checks if the game count down has reached 0 and then stops 
	 * the timer(), themeMusic and reset other values that need reseting.
	 * Finally call the recordScore() window.
	 * 
	 */
	public void checkGameEnd() {
		
		end = new AudioClip(AssignmentTemplate.class
				.getResource("resources/the-end.mp3").toExternalForm());

		if (gameSecs == 0) {
			timer.stop();
			secsTimer.cancel();
			gameSecsTimer.cancel();
			startButton.setText("Start Game");
			themeMusic.stop();
			end.play();
			recordScore();
		}
	}
	
	

	/**
	 * Stage (window) for recording scores. Prompts player to input their name 
	 * and save their score.
	 */
	public static void recordScore() {

		final Stage newStage = new Stage();
		VBox comp = new VBox();

		Pane pane = new Pane();
		pane.setPrefSize(300, 300);
		pane.setLayoutX(0);
		pane.setStyle("-fx-background-color: #3399cc");
		comp.getChildren().add(pane);

		playerName = new TextField("Enter Name");
		playerName.setMinSize(200, 40);
		playerName.setMaxSize(200, 40);
		playerName.setLayoutX(50);
		playerName.setLayoutY(50);
		playerName.setStyle("-fx-font-size: 20");

		pane.getChildren().addAll(playerName, saveButton(), finalScoreText());
		Scene stageScene = new Scene(comp, 300, 300);
		newStage.setScene(stageScene);
		newStage.show();

		saveBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				SingleSelectionModel<Tab> selectionModel = root
						.getSelectionModel();

				scoreLib.addScore(new Score(playerName.getText(), score));
				
				score = 0;
				saveScoresToFile();
				openScoresFromFile();
				selectionModel.select(tab2);
				newStage.close();
				showList();
			}
		});

		newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				newStage.close();
				score = 0;
			}
		});
	}

	/**
	 * @return saveBn Node
	 */
	private static Node saveButton() {

		saveBtn = new Button("Save Score");
		saveBtn.setLayoutX(88);
		saveBtn.setLayoutY(130);
		saveBtn.setMinSize(124, 40);
		saveBtn.setMaxSize(124, 40);
		saveBtn.setStyle("-fx-font-weight: bold");
		saveBtn.setStyle("-fx-font-size: 20");
		return saveBtn;
	}

	/**
	 * @return scoreText Node
	 */
	private static Node finalScoreText() {

		Text text = new Text();
		text.setText(String.valueOf(score));
		text.setFill(Color.WHITE);
		text.setFont(Font.font(null, FontWeight.BOLD, 50));
		text.setX(88);
		text.setY(250);
		return text;
	}

	/**
	 * @return startButton Node
	 */
	private Node startButton() {

		startButton = new Button("Start Game");
		startButton.setLayoutX(15);
		startButton.setLayoutY(15);
		startButton.setMinSize(140, 41);
		startButton.setMaxSize(140, 41);
		startButton.setStyle("-fx-font-weight: bold");
		startButton.setStyle("-fx-font-size: 20");
		return startButton;
	}

	/**
	 * @return questionBoxTitle and questionTextTitle group Node 
	 */
	public static Node questBoxTitle() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(40);
		r.setHeight(41);
		r.setFill(Color.DARKBLUE);

		Text t = new Text();
		t.setText("Q");
		t.setFill(Color.YELLOW);
		t.setFont(Font.font(null, FontWeight.BOLD, 25));
		t.setX(10);
		t.setY(40);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(t);
		g.setTranslateX(520);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @return questionBox Group Node
	 */
	private Node questBox() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(225);
		r.setHeight(41);
		r.setFill(Color.GRAY);

		questText = new Text();
		questText.setText("Get Ready!");
		questText.setFill(Color.BLACK);
		questText.setFont(Font.font(null, FontWeight.BOLD, 25));
		questText.setX(10);
		questText.setY(40);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(questText);
		g.setTranslateX(560);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @return scoreBoxTitle Group Node
	 */
	private static Node scoreBoxTitle() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(40);
		r.setHeight(41);
		r.setFill(Color.DARKBLUE);

		Text t = new Text();
		t.setText("P");
		t.setFill(Color.YELLOW);
		t.setFont(Font.font(null, FontWeight.BOLD, 25));
		t.setX(10);
		t.setY(40);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(t);
		g.setTranslateX(380);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @return scoreBox Node
	 */
	private Node scoreBox() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(90);
		r.setHeight(41);
		r.setFill(Color.GRAY);

		scoreText = new Text();
		scoreText.setText("0");
		scoreText.setFill(Color.BLACK);
		scoreText.setFont(Font.font(null, FontWeight.BOLD, 25));
		scoreText.setX(20);
		scoreText.setY(40);
		scoreText.setTextAlignment(TextAlignment.CENTER);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(scoreText);
		g.setTranslateX(420);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @return timerBoxTitle Node
	 */
	private static Node timerBoxTitle() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(40);
		r.setHeight(41);
		r.setFill(Color.DARKBLUE);

		Text t = new Text();
		t.setText("T");
		t.setFill(Color.YELLOW);
		t.setFont(Font.font(null, FontWeight.BOLD, 25));
		t.setX(10);
		t.setY(40);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(t);
		g.setTranslateX(280);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @return timerBox Node
	 */
	private Node timerBox() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(50);
		r.setHeight(41);
		r.setFill(Color.GRAY);

		timerText = new Text();
		timerText.setText("0");
		timerText.setFill(Color.BLACK);
		timerText.setFont(Font.font(null, FontWeight.BOLD, 25));
		timerText.setX(18);
		timerText.setY(40);
		timerText.setTextAlignment(TextAlignment.CENTER);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(timerText);
		g.setTranslateX(320);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @return gameTimeBox Node
	 */
	private static Node gameTimeBox() {
		Group g = new Group();

		Rectangle r = new Rectangle();
		r.setX(1);
		r.setY(10);
		r.setWidth(100);
		r.setHeight(41);
		r.setFill(Color.DARKBLUE);

		gameSecsText = new Text();
		gameSecsText.setText(String.format("%02d:%02d", (gameSecs % 3600) / 60,
				(gameSecs % 60)));
		gameSecsText.setFill(Color.YELLOW);
		gameSecsText.setFont(Font.font(null, FontWeight.BOLD, 25));
		gameSecsText.setX(10);
		gameSecsText.setY(40);

		g.setCache(true);
		g.setEffect(new Bloom());
		g.getChildren().add(r);
		g.getChildren().add(gameSecsText);
		g.setTranslateX(170);
		g.setTranslateY(5);
		return g;
	}

	/**
	 * @param low
	 * @param high
	 * @return
	 */
	public int getRandom(int low, int high) {

		Random r = new Random();
		int Low = low;
		int High = high;
		int rand = r.nextInt(High - Low) + Low;
		return rand;
	}

	/**
	 * Changes the number that is used to pick the next question.
	 */
	private void changeQuestNum() {
		// TODO Auto-generated method stub
		this.questNum = getRandom(0, 48);
	}

	/**
	 * Opens score from the score.ser text file and add them to the scoreList array 
	 * in the ScoreLibrary
	 */
	public static void openScoresFromFile() {

		try {
			GameReaderWriter libraryReaderWriter = new GameReaderWriter(
					"scores.ser");

			ScoreLibrary tmpInventory = null;
			tmpInventory = libraryReaderWriter.read();

			scoreLib.replaceScores(tmpInventory.getScores());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not open");
		}
	}

	/**
	 * Saves current scoreList array values to the score.ser text file
	 */
	public static void saveScoresToFile() {

		try {

			GameReaderWriter libraryReaderWriter = new GameReaderWriter(
					"scores.ser");
			libraryReaderWriter.write(scoreLib);
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("Could not write");
		}
	}

	/**
	 * Update the score list in the score Tab to the current values in the scoreList ArrayList
	 */
	public static void showList() {

		ArrayList<Score> sortedList = new ArrayList<Score>(
				scoreLib.getScoreList());

		Collections.sort(sortedList, new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return new Integer(s2.getScoreValue()).compareTo(s1
						.getScoreValue());
			}
		});

		lview.getItems().clear();
		int count = 1;
		lview.getItems().add(
				String.format("%-20s %-25s %-20s %-20s", "No.", "Name",
						"Score", "Date"));
		for (Score s : sortedList) {
			lview.getItems().add(
					String.format("%-20d %-25.25s %-20d %-20.25s", count,
							s.getPlayerName(), s.getScoreValue(),
							new SimpleDateFormat("MMMM d, Y").format(s
									.getDate())));
			count++;
			if (count == 22) {
				break;
			}
		}
	}
	

	/**
	 * Adds new questions to the questions ArrayList
	 */
	private void addQuestions() {
		// TODO Auto-generated method stub
		questions = new ArrayList<QuestionProduct>();
		questions.add(f.createProduct("NormalQuestion", "number0.png",
				"1001 x 0", 0));
		questions.add(f.createProduct("EasyQuestion", "number1.png", "55 - 54",
				1));
		questions.add(f.createProduct("HardQuestion", "number2.png",
				"(5 x 10) / 25", 2));
		questions.add(f.createProduct("HardQuestion", "number3.png",
				"(2 x 2) - 1", 3));
		questions.add(f.createProduct("EasyQuestion", "number4.png", "16 - 12",
				4));
		questions.add(f
				.createProduct("EasyQuestion", "number5.png", "3 + 2", 5));
		questions.add(f.createProduct("HardQuestion", "number6.png", "36 / 6",
				6));
		questions.add(f.createProduct("HardQuestion", "number7.png",
				"(5 + 3) - 1", 7));
		questions.add(f.createProduct("HardQuestion", "number8.png",
				"(3 x 3) - 1", 8));
		questions.add(f
				.createProduct("EasyQuestion", "number9.png", "3 x 3", 9));
		questions.add(f.createProduct("EasyQuestion", "number10.png", "16 - 6",
				10));
		questions.add(f.createProduct("HardQuestion", "number11.png",
				"(5 X 2) + 1", 11));
		questions.add(f.createProduct("EasyQuestion", "number12.png", "3 X 4",
				12));
		questions.add(f.createProduct("HardQuestion", "number13.png",
				"(5 X 2) + 3", 13));
		questions.add(f.createProduct("NormalQuestion", "number14.png",
				"30 - 16", 14));
		questions.add(f.createProduct("EasyQuestion", "number15.png", "16 - 1",
				15));
		questions.add(f.createProduct("NormalQuestion", "number16.png",
				"16 - 0", 16));
		questions.add(f.createProduct("HardQuestion", "number17.png",
				"(4 x 4) + 1", 17));
		questions.add(f.createProduct("EasyQuestion", "number18.png", "9 + 9",
				18));
		questions.add(f.createProduct("EasyQuestion", "number19.png", "16 + 3",
				19));
		questions.add(f.createProduct("NormalQuestion", "number20.png",
				"42 - 22", 20));
		questions.add(f.createProduct("NormalQuestion", "number21.png",
				"10 + 10 + 1", 21));
		questions.add(f.createProduct("EasyQuestion", "number22.png",
				"11 + 11", 22));
		questions.add(f.createProduct("HardQuestion", "number23.png",
				"(5 X 4) + 3", 23));
		questions.add(f.createProduct("HardQuestion", "number24.png",
				"40 - 16", 24));
		questions.add(f.createProduct("NormalQuestion", "number25.png",
				"26 - 1", 25));
		questions.add(f.createProduct("EasyQuestion", "number26.png", "26 - 0",
				26));
		questions.add(f.createProduct("HardQuestion", "number27.png",
				"(6 x 4) + 3", 27));
		questions.add(f.createProduct("NormalQuestion", "number28.png",
				"7 x 4", 28));
		questions.add(f.createProduct("NormalQuestion", "number29.png",
				"26 + 3", 29));
		questions.add(f.createProduct("NormalQuestion", "number30.png",
				"6 x 5", 30));
		questions.add(f.createProduct("NormalQuestion", "number31.png",
				"29 + 2", 31));
		questions.add(f.createProduct("NormalQuestion", "number32.png",
				"8 x 4", 32));
		questions.add(f.createProduct("NormalQuestion", "number33.png",
				"11 x 3", 33));
		questions.add(f.createProduct("HardQuestion", "number34.png",
				"(6 x 5) + 4", 34));
		questions.add(f.createProduct("NormalQuestion", "number35.png",
				"7 x 5", 35));
		questions.add(f.createProduct("HardQuestion", "number36.png",
				"(60 / 2) + 6", 36));
		questions.add(f.createProduct("HardQuestion", "number37.png",
				"(6 x 6) + 1", 37));
		questions.add(f.createProduct("EasyQuestion", "number38.png", "30 + 8",
				38));
		questions.add(f.createProduct("HardQuestion", "number39.png",
				"(3 x 12) + 3", 39));
		questions.add(f.createProduct("EasyQuestion", "number40.png",
				"20 + 20", 40));
		questions.add(f.createProduct("NormalQuestion", "number50.png",
				"25 x 2", 50));
		questions.add(f.createProduct("HardQuestion", "number60.png",
				"120 / 2", 60));
		questions.add(f.createProduct("HardQuestion", "number70.png",
				"35 + 35", 70));
		questions.add(f.createProduct("NormalQuestion", "number80.png",
				"8 x 10", 80));
		questions.add(f.createProduct("HardQuestion", "number90.png",
				"(100 + 20)-30", 90));
		questions.add(f.createProduct("EasyQuestion", "number100.png",
				"10 x 10", 100));
		questions.add(f.createProduct("NormalQuestion", "number500.png",
				"100 x 5", 500));
		questions.add(f.createProduct("HardQuestion", "number1000.png",
				"100 x 10", 1000));
	}

	/**
	 * Returns value of gameSecs
	 * 
	 * @return gameSec
	 */
	public static int getGameSecs() {
		return gameSecs;
	}

	/**
	 * Changes the value of gameSecs
	 * 
	 * @param gameSecs
	 */
	public static void setGameSecs(int gameSecs) {
		AssignmentTemplate.gameSecs = gameSecs;
	}

	/**
	 * Returns the value of secs
	 * 
	 * @return secs
	 */
	public static int getSecs() {
		return secs;
	}

	/**
	 * Changes the value of secs
	 * 
	 * @param secs
	 */
	public static void setSecs(int secs) {
		AssignmentTemplate.secs = secs;
	}

}
