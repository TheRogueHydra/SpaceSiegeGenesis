package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

public class Player {

    // Graphics.
    Texture texture;
    Sprite sprite;

    // Data.
    int hitpoints = 4;
    int score = 0;
    int bulletSpeed;
    int speed;
    float xPosition;
    float yPosition;

    public void create() {

        this.xPosition = 312;
        this.yPosition = 36;

        this.bulletSpeed = 5;
        this.speed = 3;

        texture = new Texture(Gdx.files.internal("player-basic.png"));
        sprite = new Sprite(texture, 16, 16);

    }

    public void update(ArrayList<Bullet> bullets) {

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

        // Bullet addition.
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            Bullet bullet = new Bullet();
            bullet.create(this);
            bullets.add(bullet);            
        }

    }

}