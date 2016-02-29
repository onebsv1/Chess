package com.chessbsv;


public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello");

        Bishop b1 = new Bishop("C3",Piece.piece_type.BISHOP, Piece.piece_color.WHITE);
        System.out.print(b1);
        Board board = new Board();
        board.populatePieces(b1.xID,b1);
        board.drawBoard();
    }
}
