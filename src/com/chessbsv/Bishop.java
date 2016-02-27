package com.chessbsv;

import java.util.List;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Bishop extends Piece {

    //Square currentPosition = new Square();
    List<Square> allowedMoves;

    Bishop(int id,piece_type type,piece_color color){
        super(id,type,color);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
