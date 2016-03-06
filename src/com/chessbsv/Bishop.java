package com.chessbsv;

import java.util.ArrayList;
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
        super(xID,type,color);
    }

    public void Move(String newPosition){
        String currentPos = this.xID;
        int curPos = this.positionResolver(currentPos);
        int newPos = this.positionResolver(newPosition);

        BishopRules br = new BishopRules();
        br.possibleMoves(curPos,newPos,possibleMovesDiagonal1,possibleMovesDiagonal2,possibleMovesDiagonal3,possibleMovesDiagonal4);


    }


    @Override
    public String toString() {
        return super.toString();
    }


}
