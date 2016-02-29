package com.chessbsv;

import java.util.ArrayList;
/**
 * Created by bsriniva on 2/21/16.
 */
public class Bishop extends Piece {

    Square currentPosition = new Square();

    /*
    Diagonals considering the piece to be the center and extending from the
    piece all the way to the ends of the board irrespective of other pieces on the board.
    */

    ArrayList<Square> allowedMovesDiagonal1 = new ArrayList<>();
    ArrayList<Square> allowedMovesDiagonal2 = new ArrayList<>();
    ArrayList<Square> allowedMovesDiagonal3 = new ArrayList<>();
    ArrayList<Square> allowedMovesDiagonal4 = new ArrayList<>();

    /*
    Diagonals considering blocking pieces, filled in by Bishop_rules,
    Considering the current state of the board.
    */

    ArrayList<Square> possibleMovesDiagonal1 = new ArrayList<>();
    ArrayList<Square> possibleMovesDiagonal2 = new ArrayList<>();
    ArrayList<Square> possibleMovesDiagonal3 = new ArrayList<>();
    ArrayList<Square> possibleMovesDiagonal4 = new ArrayList<>();

    //Update the position of the board, if this is a valid move.
    public void Move(Bishop b,Square position, Board currentBoard){

    }

    Bishop(int id,piece_type type,piece_color color){
        super(id,type,color);
    }

    @Override
    public String toString() {
        System.out.println("Current Position: "+currentPosition.ID);
        return super.toString();
    }


}
