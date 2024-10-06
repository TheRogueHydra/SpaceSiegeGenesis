package com.theroguehydra;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    
    int X_POSITION;
    int Y_POSITION;
    int SPEED = 1;

    boolean isActive;

    Texture texture = new Texture("alien-basic.png");

    public void create() {

        this.X_POSITION = 312;
        this.Y_POSITION = 450;
        this.isActive = true;

    }

    public void update(Player player) {

        this.Y_POSITION -= SPEED;   
        checkPlayerCollisions(player);

    }

    public void draw(SpriteBatch batch) {
        if(isActive) {
            batch.draw(texture, X_POSITION, Y_POSITION);
        }
    }

    public void checkPlayerCollisions(Player player) {

        if(player.X_POSITION <= X_POSITION + 16 && player.X_POSITION + 16 >= X_POSITION) {
            if(player.Y_POSITION <= Y_POSITION + 16 && player.Y_POSITION + 16 >= Y_POSITION) {
                this.isActive = false;
            }
        }

    }

    public void checkBulletCollisions(Bullet bullet, Player player) {

        if(bullet.X_POSITION <= X_POSITION + 16 && bullet.X_POSITION + 4 >= X_POSITION) {
            if(bullet.Y_POSITION <= Y_POSITION + 16 && bullet.Y_POSITION + 8 >= Y_POSITION) {
                this.isActive = false;
                bullet.texture.dispose();
                player.SCORE += 1;
            }
        }

    }

}