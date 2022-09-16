// package unsw.loopmania;

// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.EmptyStackException;
// import java.util.List;
// import java.util.Stack;

// import javafx.beans.property.SimpleIntegerProperty;


// // import org.graalvm.compiler.word.Word;

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.fxml.FXML;
// import javafx.scene.Node;
// import javafx.scene.control.Button;
// import javafx.scene.effect.ColorAdjust;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.GridPane;
// import javafx.scene.text.Text;
// import javafx.stage.Stage;

// import unsw.loopmania.game_modes.*;
// import unsw.loopmania.miscItems.HealthPotion;
// import unsw.loopmania.weapons.*;
// import unsw.loopmania.defenses.*;

// public class LoopManiaShopControllerOriginal {

//     @FXML
//     private GridPane shopUnequippedInventory;

//     @FXML
//     private GridPane shopListing;

//     @FXML
//     private Button shopCancel;

//     @FXML
//     private Button shopPurchase;

//     @FXML
//     private Text shopText;

//     @FXML
//     private Text coinCount;

//     private MenuSwitcher gameSwitcher;

//     private List<Item> selectedList;

//     private ColorAdjust colorAdjust;
//     private ColorAdjust original;

//     final private Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());;
//     final private Image swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
//     final private Image stakeImage = new Image((new File("src/images/stake.png")).toURI().toString());
//     final private Image staffImage = new Image((new File("src/images/staff.png")).toURI().toString());
//     final private Image armourImage = new Image((new File("src/images/armour.png")).toURI().toString());
//     final private Image shieldImage = new Image((new File("src/images/shield.png")).toURI().toString());
//     final private Image helmetImage = new Image((new File("src/images/helmet.png")).toURI().toString());
//     final private Image potionImage = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());

//     public void setGameSwitcher(MenuSwitcher gameSwitcher){
//         this.gameSwitcher = gameSwitcher;
//     }

//     @FXML
//     public void initialize() {

//         // LoopManiaWorldController.world.getCharacter().addGold(1000);
//         coinCount.setText("$ " + Integer.toString(LoopManiaWorldController.world.getCharacter().getTotalGold()));


//         this.colorAdjust = new ColorAdjust();
//         colorAdjust.setBrightness(-0.5);
//         this.original = new ColorAdjust();
//         original.setBrightness(0.0);
        
//         this.selectedList = new ArrayList<Item>();

//         Stack<Image> stack = new Stack<Image>();

//         stack.push(swordImage);
//         stack.push(stakeImage);
//         stack.push(armourImage);
//         stack.push(staffImage);
//         stack.push(shieldImage);
//         stack.push(helmetImage);
//         stack.push(potionImage);

//         int x = 0;
//         int y = 0;

//         for (x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
//             for (y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
//                 ImageView emptySlotView = new ImageView(inventorySlotImage);
//                 shopUnequippedInventory.add(emptySlotView, x, y);
//             }
//         }

//         // add the empty slot images for the unequipped inventory for shop
//         for (Item item : LoopManiaWorldController.world.getCharacter().getInventory().getUnequippedItemList())
//         {
//             ImageView emptySlotView = new ImageView(inventorySlotImage);

//             if (item instanceof Sword) emptySlotView = new ImageView(swordImage);
//             else if (item instanceof Stake) emptySlotView = new ImageView(stakeImage);
//             else if (item instanceof Armour) emptySlotView = new ImageView(armourImage);
//             else if (item instanceof Staff) emptySlotView = new ImageView(staffImage);
//             else if (item instanceof Shield) emptySlotView = new ImageView(shieldImage);
//             else if (item instanceof Helmet) emptySlotView = new ImageView(helmetImage);
//             else if (item instanceof HealthPotion) emptySlotView = new ImageView(potionImage);

            
//             shopUnequippedInventory.add(emptySlotView, y, x);
//             x++;
//             if (x == LoopManiaWorld.unequippedInventoryWidth)
//             {
//                 y++;
//                 x = 0;
//             }
//         }


//         // add the images for shop listing
//         for (x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
//             for (y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
//                 try {
//                     Image currentImage = stack.pop();
//                     // ImageView slotView = new ImageView(stack.pop());
//                     ImageView slotView = new ImageView(currentImage);
//                     slotView.setEffect(original);
//                     slotView.setOnMouseClicked(new EventHandler<MouseEvent>()
//                     {
//                         @Override
//                         public void handle(MouseEvent e)
//                         {
//                             // deselect
//                             shopText.setText("System output");
//                             if (slotView.getEffect() == colorAdjust)
//                             {
//                                 slotView.setEffect(original);
//                                 Item delete = null;
//                                 for (Item i: selectedList)
//                                 {
//                                     if (currentImage == swordImage || i instanceof Sword) delete = i;
//                                     else if (currentImage == stakeImage || i instanceof Stake) delete = i;
//                                     else if (currentImage == armourImage || i instanceof Armour) delete = i;
//                                     else if (currentImage == staffImage || i instanceof Staff) delete = i;
//                                     else if (currentImage == shieldImage || i instanceof Shield) delete = i;
//                                     else if (currentImage == helmetImage || i instanceof Helmet) delete = i;
//                                     else if (currentImage == potionImage || i instanceof HealthPotion) delete = i;
//                                 }
//                                 // LoopManiaWorldController.world.getShop().removeSelectedItem(delete);
//                             }
//                             // select
//                             else
//                             {   
//                                 slotView.setEffect(colorAdjust);
//                                 Item newItem = null;

