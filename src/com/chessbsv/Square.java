package com.chessbsv;

import java.util.ArrayList;

/**
 * Created by bsriniva on 2/23/16.
 */
public class Square {


    public enum squareColor {W,B}
    public enum rowLabels {A,B,C,D,E,F,G,H}
    public static final Integer ROW_START = 1;
    public static final Integer ROW_END   = 9;

    squareColor color;
    String ID = null;

    Piece holds = new Piece() {
        @Override
        public boolean dispMove(String d1, Board board) {
        return false;
        }

        @Override
        public boolean allowedMoves(Integer newPosition, Board currentBoard) {
            return false;
        }

        @Override
        public boolean possibleMoves(Integer currentPos, Integer newPosition) {
            return false;
        }

        @Override
        public void killFunction(String newPosition, Board currentBoard) throws IllegalArgumentException {

        }

        @Override
        public void sonar(String currentPos, String newPos, Board currentBoard) {

        }

        @Override
        public void updateKingHash(ArrayList<Integer> allowedMoves) {

        }

        @Override
        public String toString() {
            String buf = "";
            buf = buf.concat("ID: "+xID+"\n");
            buf = buf.concat("Piece type: "+type+"\n");
            buf = buf.concat("Piece color: "+color+"\n");
            buf = buf.concat("Current Position: "+currentPos+"\n");
            return buf;
        }
    };

    Square(){
        ID = "A1";
    }

    Square(String ID){
        this.ID = ID;
        this.color = null;
    }

    Square(String ID,squareColor col){
        this.ID = ID;
        this.color = col;
    }


    public void setColor(squareColor color) {
        this.color = color;
    }


}
