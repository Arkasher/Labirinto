package com.labirinto.solver;

import com.labirinto.Main;
import com.labirinto.models.Maze;
import com.labirinto.models.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchMazeSolver implements MazeSolver {

    @Override
    public List<Path> solve(Maze maze) {
        List<Path> path = new ArrayList<>();
        if (explore(maze.getPosition().getX(), maze.getPosition().getY(), path, maze)) {
            return path;
        }
        return Collections.emptyList();
    }

    private static Path getNearbyCoordinate(
            int row, int col, int x, int y) {
        return new Path(row + x, col + y);
    }


    private boolean explore(int row, int col, List<Path> path, Maze maze) {
        Path rowColPath = new Path(row, col);
        if (!maze.isWalkable(rowColPath) ||
                maze.isWalked(rowColPath)) {
            return false;
        }

        path.add(rowColPath);
        maze.setWalked(rowColPath);

        if(maze.isExibitionMode()) {
            Main.exibeLabirinto(path, maze);
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (maze.isExit(rowColPath)) {
            return true;
        }

        for (int[] direction : MOVE_DIRECTIONS) {
            Path coordinate = getNearbyCoordinate(
                    row, col, direction[0], direction[1]);
            if (explore(coordinate.getX(), coordinate.getY(), path, maze)) {
                maze.updatePosition(coordinate);
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
