package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.screens.DialogPause.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color();
    public Stage stage;
    public boolean paused;
    public boolean endLevel;
    public Array<EnemyEntity> flyingEnemies = new Array<>();
    public EnemyEntity closestFlying;
    public static int level;
    private float cloudTimer;
    
    @Override
    public void show() {
        super.show();
    
        gameScreen = this;
        BG_COLOR.set(Color.PINK);
    
        paused = false;
    
        stage = new Stage(new ScreenViewport(), batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.align(Align.bottomLeft);
        root.pad(10);
        stage.addActor(root);
        
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!paused && keycode == Keys.ESCAPE) {
                    paused = true;
                
                    DialogPause dialogPause = new DialogPause(GameScreen.this);
                    dialogPause.show(stage);
                    dialogPause.addListener(new PauseListener() {
                        @Override
                        public void resume() {
                            paused = false;
                        }
                    
                        @Override
                        public void quit() {
                            core.transition(new MenuScreen());
                        }
                    });
                }
                return super.keyDown(event, keycode);
            }
        });
    
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("game/white"));
        shapeDrawer.setPixelSize(.5f);
    
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
    
        entityController.clear();
        
        if (level == 1) {
            var player = new PlayerEntity();
            player.setPosition(100, 600);
            entityController.add(player);
            
            var level = new Level1bg();
            entityController.add(level);
    
            var spawner = new EnemySpawner1();
            entityController.add(spawner);
            PlayerEntity.landLevel = 50f;
        } else if (level == 2) {
            var player = new PlayerEntity();
            player.setPosition(100, 600);
            entityController.add(player);
            
            var level = new Level2bg();
            entityController.add(level);
    
            var spawner = new EnemySpawner2();
            entityController.add(spawner);
            PlayerEntity.landLevel = 150f;
        } else if (level == 3) {
            var player = new PlayerEntity();
            player.setPosition(100, 600);
            entityController.add(player);
            
            var level = new Level1bg();
            entityController.add(level);
    
            PlayerEntity.landLevel = 150f;
            var boss = new EnemyBoss();
            entityController.add(boss);
            boss.setPosition(1024, PlayerEntity.landLevel - 40);
        } else if (level == 4) {
            var player = new PlayerEntity2();
            player.setPosition(100, 600);
            entityController.add(player);
            
            var level = new Level3bg();
            entityController.add(level);
    
            PlayerEntity.landLevel = 200f;
            var boss = new EnemyBoss2();
            entityController.add(boss);
            boss.setPosition(1024, PlayerEntity.landLevel - 40);
        }
        
        camera.position.set(512, 288, 0);
    }
    
    @Override
    public void act(float delta) {
        if (!paused) {
            var player = PlayerEntity.player;
            var distance = Float.MAX_VALUE;
            closestFlying = null;
            for (var enemy : flyingEnemies) {
                var pointDistance = Utils.pointDistance(enemy.x, enemy.y, player.x, player.y);
                if (pointDistance < distance) {
                    distance = pointDistance;
                    closestFlying = enemy;
                }
            }
            entityController.act(delta);
            vfxManager.update(delta);
        }
        stage.act(delta);
        if (endLevel) {
            level++;
            if (level == 2) {
                core.transition(new Cinematic2Screen());
            } else if (level == 3) {
                core.transition(new Cinematic3Screen());
            } else if (level == 4) {
                core.transition(new GameScreen());
            } else if (level == 5) {
                core.transition(new Cinematic4Screen());
            }
        } else if (level == 4) {
            cloudTimer -= delta;
            if (cloudTimer < 0) {
                cloudTimer = MathUtils.random(.4f, 1f);
                var cloud = new Cloud();
                cloud.x = 1024 + 150;
                cloud.y = MathUtils.random(576);
                cloud.setMotion(1500, 180);
                entityController.add(cloud);
                cloud.skeleton.getRootBone().setRotation(MathUtils.random(360));
                cloud.skeleton.getRootBone().setScale(MathUtils.random(1f, 1.5f));
            }
        }
    }
    
    @Override
    public void draw(float delta) {
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(paused ? 0 : delta);
        batch.end();
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
        
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        entityController.dispose();
        bgm_gameplay.stop();
    }
}
