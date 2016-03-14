package com.chessbsv;


import java.util.Scanner;

public class Main {

    public void popPieces(Board board){



    }

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello");

        try {
            Bishop b1 = new Bishop("BW1", "D4", Piece.piece_color.WHITE, Piece.piece_type.BISHOP);
            Rook r1 = new Rook("RW1", "H1", Piece.piece_color.WHITE, Piece.piece_type.ROOK);
            Pawn p1 = new Pawn("PW1", "F6", Piece.piece_color.WHITE, Piece.piece_type.PAWN);
            Pawn p2 = new Pawn("PB1", "A2", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
            Pawn p3 = new Pawn("PB2", "G1", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
            System.out.print(b1);
            System.out.print(r1);
            Board board = new Board();

            board.populatePieces(b1);
            board.populatePieces(r1);
            board.populatePieces(p1);
            board.populatePieces(p2);
            board.populatePieces(p3);

            board.drawBoard();

            for (int i = 0; i < 5; i++) {

                boolean moveStatus = false;
                board.resurrectPiece();


                while(!moveStatus) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Piece: ");
                    String nextPiece = sc.next();
                    System.out.println("Enter next position: ");
                    String nextPostion = sc.next();
                    Piece px = board.positionPieceAssoc.get(board.xIDPositionAssoc.get(nextPiece));
                    moveStatus = px.dispMove(nextPostion, board);
                }


                board.drawBoard();

            }


        } catch (IllegalArgumentException e){
            System.out.println("Enter a vaild position.");
            e.printStackTrace();
            return;
        }




    }
}
