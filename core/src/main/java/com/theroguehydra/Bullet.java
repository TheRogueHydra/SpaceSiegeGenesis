package com.theroguehydra;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    int X_POSITION;
    int Y_POSITION;
    int SPEED = 12;

    Texture texture = new Texture("bullet-basic.png");

    public void create(Player player) {
        this.X_POSITION = player.X_POSITION + 4;
        this.Y_POSITION = player.Y_POSITION + 16;
    }

    public void update() {
        this.Y_POSITION += SPEED;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, X_POSITION, Y_POSITION);
    }

}
