package com.chessbsv;

import java.util.ArrayList;

/**
 * Created by Bhargav Srinivasan on 2/28/16.
 */
public class Pawn extends Piece {

    Square currentPosition = new Square();

    boolean isFirstMove;

    ArrayList<Square> allowedMoves = new ArrayList<>();
    ArrayList<Square> possibleMoves = new ArrayList<>();
    ArrayList<Square> validMoves = new ArrayList<>();

    Pawn(String xID, piece_type type, piece_color color) {
        super(xID, type, color);
    }


   // public void dispMove(){
       // System.out.println("I am in Pawn class");
    //}
}
