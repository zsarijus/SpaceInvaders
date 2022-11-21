package Controllers;

import GameObjects.Alien;
import GameObjects.DefensiveBlockBrick;
import GameObjects.EarthDefensiveBlock;
import GameObjects.PlayerController;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;

public class AlienController {


    ArrayList<Alien> aliens = new ArrayList<Alien>();
    private Boolean movingRight = true;
    private int currentPositionX = 0, cureentPositionY = 0;
    public AlienController(int x, int y) {
        Boolean isInFront = null;
        for(int i = 0; i < 11;i++){
            isInFront = false;
            for(int d = 0; d < 6;d++){
                if(d == 5) isInFront = true;
                aliens.add(new Alien(x + i * 6, y + d, isInFront));
            }
        }

    }

    public ArrayList<Alien> GetAliens(){
        return aliens;
    }

    public int[][] GetAliens(int[][] battlefield){
        aliens.forEach((a) -> {
            battlefield[a.getPositionY()][a.getPositionX()] = 4;
        });
        return battlefield;
    }

    public void UpdateArmyMovement(){
        if(movingRight == true && currentPositionX < 10) currentPositionX++;
        else if (movingRight == true){
            cureentPositionY--;
            movingRight = false;
        }
        else if(movingRight == false && currentPositionX > -10) currentPositionX--;
        else if (movingRight == false){
            cureentPositionY--;
            movingRight = true;
        }
        UpdateAliensMovement();

    }

    private void UpdateAliensMovement(){
        aliens.forEach((a) -> a.ChangePosition(currentPositionX, cureentPositionY));
    }


    public ArrayList<EarthDefensiveBlock> CheckAndComputeAlienCollisions(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks){
        aliens.forEach(a-> {
            earthDefensiveBlocks.forEach(e -> {
                for(int i = 0; i < e.getDefensiveBlockBricks().size(); i++){
                    if(e.getDefensiveBlockBricks().get(i).getPositionX() == a.getPositionX() && e.getDefensiveBlockBricks().get(i).getPositionY() == a.getPositionY()){
                        e.getDefensiveBlockBricks().remove(i);
                    }
                }
            });
        });

        return earthDefensiveBlocks;
    }

    public void ChangeShooter(int x, int y){
        aliens.forEach((a) -> {
            if(a.getPositionX() == x && a.getPositionY() == y) a.MakeShooter();
        });
    }

    public BulletsController UpdateArmyFire(BulletsController bulletsController, int chanceToShoot){
        Random rand = new Random();
        aliens.forEach((a) -> {
            int i = 0;
            if(a.getInFront() == true){
                i = rand.nextInt(100);
                if(i <= chanceToShoot){
                    bulletsController.CreateBullet(a.getPositionX(), a.getPositionY() - 1, true);
                }
            }
        });
        return bulletsController;
    }

    public Boolean CheckIfArmyDefeated(){
        if(aliens.size() > 0) return false;
        else return true;
    }

    public Boolean CheckIfArmyReachedEarth(){
        Boolean reachedEnd = false;
        for(int a = 0; a < aliens.size(); a++){
            if(aliens.get(a).getPositionY() == 18){
                reachedEnd = true;
                break;
            }
        }
        return reachedEnd;
    }

}
