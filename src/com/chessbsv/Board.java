package com.chessbsv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Board extends Square{


    //These maps contain all the active pieces on the currentBoard.
    //They are used by both the drawing functions, and the actual pieces,
    //in order to play the game.
    public static HashMap<String,Piece> positionPieceAssoc = new HashMap<>();
    public static HashMap<String,String> xIDPositionAssoc = new HashMap<>();


    public static HashMap<String,Boolean> kingsPos = new HashMap<>();
    public static HashMap<String,Boolean> kingsEight = new HashMap<>();

    //These arrays contain the dead pieces, in order to be able to ressurect them
    //after PAWN advancment.

    public static ArrayList<Piece> deadBlackPieces = new ArrayList<>();
    public static ArrayList<Piece> deadWhitePieces = new ArrayList<>();

    public void populatePieces(Piece p){
        //This function adds a piece to the Assoc arrays.
        positionPieceAssoc.put(p.currentPos,p);
        xIDPositionAssoc.put(p.xID,p.currentPos);
    }

    public void removePieces(Piece p){
        //This function removes the pieces from the Assoc arrays.
        positionPieceAssoc.remove(p.currentPos,p);
        xIDPositionAssoc.remove(p.xID,p.currentPos);
    }


    public void resurrectPiece (){

        //Check the top and bottom row for any piece
        //of type PAWN and offer to substitute it.
        //If the LMA flag is set on the pawn, then don't offer substitution.

        String currentPos = new String();

        for (Integer i = ROW_START; i < ROW_END; i++) {

            currentPos = rowLabels.values()[0] + i.toString();

            if(positionPieceAssoc.get(currentPos) != null) {
                if (positionPieceAssoc.get(currentPos).type == Piece.piece_type.PAWN) {
                    Pawn sub = (Pawn) positionPieceAssoc.get(currentPos);
                    System.out.println("Pawn found!"+currentPos);
                    if(!sub.leaveMeAlone) {
                        this.offerSubstitution(sub);
                        return;
                    } else {
                        return;
                    }
                }
            }

            currentPos = rowLabels.values()[7] + i.toString();
            if(positionPieceAssoc.get(currentPos) != null) {
                if (positionPieceAssoc.get(currentPos).type == Piece.piece_type.PAWN) {
                    Pawn sub = (Pawn) positionPieceAssoc.get(currentPos);
                    System.out.println("Pawn found!"+currentPos);
                    if(!sub.leaveMeAlone) {
                        this.offerSubstitution(sub);
                        return;
                    } else {
                        return;
                    }
                }
            }

        }
    }

    public void offerSubstitution(Pawn sub) {

        //Offer pieces of the same color of the pawn as substitutes.
        //Wait for input and do a remove piece on the pawn and populate piece on the selected
        //dead piece, after updating its currentPos.

        if(sub.color == Piece.piece_color.BLACK){

            if(deadBlackPieces.size()>0) {
                System.out.println("Spawn piece options: ");
                for (int i = 0; i < deadBlackPieces.size(); i++) {
                    System.out.println(deadBlackPieces.get(i));
                }
                System.out.println("Enter Choice, for leave me alone enter LMA: ");
                Scanner sc = new Scanner(System.in);
                String nextPiece = sc.next();
                if(nextPiece.equals("LMA")){
                    sub.leaveMeAlone=true;
                    this.drawBoard();
                    return;
                }
                for (int i = 0; i < deadBlackPieces.size(); i++) {
                    if (deadBlackPieces.get(i).xID.equals(nextPiece)) {
                        //System.out.println(deadBlackPieces.get(i));
                        Piece x = deadBlackPieces.get(i);
                        x.currentPos = sub.currentPos;
                        this.removePieces(sub);
                        this.populatePieces(x);
                        this.drawBoard();
                        return;
                    }

                }
            }

        } else if (sub.color == Piece.piece_color.WHITE){

            if(deadWhitePieces.size()>0) {
                System.out.println("Spawn piece options: ");
                for (int i = 0; i < deadWhitePieces.size(); i++) {
                    System.out.println(deadWhitePieces.get(i));
                }
                System.out.println("Enter Choice, for leave me alone enter LMA: ");
                Scanner sc = new Scanner(System.in);
                String nextPiece = sc.next();
                if(nextPiece.equals("LMA")){
                    sub.leaveMeAlone=true;
                    this.drawBoard();
                    return;
                }
                for (int i = 0; i < deadWhitePieces.size(); i++) {
                    if (deadWhitePieces.get(i).xID.equals(nextPiece)) {
                        Piece x = deadWhitePieces.get(i);
                        x.currentPos = sub.currentPos;
                        this.removePieces(sub);
                        this.populatePieces(x);
                        this.drawBoard();
                        return;
                    }

                }
            }

        }


    }


    //A list of all the squares in one row, used by the drawing function
    //to draw the board, row by row.
    ArrayList<Square> squares = new ArrayList<>();

    //A toggle flag to toggle colors of squares,
    //while drawing the board.
    boolean toggle = false;

    public squareColor toggleColor(boolean toggle){
        //Small helper function to toggle the color of squares.
        squareColor col;
        if(toggle){
            col = squareColor.B;
        } else {
            col = squareColor.W;
        }
        return col;
    }

    public void spawnSquares(boolean toggle, int row){

        //Spawns an entire row of squares and populates the 'squares' array
        //The squares array is used to draw the board.
        //This function also uses the positionPiece Assoc map to populate the
        //holds field of the square in order for it to be able to access the pieces
        //that it holds.

        squareColor col = toggleColor(toggle);

        for (Integer i = ROW_START; i < ROW_END; i++) {
            String currentPos = rowLabels.values()[row] + i.toString();

            Square a = new Square(currentPos,col);
            if(positionPieceAssoc.containsKey(currentPos)){
                a.holds = positionPieceAssoc.get(currentPos);
            }

            squares.add(a);
            toggle = !toggle;
            col = toggleColor(toggle);
        }
    }

    public String Draw() {

        //This function draws the chess board.
        //The first variable that it prints is the color of the square,
        //next is the xID of the piece that sits on it.

        StringBuffer buf = new StringBuffer();
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        buf.append(squares.get(0).ID+" |"+squares.get(0).color+squares.get(0).holds.xID+"| |"+squares.get(1).color+squares.get(1).holds.xID+"| |"+squares.get(2).color+squares.get(2).holds.xID+"| |"+squares.get(3).color+squares.get(3).holds.xID+"| |"+squares.get(4).color+squares.get(4).holds.xID+"| |"+squares.get(5).color+squares.get(5).holds.xID+"| |"+squares.get(6).color+squares.get(6).holds.xID+"| |"+squares.get(7).color+squares.get(7).holds.xID+"| \n");
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        return buf.toString();
    }

    public void drawBoard(){

        //This is the main drawing function which draws all the squares.
        //It first calls spawnSquares() , in order to spawn an entire row.
        //Then it calls Draw() to display the entire row, it does this 8 times.
        //toggle, when unset will result in a row starting with a white square.

        for (int i = 0; i < 8; i++) {
            spawnSquares(toggle,i);
            System.out.print(Draw());
            squares.clear();
            toggle = !toggle;
        }
    }

}
