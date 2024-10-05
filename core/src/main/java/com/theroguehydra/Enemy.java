package com.theroguehydra;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    
    int X_POSITION;
    int Y_POSITION;
    int SPEED = 3;

    Texture texture = new Texture("alien-basic.png");

    public void create() {
        this.X_POSITION = 312;
        this.Y_POSITION = 450;
    }

    public void update() {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, X_POSITION, Y_POSITION);
    }

}