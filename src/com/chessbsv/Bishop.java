package com.chessbsv;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bsriniva on 2/21/16.
 */
public class Bishop extends Piece {

    /*
    Diagonals considering the piece to be the center and extending from the
    piece all the way to the ends of the board irrespective of other pieces on the board.
    */

    ArrayList<Integer> possibleMovesDiagonal1 = new ArrayList<>();
    ArrayList<Integer> possibleMovesDiagonal2 = new ArrayList<>();
    ArrayList<Integer> possibleMovesDiagonal3 = new ArrayList<>();
    ArrayList<Integer> possibleMovesDiagonal4 = new ArrayList<>();

    /*
    Diagonals considering blocking pieces, filled in by Bishop_rules,
    Considering the current state of the board.
    */

    ArrayList<Integer> allowedMovesDiagonal1 = new ArrayList<>();
    ArrayList<Integer> allowedMovesDiagonal2 = new ArrayList<>();
    ArrayList<Integer> allowedMovesDiagonal3 = new ArrayList<>();
    ArrayList<Integer> allowedMovesDiagonal4 = new ArrayList<>();

    /*
    Boundary Hashes to determine out of bounds check, while
    populating the diagonals.
    */

    HashSet<Integer> topLeft = new HashSet<>();
    HashSet<Integer> topRight = new HashSet<>();
    HashSet<Integer> bottomLeft = new HashSet<>();
    HashSet<Integer> bottomRight = new HashSet<>();


    Bishop(String xID, String currentPos,piece_color color,piece_type type) throws IllegalArgumentException {
        this.xID = xID;
        this.color = color;
        this.type = type;
        if(this.validPosition(currentPos)){
            this.currentPos = currentPos;
        } else {
            throw new IllegalArgumentException("Invalid Bishop position");
        }
    }

    public void dispMove(String newPosition) {

        //System.out.println("This is Bishop:dispMove.");
        String curPos = this.currentPos;
        int cPos = this.positionResolver(curPos);
        int newPos = this.positionResolver(newPosition);
        boolean moveStatus = possibleMoves(cPos,newPos);
        if(!moveStatus){
            System.out.println("Not a vaild position, try again.");
        } else {
            System.out.println("This is a vaild move: "+this.type+" to "+newPosition);
        }

    }


    public boolean possibleMoves(Integer currentPos, Integer newPosition){
        //public int populate_diagonal();
        // check diagonal entries, store aLL hashed square ids along 4 1D arrays.
        boolean validMoves = false;
        System.out.println("Current Pos, New Pos: "+this.xIDResolver(currentPos)+" , "+this.xIDResolver(newPosition));

        loadtopLeft();
        loadtopRight();
        loadbottomLeft();
        loadbottomRight();

        Integer tempPos = currentPos;

        //System.out.println("\nTop Left: ");

        while (!topLeft.contains(currentPos)){
            currentPos = currentPos -9;
            possibleMovesDiagonal1.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nTop Right: ");

        while (!topRight.contains(currentPos)){
            currentPos = currentPos -7;
            possibleMovesDiagonal1.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Right: ");

        while (!bottomRight.contains(currentPos)){
            currentPos = currentPos + 9;
            possibleMovesDiagonal1.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Left: ");

        while (!bottomLeft.contains(currentPos)){
            currentPos = currentPos + 7;
            possibleMovesDiagonal1.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //while index !in (starting col && starting row)
        //pushback currpos-9
        //end
        return validMoves;
    }

    public void allowedMoves() {
        /*
        find position occupied by pieces in the diagonal array and move there
        if same color found, move to that (square-1)
        else if opp. color move to that square and kill the piece.
         */
    }


    public void loadtopLeft(){

        for (Integer i = 0; i < 8; i++) {
            topLeft.add(i);
        }

        for (Integer i=0; i<64; i+=8) {
            topLeft.add(i);
        }

    }

    public void loadtopRight(){

        for (Integer i = 0; i < 8; i++) {
            topRight.add(i);
        }

        for (Integer i=7; i<64; i+=8) {
            topRight.add(i);
        }

    }

    public void loadbottomLeft(){

        for (Integer i = 56; i < 64; i++) {
            bottomLeft.add(i);
        }

        for (Integer i=0; i<64; i+=8) {
            bottomLeft.add(i);
        }

    }

    public void loadbottomRight(){

        for (Integer i = 56; i < 64; i++) {
            bottomRight.add(i);
        }

        for (Integer i=7; i<64; i+=8) {
            bottomRight.add(i);
        }

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
