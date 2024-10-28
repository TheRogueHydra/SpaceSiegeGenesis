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
    ArrayList<Enemy> worldEnemies = new ArrayList<Enemy>();
    ArrayList<Shield> worldShields = new ArrayList<Shield>();

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

        // Creating shields.
        Shield shield1 = new Shield();
        Shield shield2 = new Shield();
        Shield shield3 = new Shield();
        Shield shield4 = new Shield();
        Shield shield5 = new Shield();
        Shield shield6 = new Shield();

        shield1.create(16, 176, worldShields);
        shield2.create(112, 176, worldShields);
        shield3.create(208, 176, worldShields);
        shield4.create(384, 176, worldShields);
        shield5.create(480, 176, worldShields);
        shield6.create(576, 176, worldShields);

        // Creating enemies.
        Enemy enemy1 = new Enemy();
        Enemy enemy2 = new Enemy();
        Enemy enemy3 = new Enemy();
        Enemy enemy4 = new Enemy();

        enemy1.create(282, 480, worldEnemies);
        enemy2.create(302, 480, worldEnemies);
        enemy3.create(322, 480, worldEnemies);
        enemy4.create(342, 480, worldEnemies);

    }

    @Override
    public void render() {

        // Update Logic.
        player.update(worldBullets);

        // Update enemies and bullets.
        for(int i=0; i<worldEnemies.size(); i++) {

            Enemy currentEnemy = worldEnemies.get(i);
            currentEnemy.update(player);

            for(int j=0; j<worldBullets.size(); j++) {

                Bullet currentBullet = worldBullets.get(j);
                currentBullet.update();

                if(currentEnemy.sprite.getBoundingRectangle().overlaps(currentBullet.sprite.getBoundingRectangle()) && currentEnemy.isActive) {
                    currentBullet.isActive = false;
                    currentEnemy.isActive = false;
                    player.score += 1;
                    System.out.println("Player killed an enemy.");
                }

            }

        }

        // Update shields.
        for(int i=0; i<worldShields.size(); i++) {

            Shield currentShield = worldShields.get(i);
            currentShield.update();

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
            if(currentBullet.isActive) {
                currentBullet.sprite.draw(batch);
            }
        }

        // Drawing enemies.
        for(int i=0; i<worldEnemies.size(); i++) {
            Enemy currentEnemy = worldEnemies.get(i);
            if(currentEnemy.isActive) {
                currentEnemy.sprite.draw(batch);
            }
        }

        // Drawing shields.
        for(int i=0; i<worldShields.size(); i++) {
            Shield currentShield = worldShields.get(i);
            if(currentShield.isActive) {
                currentShield.sprite.draw(batch);
            }
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

}
