package logic.basicprogramming.knightstour.khightstoursolution;

import logic.basicprogramming.magicsquares.mssolution.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution of knight`s tour problem
 *
 * @author hellnyk
 */
public class KnightTourProblem {

    /**
     * the start (most top-left) position on the chessboard
     */
    public static final int START_ON_CHESSBOARD = 0;

    /**
     * types of knight move
     */
    private static final int[][] KNIGHT_MOVES_XY = {
            {-2,  1},
            {-1,  2},
            { 1,  2},
            { 2,  1},
            { 2, -1},
            { 1, -2},
            {-1, -2},
            {-2, -1}
    };

    /**
     * size of the chessboard
     */
    private int sizeOfChessboard;

    /**
     * the knight`s moves on the chessboard
     */
    public Position[] moves;

    /**
     * cells of chessboard, where knight was
     */
    private boolean[][] visitedCells;

    private int[][] helpMatrix;

    private final int symmetricalCourse;

    /**
     * Default constructor
     */
    public KnightTourProblem() {
        sizeOfChessboard = 8;
        visitedCells = new boolean[sizeOfChessboard][sizeOfChessboard];
        moves = new Position[sizeOfChessboard * sizeOfChessboard];
        helpMatrix = new int[sizeOfChessboard][sizeOfChessboard];
        symmetricalCourse = sizeOfChessboard*sizeOfChessboard / 2;

    }


    public void getResult(int xCoordinate, int yCoordinate) {
        setStartPosition(xCoordinate, yCoordinate);
        int move = 0;
        solveRecAllMoves(moves[move], move);
    }


    private void setStartPosition(int xCoordinate, int yCoordinate) {
        if (isPositionOnChessboard(xCoordinate, yCoordinate))
            moves[0] = new Position(xCoordinate, yCoordinate, 0);
        else
            moves[0] = new Position(START_ON_CHESSBOARD, START_ON_CHESSBOARD, 0);
    }

    private boolean solveRecAllMoves(Position currentPosition, int move) {

        moves[move] = currentPosition;
        Position symmetricPosition = getSymmetricPosition(currentPosition);
        moves[move + symmetricalCourse] = symmetricPosition;
        visitedCells[currentPosition.getX()][currentPosition.getY()] = true;
        visitedCells[symmetricPosition.getX()][symmetricPosition.getY()] = true;

//        helpMatrix[currentPosition.getX()][currentPosition.getY()] = move+1;
//        helpMatrix[symmetricPosition.getX()][symmetricPosition.getY()] = move + 1 + symmetricalCourse;
//        FileWriter.writeMatrix(helpMatrix, move+1);
        if (move == (sizeOfChessboard * sizeOfChessboard - 1)/2) {
            printResult();
            return true;
        } else {
            List<Position> positions = getAllAvailablePositions(currentPosition);
            Position nextPosition = getWarnsdorffsPosition(positions);
            return solveRecAllMoves(nextPosition, move + 1);
        }
    }

    private Position getSymmetricPosition(Position positionFor){
        int xCoordinate = sizeOfChessboard - positionFor.getX() - 1;
        int yCoordinate = sizeOfChessboard - positionFor.getY() - 1;
        return new Position(xCoordinate, yCoordinate, 0);
    }


    private List<Position> getAllAvailablePositions(Position positionFor) {
        List<Position> availablePositions = new ArrayList<>();
        for (int typeOfMove = 0; typeOfMove < KNIGHT_MOVES_XY.length; typeOfMove++) {
            int canBePositionX = positionFor.getX() + KNIGHT_MOVES_XY[typeOfMove][0];
            int canBePositionY = positionFor.getY() + KNIGHT_MOVES_XY[typeOfMove][1];
            if (isPositionOnChessboard(canBePositionX, canBePositionY) && !visitedCells[canBePositionX][canBePositionY]) {
                availablePositions.add(new Position(canBePositionX, canBePositionY, 0));
            }
        }
        return availablePositions;
    }


    /**
     * find position with minimum chances to move by using Warnsdorff`s algorithm
     *
     * @param list
     *      {@link List<Position>} of available positions to move
     * @return
     *      {@link Position} with minimum chances to move
     */
    private Position getWarnsdorffsPosition(List<Position> list) {
        if(list.isEmpty()){
            return null;
        }
        int counterOfMinPositions = sizeOfChessboard;
        int numberOfMinPos = 0;
        for (int iter = 0; iter < list.size(); iter++) {
            List<Position> availPos = getAllAvailablePositions(list.get(iter));
            if (availPos.size() < counterOfMinPositions) {
                counterOfMinPositions = availPos.size();
                numberOfMinPos = iter;
            }
        }
        return list.get(numberOfMinPos);
    }

    /**
     * check if position with specified coordinates is on the chessboard
     *
     * @param xCoordinate
     *      vertical coordinate of position
     * @param yCoordinate
     *      horizontal coordinate of position
     * @return
     *      true, if this position on chessboard, false - otherwise
     */
    private boolean isPositionOnChessboard(int xCoordinate, int yCoordinate) {
        return xCoordinate >= START_ON_CHESSBOARD && xCoordinate < sizeOfChessboard &&
                yCoordinate >= START_ON_CHESSBOARD && yCoordinate < sizeOfChessboard;
    }

    /**
     * print result of knights problen in case of array of moves
     */
    private void printResult() {
        String[] chessboardX = {"8", "7", "6", "5", "4", "3", "2", "1"};
        String[] chessboardY = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int rowSeparator = 1;
        for (Position move : moves) {
            System.out.print(chessboardX[move.getX()] + chessboardY[move.getY()] + " ");
            if (rowSeparator % 10 == 0) {
                System.out.println();
            }
            rowSeparator++;
        }
        System.out.println();
    }
}
