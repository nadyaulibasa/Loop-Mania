package unsw.loopmania;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

/**
 * the main application
 * run main method from this class
 */
public class LoopManiaApplication extends Application {

    /**
     * the controller for the game. Stored as a field so can terminate it when click exit button
     */
    private LoopManiaWorldController mainController;

    private LoopManiaShopController loopManiaShopController;

    /**
     * music
     */
    private MediaPlayer mediaPlayer;
    private final String menuSound = "src/music/opening.mp3";
    private final String mainSound = "src/music/path.mp3";
    private final String shopSound = "src/music/casino.mp3";
    private final String battleSound = "src/music/battle.mp3";

    @Override
    public void start(Stage primaryStage) throws IOException {
        // set title on top of window bar
        primaryStage.setTitle("Loop Mania");

        // prevent human player resizing game window (since otherwise would see white space)
        // alternatively, you could allow rescaling of the game (you'd have to program resizing of the JavaFX nodes)
        primaryStage.setResizable(false);

        // load the main game
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader("world_with_twists_and_turns.json");
        mainController = loopManiaLoader.loadController();
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
        gameLoader.setController(mainController);
        Parent gameRoot = gameLoader.load();

        // load the main menu
        MainMenuController mainMenuController = new MainMenuController();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        menuLoader.setController(mainMenuController);
        Parent mainMenuRoot = menuLoader.load();

        // load the shop
        loopManiaShopController = new LoopManiaShopController(mainController);
        FXMLLoader shopLoader = new FXMLLoader(getClass().getResource("LoopManiaShop.fxml"));
        shopLoader.setController(loopManiaShopController);
        Parent shopRoot = shopLoader.load();

        // load GameOver
        GameOverController gameOverController = new GameOverController();
        FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
        gameOverLoader.setController(gameOverController);
        Parent gameOverRoot = gameOverLoader.load();

        // load Battle
        BattleController battleController = new BattleController();
        FXMLLoader battleLoader = new FXMLLoader(getClass().getResource("BattleView.fxml"));
        battleLoader.setController(battleController);
        Parent battleRoot = battleLoader.load();

        // load OpenShop
        OpenShopController openShopController = new OpenShopController();
        FXMLLoader openShopLoader = new FXMLLoader(getClass().getResource("OpenShopView.fxml"));
        openShopLoader.setController(openShopController);
        Parent openShopRoot = openShopLoader.load();

        // create new scene with the main menu (so we start with the main menu)
        Scene scene = new Scene(mainMenuRoot);
        scene.getRoot().setStyle("-fx-font-family: 'roboto', 'arial', 'helvetica'");
                
        // set functions which are activated when button click to switch menu is pressed
        // e.g. from main menu to start the game, or from the game to return to main menu
        mainController.setMainMenuSwitcher(() -> {switchToRoot(scene, mainMenuRoot, primaryStage);});
        mainController.setShopSwitcher(() -> {
                                                fadeSwitch(scene, shopRoot, primaryStage);
                                                updateShop();
                                            });
        mainController.setGameOverSwitcher(() -> {switchToRoot(scene, gameOverRoot, primaryStage);});

        gameOverController.setMenuSwitcher(() -> {switchToRoot(scene, mainMenuRoot, primaryStage);});
        mainController.setMainMenuSwitcher(() -> {
            // playMusic(menuSound);
            switchToRoot(scene, mainMenuRoot, primaryStage);}
        );
        mainController.setShopSwitcher(() -> {
            // playMusic(shopSound);
            switchToRoot(scene, shopRoot, primaryStage);}
        );
        mainController.setGameOverSwitcher(() -> {
            // playMusic(mainSound);
            switchToRoot(scene, gameOverRoot, primaryStage);
        });
        gameOverController.setMenuSwitcher(() -> {
            // playMusic(menuSound);
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });
        mainMenuController.setGameSwitcher(() -> {
            // playMusic(mainSound);
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });
        mainController.setOpenShopSwitcher(() -> {
            // playMusic(shopSound);
            switchToRoot(scene, openShopRoot, primaryStage);}
        );
        loopManiaShopController.setGameSwitcher( () -> {
            // playMusic(mainSound);
            updateShop();
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });
        mainController.setBattleSwitcher( () -> {
            // playMusic(battleSound);
            fadeSwitch(scene, battleRoot, primaryStage);
            battleController.startBattle();
        });
        battleController.setGameSwitcher( () -> {
            // playMusic(mainSound);
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        openShopController.setGameSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            updateShop();
            mainController.startTimer();
        });
        openShopController.setShopSwitcher(() -> {switchToRoot(scene, shopRoot, primaryStage);});


        // deploy the main onto the stage
        gameRoot.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
        // playMusic(menuSound);
    }

    @Override
    public void stop(){
        // wrap up activities when exit program
        mainController.terminate();
    }

    /**
     * switch to a different Root
     */
    private void switchToRoot(Scene scene, Parent root, Stage stage){
        scene.setRoot(root);
        scene.getRoot().setStyle("-fx-font-family: 'roboto', 'arial', 'helvetica'");
        root.requestFocus();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private void fadeSwitch(Scene scene, Parent root, Stage stage) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        switchToRoot(scene, root, stage);
    }

    private void updateShop(){
        this.loopManiaShopController.updateShopPuchasableItems();
        this.loopManiaShopController.updateInventory();
        this.loopManiaShopController.updateTotalGold();
        this.loopManiaShopController.updateTotalCoins();
    }
    
    // /**
    //  * play music for the sceene
    //  * @param soundStr sound file name
    //  */
    // private void playMusic(String soundFile) {
    //     Media sound = new Media(new File(soundFile).toURI().toString());
    //     mediaPlayer = new MediaPlayer(sound);
    //     mediaPlayer.setVolume(0.2);
    //     mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    //     mediaPlayer.play();
    // }

    public static void main(String[] args) {
        launch(args);
    }
}
