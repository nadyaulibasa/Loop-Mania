package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class OpenShopController {

    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;
    private ShopSwitcher shopSwitcher;
    


    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    public void setShopSwitcher(ShopSwitcher shopSwitcher){
        this.shopSwitcher = shopSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame() {
  
        gameSwitcher.switchMenu();
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToShop() {
  
        shopSwitcher.switchShop();
    }
}
