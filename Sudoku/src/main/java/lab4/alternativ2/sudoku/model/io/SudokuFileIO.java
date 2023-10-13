package lab4.alternativ2.sudoku.model.io;

import lab4.alternativ2.sudoku.model.Square;

import java.io.*;

public class SudokuFileIO {
    public static void serializeToFile(File file, Square[][] data) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(data);
        }
        finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }

    public static Square[][] deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            return (Square[][]) objectInputStream.readObject();
        }
        finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }

    private SudokuFileIO() {}
}