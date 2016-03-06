package com.chessbsv;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Bishop extends Piece {

    /*
    Diagonals considering the piece to be the center and extending from the
    piece all the way to the ends of the board irrespective of other pieces on the board.
    */

    ArrayList<Square> possibleMovesDiagonal1 = new ArrayList<>();
    ArrayList<Square> possibleMovesDiagonal2 = new ArrayList<>();
    ArrayList<Square> possibleMovesDiagonal3 = new ArrayList<>();
    ArrayList<Square> possibleMovesDiagonal4 = new ArrayList<>();

    /*
    Diagonals considering blocking pieces, filled in by Bishop_rules,
    Considering the current state of the board.
    */

    ArrayList<Square> allowedMovesDiagonal1 = new ArrayList<>();
    ArrayList<Square> allowedMovesDiagonal2 = new ArrayList<>();
    ArrayList<Square> allowedMovesDiagonal3 = new ArrayList<>();
    ArrayList<Square> allowedMovesDiagonal4 = new ArrayList<>();


    Bishop(String xID, piece_type type, piece_color color){
        this.xID = xID;
        this.type = type;
        this.color = color;
    }

    public void dispMove(String newPosition){

        System.out.println("This is Bishop:dispMove.");
        String currentPos = this.xID;
        int curPos = this.positionResolver(currentPos);
        int newPos = this.positionResolver(newPosition);
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("ID: "+xID+"\n");
        buf.append("Type:"+type+"\n");
        buf.append("Color:"+color+"\n");
        buf.append("Current Position: "+xID+"\n");
        return buf.toString();
    }


}
