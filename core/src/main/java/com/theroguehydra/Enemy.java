package com.theroguehydra;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    
    int X_POSITION;
    int Y_POSITION;
    int SPEED = 1;

    boolean isActive;

    Texture texture = new Texture("alien-basic.png");
    Sprite enemySprite = new Sprite(texture);

    public void create() {
        this.isActive = true;
    }

    public void update(Player player) {
        this.Y_POSITION -= SPEED;   
    }

    public void draw(SpriteBatch batch) {
        if(isActive) {
            batch.draw(texture, X_POSITION, Y_POSITION);
        }
    }

    public void checkBulletCollision(Bullet bullet) {
        Rectangle enemyRect = enemySprite.getBoundingRectangle();
        Rectangle bulletRect = enemySprite.getBoundingRectangle();
        if(enemyRect.overlaps(bulletRect)) {
            this.isActive = false;
            bullet.texture.dispose();   
        }
    }

}