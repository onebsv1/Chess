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


    Bishop(String xID, String currentPos){
        this.xID = xID;
        this.currentPos = currentPos;
    }

    public void dispMove(String newPosition){

        System.out.println("This is Bishop:dispMove.");
        String curPos = this.currentPos;
        int cPos = this.positionResolver(curPos);
        int newPos = this.positionResolver(newPosition);
    }


    public void possibleMoves(int currentPos, int newPosition){
        //public int populate_diagonal();
        // check diagonal entries, store aLL hashed square ids along 4 1D arrays.
        System.out.println("Current Pos: "+currentPos+" "+newPosition);

        //while index !in (starting col && starting row)
        //pushback currpos-9
        //end

    }

    public void allowedMoves() {
        /*
        find position occupied by pieces in the diagonal array and move there
        if same color found, move to that (square-1)
        else if opp. color move to that square and kill the piece.
         */
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("ID: "+xID+"\n");
        buf.append("Current Position: "+xID+"\n");
        return buf.toString();
    }


}
