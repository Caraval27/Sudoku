package lab4.alternativ2.sudoku.model;

public class SudokuUtilities {

    public enum SudokuLevel {EASY, MEDIUM, HARD}

    public static final int GRID_SIZE = 9;
    public static final int NR_OF_GRIDS = 2;
    public static final int SECTIONS_PER_ROW = 3;
    public static final int SECTION_SIZE = 3;
    public static final int MAX_POSITION = 8;
    public static final int MIN_POSITION = 0;
    public static final int MAX_NUMBER = 9;
    public static final int MIN_NUMBER = 1;

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param level The level, i.e. the difficulty, of the initial standing.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    public static int[][][] generateSudokuMatrix(SudokuLevel level) {
        String representationString;
        int[][][] numbers = new int[GRID_SIZE][GRID_SIZE][NR_OF_GRIDS];
        representationString = switch (level) {
            case EASY -> easy;
            case MEDIUM -> medium;
            case HARD -> hard;
            default -> medium;
        };

        convertStringToIntMatrix(numbers, representationString);
        //print(numbers); // ta bort
        randomSudokuMatrix(numbers);
        print(numbers); // ta bort

        return numbers;
    }

    /**
     * Create a 3-dimensional matrix with initial numbers and solution in Sudoku.
     *
     * @param stringRepresentation A string of 2*81 characters, 0-9. The first 81 characters represents
     *                             the initial numbers, '0' representing an empty cell.
     *                             The following 81 characters represents the solution.
     * Fills A 3-dimensional int matrix.
     * [row][col][0] represents the initial numbers, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    /*package private*/
    static void convertStringToIntMatrix(int[][][] numbers, String stringRepresentation) {
        if (stringRepresentation.length() != GRID_SIZE * GRID_SIZE * NR_OF_GRIDS)
            throw new IllegalArgumentException("representation length " + stringRepresentation.length());

        char[] charRepresentation = stringRepresentation.toCharArray();
        int charIndex = 0;

        for (int grid = 0; grid < NR_OF_GRIDS; grid++) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    numbers[row][column][grid] = convertCharToSudokuInt(charRepresentation[charIndex++]);
                }
            }
        }
    }

    public static void print(int[][][] numbers) { // ta bort sen
        for (int i = 0; i < NR_OF_GRIDS - 1; i++) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    System.out.print(numbers[row][column][i]);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    private static int convertCharToSudokuInt(char ch) {
        if (ch < '0' || ch > '9') throw new IllegalArgumentException("character " + ch);
        return ch - '0';
    }

    private static void randomSudokuMatrix(int[][][] numbers) {
        int random = generateRandomNumber(3, 0);
        switch (random) {
            case 0 -> {}
            case 1 -> flipNumbersHorizontal(numbers);
            case 2 -> flipNumbersVertical(numbers);
            case 3 -> replaceNumbers(numbers);
            default -> replaceNumbers(numbers);
        }
    }

    public static int generateRandomNumber(int max, int min) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static void flipNumbersHorizontal(int[][][] numbers) {
        int[] temp;
        for (int column = 0; column < MAX_POSITION / 2; column++) {
            for (int row = 0; row < GRID_SIZE; row++) {
                temp = numbers[row][column];
                numbers[row][column] = numbers[row][GRID_SIZE - column - 1];
                numbers[row][GRID_SIZE - column - 1] = temp;
            }
        }
    }

    private static void flipNumbersVertical(int[][][] numbers) {
        int[][] temp;
        for (int row = 0; row < MAX_POSITION / 2; row++) {
            temp = numbers[row];
            numbers[row] = numbers[GRID_SIZE - row - 1];
            numbers[GRID_SIZE - row - 1] = temp;
        }
    }

    private static void replaceNumbers(int[][][] numbers) {
        int num1, num2;
        num1 = generateRandomNumber(MAX_NUMBER, MIN_NUMBER);
        num2 = generateRandomNumber(MAX_NUMBER , MIN_NUMBER);
        while (num1 == num2) {
            num2 = generateRandomNumber(MAX_NUMBER, MIN_NUMBER);
        }
        switchNumbers(numbers, num1, num2);
    }

    private static void switchNumbers(int[][][] numbers, int num1, int num2) {
        for (int grid = 0; grid < NR_OF_GRIDS; grid++) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (numbers[row][column][grid] == num1) {
                        numbers[row][column][grid] = num2;
                    } else if (numbers[row][column][grid] == num2) {
                        numbers[row][column][grid] = num1;
                    }
                }
            }
        }
    }

    private static final String easy =
            "000914070" +
                    "010000054" +
                    "040002000" +
                    "007569001" +
                    "401000500" +
                    "300100000" +
                    "039000408" +
                    "650800030" +
                    "000403260" + // solution numbers after this substring
                    "583914672" +
                    "712386954" +
                    "946752183" +
                    "827569341" +
                    "461238597" +
                    "395147826" +
                    "239675418" +
                    "654821739" +
                    "178493265";

    private static final String medium =
            "300000010" +
                    "000050906" +
                    "050401200" +
                    "030000080" +
                    "002069400" +
                    "000000002" +
                    "900610000" +
                    "200300058" +
                    "100800090" +
                    "324976815" +
                    "718253946" +
                    "659481273" +
                    "536142789" +
                    "872569431" +
                    "491738562" +
                    "985617324" +
                    "267394158" +
                    "143825697";

    private static final String hard =
            "030600000" +
                    "000010070" +
                    "080000000" +
                    "000020000" +
                    "340000800" +
                    "500030094" +
                    "000400000" +
                    "150800200" +
                    "700006050" +
                    "931687542" +
                    "465219378" +
                    "287345916" +
                    "876924135" +
                    "349561827" +
                    "512738694" +
                    "693452781" +
                    "154873269" +
                    "728196453";
}