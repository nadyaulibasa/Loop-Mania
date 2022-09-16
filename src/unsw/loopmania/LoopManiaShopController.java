package unsw.loopmania;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Loop mania Shop controller class
 */
public class LoopManiaShopController {

    private final double INVENTORY_MAX_ROW_DIM = 4;

    @FXML
    private AnchorPane paneTradingCentre;

    @FXML
    private Pane paneTopShop;
    
    @FXML
    private Pane paneShop;

    @FXML
    private Pane paneInventory;

    @FXML
    private GridPane paneShopItemList;

    @FXML
    private GridPane paneInventoryList;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnPurchaseItems;

    @FXML
    private Button btnSellItems;

    @FXML
    private Button btnSellCoins;

    @FXML
    private Label lbPurchaseTotal;

    @FXML
    private Label lbSellTotal;

    @FXML
    private Label lbEqGold;

    @FXML
    private Label lbTotalGold;

    @FXML
    private Label lbTotalCoins;

    @FXML
    private TextField txtCoins;

    @FXML
    private ImageView imgViewGold;

    @FXML
    private ImageView imgViewCoins;


    private MenuSwitcher gameSwitcher;


    private List<Item> shopItemsList;

    private List<Item> inventoryList;
    private List<Item> selectedItems;

    private double totalPurchase;
    private double totalSell;
    private double totalGold;

    private LoopManiaWorldController loopManiaWorldController;
    private Shop shop;

    /**
     * Constructor
     * @param loopManiaWorldController
     */
    public LoopManiaShopController(LoopManiaWorldController loopManiaWorldController) {
        this.loopManiaWorldController = loopManiaWorldController;
        this.shop = loopManiaWorldController.getShop();
        this.selectedItems = new ArrayList<Item>();
        this.inventoryList = null;
    }

    /**
     * Setter - sets the game switcher
     * @param gameSwitcher
     */
    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * Updates the purchasable item list
     */
    private void getNewShopItemsList(){
        shop.setNewPurchasableItemList();
        this.shopItemsList = shop.getPurchasabletemList();
    }

    /**
     * Updates the inventory list
     */
    private void getInventory(){
        this.inventoryList = loopManiaWorldController.getInventory().getUnequippedItemList();
    }

    /**
     * Initialises controls and lists
     */
    @FXML
    public void initialize() {

        this.imgViewGold.setImage(new Image((new File("src/images/gold.png")).toURI().toString()));
        this.imgViewCoins.setImage(new Image((new File("src/images/doggiecoin.png")).toURI().toString()));

        this.updateShopPuchasableItems();

        this.updateInventory();

        this.updateTotalGold();

        this.updateTotalPurchase(null);
        this.updateTotalSell(null);
    }

    /**
     * Updates the total gold available
     */
    public void updateTotalGold(){
        this.totalGold = shop.getTotalGold();
        lbTotalGold.setText("Gold: $" + totalGold);
    }

    /**
     * Updated the total coins available
     */
    public void updateTotalCoins(){
        // Complete this.
    }

    /**
     * Clears the purchased item list
     */
    public void clearPurchasedItemList(){
        shop.clearPurchasedItemList();
    }

