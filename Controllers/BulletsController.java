package Controllers;

import GameObjects.Bullet;
import GameObjects.EarthDefensiveBlock;
import GameObjects.PlayerController;

import java.util.ArrayList;

public class BulletsController {
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public void CreateBullet(int xPos, int yPos, Boolean enemyBullet){
        bullets.add(new Bullet(xPos, yPos, enemyBullet));
    }

    public int[][] GetBullets(int[][] battlefield){
        bullets.forEach((b) -> {
            battlefield[b.getPositionY()][b.getPositionX()] = 6;
            if(b.getEnemyBullet() == true) battlefield[b.getPositionY() + 1][b.getPositionX()] = 6;
            else battlefield[b.getPositionY() - 1][b.getPositionX()] = 6;
        });
        return battlefield;
    }

    public void MoveBullets(){
        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).getPositionY() < 17 && bullets.get(i).getEnemyBullet() == true) bullets.get(i).MoveBullet();
            else if (bullets.get(i).getEnemyBullet() == true) bullets.remove(i);
            else if(bullets.get(i).getPositionY() > 3 && bullets.get(i).getEnemyBullet() == false) bullets.get(i).MoveBullet();
            else if (bullets.get(i).getEnemyBullet() == false) bullets.remove(i);
        }
    }

    public ArrayList<EarthDefensiveBlock> CheckAndComputeBulletDefensiveBlockCollision(ArrayList<EarthDefensiveBlock> earthDefensiveBlocks){
        for(int d = 0; d < bullets.size(); d++){
            Boolean hit = false;
            for(int c = 0; c < earthDefensiveBlocks.size(); c++){
                for(int i = 0; i < earthDefensiveBlocks.get(c).getDefensiveBlockBricks().size(); i++){
                    if(earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionX() == bullets.get(d).getPositionX() && earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionY() == bullets.get(d).getPositionY()){
                        earthDefensiveBlocks.get(c).getDefensiveBlockBricks().remove(i);
                        bullets.remove(d);
                        hit = true;
                        break;
                    }
                }
                if(hit == false)
                    for(int i = 0; i < earthDefensiveBlocks.get(c).getDefensiveBlockBricks().size(); i++){
                        if(earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionX() == bullets.get(d).getPositionX() && earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionY() == bullets.get(d).getPositionY() - 1 && bullets.get(d).getEnemyBullet() == false){
                            earthDefensiveBlocks.get(c).getDefensiveBlockBricks().remove(i);
                            bullets.remove(d);
                            hit = true;
                            break;
                        }
                        else if(earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionX() == bullets.get(d).getPositionX() && earthDefensiveBlocks.get(c).getDefensiveBlockBricks().get(i).getPositionY() == bullets.get(d).getPositionY() + 1 && bullets.get(d).getEnemyBullet() == true){
                            earthDefensiveBlocks.get(c).getDefensiveBlockBricks().remove(i);
                            bullets.remove(d);
                            hit = true;
                            break;
                        }
                    }
                if(hit == true) break;
            }

        }
        return earthDefensiveBlocks;
    }


    public AlienController CheckAndComputeBulletAliensCollision(AlienController alienController){
        for(int d = 0; d < bullets.size(); d++){
            if(bullets.get(d).getEnemyBullet() == false){
                Boolean hit = false;
                for(int c = 0; c < alienController.GetAliens().size(); c++){
                    if(alienController.GetAliens().get(c).getPositionX() == bullets.get(d).getPositionX() && alienController.GetAliens().get(c).getPositionY() == bullets.get(d).getPositionY()){
                        alienController.ChangeShooter(alienController.GetAliens().get(c).getPositionX(), alienController.GetAliens().get(c).getPositionY() - 1);
                        alienController.GetAliens().remove(c);
                        bullets.remove(d);
                        hit = true;
                        break;
                    }
                    if(hit == true) break;
                }
                if(hit == false)
                    for(int c = 0; c < alienController.GetAliens().size(); c++){
                        if(alienController.GetAliens().get(c).getPositionX() == bullets.get(d).getPositionX() && alienController.GetAliens().get(c).getPositionY() == bullets.get(d).getPositionY() - 1){
                            alienController.ChangeShooter(alienController.GetAliens().get(c).getPositionX(), alienController.GetAliens().get(c).getPositionY() - 1);
                            alienController.GetAliens().remove(c);
                            bullets.remove(d);
                            break;
                        }
                    }
            }
        }
        return alienController;
    }

    public PlayerController CheckAndComputeBulletPlayerCollision(PlayerController playerController){
        for(int d = 0; d < bullets.size(); d++){
            if(playerController.getPositionX() == bullets.get(d).getPositionX() && playerController.getPositionY() == bullets.get(d).getPositionY()){
                playerController.PlayerHit(false);
                bullets.remove(d);
                break;
            }
            else if(playerController.getPositionX() == bullets.get(d).getPositionX() && playerController.getPositionY() == bullets.get(d).getPositionY() + 1 && bullets.get(d).getEnemyBullet() == true){
                playerController.PlayerHit(false);
                bullets.remove(d);
                break;
            }
        }
        return playerController;
    }

}
