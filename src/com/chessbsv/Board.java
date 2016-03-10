package com.chessbsv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Board extends Square{

    ArrayList<Piece> allPieces = new ArrayList<>();
    ArrayList<Square> squares = new ArrayList<>();
    public static HashMap<String,Piece> positionPieceAssoc = new HashMap<>();
    public static HashMap<String,String> xIDPositionAssoc = new HashMap<>();

    public static ArrayList<Piece> deadBlackPieces = new ArrayList<>();
    public static ArrayList<Piece> deadWhitePieces = new ArrayList<>();

    boolean toggle = false;

    public void resurrectPiece (){

        //Will have to check the top and bottom row for any piece
        //of type PAWN and offer to substitute it.

        String currentPos = new String();

        for (Integer i = ROW_START; i < ROW_END; i++) {

            currentPos = rowLabels.values()[0] + i.toString();

            if(positionPieceAssoc.get(currentPos) != null) {
                if (positionPieceAssoc.get(currentPos).type == Piece.piece_type.PAWN) {
                    Pawn sub = (Pawn) positionPieceAssoc.get(currentPos);
                    System.out.print("Pawn found!"+currentPos);
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
                    System.out.print("Pawn found!"+currentPos);
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
                    System.out.print(deadBlackPieces.get(i));
                    System.out.print("   ");
                }
                System.out.println("Enter Choice, for leave me alone enter LMA: ");
                Scanner sc = new Scanner(System.in);
                String nextPiece = sc.next();
                if(nextPiece.equals("LMA")){
                    sub.leaveMeAlone=true;
                    return;
                }
                for (int i = 0; i < deadBlackPieces.size(); i++) {
                    if (deadBlackPieces.get(i).xID.equals(nextPiece)) {
                        //System.out.println(deadBlackPieces.get(i));
                        Piece x = deadBlackPieces.get(i);
                        x.currentPos = sub.currentPos;
                        this.removePieces(sub);
                        this.populatePieces(x);
                    }

                }
            }

        } else if (sub.color == Piece.piece_color.WHITE){

            if(deadWhitePieces.size()>0) {
                System.out.println("Spawn piece options: ");
                for (int i = 0; i < deadWhitePieces.size(); i++) {
                    System.out.print(deadWhitePieces.get(i));
                    System.out.print("   ");
                }
                System.out.println("Enter Choice, for leave me alone enter LMA: ");
                Scanner sc = new Scanner(System.in);
                String nextPiece = sc.next();
                if(nextPiece.equals("LMA")){
                    sub.leaveMeAlone=true;
                    return;
                }
                for (int i = 0; i < deadWhitePieces.size(); i++) {
                    if (deadWhitePieces.get(i).xID.equals(nextPiece)) {
                        Piece x = deadWhitePieces.get(i);
                        x.currentPos = sub.currentPos;
                        this.removePieces(sub);
                        this.populatePieces(x);
                    }

                }
            }

        }


    }

    public squareColor toggleColor(boolean toggle){
        squareColor col;
        if(toggle){
            col = squareColor.B;
        } else {
            col = squareColor.W;
        }
        return col;
    }

    public void spawnSquares(boolean toggle, int row){
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
        StringBuffer buf = new StringBuffer();
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        buf.append(squares.get(0).ID+" |"+squares.get(0).color+squares.get(0).holds.xID+"| |"+squares.get(1).color+squares.get(1).holds.xID+"| |"+squares.get(2).color+squares.get(2).holds.xID+"| |"+squares.get(3).color+squares.get(3).holds.xID+"| |"+squares.get(4).color+squares.get(4).holds.xID+"| |"+squares.get(5).color+squares.get(5).holds.xID+"| |"+squares.get(6).color+squares.get(6).holds.xID+"| |"+squares.get(7).color+squares.get(7).holds.xID+"| \n");
        //buf.append("  ----     ----     ----     ----     ----     ----     ----     ----  \n");
        return buf.toString();
    }

    public void populatePieces(Piece p){
        positionPieceAssoc.put(p.currentPos,p);
        xIDPositionAssoc.put(p.xID,p.currentPos);
    }

    public void removePieces(Piece p){
        positionPieceAssoc.remove(p.currentPos,p);
        xIDPositionAssoc.remove(p.xID,p.currentPos);
    }

    public void drawBoard(){

        for (int i = 0; i < 8; i++) {
            spawnSquares(toggle,i);
            System.out.print(Draw());
            squares.clear();
            toggle = !toggle;
        }
    }

}