    /**
     * Updates shop purchasable items list
     */
    public void updateShopPuchasableItems(){
        this.getNewShopItemsList();

        this.paneShopItemList.getChildren().clear();
        int w = 0;
        for (int i = 0; i < this.shopItemsList.size(); i+=2){
            for (int j = 0; j < 2; j++){
                if (i * j < (this.shopItemsList.size() - 1)){
                    Item item = this.shopItemsList.get(w);
                    ToggleButton tbtn = new ToggleButton();
                    tbtn.setMaxSize(32, 32);
                    tbtn.setMinSize(32, 32);
                    
                    this.updateItemIcons(tbtn, item);

                    tbtn.selectedProperty().bindBidirectional(item.getIsSelected());
                    tbtn.disableProperty().bind(item.getIsNotPurchasable());

                    tbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            updateTotalPurchase(item);
                            updateItemIcons(tbtn, item);
                        }
                    });

                    paneShopItemList.add(tbtn, j, i);

                    Label lb = new Label(item.getItemName() + " - $" + item.getItemPrice());
                    lb.setFont(Font.font("Roboto", 12));
                    paneShopItemList.add(lb, j, i+1);
                    w++;
                }
            }
        }
    }

    /**
     * Updates the inventory list
     */
    public void updateInventory(){
        this.getInventory();

        this.paneInventoryList.getChildren().clear();
        System.out.println("Inventory list size: " + this.inventoryList.size());

        int listSize = this.inventoryList.size();
        int rows = (int)Math.ceil(((double)listSize)/this.INVENTORY_MAX_ROW_DIM);
        int w = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < this.INVENTORY_MAX_ROW_DIM; j++){
                if (w < listSize){

                
                    Item item = this.inventoryList.get(w);
                    ToggleButton tbtn = new ToggleButton(item.getItemName());
                    tbtn.setMaxSize(32, 32);
                    tbtn.setMinSize(32, 32);
                
                    this.updateItemIcons(tbtn, item);

                    tbtn.selectedProperty().bindBidirectional(item.getIsSelected());

                    tbtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            updateTotalSell(item);
                            updateItemIcons(tbtn, item);
                        }
                    });
                    paneInventoryList.add(tbtn, j, i);
                }
                w++;
            }
        }
    }

    /**
     * Updates the item icon
     * @param tbtn
     * @param item
     */
    private void updateItemIcons(ToggleButton tbtn, Item item){
        Image tbtnImage;
        BackgroundFill[] bgFill;

        if (item.getIsSelected().get()){
            this.selectedItems.add(item);
            tbtnImage = new Image((new File(item.getImageString())).toURI().toString());            
            BackgroundFill[] bgFillSel = {new BackgroundFill(Color.LIGHTGREY, new CornerRadii(2), null)};
            bgFill = bgFillSel;
        } else{
            this.selectedItems.remove(item);
            tbtnImage = new Image((new File(item.getImageString())).toURI().toString());
            BackgroundFill[] bgFillUnSel = {new BackgroundFill(Color.SNOW, new CornerRadii(2), null)};
            bgFill = bgFillUnSel;
        }
        BackgroundImage[] bgImage = {new BackgroundImage(tbtnImage, null, null, null, null)};
        tbtn.setBackground(new Background(bgFill, bgImage));
    }

    /**
     * Purchases the selected items in shop
     * @param e
     * @throws IOException
     */
    @FXML
    private void purchaseSelectedItems(ActionEvent e) throws IOException{
        System.err.println("Purchasing");
        shop.purchaseItems(this.inventoryList, this.loopManiaWorldController);
        this.updateShopPuchasableItems();
        this.updateInventory();
        this.updateTotalGold();
        this.updateTotalPurchase(null);
    }

    /**
     * Sells the selected items in inventory
     * @param e
     * @throws IOException
     */
    @FXML
    private void sellSelectedItems(ActionEvent e) throws IOException{
        shop.sellInventoryItems(this.inventoryList);
        this.updateInventory();
        this.updateTotalGold();
        this.updateTotalSell(null);
    }

    /**
     * Sells doggie coins
     * @param e
     * @throws IOException
     */
    @FXML
    private void sellDoggieCoins(ActionEvent e) throws IOException{
        return;
    }

    /**
     * Updates the total purchase value of selected items
     * @param item
     */
    private void updateTotalPurchase(Item item){
        if (item != null){
            item.setIsSelected(shop.canSelectItem(item, this.selectedItems));
        }
        this.totalPurchase = shop.getTotalPurchase();

        this.lbPurchaseTotal.setText("Total: $" + this.totalPurchase);
    }

    /**
     * Updates the total selling value of selected items
     * @param item
     */
    private void updateTotalSell(Item item){

        this.totalSell = shop.getTotalSell(this.inventoryList);
        this.lbSellTotal.setText("Total: $" + this.totalSell);
    }

    /**
     * Facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame(ActionEvent e) throws IOException {
        gameSwitcher.switchMenu();
    }
}
