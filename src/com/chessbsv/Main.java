package com.chessbsv;


public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello");

        Bishop b1 = new Bishop("C3",Piece.piece_type.BISHOP, Piece.piece_color.WHITE);
        Pawn pawn1 = new Pawn("F4",Piece.piece_type.PAWN, Piece.piece_color.WHITE);
        System.out.print(b1);
        Board board = new Board();
        board.populatePieces(b1.xID,b1);
        board.populatePieces(pawn1.xID,pawn1);
        board.drawBoard();
        Piece px = board.spAssoc.get(b1.xID);
        px.dispMove("D1");
        Piece px2 = board.spAssoc.get(pawn1.xID);
        px2.dispMove("D1");




    }
}
