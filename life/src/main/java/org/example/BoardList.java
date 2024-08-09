package org.example;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BoardList {

    int width;
    int height;
    List<Cell> cellslist;


public BoardList(){

    this.cellslist = new LinkedList<>();

}

    public void initialize(List<Cell> initCells, int width, int height) {
        this.width = width;
        this.height = height;


        for (Cell cell : initCells) {
            cell.revive();
            cellslist.add(cell);
        }
    }

    Point[] Directions = {
            new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
            new Point(0, -1), new Point(0, 1),
            new Point(1, -1), new Point(1, 0), new Point(1, 1)
    };


    public List<Cell> getNeighbours(Cell cell) {
        List<Cell> neighbours = new LinkedList<>();
        int x = cell.x;
        int y = cell.y;

        for (Point direction : Directions) {
            int neighborX = x + direction.x;
            int neighborY = y + direction.y;

            if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                neighbours.add(new Cell(neighborX, neighborY, isCellAlive(neighborX, neighborY)));
            }
        }
        return neighbours;
    }

    private boolean isCellAlive(int x, int y) {
        for (Cell cell : cellslist) {
            if (cell.x == x && cell.y == y) {
                return cell.isAlive;
            }
        }
        return false;
    }


    public void iterate() {
        Set<Point> cellsToCheck = new HashSet<>();


        for (Cell cell : cellslist) {
            cellsToCheck.add(new Point(cell.x, cell.y));
            for (Cell neighbour : getNeighbours(cell)) {
                cellsToCheck.add(new Point(neighbour.x, neighbour.y));
            }
        }

        List<Cell> newTurnBoard = new LinkedList<>();


        for (Point point : cellsToCheck) {
            int aliveNeighbours = 0;
            for (Cell neighbour : getNeighbours(new Cell(point.x, point.y, false))) {
                if (neighbour.isAlive) {
                    aliveNeighbours++;
                }
            }

            boolean currentlyAlive = isCellAlive(point.x, point.y);
            if (currentlyAlive) {
                if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                    newTurnBoard.add(new Cell(point.x, point.y, true));
                }
            } else {
                if (aliveNeighbours == 3) {
                    newTurnBoard.add(new Cell(point.x, point.y, true));
                }
            }
        }

        cellslist = newTurnBoard;
    }

    public void print() {
        char[][] boardPrintation = new char[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardPrintation[i][j] = '○';
            }
        }

        for (Cell cell : cellslist) {
            if (cell.isAlive) {
                boardPrintation[cell.x][cell.y] = 'X';
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(boardPrintation[j][i] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }





}
