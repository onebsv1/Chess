package com.chessbsv;

/**
 * Created by Bhargav Srinivasan on 3/5/16.
 */
public class Pawn extends Piece {

    //Will stop resurrecting piece after the first time.
    boolean leaveMeAlone = false;

    Pawn(String xID, String currentPos,piece_color color,piece_type type) throws IllegalArgumentException {
        this.xID = xID;
        this.color = color;
        this.type = type;
        if(this.validPosition(currentPos)){
            this.currentPos = currentPos;
        } else {
            throw new IllegalArgumentException("Invalid Bishop position");
        }
    }

    public boolean dispMove(String newPosition, Board board){
        //System.out.println("This is Pawn:dispMove.");
        String currPos = this.currentPos;
        int cPos = this.positionResolver(currPos);
        int newPos = this.positionResolver(newPosition);

        return false;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("ID: "+xID+"\n");
        buf.append("Piece type: "+type+"\n");
        buf.append("Piece color: "+color+"\n");
        buf.append("Current Position: "+currentPos+"\n");
        return buf.toString();
    }
}
