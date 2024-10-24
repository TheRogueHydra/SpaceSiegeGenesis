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

public class GameScreen extends ApplicationAdapter {

    private OrthographicCamera gameCam;
    private Viewport gameViewport;
    SpriteBatch batch;

    Texture backgroundTexture;
    Player player;

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
        player.update();

        // Draw Logic.
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        player.sprite.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

}
