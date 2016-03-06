package com.chessbsv;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello");

        try {
            Bishop b1 = new Bishop("BW1", "D4", Piece.piece_color.WHITE, Piece.piece_type.BISHOP);
            Pawn p1 = new Pawn("PW1", "F6", Piece.piece_color.WHITE, Piece.piece_type.PAWN);
            Pawn p2 = new Pawn("PB1", "B2", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
            Pawn p3 = new Pawn("PB1", "G1", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
            System.out.print(b1);
            Board board = new Board();
            board.populatePieces(b1);
            board.populatePieces(p1);
            board.populatePieces(p2);
            board.populatePieces(p3);
            board.drawBoard();


            boolean moveStatus = false;
            while(!moveStatus) {
                System.out.println("Enter next position: ");
                Scanner sc = new Scanner(System.in);
                String nextPostion = sc.next();
                Piece px = board.spAssoc.get(b1.currentPos);
                moveStatus = px.dispMove(nextPostion);
            }

        } catch (IllegalArgumentException e){
            System.out.println("Enter a vaild position.");
            e.printStackTrace();
            return;
        }




    }
}
