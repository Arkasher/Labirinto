package com.labirinto.solver;

import com.labirinto.Main;
import com.labirinto.models.Maze;
import com.labirinto.models.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearchMazeSolver implements MazeSolver {
    @Override
    public List<Path> solve(Maze maze) {
        LinkedList<Path> pathToVisit = new LinkedList<>();
        Path start = maze.getPosition();
        pathToVisit.add(start);

        while (!pathToVisit.isEmpty()) {
            Path cur = pathToVisit.remove();

            if (maze.isWalked(cur)) {
                continue;
            }

            if (!maze.isWalkable(cur)) {
                maze.setWalked(cur);
                continue;
            }

            if (maze.isExit(cur)) {
                return backtrackPath(cur);
            }

            for (int[] direction : MOVE_DIRECTIONS) {
                Path coordinate = new Path(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                pathToVisit.add(coordinate);
                maze.setWalked(cur);

                if(maze.isExibitionMode()) {
                    Main.exibeLabirinto(backtrackPath(coordinate), maze);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Path> backtrackPath(Path current) {
        List<Path> path = new ArrayList<>();
        Path parent = current;

        while (parent != null) {
            path.add(parent);
            parent = parent.getParent();
        }
        return path;
    }
}
