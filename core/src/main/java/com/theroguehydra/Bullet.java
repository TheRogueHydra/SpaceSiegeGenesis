package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {

    // Graphics.
    Texture texture;
    Sprite sprite;

    // Data.
    boolean isActive = true;
    int ySpeed;
    float xPosition;
    float yPosition;

    public void create(Player player) {

        this.ySpeed = player.bulletSpeed;

        this.xPosition = player.xPosition + 6;
        this.yPosition = player.yPosition + 18;

        this.texture = new Texture(Gdx.files.internal("bullet-basic.png"));
        this.sprite = new Sprite(texture, 4, 16);

    }

    public void update() {
        
        this.sprite.setPosition(xPosition, yPosition);
        this.yPosition += this.ySpeed;

    }

}