package com.labirinto;

import com.labirinto.models.Maze;
import com.labirinto.models.Path;
import com.labirinto.solver.BreadthFirstSearchMazeSolver;
import com.labirinto.solver.MazeSolver;

import java.util.*;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        String labirinto = """
                   ############################
                   #              #           #
                #  #  #######  #  #  #  #######
                #     #        #     #  #     #
                #  #######  #  #############  #
                #  #  #  #  #  #  #     #     #
                ####  #  ####  #  ####  #  ####
                #        #        #        #  #
                #  #######  ##########  ####  #
                #           #           #     #
                ##########  ##########  #  ####
                #  #     #  #  #        #     #
                #  #  ####  #  ####  #######  #
                #        #  #     #  #  #  #  #
                #######  #  #  ####  #  #  #  #
                #              #  #  #        #
                #  #######  #  #  #  #  ####  #
                #     #  #  #     #     #  #  #
                #  #  #  ####  #######  #  ####
                #  #  #                       \s
                ###############################
                """;

        String[] lines = labirinto.split("\n");

        Maze maze = new Maze();
        maze.map(lines);
        maze.setExibitionMode(false);

        MazeSolver mazeSolver = new BreadthFirstSearchMazeSolver();

        exibeLabirinto(mazeSolver.solve(maze), maze);
    }


    public static void exibeLabirinto(List<Path> path, Maze maze) {
        System.out.println(" ");
        for (int y = 0; y < maze.getColumns(); y++) {
            for (int x = 0; x < maze.getRows(); x++) {
                if (maze.isWalkable(new Path(x, y))) {
                    int finalX = x;
                    int finalY = y;
                    if (maze.getPosition().equals(new Path(x, y)) || path.stream().anyMatch(e -> e.equals(new Path(finalX, finalY)))) {
                        System.out.print(ANSI_RED + ". " + ANSI_RESET);
                    } else {
                        System.out.print("  ");
                    }
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
