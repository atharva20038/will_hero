package com.example.project_try;

public class Coordinate {
    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;

    Coordinate(int xStart,int yStart,int width,int height){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = this.xStart + width;
        this.yEnd = this.yStart + height;
    }

    public int getXEnd() {
        return xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }

    public int getXStart() {
        return xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public void setXEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public void setXStart(int xStart) {
        this.xStart = xStart;
    }

    public void setYEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public void setYStart(int yStart) {
        this.yStart = yStart;
    }
}
