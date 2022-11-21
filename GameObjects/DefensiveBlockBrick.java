package GameObjects;

import lombok.Getter;

@Getter
public class DefensiveBlockBrick {
    int positionX;
    int positionY;

    public DefensiveBlockBrick(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
