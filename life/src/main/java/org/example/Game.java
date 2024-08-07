package org.example;

public class Game {
    public Board board;
    public Game(Board board){
        this.board=board;
    }
    public void Play(){
        board.print();
        while (true) {
            try {
                Thread.sleep(5000);
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
        Board board = new Board(width, height);
        Cell[] initCells = {
                new Cell(3, 6, true),
                new Cell(4, 5, true),
                new Cell(5, 4, true),
                new Cell(6, 3, true),
                new Cell(7, 2, true),
                new Cell(6, 5, true)
        };
        board.initialize(initCells);
        Game game = new Game(board);
        game.Play();
    }
}