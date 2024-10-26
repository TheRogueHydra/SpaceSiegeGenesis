package com.theroguehydra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

    Texture backgroundTexture;
    Player player;

    ArrayList<Bullet> worldBullets = new ArrayList<Bullet>();

    @Override
    public void create() {

        // Initializing viewport.
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(640, 480, gameCam);

        // Initializing objects.
        this.player = new Player();
        player.create();
        this.batch = new SpriteBatch();
        this.backgroundTexture = new Texture(Gdx.files.internal("bg-placeholder.png"));

    }

    @Override
    public void render() {

        // Update Logic.
        player.update(worldBullets);

        // Update bullets.
        for(int i=0; i<worldBullets.size(); i++) {
            Bullet currentBullet = worldBullets.get(i);
            currentBullet.update();
        }

        // Draw Logic.
        ScreenUtils.clear(Color.BLACK);

        batch.begin();

        // Using SpriteBatch.
        batch.draw(backgroundTexture, 0, 0);
        player.sprite.draw(batch);

        // Drawing bullets.
        for(int i=0; i<worldBullets.size(); i++) {
            Bullet currentBullet = worldBullets.get(i);
            currentBullet.sprite.draw(batch);
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

}
