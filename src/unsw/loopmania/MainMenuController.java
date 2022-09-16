package unsw.loopmania;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import unsw.loopmania.game_modes.BerserkerGameMode;
import unsw.loopmania.game_modes.StandardGameMode;
import unsw.loopmania.game_modes.SurvivalGameMode;

/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController{


    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;
    
    @FXML
    private ImageView gamemode;

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    final Image standardImage = new Image((new File("src/images/standard_mode.png")).toURI().toString());
    final Image survivalImage = new Image((new File("src/images/survival_mode.png")).toURI().toString());
    final Image berserkerImage = new Image((new File("src/images/berserker_mode.png")).toURI().toString());
    final LoopManiaWorld world = LoopManiaWorldController.world;

    @FXML
    private void initialize() {
        gamemode.setImage(standardImage);
    }

    /**
     * select corresponding gamemode
     * @throws IOException
     */
    @FXML
    private void selectGamemode() throws IOException {
        Image curr = gamemode.getImage();

        if (curr == standardImage)
        {
            gamemode.setImage(survivalImage);
        }
        else if (curr == survivalImage)
        {
            gamemode.setImage(berserkerImage);
        }
        else if (curr == berserkerImage)
        {
            gamemode.setImage(standardImage);
        }
        
        // switch () {
        //     case "Gamemode : Standard":
        //         gamemode.setText("Gamemode : Survival");
        //         break;
        //     case "Gamemode : Survival":
        //         gamemode.setText("Gamemode : Berserker");
        //         break;
        //     case "Gamemode : Berserker":
        //         gamemode.setText("Gamemode : Standard");
        //         break;
        // }
        // gamemode.setMinWidth(175);
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame(MouseEvent event) throws IOException {

        Image curr = gamemode.getImage();

        if (curr == standardImage)
        {
            world.setGameMode(new StandardGameMode());
        }
        else if (curr == survivalImage)
        {
            world.setGameMode(new SurvivalGameMode());
        }
        else if (curr == berserkerImage)
        {
            world.setGameMode(new BerserkerGameMode());
        }

  
        gameSwitcher.switchMenu();
    }
}
