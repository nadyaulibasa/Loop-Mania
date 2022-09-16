package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {
    @FXML
    private Button startOver;

    private MenuSwitcher menuSwitcher;

    public void setMenuSwitcher(MenuSwitcher menuSwitcher){
        this.menuSwitcher = menuSwitcher;
    }

    @FXML
    private void startOver() throws IOException
    {
        menuSwitcher.switchMenu();
    }
}
