package com.chessbsv;

/**
 * Created by bsriniva on 2/23/16.
 */
public class Square {


    public enum squareColor {W,B}

    Piece holds = new Piece();
    squareColor color;
    String ID = null;

    Square(){
        ID = "A1";
    }

    Square(String ID){
        this.ID = ID;
        this.color = null;
    }

    Square(String ID, Piece holds){
        this.ID = ID;
        this.holds = holds;
    }

    Square(String ID,Piece p,squareColor col){
        this.ID = ID;
        this.color = col;
        this.holds = p;
    }


    public void setColor(squareColor color) {
        this.color = color;
    }

    public void setHolds(Piece holds) {
        this.holds = holds;
    }


}
