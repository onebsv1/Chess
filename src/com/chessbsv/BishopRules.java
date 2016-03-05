package com.chessbsv;

import java.util.ArrayList;

/**
 * Created by OM on 2/28/2016.
 */

public class BishopRules extends Rules {


    public void possibleMoves(int currentPos, int newPosition, ArrayList<Square> p1, ArrayList<Square> p2, ArrayList<Square> p3, ArrayList<Square> p4){
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

    //Update the position of the board, if this is a valid move.
    public void Move(Bishop b,Square position, Board currentBoard){

    }

}
