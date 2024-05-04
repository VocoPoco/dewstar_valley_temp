package org.example.models;

public class Farm {
    private static Farm instance;
    private Field[][] grid;
    private Farm() {
        this.grid = new Field[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.grid[i][j] = new Field();
            }
        }
    }

    public static Farm getInstance() {
        if (instance == null) {
            instance = new Farm();
        }
        return instance;
    }

    public Field getField(PlaneVector position) {
        int x = (int) position.getX();
        int y = (int) position.getY();
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new IndexOutOfBoundsException("Position out of bounds!");
        }
        return this.grid[x][y];
    }

    public Field[][] getGrid() {
        return this.grid;
    }
}
