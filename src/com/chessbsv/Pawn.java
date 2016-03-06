package com.chessbsv;

/**
 * Created by Bhargav Srinivasan on 3/5/16.
 */
public class Pawn extends Piece {

    Pawn(String xID, String currentPos, piece_type type, piece_color color){
        this.xID = xID;
        this.type = type;
        this.color = color;
        this.currentPos = currentPos;
    }

    public void dispMove(String newPosition){

        System.out.println("This is Pawn:dispMove.");
        String currPos = this.currentPos;
        int cPos = this.positionResolver(currPos);
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
