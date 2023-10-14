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
import lab4.alternativ2.sudoku.model.SudokuUtilities;

public class SudokuView {
    private final GridModel gridModel;
    private VBox rootPane;
    private MenuBar menuBar;
    private BorderPane gamePane;
    private GridView gridView;

    public SudokuView() {
        gridModel = new GridModel();
        GridController gridController = new GridController(this.gridModel, this);
        rootPane = new VBox();
        createMenuBar(gridController);
        gamePane = new BorderPane();
        rootPane.getChildren().addAll(menuBar, gamePane);
        gridView = new GridView(gridModel);
        gamePane.setCenter(gridView.getNumberPane());

        //lägga till 2 Vbox i gamePane
        //createButtons(gridController);
    }

    public GridModel getGridModel() {
        return gridModel;
    }

    public VBox getRootPane() {
        return rootPane;
    }

    public GridView getGridView() {
        return gridView;
    }

    public BorderPane getGamePane() {
        return gamePane;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
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
                gridController.handleSelectNewLevel(SudokuUtilities.SudokuLevel.EASY);
            }
        };
        easyLevelItem.addEventHandler(ActionEvent.ACTION, easyLevelHandler);

        EventHandler<ActionEvent> mediumLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleSelectNewLevel(SudokuUtilities.SudokuLevel.MEDIUM);
            }
        };
        mediumLevelItem.addEventHandler(ActionEvent.ACTION, mediumLevelHandler);

        EventHandler<ActionEvent> hardLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleSelectNewLevel(SudokuUtilities.SudokuLevel.HARD);
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