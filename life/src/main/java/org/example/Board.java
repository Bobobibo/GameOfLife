package org.example;

import java.awt.*;

public class Board {

    boolean isClosed;
    int width;
    int height;
    Cell[][] cells;

    public Board( int width,int height){
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
    }
    public void initialize( Cell[] initCells){
        for ( int i=0; i<width; i++){
            for(int j=0;j<height;j++){
                cells[i][j] = new Cell(i,j,false);
            }
        }
        for(int i=0; i < initCells.length;i++){
            Cell current = initCells[i];
            cells[current.x][current.y].revive();
        }
    }

    Point[] Directions = {
            new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
            new Point(0, -1), new Point(0, 1),
            new Point(1, -1), new Point(1, 0), new Point(1, 1)
    };

    public int getNeighboursCount(Cell cell){
        int neighbourCount = 0;
        int x = cell.x;
        int y = cell.y;
        for(Point direction: Directions){
            int deltaX = x + direction.x;
            int deltaY= y + direction.y;
            if ( deltaX <0 ||deltaX>= width){
                continue;
            }
            if( deltaY < 0|| deltaY>= height){
                continue;
            }
            if (cells[deltaX][deltaY].isAlive){
                neighbourCount++;
            }
        }
        return neighbourCount;
    }
    private Cell[][] createNewTurn() {
        Cell[][] newTurn = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newTurn[i][j] = new Cell(i, j, cells[i][j].isAlive);
            }
        }
        return newTurn;
    }

    public void iterate() {
        Cell[][] newTurn = createNewTurn();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int neighbours = getNeighboursCount(cells[x][y]);
                newTurn[x][y].iterate(neighbours);
            }
        }
        cells = newTurn;
    }
    public void print() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(cells[j][i].print() + "  ");
            }
            System.out.println();

        }System.out.println();
    }




}
