package lab4.alternativ2.sudoku.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lab4.alternativ2.sudoku.model.GridModel;

public class SudokuView {
    private final GridModel model;
    private VBox rootPane;
    private MenuBar menuBar;
    private BorderPane borderPane;
    private GridView gridView;

    public SudokuView(GridModel model) {
        this.model = model;
        gridView = new GridView(model);
        borderPane = new BorderPane();
        borderPane.setCenter(gridView.getNumberPane());
        GridController gridController = new GridController(this.model, this, gridView);

        //initView();

        createMenuBar(gridController);
        //createButtons(gridController);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    public void showAlert(Alert.AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }

    private void createMenuBar(GridController gridController) {
        Menu fileMenu = new Menu("File");
        MenuItem loadGameItem = new MenuItem("Load game");
        MenuItem saveGameItem = new MenuItem("Save game");
        MenuItem exitItem = new MenuItem("Exit");

        EventHandler<ActionEvent> loadGameHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleLoadGame();
            }
        };
        loadGameItem.addEventHandler(ActionEvent.ACTION, loadGameHandler);

        EventHandler<ActionEvent> saveGameHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleSaveGame();
            }
        };
        saveGameItem.addEventHandler(ActionEvent.ACTION, saveGameHandler);

        EventHandler<ActionEvent> exitHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        };
        exitItem.addEventHandler(ActionEvent.ACTION, exitHandler);
        fileMenu.getItems().addAll(loadGameItem, saveGameItem, exitItem);

        Menu gameMenu = new Menu("Game");
        MenuItem newGameItem = new MenuItem("New game");
        Menu selectNewLevelMenu = new Menu("Select new level");
        MenuItem easyLevelItem = new MenuItem("Easy");
        MenuItem mediumLevelItem = new MenuItem("Medium");
        MenuItem hardLevelItem = new MenuItem("Hard");

        EventHandler<ActionEvent> newGameHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNewGame();
            }
        };
        newGameItem.addEventHandler(ActionEvent.ACTION, newGameHandler);

        EventHandler<ActionEvent> easyLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNewLevelEasy();
            }
        };
        easyLevelItem.addEventHandler(ActionEvent.ACTION, easyLevelHandler);

        EventHandler<ActionEvent> mediumLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNewLevelMedium();
            }
        };
        mediumLevelItem.addEventHandler(ActionEvent.ACTION, mediumLevelHandler);

        EventHandler<ActionEvent> hardLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNewLevelHard();
            }
        };
        hardLevelItem.addEventHandler(ActionEvent.ACTION, hardLevelHandler);

        selectNewLevelMenu.getItems().addAll(easyLevelItem, mediumLevelItem, hardLevelItem);
        gameMenu.getItems().addAll(newGameItem, selectNewLevelMenu);

        Menu helpMenu = new Menu("Help");
        MenuItem startOverItem = new MenuItem("Start over");
        MenuItem gameRulesItem = new MenuItem("Game rules");

        EventHandler<ActionEvent> startOverHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleStartOver();
            }
        };
        startOverItem.addEventHandler(ActionEvent.ACTION, startOverHandler);

        EventHandler<ActionEvent> gameRulesHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleGameRules();
            }
        };
        gameRulesItem.addEventHandler(ActionEvent.ACTION, gameRulesHandler);
        helpMenu.getItems().addAll(startOverItem, gameRulesItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }
}
