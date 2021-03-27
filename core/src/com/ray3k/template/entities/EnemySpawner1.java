package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;

public class EnemySpawner1 extends Entity {
    float timeline;
    final float CLUB_APPEARANCE = 2;
    final float SMALL_CLUB_APPEARANCE = 20;
    final float DIAMOND_APPEARANCE = 40;
    final float HEART_APPEARANCE = 60;
    final float SPADE_APPEARANCE = 80;
    final float WIN = 100;
    float clubTimer;
    float clubDelay = 1;
    float smallClubTimer;
    float smallClubDelay = 1.5f;
    float diamondTimer;
    float diamondDelay = 3;
    float heartTimer;
    float heartDelay = 10;
    float spadeTimer;
    float spadeDelay = 10;
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        timeline += delta;
        
        if (timeline > CLUB_APPEARANCE) {
            clubTimer -= delta;
            if (clubTimer < 0) {
                clubTimer = clubDelay;
                var enemy = new EnemyClub();
                enemy.y = 50;
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
    
        if (timeline > SMALL_CLUB_APPEARANCE) {
            smallClubTimer -= delta;
            if (smallClubTimer < 0) {
                smallClubTimer = smallClubDelay;
                var enemy = new EnemyClubTiny();
                enemy.y = 50;
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
    
        if (timeline > DIAMOND_APPEARANCE) {
            diamondTimer -= delta;
            if (diamondTimer < 0) {
                diamondTimer = diamondDelay;
                var enemy = new EnemyDiamond();
                enemy.y = MathUtils.random(200, 700);
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
    
        if (timeline > HEART_APPEARANCE) {
            heartTimer -= delta;
            if (heartTimer < 0) {
                heartTimer = heartDelay;
                var enemy = new EnemyHeart();
                enemy.y = 50;
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
    
        if (timeline > SPADE_APPEARANCE) {
            spadeTimer -= delta;
            if (spadeTimer < 0) {
                spadeTimer = spadeDelay;
                var enemy = new EnemySpade();
                enemy.y = MathUtils.random(200, 700);
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
        
        if (timeline > WIN) {
            GameScreen.gameScreen.endLevel = true;
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
