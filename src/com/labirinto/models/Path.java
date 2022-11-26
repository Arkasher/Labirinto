package com.labirinto.models;

import java.util.Objects;

public class Path {

    private boolean walked = false;
    private int x;
    private int y;
    private Path parent;

    public Path(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Path(int x, int y, Path parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Path getParent() {
        return parent;
    }

    public boolean isWalked() {
        return walked;
    }

    public void setWalked(boolean walked) {
        this.walked = walked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Path update(Path newPath) {
        setX(newPath.getX());
        setY(newPath.getY());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        Path path = (Path) o;
        return x == path.x && y == path.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(walked, x, y);
    }
}
