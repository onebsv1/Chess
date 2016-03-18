package com.chessbsv;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean gameOver = false;
        boolean moveStatus;

        try {
            Board board = new Board();
            populatePieces(board);
            board.drawBoard();

            while(!gameOver) {
                board.resurrectPiece();


                moveStatus = false;
                while(!moveStatus) {
                    System.out.println("Player1 (white)'s move: ");
                    King whtKing = (King) board.positionPieceAssoc.get(board.xIDPositionAssoc.get("KiW1"));
                    whtKing.checkEight(board);

                    if(!whtKing.checkForMate(board)){
                        System.out.println("Game over: "+gameOver);
                        gameOver=true;
                        break;
                    }



                    if(!whtKing.checkForCheck(board)){
                        while (!moveStatus) {
                            System.out.println("White king is Under Check: ");
                            System.out.println("Enter next position: ");
                            String nextPostion = sc.next();
                            moveStatus = whtKing.dispMove(nextPostion, board);
                            board.drawBoard();
                        }

                    }

                    System.out.println("Enter Piece: ");
                    String nextPiece = sc.next();
                    System.out.println("Enter next position: ");
                    String nextPostion = sc.next();
                    Piece px = board.positionPieceAssoc.get(board.xIDPositionAssoc.get(nextPiece));
                    moveStatus = px.dispMove(nextPostion, board);
                    board.drawBoard();
                }


                moveStatus = false;
                while(!moveStatus) {
                    System.out.println("Player2 (blk)'s move: ");
                    King blkKing = (King) board.positionPieceAssoc.get(board.xIDPositionAssoc.get("KiB1"));
                    blkKing.checkEight(board);

                    if(!blkKing.checkForMate(board)){
                        System.out.println("Game over: "+gameOver);
                        gameOver=true;
                        break;
                    }

                    if(!blkKing.checkForCheck(board)) {
                        while(!moveStatus) {
                            System.out.println("Black king is Under Check: ");
                            System.out.println("Enter next position: ");
                            String nextPostion = sc.next();
                            moveStatus = blkKing.dispMove(nextPostion, board);
                            board.drawBoard();
                        }
                    }



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
        Rook r1 = new Rook("RB1", "H4", Piece.piece_color.BLACK, Piece.piece_type.ROOK);
        Knight k1 = new Knight("KB1", "D1", Piece.piece_color.BLACK, Piece.piece_type.KNIGHT);
        Pawn p1 = new Pawn("PW1", "F6", Piece.piece_color.WHITE, Piece.piece_type.PAWN);
        Pawn p2 = new Pawn("PB1", "A2", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
        Pawn p3 = new Pawn("PB2", "G1", Piece.piece_color.BLACK, Piece.piece_type.PAWN);
        King whtKing = new King("KiW1","A5", Piece.piece_color.WHITE, Piece.piece_type.KING);
        King blkKing = new King("KiB1","F5", Piece.piece_color.BLACK, Piece.piece_type.KING);

        board.populatePieces(b1);
        board.populatePieces(r1);
        board.populatePieces(k1);
        board.populatePieces(p1);
        board.populatePieces(p2);
        board.populatePieces(p3);
        board.populatePieces(whtKing);
        board.populatePieces(blkKing);

    }




}
