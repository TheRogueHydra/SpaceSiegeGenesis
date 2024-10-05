package com.theroguehydra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Player {

    int X_POSITION;
    int Y_POSITION;
    int SPEED;

    Texture texture = new Texture("player-basic.png");

    public void create() {
        this.SPEED = 3;
        this.X_POSITION = 304;
        this.Y_POSITION = 8;
    }

    public void update(ArrayList<Bullet> bullets) {
        if(Gdx.input.isKeyPressed(Keys.LEFT) && X_POSITION>8) {
            this.move(-SPEED, 0);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT) && X_POSITION<616) {
            this.move(SPEED, 0);
        }
        if(Gdx.input.isKeyPressed(Keys.UP) && Y_POSITION<456) {
            this.move(0, SPEED);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN) && Y_POSITION>8) {
            this.move(0, -SPEED);
        }
        if(Gdx.input.isKeyJustPressed(Keys.Z)) {
            Bullet bullet = new Bullet();
            bullet.create(this);
            bullets.add(bullet);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, X_POSITION, Y_POSITION);
    }

    public void move(int xSpeed, int ySpeed) {
        this.X_POSITION += xSpeed;
        this.Y_POSITION += ySpeed;
    }

}