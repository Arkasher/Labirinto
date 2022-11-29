package com.labirinto.solver;

import com.labirinto.models.DijkstraPath;
import com.labirinto.models.Maze;
import com.labirinto.models.Path;

import java.util.*;

public class DijkstraMazeSolver implements MazeSolver {

    @Override
    public List<Path> solve(Maze maze) {
        maze.calculateAdjacentPaths((DijkstraPath) maze.getPosition(), 0);
        ((DijkstraPath) maze.getPosition()).setDistance(0);

        Set<DijkstraPath> settledNodes = new HashSet<>();
        Set<DijkstraPath> unsettledNodes = new HashSet<>();

        unsettledNodes.add((DijkstraPath) maze.getPosition());

        while (unsettledNodes.size() != 0) {
            DijkstraPath currentNode = getLowestDistancePath(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<DijkstraPath, Integer> adjacencyPair : currentNode.getAdjacentPaths().entrySet()) {
                DijkstraPath adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return new ArrayList<>(settledNodes);
    }

    private DijkstraPath getLowestDistancePath(Set<DijkstraPath> unsettledNodes) {
        DijkstraPath lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (DijkstraPath node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(DijkstraPath evaluationNode, Integer edgeWeigh, DijkstraPath sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<DijkstraPath> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
