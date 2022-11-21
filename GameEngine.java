import Controllers.AlienController;
import Controllers.BulletsController;
import GameObjects.Battlefield;
import GameObjects.EarthDefensiveBlock;
import GameObjects.PlayerController;

import java.util.ArrayList;

public class GameEngine {
    Battlefield battlefield = new Battlefield();
    ArrayList<EarthDefensiveBlock> earthDefensiveBlocks = new ArrayList<EarthDefensiveBlock>();
    AlienController alienController = null;
    PlayerController playerController = null;
    BulletsController bulletsController = null;
    int chanceToShoot = 5;

    public GameEngine() {
        earthDefensiveBlocks.add(new EarthDefensiveBlock(10, 15));
        earthDefensiveBlocks.add(new EarthDefensiveBlock(28, 15));
        earthDefensiveBlocks.add(new EarthDefensiveBlock(47, 15));
        earthDefensiveBlocks.add(new EarthDefensiveBlock(65, 15));
        alienController = new AlienController(13, 2);
        playerController = new PlayerController(42, 18, 1);
        bulletsController = new BulletsController();
    }

    public void UpdatePlayer(String input){
        if(input.equals("a")) playerController.MoveLeft();
        else if(input.equals("d")) playerController.MoveRight();
        else if(input.equals(" ")) bulletsController = playerController.Shoot(bulletsController);
        else if(input.equals("q")) playerController.PlayerHit(true);
    }

    public void UpdateBattleField(){
        alienController.UpdateArmyMovement();
        alienController.CheckAndComputeAlienCollisions(earthDefensiveBlocks);
        alienController.UpdateArmyFire(bulletsController, chanceToShoot);
        bulletsController.MoveBullets();
        bulletsController.CheckAndComputeBulletDefensiveBlockCollision(earthDefensiveBlocks);
        bulletsController.CheckAndComputeBulletAliensCollision(alienController);
        bulletsController.CheckAndComputeBulletPlayerCollision(playerController);
        battlefield = new Battlefield();
        earthDefensiveBlocks.forEach(e -> battlefield.setBattlefield(e.GetDefensiveBlock(battlefield.getBattlefield())));
        battlefield.setBattlefield(alienController.GetAliens(battlefield.getBattlefield()));
        battlefield.setBattlefield(playerController.GetPlayer(battlefield.getBattlefield()));
        battlefield.setBattlefield(bulletsController.GetBullets(battlefield.getBattlefield()));
    }

    public void RenderGame(){
        System.out.println(battlefield.ToString());
    }


    public Boolean CheckIfRoundEnded(){
        return alienController.CheckIfArmyDefeated();
    }

    public Boolean CheckIfGameEnded(){
        if(playerController.getLives() > 0 && alienController.CheckIfArmyReachedEarth() == false) return false;
        else return true;
    }
}
