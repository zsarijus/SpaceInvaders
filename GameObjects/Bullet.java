package GameObjects;

import lombok.Getter;

@Getter
public class Bullet {
    private int positionX, positionY;
    private Boolean enemyBullet;

    public Bullet(int positionX, int positionY, Boolean enemyBullet) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.enemyBullet = enemyBullet;
    }

    public void MoveBullet(){
        if(enemyBullet == true) positionY += 2;
        else positionY -= 2;
    }

}
