package com.chessbsv;

/**
 * Created by bsriniva on 2/21/16.
 */

public class Piece {

    public enum piece_type {BISHOP,ROOK,KING,KNIGHT,QUEEN,PAWN}
    public enum piece_color{BLACK,WHITE}

    int id;
    piece_type type;
    piece_color color;


    Piece(int id,piece_type type,piece_color color){
        this.id    = id;
        this.type  = type;
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("ID: "+id+"\n");
        buf.append("Type:"+type+"\n");
        buf.append("Color:"+color+"\n");
        return buf.toString();
    }
}
