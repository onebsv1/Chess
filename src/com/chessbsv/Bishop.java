package com.chessbsv;

import java.util.ArrayList;
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

    ArrayList<Integer> allowedMoves = new ArrayList<>();

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

    public boolean dispMove(String newPosition, Board currentBoard) {

        //System.out.println("This is Bishop:dispMove.");
        String curPos = this.currentPos;
        Integer cPos = this.positionResolver(curPos);
        Integer newPos = this.positionResolver(newPosition);

        three_state blk = three_state.NEITHER;

        boolean moveStatus = possibleMoves(cPos,newPos);
        if (moveStatus) {
            System.out.println("This is a vaild move: "+this.type+" to "+newPosition);
        } else {
            System.out.println("Not a vaild position, try again.");
            return moveStatus;
        }

        boolean allowedMoveStatus = allowedMoves(newPos,currentBoard,blk);
        if (allowedMoveStatus) {
            System.out.println("This is an allowed move: "+this.type+" to "+newPosition);
        } else {
            System.out.println("Not an allowed position, try again.");
            return allowedMoveStatus;
        }

        if(allowedMoveStatus){
            currentBoard.removePieces(this);
            this.currentPos = newPosition;
            if(!currentBoard.xIDPositionAssoc.containsValue(newPosition)) {
                currentBoard.populatePieces(this);
            } else {
                System.out.println("This is a kill");
                killFunction(newPosition,currentBoard);
                currentBoard.populatePieces(this);
            }
        }


        return allowedMoveStatus;
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
            possibleMovesDiagonal2.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Right: ");

        while (!bottomRight.contains(currentPos)){
            currentPos = currentPos + 9;
            possibleMovesDiagonal3.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Left: ");

        while (!bottomLeft.contains(currentPos)){
            currentPos = currentPos + 7;
            possibleMovesDiagonal4.add(currentPos);
            //System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }


        return validMoves;
    }

    @Override
    public boolean allowedMoves(Integer newPosition, Board currentBoard, three_state blk) {
        /*
        find position occupied by pieces in the diagonal array and move there
        if same color found, move to that (square-1)
        else if opp. color move to that square and kill the piece.
         */

        System.out.println("\nTop Left: ");
        for (Integer j = 0; j < possibleMovesDiagonal1.size(); j++) {

            Integer currentPosition = possibleMovesDiagonal1.get(j);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);

            if(tempPiece == null){
                allowedMovesDiagonal1.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal1.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }
        }

        System.out.println("\nTop Right: ");


        for (Integer j = 0; j < possibleMovesDiagonal2.size(); j++) {

            Integer currentPosition = possibleMovesDiagonal2.get(j);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);

            if(tempPiece == null){
                allowedMovesDiagonal2.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal2.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }
        }

        System.out.println("\nBottom Right: ");

        for (Integer j = 0; j < possibleMovesDiagonal3.size(); j++) {

            Integer currentPosition = possibleMovesDiagonal3.get(j);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);

            if(tempPiece == null){
                allowedMovesDiagonal3.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal3.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }
        }

        System.out.println("\nBottom Left: ");

        for (Integer j = 0; j < possibleMovesDiagonal4.size(); j++) {

            Integer currentPosition = possibleMovesDiagonal4.get(j);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);

            if(tempPiece == null){
                allowedMovesDiagonal4.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMovesDiagonal4.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
                break;
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
                break;
            }
        }

        boolean allowedMoveStatus = false;

        for (int j = 0; j < allowedMovesDiagonal1.size(); j++) {
            allowedMoves.add(allowedMovesDiagonal1.get(j));
            if(newPosition == allowedMovesDiagonal1.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesDiagonal2.size(); j++) {
            allowedMoves.add(allowedMovesDiagonal2.get(j));
            if(newPosition == allowedMovesDiagonal2.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesDiagonal3.size(); j++) {
            allowedMoves.add(allowedMovesDiagonal3.get(j));
            if(newPosition == allowedMovesDiagonal3.get(j)){
                allowedMoveStatus = true;
            }
        }

        for (int j = 0; j < allowedMovesDiagonal4.size(); j++) {
            allowedMoves.add(allowedMovesDiagonal4.get(j));
            if(newPosition == allowedMovesDiagonal4.get(j)){
                allowedMoveStatus = true;
            }
        }

        possibleMovesDiagonal1.clear();
        possibleMovesDiagonal2.clear();
        possibleMovesDiagonal3.clear();
        possibleMovesDiagonal4.clear();

        if(blk == three_state.BLACK){
            updateBlkKingHash(allowedMoves,currentBoard);
        } else if(blk == three_state.WHITE){
            updateWhtKingHash(allowedMoves,currentBoard);
        } else {
        }

        allowedMovesDiagonal1.clear();
        allowedMovesDiagonal2.clear();
        allowedMovesDiagonal3.clear();
        allowedMovesDiagonal4.clear();
        allowedMoves.clear();

        return allowedMoveStatus;

    }

    public void killFunction(String newPosition, Board currentBoard) throws IllegalArgumentException {
        Piece killedPiece  = currentBoard.positionPieceAssoc.get(newPosition);
        currentBoard.removePieces(currentBoard.positionPieceAssoc.get(newPosition));
        if((killedPiece.color == piece_color.BLACK) &&(this.color == piece_color.WHITE)){
            killedPiece.currentPos = "DEAD";
            currentBoard.deadBlackPieces.add(killedPiece);
        } else if ((killedPiece.color == piece_color.WHITE) && (this.color == piece_color.BLACK)) {
            killedPiece.currentPos = "DEAD";
            currentBoard.deadWhitePieces.add(killedPiece);
        } else {
            throw new IllegalArgumentException("Unknown dead piece!");
        }

    }

    // ########################## whtSonar <-> blkKingHashes ##########################

    @Override
    public void updateBlkKingHash(ArrayList<Integer> allowedMoves,Board currentBoard){
        for (Integer x: allowedMoves) {
            String tempPos = new String();
            tempPos = xIDResolver(x);
            if(currentBoard.blkKingsEight.containsKey(tempPos)){
                System.out.println("Calling white bishop edits blkKingsEight"+tempPos);
                currentBoard.blkKingsEight.replace(tempPos,false);
            }

            if(currentBoard.blkKingsPos.containsKey(tempPos)){
                System.out.println("Calling white bishop edits blkKingsPos"+tempPos);
                currentBoard.blkKingsPos.replace(tempPos,false);
            }
        }
    }

    @Override
    public void whtSonar(String currentPos, Board currentBoard){

        three_state blk = three_state.BLACK;

        for (String pos : currentBoard.blkKingsEight.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            System.out.println("Calling white bishop's moves"+pos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

        for (String pos : currentBoard.blkKingsPos.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            System.out.println("Calling white bishop's moves"+pos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

    }


    // ########################## blkSonar <-> whtKingHashes ##########################


    @Override
    public void updateWhtKingHash(ArrayList<Integer> allowedMoves, Board currentBoard) {
        String tempPos = new String();
        for (Integer x: allowedMoves) {
            tempPos = xIDResolver(x);
            if(currentBoard.whtKingsEight.containsKey(tempPos)){
                currentBoard.whtKingsEight.replace(tempPos,false);
            }

            if(currentBoard.whtKingsPos.containsKey(tempPos)){
                currentBoard.whtKingsPos.replace(tempPos,false);
            }
        }

    }


    @Override
    public void blkSonar(String currentPos, Board currentBoard) {
        three_state blk = three_state.WHITE;

        for (String pos : currentBoard.whtKingsEight.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

        for (String pos : currentBoard.whtKingsPos.keySet()) {
            Integer sqpos = positionResolver(pos);
            Integer curPos = positionResolver(currentPos);
            possibleMoves(curPos,sqpos);
            allowedMoves(sqpos,currentBoard,blk);
        }

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
        String buf = "";
        buf = buf.concat("ID: "+xID+"\n");
        buf = buf.concat("Piece type: "+type+"\n");
        buf = buf.concat("Piece color: "+color+"\n");
        buf = buf.concat("Current Position: "+currentPos+"\n");
        return buf;
    }


}
