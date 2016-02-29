package com.chessbsv;

/**
 * Created by bsriniva on 2/21/16.
 */

public class Piece {

    public enum piece_type {BISHOP,ROOK,KING,KNIGHT,QUEEN,PAWN}
    public enum piece_color{BLACK,WHITE}

    String xID;
    piece_type type;
    piece_color color;


    Piece(){
        this.xID = null;
        this.type = null;
        this.color = null;
    }

    Piece(String id,piece_type type,piece_color color){
        this.xID   = id;
        this.type  = type;
        this.color = color;
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
