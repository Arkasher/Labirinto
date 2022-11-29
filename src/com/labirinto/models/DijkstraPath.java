package com.labirinto.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DijkstraPath extends Path {
    public DijkstraPath(int x, int y) {
        super(x, y);
    }

    public DijkstraPath(int x, int y, Path parent) {
        super(x, y, parent);
    }

    private List<DijkstraPath> shortestPath = new LinkedList<>();
    private int distance = Integer.MAX_VALUE;

    private final Map<DijkstraPath, Integer> adjacentPaths = new HashMap<>();

    public void addDestination(DijkstraPath destination, int distance) {
        adjacentPaths.put(destination, distance);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<DijkstraPath> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<DijkstraPath> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<DijkstraPath, Integer> getAdjacentPaths() {
        return adjacentPaths;
    }
}
