package lab4.alternativ2.sudoku.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuView extends Application {
    public VBox rootPane;
    public MenuBar menuBar;
    public BorderPane gamePane;
    public GridView gridView;
    public GridController gridController;

    @Override
    public void start(Stage stage) throws Exception {

    }

    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    private void createMenuBar(GridController gridController) {
        Menu fileMenu = new Menu("File");
        MenuItem loadGameItem = new MenuItem("Load game");
        MenuItem saveGameItem = new MenuItem("Save game");
        MenuItem exitItem = new MenuItem("Exit");

        EventHandler<ActionEvent> loadGameHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // controller
            }
        };
        loadGameItem.addEventHandler(ActionEvent.ACTION, loadGameHandler);
        fileMenu.getItems().add(loadGameItem);

        EventHandler<ActionEvent> saveGameHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // controller
            }
        };
        saveGameItem.addEventHandler(ActionEvent.ACTION, saveGameHandler);
        fileMenu.getItems().add(saveGameItem);

        EventHandler<ActionEvent> exitHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        };
        exitItem.addEventHandler(ActionEvent.ACTION, exitHandler);
        fileMenu.getItems().add(exitItem);

        Menu gameMenu = new Menu("Game");
        MenuItem newGameItem = new MenuItem("New game");
        MenuItem selectNewLevelItem = new MenuItem("Select new level");

        EventHandler<ActionEvent> newGameHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // controller
            }
        };
        newGameItem.addEventHandler(ActionEvent.ACTION, newGameHandler);
        gameMenu.getItems().add(newGameItem);

        EventHandler<ActionEvent> selectNewLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // controller
            }
        };
        selectNewLevelItem.addEventHandler(ActionEvent.ACTION, selectNewLevelHandler);
        gameMenu.getItems().add(selectNewLevelItem);

        Menu helpMenu = new Menu("Help");
        MenuItem startOverItem = new MenuItem("Start over");
        MenuItem gameRulesItem = new MenuItem("Game rules");

        EventHandler<ActionEvent> startOverHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // controller
            }
        };
        startOverItem.addEventHandler(ActionEvent.ACTION, startOverHandler);
        helpMenu.getItems().add(startOverItem);

        EventHandler<ActionEvent> gameRulesHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // controller
            }
        };
        gameRulesItem.addEventHandler(ActionEvent.ACTION, gameRulesHandler);
        helpMenu.getItems().add(gameRulesItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }
}