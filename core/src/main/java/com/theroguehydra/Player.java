package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {

    // Graphics.
    Texture texture;
    Sprite sprite;

    // Data.
    int speed = 3;
    float xPosition;
    float yPosition;

    public void create() {

        this.xPosition = 304;
        this.yPosition = 20;

        texture = new Texture(Gdx.files.internal("player-basic.png"));
        sprite = new Sprite(texture, 16, 16);

    }

    public void update() {

        this.sprite.setPosition(xPosition, yPosition);

    }

}