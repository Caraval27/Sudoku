package com.example.test.model;

import java.io.Serializable;

public class Square implements Serializable {
    private final int correctNumber;
    private int selectedNumber;
    private final boolean changeable;

    public Square(int correctNumber, int selectedNumber, boolean changeable) {
        this.correctNumber = correctNumber;
        this.selectedNumber = selectedNumber;
        this.changeable = changeable;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    @Override
    public String toString() {
        return "Square [correctNumber: " + correctNumber + ", selectedNumber: " + selectedNumber + ", changeable: " + changeable + ']';
    }
}

