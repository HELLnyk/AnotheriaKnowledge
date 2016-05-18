package mssolution;

import java.util.*;

/**
 * Resolve magic square problem
 *
 * @author hellnyk
 */
public class MagicSquareProblem{

    private int capacityOfSquare;
    private int magicConstant;


    public MagicSquareProblem(int capacityOfSquare){
        this.capacityOfSquare = capacityOfSquare;
        magicConstant = initMagicConstant();
    }





    private int initMagicConstant(){
        return (capacityOfSquare * (capacityOfSquare * capacityOfSquare) + 1) / 2;
    }

}


