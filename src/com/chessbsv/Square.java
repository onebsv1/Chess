package com.chessbsv;

/**
 * Created by bsriniva on 2/23/16.
 */
public class Square {


    public enum squareColor {W,B}

    Piece holds = new Piece(0,null,null);
    squareColor color = null;
    String ID = null;

    Square(){
        ID = "A1";
    }

    Square(String ID){
        this.ID = ID;
    }

    Square(String ID, Piece holds){
        this.ID = ID;
        this.holds = holds;
    }


    public void setColor(squareColor color) {
        this.color = color;
    }

    public void setHolds(Piece holds) {
        this.holds = holds;
    }


}
