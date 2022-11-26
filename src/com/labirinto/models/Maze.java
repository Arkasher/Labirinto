package com.labirinto.models;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private int[][] mazeArray;
    private Path exit;
    private final List<Path> walked = new ArrayList<>();
    private int rows;
    private int columns;
    private boolean exibitionMode;
    private final Path position = new Path(1, 1);

    public void map(String[] lines) {
        rows = lines[0].length();
        columns = lines.length;

        mazeArray = new int[rows][columns];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (lines[y].charAt(x) == 'q') {
                    mazeArray[x][y] = 4;
                    exit = new Path(x, y);
                } else if (x == 0 || y == 0 || x == rows - 1 || y == columns - 1) {
                    mazeArray[x][y] = 2;
                } else {
                    mazeArray[x][y] = lines[y].charAt(x) == '#' ? 0 : 1;
                }
            }
        }
    }

    public boolean isExit(Path path) {
        return path.equals(exit);
    }

    public void setWalked(Path path) {
        walked.add(path);
    }

    public boolean isWalked(Path path) {
        return walked.stream().anyMatch(e -> e.equals(path));
    }

    public boolean isWalkable(Path destination) {
        int value = mazeArray[destination.getX()][destination.getY()];
        return value == 1 || value == 4;
    }

    public boolean isExibitionMode() {
        return exibitionMode;
    }

    public void setExibitionMode(boolean exibitionMode) {
        this.exibitionMode = exibitionMode;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void updatePosition(Path path) {
        position.update(path);
    }

    public Path getPosition() {
        return position;
    }
}
