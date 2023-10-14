package lab4.alternativ2.sudoku.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.model.SudokuUtilities;

public class SudokuView {
    private final GridController gridController;
    private final VBox rootPane;
    private MenuBar menuBar;
    private final VBox helpButtonPane;
    private final GridView gridView;
    private final VBox numberButtonPane;

    public SudokuView() {
        GridModel gridModel = new GridModel();
        gridController = new GridController(gridModel, this);

        rootPane = new VBox();
        createMenuBar();
        BorderPane gamePane = new BorderPane();
        rootPane.getChildren().addAll(menuBar, gamePane);

        helpButtonPane = new VBox();
        gamePane.setLeft(helpButtonPane);
        gridView = new GridView(gridModel);
        gamePane.setCenter(gridView.getNumberPane());
        numberButtonPane = new VBox();
        gamePane.setRight(numberButtonPane);

        createButtons();
    }

    public VBox getRootPane() {
        return rootPane;
    }

    public GridView getGridView() {
        return gridView;
    }

    public void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private void createMenuBar() {
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

    public void createButtons() {
        Button checkButton = new Button("Check");
        EventHandler<ActionEvent> checkHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleCheck();
            }
        };
        checkButton.addEventHandler(ActionEvent.ACTION, checkHandler);
        Button hintButton = new Button("Hint");
        EventHandler<ActionEvent> hintHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleHint();
            }
        };
        hintButton.addEventHandler(ActionEvent.ACTION, hintHandler);
        helpButtonPane.getChildren().addAll(checkButton, hintButton);

        Button number1Button = new Button("1");
        EventHandler<ActionEvent> number1Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(1);
            }
        };
        number1Button.addEventHandler(ActionEvent.ACTION, number1Handler);
        Button number2Button = new Button("2");
        EventHandler<ActionEvent> number2Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(2);
            }
        };
        number2Button.addEventHandler(ActionEvent.ACTION, number2Handler);
        Button number3Button = new Button("3");
        EventHandler<ActionEvent> number3Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(3);
            }
        };
        number3Button.addEventHandler(ActionEvent.ACTION, number3Handler);
        Button number4Button = new Button("4");
        EventHandler<ActionEvent> number4Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(4);
            }
        };
        number4Button.addEventHandler(ActionEvent.ACTION, number4Handler);
        Button number5Button = new Button("5");
        EventHandler<ActionEvent> number5Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(5);
            }
        };
        number5Button.addEventHandler(ActionEvent.ACTION, number5Handler);
        Button number6Button = new Button("6");
        EventHandler<ActionEvent> number6Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(6);
            }
        };
        number6Button.addEventHandler(ActionEvent.ACTION, number6Handler);
        Button number7Button = new Button("7");
        EventHandler<ActionEvent> number7Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(7);
            }
        };
        number7Button.addEventHandler(ActionEvent.ACTION, number7Handler);
        Button number8Button = new Button("8");
        EventHandler<ActionEvent> number8Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(8);
            }
        };
        number8Button.addEventHandler(ActionEvent.ACTION, number8Handler);
        Button number9Button = new Button("9");
        EventHandler<ActionEvent> number9Handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(9);
            }
        };
        number9Button.addEventHandler(ActionEvent.ACTION, number9Handler);
        Button clearButton = new Button("C");
        EventHandler<ActionEvent> clearHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController.handleNumbers(0);
            }
        };
        clearButton.addEventHandler(ActionEvent.ACTION, clearHandler);
        numberButtonPane.getChildren().addAll(number1Button, number2Button, number3Button, number4Button, number5Button, number6Button, number7Button, number8Button, number9Button, clearButton);
    }
}