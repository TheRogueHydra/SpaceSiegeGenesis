package com.theroguehydra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;

public class GameScreen extends ApplicationAdapter {

    private OrthographicCamera gameCam;
    private Viewport gameViewport;
    SpriteBatch batch;
    Texture bgTexture;

    Player player;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    Enemy testEnemy;

    @Override
    public void create() {

        // Initializing viewport.
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(640, 480, gameCam);

        batch = new SpriteBatch();
        bgTexture = new Texture("bg-placeholder.png");

        player = new Player();
        player.create();

        testEnemy = new Enemy();
        testEnemy.create();

    }

    @Override
    public void render() {
        // Drawing the scene.
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(bgTexture, 0, 0);
        player.draw(batch);
        for(int i=0; i<bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.draw(batch);
        }
        testEnemy.draw(batch);
        batch.end();

        // Updating the scene.
        player.update(bullets);
        for(int i=0; i<bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
        }
        testEnemy.update(player);
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

}
