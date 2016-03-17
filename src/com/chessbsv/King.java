package com.chessbsv;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by OM on 3/14/2016.
 */



public class King extends Piece{
    ArrayList<Integer> possibleMoves = new ArrayList<>();

    /*
    Diagonals considering blocking pieces, filled in by King_rules,
    Considering the current state of the board.
    */

    ArrayList<Integer> allowedMoves= new ArrayList<>();

    King(String xID, String currentPos, Piece.piece_color color, Piece.piece_type type) throws IllegalArgumentException {
        this.xID = xID;
        this.color = color;
        this.type = type;
        if(this.validPosition(currentPos)){
            this.currentPos = currentPos;
        } else {
            throw new IllegalArgumentException("Invalid King position");
        }
    }

    public boolean dispMove(String newPosition, Board currentBoard) {

        //System.out.println("This is Knight:dispMove.");
        String curPos = this.currentPos;
        Integer cPos = this.positionResolver(curPos);
        Integer newPos = this.positionResolver(newPosition);
        boolean moveStatus = possibleMoves(cPos,newPos);

        three_state blk = three_state.NEITHER;

        if(!moveStatus){
            System.out.println("Not a vaild position, try again.");
            return moveStatus;
        } else {
            System.out.println("This is a vaild move: "+this.type+" to "+newPosition);
        }

        boolean allowedMoveStatus = allowedMoves(newPos,currentBoard,blk);

        if(!allowedMoveStatus){
            System.out.println("Not an allowed position, try again.");
            return allowedMoveStatus;
        } else {
            System.out.println("This is an allowed move: "+this.type+" to "+newPosition);
        }

        boolean checkEightStatus = checkEight(newPos,currentBoard);

        if(allowedMoveStatus){
            currentBoard.removePieces(this);
            this.currentPos = newPosition;
            if(!currentBoard.xIDPositionAssoc.containsValue(newPosition)) {
                currentBoard.populatePieces(this);
            } else {
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

        Integer tempPos = currentPos;

        //System.out.println("\nTop Left: ");

        currentPos = currentPos -1;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if (newPosition == currentPos) {
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nTop Right: ");

        currentPos = currentPos - 7;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Right: ");

        currentPos = currentPos -8;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }

        currentPos = tempPos;

        //System.out.println("\nBottom Left: ");


        currentPos = currentPos -9;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +1;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +7;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +8;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
            if(newPosition==currentPos){
                validMoves = true;
            }
        }
        currentPos = tempPos;

        currentPos = currentPos +9;
        if (validPosition(currentPos)) {
            possibleMoves.add(currentPos);
            System.out.println("Current pos: "+currentPos);
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

        //System.out.println("\nTop Left: ");
        for (int j = 0; j < possibleMoves.size(); j++) {
            Integer currentPosition = possibleMoves.get(j);
            String exID = this.xIDResolver(currentPosition);
            //System.out.println(exID);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                allowedMoves.add(currentPosition);
                System.out.println("Nothing: "+this.xIDResolver(currentPosition));
            } else if(tempPiece.color != this.color){
                allowedMoves.add(currentPosition);
                System.out.println("Opp: "+this.xIDResolver(currentPosition));
            } else if (tempPiece.color == this.color){
                System.out.println("Same: "+this.xIDResolver(currentPosition));
            }

        }

        boolean allowedMoveStatus = false;

        for (int j = 0; j < allowedMoves.size(); j++) {
            if(newPosition == allowedMoves.get(j)){
                allowedMoveStatus = true;
            }
        }


        possibleMoves.clear();

        if(blk == three_state.BLACK){
            updateBlkKingHash(allowedMoves,currentBoard);
        } else if(blk == three_state.WHITE){
            updateWhtKingHash(allowedMoves,currentBoard);
        } else {
        }


        allowedMoves.clear();

        return allowedMoveStatus;

    }

    public boolean checkEight(Integer newPos,Board currentBoard){

        if(this.color.equals(piece_color.WHITE)) {

            loadWhtKingHash(currentBoard.whtKingsEight, currentBoard.whtKingsPos);

            for (Piece p : currentBoard.positionPieceAssoc.values()) {
                for (String pos : currentBoard.whtKingsEight.keySet()) {
                    if (p.color != this.color) p.blkSonar(p.currentPos, pos, currentBoard);
                }
            }

            if (currentBoard.whtKingsEight.get(newPos).booleanValue()) {
                return true;
            } else {
                return false;
            }

        } else if (this.color.equals(piece_color.BLACK)){
            loadBlkKingHash(currentBoard.blkKingsEight, currentBoard.blkKingsPos);

            for (Piece p : currentBoard.positionPieceAssoc.values()) {
                for (String pos : currentBoard.blkKingsEight.keySet()) {
                    if (p.color != this.color) p.whtSonar(p.currentPos, pos, currentBoard);
                }
            }

            if (currentBoard.blkKingsEight.get(newPos).booleanValue()) {
                return true;
            } else {
                return false;
            }
            
        }


        return false;
    }

    public boolean checkForCheck(Board currentBoard){
        return false;
    }

    public boolean checkForMate(Board currentBoard){
        return false;
    }
    
    public void checkCondition(){
        //set some flag in Board to move only this piece
    }

    private void loadWhtKingHash(HashMap<String, Boolean> whtKingsEight, HashMap<String, Boolean> whtKingsPos) {

        whtKingsEight.put(xIDResolver(positionResolver(currentPos)-1),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)-7),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)-9),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)-8),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)+1),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)+7),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)+9),true);
        whtKingsEight.put(xIDResolver(positionResolver(currentPos)+8),true);

        whtKingsPos.put(currentPos,true);
    }

    private void loadBlkKingHash(HashMap<String, Boolean> blkKingsEight, HashMap<String, Boolean> blkKingsPos) {

        blkKingsEight.put(xIDResolver(positionResolver(currentPos)-1),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)-7),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)-9),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)-8),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)+1),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)+7),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)+9),true);
        blkKingsEight.put(xIDResolver(positionResolver(currentPos)+8),true);

        blkKingsPos.put(currentPos,true);
    }

    @Override
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

    @Override
    public void whtSonar(String currentPos, String newPos, Board currentBoard) {

        return;

    }

    @Override
    public void updateWhtKingHash(ArrayList<Integer> allowedMoves, Board currentBoard ) {

        return;

    }

    @Override
    public void blkSonar(String currentPos, String newPos, Board currentBoard) {
        
    }

    @Override
    public void updateBlkKingHash(ArrayList<Integer> allowedMoves,Board currentBoard) {

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