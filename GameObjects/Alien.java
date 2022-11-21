package GameObjects;

import lombok.Getter;
import lombok.Setter;


public class Alien {
    int originalPositionX;
    int originalPositionY;
    int positionX;
    int positionY;
    Boolean inFront;
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }


    public Boolean getInFront() {
        return inFront;
    }

    public Alien(int positionX, int positionY, Boolean inFront) {
        this.originalPositionX = positionX;
        this.originalPositionY = positionY;
        this.positionX = originalPositionX;
        this.positionY = originalPositionY;
        this.inFront = inFront;
    }

    public void MakeShooter(){
        inFront = true;
    }

    public void ChangePosition(int positionX, int positionY){
        this.positionX = originalPositionX + positionX;
        this.positionY = originalPositionY - positionY;
    }

}
