package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;

public class EnemySpawner2 extends Entity {
    float timeline;
    final float CUP_APPEARANCE = 2;
    final float SMALL_CUP_APPEARANCE = 20;
    final float SMALL_CUP_INVINCIBLE_CHANCE = .5f;
    final float PLATE_APPEARANCE = 60;
    final float POT_APPEARANCE = 40;
    final float HAT_APPEARANCE = 80;
    final float WIN = 100;
    float clubTimer;
    float clubDelay = 1;
    float smallClubTimer;
    float smallClubDelay = 1.5f;
    float diamondTimer;
    float diamondDelay = 8;
    float heartTimer;
    float heartDelay = 10;
    float spadeTimer;
    float spadeDelay = 15;
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        timeline += delta;
        
        if (timeline > CUP_APPEARANCE) {
            clubTimer -= delta;
            if (clubTimer < 0) {
                clubTimer = clubDelay;
                var enemy = new EnemyCup();
                enemy.y = PlayerEntity.landLevel;
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
    
        if (timeline > SMALL_CUP_APPEARANCE) {
            smallClubTimer -= delta;
            if (smallClubTimer < 0) {
                smallClubTimer = smallClubDelay;
                var enemy = MathUtils.randomBoolean(SMALL_CUP_INVINCIBLE_CHANCE)? new EnemyCupTinyInvincible() : new EnemyCupTiny();
                enemy.y = PlayerEntity.landLevel;
                enemy.x = 1100;
                entityController.add(enemy);
            }
        }
    
        if (timeline > PLATE_APPEARANCE) {
            diamondTimer -= delta;
            if (diamondTimer < 0) {
                diamondTimer = diamondDelay;
                var y = PlayerEntity.landLevel + MathUtils.random(150, 650);
                for (int i = 0; i < 5; i++) {
                    var enemy = new EnemyPlate();
                    enemy.y = y;
                    enemy.x = 1100 + i * 60f;
                    entityController.add(enemy);
                }
            }
        }
    
        if (timeline > POT_APPEARANCE) {
            heartTimer -= delta;
            if (heartTimer < 0) {
                heartTimer = heartDelay;
                var enemy = new EnemyPot();
                enemy.y = -150;
                enemy.x = MathUtils.random(200, 900);
                entityController.add(enemy);
            }
        }
    
        if (timeline > HAT_APPEARANCE) {
            spadeTimer -= delta;
            if (spadeTimer < 0) {
                spadeTimer = spadeDelay;
                var enemy = new EnemyHat();
                enemy.y = PlayerEntity.landLevel + MathUtils.random(150, 650);
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
