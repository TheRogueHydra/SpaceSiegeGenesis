package com.theroguehydra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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

    int[] shieldXPositions = new int[] {16, 112, 208, 384, 480, 576};


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
        for (int i = 0; i < 6; i++) {
            Shield shield = new Shield();
            shield.create(shieldXPositions[i], 176);
            worldShields.add(shield);
        }

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

            for(int j=0; j<worldShields.size(); j++) {

                Shield currentShield = worldShields.get(i);
                currentShield.update();

                for(int k=0; k<worldBullets.size(); k++) {

                    Bullet currentBullet = worldBullets.get(j);
                    currentBullet.update();

                    Rectangle shieldBoundRect = currentShield.sprite.getBoundingRectangle();
                    Rectangle bulletBoundRect = currentBullet.sprite.getBoundingRectangle();
                    Rectangle enemyBoundRect = currentEnemy.sprite.getBoundingRectangle();

                    // Collision between a bullet and an enemy.
                    if(enemyBoundRect.overlaps(bulletBoundRect)) {
                        currentEnemy.isActive = false;
                        currentBullet.isActive = false;
                        player.score += 1;
                        System.out.println("Player killed an enemy.");
                    }

                    // Collision between a bullet and a shield.
                    if(shieldBoundRect.overlaps(bulletBoundRect)) {
                        currentBullet.isActive = false;
                    }

                    // Collision between an enemy and a shield.
                    if(shieldBoundRect.overlaps(enemyBoundRect)) {
                        currentEnemy.isActive = false;
                    }

                }

            }

        }

        // Draw Logic.
        ScreenUtils.clear(Color.BLACK);

        batch.begin();

        // Using SpriteBatch.
        batch.draw(backgroundTexture, 0, 0);
        player.sprite.draw(batch);

        // Drawing shields.
        for(int i=0; i<worldShields.size(); i++) {
            Shield currentShield = worldShields.get(i);
            if(currentShield.isActive) {
                currentShield.sprite.draw(batch);
            }
        }

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

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

}
