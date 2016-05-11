package logic.basicprogramming.magicsquares.anotherms;

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

    public void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }

    public int getElementsBefore() {
        return elementsBefore;
    }

    public void setElementsBefore(int elementsBefore) {
        this.elementsBefore = elementsBefore;
    }

    public int getElementsAfter() {
        return elementsAfter;
    }

    public void setElementsAfter(int elementsAfter) {
        this.elementsAfter = elementsAfter;
    }

    public int getPositionVertical() {
        return positionVertical;
    }

    public void setPositionVertical(int positionVertical) {
        this.positionVertical = positionVertical;
    }

    public int getPositionHorizontal() {
        return positionHorizontal;
    }

    public void setPositionHorizontal(int positionHorizontal) {
        this.positionHorizontal = positionHorizontal;
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
