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
    Texture gameOverText;

    Player player;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    @Override
    public void create() {

        // Initializing viewport.
        gameCam = new OrthographicCamera();
        gameViewport = new FitViewport(640, 480, gameCam);

        batch = new SpriteBatch();
        bgTexture = new Texture("bg-placeholder.png");
        gameOverText = new Texture("ui/gameover.png");
        player = new Player();
        player.create();

        createEnemy(enemies, 456, 620);
        createEnemy(enemies, 436, 620);
        createEnemy(enemies, 420, 620);
        createEnemy(enemies, 412, 620);

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
        for(int x=0; x<enemies.size(); x++) {
            Enemy enemy = enemies.get(x);
            enemy.draw(batch);
        }
        checkGameOver(player, batch);
        batch.end();

        // Updating the scene.
        player.update(bullets);
        for(int i=0; i<bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            for(int n=0; n<enemies.size(); n++) {
                Enemy enemy = enemies.get(n);
                enemy.update(player);
                enemy.checkBulletCollisions(bullet, player);
                enemy.checkPlayerCollisions(player);
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    public void checkGameOver(Player player, SpriteBatch batch) {
        if(player.HP <= 0) {
            drawGameOverScreen(batch);
            player.texture.dispose();
            player.isActive = false;
        }
    }

    public void drawGameOverScreen(SpriteBatch batch) {
        ScreenUtils.clear(Color.BLACK);
        batch.draw(gameOverText, 0, 0);
    }

    public void createEnemy(ArrayList<Enemy> enemies, int xPos, int yPos) {
        Enemy enemy = new Enemy();
        enemy.X_POSITION = xPos;
        enemy.Y_POSITION = yPos;
        enemies.add(enemy);
    }

}
