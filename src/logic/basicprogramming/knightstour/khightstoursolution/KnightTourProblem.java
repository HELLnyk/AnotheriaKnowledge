package logic.basicprogramming.knightstour.khightstoursolution;

/**
 * Solution of knight`s tour problem
 *
 * @author hellnyk
 */
public class KnightTourProblem {

    public static final int START_ON_CHESSBOARD = 0;

    private static final int[][] KNIGHT_MOVES_XY = {
            {-2,1},
            {-1,2},
            {1,2},
            {2,1},
            {2,-1},
            {1,-2},
            {-1,-2},
            {-2,-1}
    };

    private int sizeOfChessboard;

    private int capacityOfAllMoves;

    private Position[] moves;

    private boolean[][] visitedCells;

    public KnightTourProblem(){
        sizeOfChessboard = 8;
        visitedCells = new boolean[sizeOfChessboard][sizeOfChessboard];
        capacityOfAllMoves = sizeOfChessboard*sizeOfChessboard + 1;
        moves = new Position[capacityOfAllMoves];

    }

    public void getResult(int xCoordinate, int yCoordinate){
        setStartPosition(xCoordinate, yCoordinate);
        int move = 0;
        setMoves(moves[move], move);
        printResult();
    }

    private void setStartPosition(int xCoordinate, int yCoordinate){
        if(isPositionOnChessboard(xCoordinate, yCoordinate))
            moves[0] = new Position(xCoordinate, yCoordinate, 0);
        else
            moves[0] = new Position(0,0,0);
    }



    private void setMoves(Position currentPosition, int move) {
        int lastMove = 7;
        moves[move] = currentPosition;
        visitedCells[currentPosition.getX()][currentPosition.getY()] = true;
        Position availablePosition = getAvailablePosition(currentPosition, currentPosition.getKindOfMove());
        if(availablePosition == null){

        }
        else {

        }

    }

    private Position getAvailablePosition(Position prevPosition, int kindOfMove){
        for (int typeOfMove = kindOfMove; typeOfMove < KNIGHT_MOVES_XY.length; typeOfMove++) {
            int canBePositionX = prevPosition.getX() + KNIGHT_MOVES_XY[typeOfMove][0];
            int canBePositionY = prevPosition.getY() + KNIGHT_MOVES_XY[typeOfMove][1];
            if(isPositionOnChessboard(canBePositionX, canBePositionY) &&
                    !visitedCells[canBePositionX][canBePositionY]){
                prevPosition.setKindOfMove(typeOfMove);
                return new Position(canBePositionX, canBePositionY, 0);
            }
        }
        return null;
    }

    private boolean isPositionOnChessboard(int xCoordinate, int yCoordinate){
        return xCoordinate >= START_ON_CHESSBOARD && xCoordinate < sizeOfChessboard &&
                yCoordinate >= START_ON_CHESSBOARD && yCoordinate < sizeOfChessboard;
    }

    private void printResult(){

        String[] chessboardX = {"8", "7", "6", "5", "4", "3", "2", "1"};
        String[] chessboardY = {"a", "b", "c", "d", "e", "f", "g", "h"};

        int rowSeparator = 1;
        for(Position move: moves){
            System.out.print(chessboardX[move.getX()] + chessboardY[move.getY()] + " ");
            if(rowSeparator % 10 == 0){
                System.out.println();
            }
            rowSeparator++;
            if(rowSeparator == 53)break;
        }
    }
}
