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
        
        if(player.X_POSITION <= X_POSITION + 16 && player.X_POSITION + 16 >= X_POSITION) {
            if(player.Y_POSITION <= Y_POSITION + 16 && player.Y_POSITION + 16 >= Y_POSITION) {
                this.isActive = false;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        if(isActive) {
            batch.draw(texture, X_POSITION, Y_POSITION);
        }
    }

}