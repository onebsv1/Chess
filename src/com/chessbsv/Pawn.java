package com.chessbsv;

import java.util.ArrayList;

/**
 * Created by Bhargav Srinivasan on 3/5/16.
 */
public class Pawn extends Piece {

    //Will stop resurrecting piece after the first time.
    boolean leaveMeAlone = false;

    //The first move
    boolean isFirstMove = true;

    /*
    These moves are possible if they are just vaild "on the board"
    positions.
     */

    ArrayList<Integer> possibleMoves = new ArrayList<>();

    /*
    These moves are allowed after taking into account the
    existing pieces on the board.
    */

    ArrayList<Integer> allowedMoves= new ArrayList<>();

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

    public boolean dispMove(String newPosition, Board currentBoard){
        //System.out.println("This is Pawn:dispMove.");
        String currPos = this.currentPos;

        Integer cPos = this.positionResolver(currPos);
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

        if(this.isFirstMove){
            this.isFirstMove = !this.isFirstMove;
        }

        return allowedMoveStatus;
    }

    @Override
    public boolean possibleMoves(Integer currentPos, Integer newPosition) {

        boolean validMoves = false;
        System.out.println("Current Pos, New Pos: "+this.xIDResolver(currentPos)+" , "+this.xIDResolver(newPosition));

        Integer tempPos = currentPos;

        if(this.color.equals(piece_color.BLACK)){
            currentPos = currentPos + 8;
            if(validPosition(currentPos)){
                possibleMoves.add(currentPos);
                if(newPosition == currentPos){
                    validMoves = true;
                }
            }

            if(validPosition(currentPos-1)){
                possibleMoves.add(currentPos-1);
                if(newPosition == currentPos-1){
                    validMoves = true;
                }
            }

            if(validPosition(currentPos+1)){
                possibleMoves.add(currentPos+1);
                if(newPosition == currentPos+1){
                    validMoves = true;
                }
            }

            currentPos = tempPos;

            if(this.isFirstMove){
                currentPos = currentPos + 16;
                if(validPosition(currentPos)){
                    if(newPosition == currentPos){
                        validMoves = true;
                    }
                }
            }

        }

        if(this.color.equals(piece_color.WHITE)){
            currentPos = currentPos - 8;
            if(validPosition(currentPos)){
                possibleMoves.add(currentPos);
                if(newPosition == currentPos){
                    validMoves = true;
                }
            }

            if(validPosition(currentPos-1)){
                possibleMoves.add(currentPos-1);
                if(newPosition == currentPos-1){
                    validMoves = true;
                }
            }

            if(validPosition(currentPos+1)){
                possibleMoves.add(currentPos+1);
                if(newPosition == currentPos+1){
                    validMoves = true;
                }
            }

            currentPos = tempPos;

            if(this.isFirstMove){
                currentPos = currentPos - 16;
                if(validPosition(currentPos)){
                    if(newPosition == currentPos){
                        validMoves = true;
                    }
                }
            }

        }

        return validMoves;
    }

    @Override
    public boolean allowedMoves(Integer newPosition, Board currentBoard, three_state blk) {

        for (int j = 0; j < possibleMoves.size(); j++) {
            Integer currentPosition = possibleMoves.get(j);
            String exID = this.xIDResolver(currentPosition);
            Piece tempPiece = currentBoard.positionPieceAssoc.get(exID);
            if(tempPiece == null){
                if(j==0) { //If "Center" && nothing in front
                    allowedMoves.add(currentPosition);
                    System.out.println("Nothing: " + this.xIDResolver(currentPosition));

                    if(this.isFirstMove){
                        if (this.color == piece_color.WHITE){
                            if(validPosition(currentPosition-8)){
                                exID = this.xIDResolver(currentPosition-8);
                                tempPiece = currentBoard.positionPieceAssoc.get(exID);
                                if(tempPiece == null) {
                                    allowedMoves.add(currentPosition - 8);
                                    System.out.println("Nothing: " + this.xIDResolver(currentPosition - 8));
                                }
                            }

                        } else if(this.color == piece_color.BLACK){
                            if(validPosition(currentPosition+8)){
                                exID = this.xIDResolver(currentPosition+8);
                                tempPiece = currentBoard.positionPieceAssoc.get(exID);
                                if(tempPiece == null) {
                                    allowedMoves.add(currentPosition+8);
                                    System.out.println("Nothing: " + this.xIDResolver(currentPosition + 8));
                                }
                            }

                        }
                    }
                }
            } else if(tempPiece.color != this.color){
                if(j!=0) {  //If NOT "Center" && opposite color
                    allowedMoves.add(currentPosition);
                    System.out.println("Opp: " + this.xIDResolver(currentPosition));
                }
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
        allowedMoves.clear();

        return allowedMoveStatus;
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
    public void whtSonar(String currentPos, Board currentBoard) {

    }


    @Override
    public void updateWhtKingHash(ArrayList<Integer> allowedMoves, Board currentBoard) {

    }

    @Override
    public void blkSonar(String currentPos, Board currentBoard) {

    }


    @Override
    public void updateBlkKingHash(ArrayList<Integer> allowedMoves, Board currentBoard) {

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
