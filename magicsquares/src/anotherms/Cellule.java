package anotherms;

/**
 * @author hellnyk
 */
public class Cellule {

    private int stateValue;
    private int elementsBefore;
    private int elementsAfter;
    private int positionVertical;
    private int positionHorizontal;

    public Cellule(int stateValue, int elementsBefore, int elementsAfter,
                   int positionVertical, int positionHorizontal) {
        this.stateValue = stateValue;
        this.elementsBefore = elementsBefore;
        this.elementsAfter = elementsAfter;
        this.positionVertical = positionVertical;
        this.positionHorizontal = positionHorizontal;
    }

    public int getStateValue() {
        return stateValue;
    }

    public int getPositionVertical() {
        return positionVertical;
    }

    public int getPositionHorizontal() {
        return positionHorizontal;
    }

    @Override
    public String toString() {
        return "Cellule{" +
                "stateValue=" + stateValue +
                ", elementsBefore=" + elementsBefore +
                ", elementsAfter=" + elementsAfter +
                ", positionVertical=" + positionVertical +
                ", positionHorizontal=" + positionHorizontal +
                '}';
    }
}
