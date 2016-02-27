package com.chessbsv;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Created by Bhargav Srinivasan on 2/22/16.
 */
public class Row extends Square {

    public ArrayList<Square> squares = new ArrayList<>();
    public static final Integer ROW_START = 1;
    public static final Integer ROW_END   = 9;


    Row(){
    }

    public squareColor toggleColor(boolean toggle){
        squareColor col;
        if(toggle){
            col = squareColor.B;
        } else {
            col = squareColor.W;
        }
        return col;
    }

    public void spawnSquares(boolean toggle, Board.rowLabels rowID){
        squareColor col = toggleColor(toggle);
        for (Integer i = ROW_START; i < ROW_END; i++) {
            String xID = rowID + i.toString();
            Square a = new Square(xID);
            a.setColor(col);
            squares.add(a);
            toggle = !toggle;
            col = toggleColor(toggle);
        }
    }

    public String Draw() {
        StringBuffer buf = new StringBuffer();
        buf.append("  ---   ---   ---   ---   ---   ---   ---   ---  \n");
        buf.append(" |"+squares.get(0).color+squares.get(0).ID+"| |"+squares.get(1).color+squares.get(1).ID+"| |"+squares.get(2).color+squares.get(2).ID+"| |"+squares.get(3).color+squares.get(3).ID+"| |"+squares.get(4).color+squares.get(4).ID+"| |"+squares.get(5).color+squares.get(5).ID+"| |"+squares.get(6).color+squares.get(6).ID+"| |"+squares.get(7).color+squares.get(7).ID+"| \n");
        buf.append("  ---   ---   ---   ---   ---   ---   ---   ---  \n");
        return buf.toString();
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

}
