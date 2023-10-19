package lab4.alternativ2.sudoku.model.io;

import lab4.alternativ2.sudoku.model.Square;
import java.io.*;

/**
 * This class represents the reading and writing Square objects to and from a given file.
 * If a I/O error occurs while reading or writing from a file, an IOException is thrown.
 * The method for reading from a file, deSerializeFromFile, also throws a ClassNotFoundException
 * if the serialized objects class, Square, was not found.
 */
public class SudokuFileIO {

    /**
     * Serializes a 2D array of Square objects to a specified file, if the file already exists,
     * it will be overwritten.
     *
     * @param file File object representing the file to which the given data will be written.
     * @param data 2D array of Square objects that will be serialized, written to the given file.
     * @throws IOException if an I/O error occurs while writing to the file,
     * it will be handled in the GridController class.
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
     * Deserialize a 2D array of Square objects from a specified file.
     * The method reads a 2D array of Square objects from a given file and returns it.
     *
     * @param file File object representing the file from which the data will be read.
     * @return the 2D array of Square objects deserialized from the given file.
     * @throws IOException if an I/O error occurs while reading from the given file,
     * it will be handled in the GridController class.
     * @throws ClassNotFoundException if the class of the serialized object, Square, can't be found.
     */
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