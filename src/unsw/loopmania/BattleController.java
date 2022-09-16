package unsw.loopmania;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import unsw.loopmania.enemies.Enemy;
import javafx.scene.image.Image;

public class BattleController {
    private Battle battle;
    private Timeline timeline;
    final private int BATTLE_CONTINUES = 0;
    final private int HERO_WIN = 1;
    final private int ENEMY_WIN = 2;
    final private LoopManiaWorld world = LoopManiaWorldController.world;

    private MenuSwitcher gameSwitcher;
    private BattleSwitcher battleSwitcher;
    private Image heroImage;

    @FXML
    private TextArea battleLog;
    @FXML
    private VBox enemies;
    /**
     * Character view
     */
    @FXML
    private ImageView characterImageView;
    @FXML
    private ProgressBar characterHealth;
    @FXML
    /**
     * Ally view
     */
    private ImageView a1View;
    @FXML
    private ProgressBar a1HP;
    @FXML
    private ImageView a2View;
    @FXML
    private ProgressBar a2HP;
    @FXML
    private ImageView a3View;
    @FXML
    private ProgressBar a3HP;
    @FXML
    /**
     * Enemy view
     */
    private ImageView e1View;
    @FXML
    private ProgressBar e1HP;
    @FXML
    private ImageView e2View;
    @FXML
    private ProgressBar e2HP;
    @FXML
    private ImageView e3View;
    @FXML
    private ProgressBar e3HP;
    @FXML
    private ImageView e4View;
    @FXML
    private ProgressBar e4HP;


    public BattleController() {       
        heroImage = new Image((new File("src/images/heroImage.png")).toURI().toString());
    }

    /**
     * Start the battle in the backend while updating the fx in front end
     */
    public void startBattle() {
        if (world.getCurrentBattle() == null) return;
        this.battle = world.getCurrentBattle();
        resetFX();
        updateFX();
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), event -> {
            System.err.print("erm\n");
            int result = battle.tickBattle();
            updateFX();
            if (result != BATTLE_CONTINUES) {
                timeline.stop();
                resetFX();
                gameSwitcher.switchMenu();
            }
            
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Sets game wticher
     * @param gameSwitcher
     */
    public void setGameSwitcher (MenuSwitcher gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * Updates what the human character sees in the front end
     */
    public void updateFX() {
        characterImageView.setImage(heroImage);
        characterHealth.setProgress(world.getCharacter().getPercentageHealth());
        updateEnemies();
        updateAllies();
        battleLog.appendText(battle.getTurnDetails() + "\n");
    }
    
    /**
     * Resets any hidden nodes in the front end
     */
    public void resetFX() {
        e1View.setVisible(true);
        e1HP.setVisible(true);
        e2View.setVisible(true);
        e2HP.setVisible(true);
        e3View.setVisible(true);
        e3HP.setVisible(true);
        e4View.setVisible(true);
        e4HP.setVisible(true);
        a1View.setVisible(true);
        a1HP.setVisible(true);
        a2View.setVisible(true);
        a2HP.setVisible(true);
        a3View.setVisible(true);
        a3HP.setVisible(true);
        battleLog.setText(" ");
    }

    /**
     * Updates health/ if enemy is alive
     */
    public void updateEnemies() {
        int iter = 1;
        for (Enemy e: battle.getEnemies()) {
            switch(iter) {
                case 1:
                    e1View.setImage(new Image((new File(e.getImageString())).toURI().toString()));
                    e1HP.setProgress(e.getPercentageHealth());
                    iter++;
                    break;
                case 2:
                    e2View.setImage(new Image((new File(e.getImageString())).toURI().toString()));
                    e2HP.setProgress(e.getPercentageHealth());
                    iter++;
                    break;
                case 3:
                    e3View.setImage(new Image((new File(e.getImageString())).toURI().toString()));
                    e3HP.setProgress(e.getPercentageHealth());
                    iter++;
                    break;
                case 4:
                    e4View.setImage(new Image((new File(e.getImageString())).toURI().toString()));
                    e4HP.setProgress(e.getPercentageHealth());
                    iter++;
                    break;
                default:
                    break;
            }
        }
        while (iter < 5) {
            switch(iter) {
                case 1:
                    e1View.setVisible(false);
                    e1HP.setVisible(false);
                    iter++;
                    break;
                case 2:
                    e2View.setVisible(false);
                    e2HP.setVisible(false);
                    iter++;
                    break;
                case 3:
                    e3View.setVisible(false);
                    e3HP.setVisible(false);
                    iter++;
                    break;
                case 4:
                    e4View.setVisible(false);
                    e4HP.setVisible(false);
                    iter++;
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Updates health/ if allies are alive
     */
    public void updateAllies() {
        int iter = 1;
        for (Ally a: world.getCharacter().getAllies()) {
            switch(iter) {
                case 1:
                    a1View.setImage(new Image((new File(a.getImageString())).toURI().toString()));
                    a1HP.setProgress(a.getPercentageHealth());
                    iter++;
                    break;
                case 2:
                    a2View.setImage(new Image((new File(a.getImageString())).toURI().toString()));
                    a2HP.setProgress(a.getPercentageHealth());
                    iter++;
                    break;
                case 3:
                    a3View.setImage(new Image((new File(a.getImageString())).toURI().toString()));
                    a3HP.setProgress(a.getPercentageHealth());
                    iter++;
                    break;
                default:
                    break;
            }
        }
        while (iter < 4) {
            switch(iter) {
                case 1:
                    a1View.setVisible(false);
                    a1HP.setVisible(false);
                    iter++;
                    break;
                case 2:
                    a2View.setVisible(false);
                    a2HP.setVisible(false);
                    iter++;
                    break;
                case 3:
                    a3View.setVisible(false);
                    a3HP.setVisible(false);
                    iter++;
                    break;
                default:
                    break;
            }
        }
    }
}