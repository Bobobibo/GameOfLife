package org.example;

import java.util.LinkedList;
import java.util.List;

public class Game {
    public BoardList board;

    public Game(BoardList board) {
        this.board = board;
    }

    public void Play() {
        board.print();
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            board.iterate();
            board.print();
        }
    }

    public static void main(String[] args) {
        int width = 10;
        int height = 10;
        BoardList board = new BoardList();
        List<Cell> initialCells = new LinkedList<>();


        initialCells.add(new Cell(4, 3, true));
        initialCells.add(new Cell(5, 3, true));
        initialCells.add(new Cell(6, 4, true));
        initialCells.add(new Cell(6, 3, true));

        board.initialize(initialCells, width, height);
        Game game = new Game(board);
        game.Play();
    }
}
