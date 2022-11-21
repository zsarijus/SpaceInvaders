package GameObjects;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class EarthDefensiveBlock {
    ArrayList<DefensiveBlockBrick> defensiveBlockBricks = new ArrayList<DefensiveBlockBrick>();

    public EarthDefensiveBlock(int x, int y) {
        for(int i = 0; i < 10; i++)
            for(int d = 0; d < 3; d++) {
                if (d != 2)
                    defensiveBlockBricks.add(new DefensiveBlockBrick(x + i, y + d));
                else if ((i < 3 || i > 6))
                    defensiveBlockBricks.add(new DefensiveBlockBrick(x + i, y + d));
            }
    }

    public int[][] GetDefensiveBlock(int[][] battlefield){
        defensiveBlockBricks.forEach((d) -> battlefield[d.getPositionY()][d.getPositionX()] = 3);
        return battlefield;
    }

}
