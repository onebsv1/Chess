package com.chessbsv;


public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello");

        try {
            Bishop b1 = new Bishop("BW1", "D4", Piece.piece_color.WHITE, Piece.piece_type.BISHOP);
            System.out.print(b1);
            Board board = new Board();
            board.populatePieces(b1.currentPos,b1);
            board.drawBoard();
            Piece px = board.spAssoc.get(b1.currentPos);
            px.dispMove("A1");
        } catch (IllegalArgumentException e){
            System.out.println("Enter a vaild position.");
            e.printStackTrace();
            return;
        }




    }
}
