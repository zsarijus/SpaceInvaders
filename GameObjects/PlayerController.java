package GameObjects;

import Controllers.BulletsController;
import com.sun.tools.javac.Main;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PlayerController {
    int positionX, positionY, lives;
    public PlayerController(int positionX, int positionY, int lives) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.lives = lives;
    }



    public int[][] GetPlayer(int[][] battlefield){
        battlefield[positionY][positionX] = 5;
        return battlefield;
    }

    public void MoveRight(){
        if(this.positionX < 84){
            this.positionX++;
        }
    }

    public void MoveLeft(){
        if(this.positionX > 0){
            this.positionX--;
        }
    }

    public BulletsController Shoot(BulletsController bulletsController){
        bulletsController.CreateBullet(positionX, positionY + 1, false);
        return bulletsController;
    }

    public void PlayerHit(Boolean isFatal) {
        if(isFatal == true) lives = 0;
        else lives--;
    }

}
