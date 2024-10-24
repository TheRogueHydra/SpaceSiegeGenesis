package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {

    // Graphics.
    Texture texture;
    Sprite sprite;

    // Data.
    int hitpoints = 4;
    int speed = 3;
    float xPosition;
    float yPosition;

    public void create() {

        this.xPosition = 312;
        this.yPosition = 36;

        texture = new Texture(Gdx.files.internal("player-basic.png"));
        sprite = new Sprite(texture, 16, 16);

    }

    public void update() {

        this.sprite.setPosition(xPosition, yPosition);

        // Input handling.
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && xPosition >= 16) {
            this.xPosition -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && xPosition <= 608) {
            this.xPosition += speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && yPosition <= 144) {
            this.yPosition += speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && yPosition >= 16) {
            this.yPosition -= speed;
        }

    }

}