package com.chessbsv;

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

    Piece holds = new Piece();

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
