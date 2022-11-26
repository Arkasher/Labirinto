package com.labirinto.solver;

import com.labirinto.models.Maze;
import com.labirinto.models.Path;

import java.util.List;

public interface MazeSolver {

    int[][] MOVE_DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    List<Path> solve(Maze maze);

}
