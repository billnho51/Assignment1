package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.FrogGame;
import com.mygdx.game.Screen.PlayScreen;

public class Truck extends obstacle{


    Texture truckTexture;
    Sprite truckSprite;
    Vector2 truckDelta;
    Rectangle truckDeltaRectangle;
    TextureRegion TruckNor;


    public Truck(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        truckTexture = new Texture("car.png");
        TruckNor = new TextureRegion(truckTexture,0,0, 100,100);
        setBounds(0, 0, 150/ FrogGame.PPM, 150/FrogGame.PPM);
        setRegion(TruckNor);

    }

    public void update(float dt){
        setPosition((float) (b2body.getPosition().x - getWidth() /2.15), (float) (b2body.getPosition().y -getHeight() / 1.25));


    }

    @Override
    protected void defineTruck() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(80 / FrogGame.PPM, 100 / FrogGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();


        CircleShape shape = new CircleShape();
        shape.setRadius(35 / FrogGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
