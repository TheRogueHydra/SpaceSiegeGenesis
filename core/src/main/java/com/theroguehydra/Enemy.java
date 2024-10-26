package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

public class Enemy {

    // Graphics.
    Texture texture;
    Sprite sprite;

    // Data.
    boolean isActive = true;
    double xSpeed;
    double ySpeed;
    float xPosition;
    float yPosition;

    public void create(float xPos, float yPos, ArrayList<Enemy> enemies) {

        this.texture = new Texture(Gdx.files.internal("alien-basic.png"));
        this.sprite = new Sprite(texture, 16,16);

        this.xSpeed = 1;
        this.ySpeed = 0.25;

        this.xPosition = xPos;
        this.yPosition = yPos;

        enemies.add(this);

    }

    public void update(Player player) {

        // Vertical motion.
        this.yPosition -= ySpeed;

        // Horizontal motion.
        this.xPosition += xSpeed;
        if(xPosition == 608 || xPosition == 32) {
            this.xSpeed = -xSpeed;
        }

        // Collision detection.
        if(sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
            this.isActive = false;
            player.hitpoints -= 1;
        }

        this.sprite.setPosition(xPosition, yPosition);

    }

}