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
}