//                                 if (currentImage == swordImage) newItem = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//                                 else if (currentImage == stakeImage) newItem = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//                                 else if (currentImage == armourImage) newItem = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//                                 else if (currentImage == staffImage) newItem = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//                                 else if (currentImage == shieldImage) newItem = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//                                 else if (currentImage == helmetImage) newItem = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
//                                 else if (currentImage == potionImage) newItem = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

//                                 if (!LoopManiaWorldController.world.getShop().addSelectedItem(newItem))
//                                 {
//                                     shopText.setText("Not enough gold");
//                                     // System.out.println(newItem);
//                                     // System.out.println(LoopManiaWorldController.world.getCharacter().getTotalGold());
//                                     slotView.setEffect(original);
//                                 }
//                                 else
//                                 {
//                                     // for (Item item: LoopManiaWorldController.world.getShop().getSelectedItemList())
//                                     // {
//                                     //     System.out.println(item);
//                                     // }
//                                 }
//                             }
       

//                         }

//                     });
//                     shopListing.add(slotView, y, x);    
//                 } catch (EmptyStackException e) {
//                     ImageView emptySlotView = new ImageView(inventorySlotImage);
//                     shopListing.add(emptySlotView, y, x);    
//                 }
                
//             }
//         }


//     }


//     @FXML
//     private void purchase(ActionEvent e) throws IOException 
//     {
        
        
//         LoopManiaWorldController.world.getShop().purchaseItems();

//         for (Node n: shopListing.getChildren())
//         {
//             n.setEffect(original);
//         }


//         int x = 0;
//         int y = 0;
        
//         for (Item item : LoopManiaWorldController.world.getCharacter().getInventory().getUnequippedItemList())
//         {
//             // System.out.println(item);
//             ImageView emptySlotView = new ImageView(inventorySlotImage);

//             if (item instanceof Sword) emptySlotView = new ImageView(swordImage);
//             else if (item instanceof Stake) emptySlotView = new ImageView(stakeImage);
//             else if (item instanceof Armour) emptySlotView = new ImageView(armourImage);
//             else if (item instanceof Staff) emptySlotView = new ImageView(staffImage);
//             else if (item instanceof Shield) emptySlotView = new ImageView(shieldImage);
//             else if (item instanceof Helmet) emptySlotView = new ImageView(helmetImage);
//             else if (item instanceof HealthPotion) emptySlotView = new ImageView(potionImage);

            
//             shopUnequippedInventory.add(emptySlotView, y, x);
//             x++;
//             if (x == LoopManiaWorld.unequippedInventoryWidth)
//             {
//                 y++;
//                 x = 0;
//             }
//         }
//         coinCount.setText("$ " + Integer.toString(LoopManiaWorldController.world.getCharacter().getTotalGold()));
//     }

//     /**
//      * facilitates update the view from the world status
//      * @throws IOException
//      */
//     @FXML
//     private void update(ActionEvent e) throws IOException
//     {
//         coinCount.setText("$ " + Integer.toString(LoopManiaWorldController.world.getCharacter().getTotalGold()));
//         int x = 0;
//         int y = 0;
        
//         for (Item item : LoopManiaWorldController.world.getCharacter().getInventory().getUnequippedItemList())
//         {
//             // System.out.println(item);
//             ImageView emptySlotView = new ImageView(inventorySlotImage);

//             if (item instanceof Sword) emptySlotView = new ImageView(swordImage);
//             else if (item instanceof Stake) emptySlotView = new ImageView(stakeImage);
//             else if (item instanceof Armour) emptySlotView = new ImageView(armourImage);
//             else if (item instanceof Staff) emptySlotView = new ImageView(staffImage);
//             else if (item instanceof Shield) emptySlotView = new ImageView(shieldImage);
//             else if (item instanceof Helmet) emptySlotView = new ImageView(helmetImage);
//             else if (item instanceof HealthPotion) emptySlotView = new ImageView(potionImage);

            
//             shopUnequippedInventory.add(emptySlotView, y, x);
//             x++;
//             if (x == LoopManiaWorld.unequippedInventoryWidth)
//             {
//                 y++;
//                 x = 0;
//             }
//         }
//         // Node node = (Node) e.getSource();
//         // Stage stage = (Stage) node.getScene().getWindow();
//         // LoopManiaWorld world = (LoopManiaWorld) stage.getUserData();
        
//         // if (world.getShop().getGameMode() instanceof BerserkerGameMode)
//         // {
//         //     System.out.println("Berserker");
//         // }
//         // else if (world.getShop().getGameMode() instanceof SurvivalGameMode)
//         // {
//         //     System.out.println("Survival");
//         // }
//         // else if (world.getShop().getGameMode() instanceof StandardGameMode)
//         // {
//         //     System.out.println("Standard");
//         // }
//     }

//     /**
//      * facilitates switching to main game upon button click
//      * @throws IOException
//      */
//     @FXML
//     private void switchToGame(ActionEvent e) throws IOException {
//         gameSwitcher.switchMenu();
//     }

// }
