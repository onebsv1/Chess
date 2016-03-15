package com.chessbsv;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Board board = new Board();
            populatePieces(board);
            board.drawBoard();

            for (Integer i = 0; i < 5; i++) {
                boolean moveStatus = false;
                board.resurrectPiece();
                while(!moveStatus) {
                    System.out.println("Enter Piece: ");
                    String nextPiece = sc.next();
                    System.out.println("Enter next position: ");
                    String nextPostion = sc.next();
                    Piece px = board.positionPieceAssoc.get(board.xIDPositionAssoc.get(nextPiece));
                    moveStatus = px.dispMove(nextPostion, board);
                    board.drawBoard();
                }

            }

        } catch (IllegalArgumentException e){
            System.out.println("Enter a vaild position.");
            e.printStackTrace();
        }
    }



    public static void populatePieces(Board board){
        //A function that calls the required constructors and assembles
        //the pieces onto the board.

        Bishop b1 = new Bishop("BW1", "D4", Piece.piece_color.WHITE, Piece.piece_type.BISHOP);
        Rook r1 = new Rook("RW1", "H1", Piece.piece_color.WHITE, Piece.piece_type.ROOK);
        Knight k1 = new Knight("KB1", "H5", Piece.piece_color.BLACK, Piece.piece_type.KNIGHT);
        Pawn p1 = new Pawn("PW1", "F6", Piece.piece_color.WHITE, Piece.piece_type.PAWN);
        Pawn p2 = new Pawn("PB1", "A2", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
        Pawn p3 = new Pawn("PB2", "G1", Piece.piece_color.BLACK, Piece.piece_type.PAWN);

        board.populatePieces(b1);
        board.populatePieces(r1);
        board.populatePieces(k1);
        board.populatePieces(p1);
        board.populatePieces(p2);
        board.populatePieces(p3);

    }




}
