package lab4.alternativ2.sudoku.model.io;


import lab4.alternativ2.sudoku.model.Square;

import java.io.*;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class SudokuFileIO {
    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
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

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
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
