package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

public class Shield {

    // Graphics.
    Texture texture;
    Sprite sprite;

    // Data.
    boolean isActive = true;
    int hitpoints = 3;
    float xPosition;
    float yPosition;

    public void create(float xPos, float yPos, ArrayList<Shield> shields) {

        this.xPosition = xPos;
        this.yPosition = yPos;

        this.texture = new Texture(Gdx.files.internal("shield3.png"));
        this.sprite = new Sprite(texture, 48, 32);
        this.sprite.setPosition(xPosition, yPosition);

        shields.add(this);

    }

    public void update() {

        // Active checking.
        if(hitpoints<=0) {
            this.isActive = false;
        }

    }

}