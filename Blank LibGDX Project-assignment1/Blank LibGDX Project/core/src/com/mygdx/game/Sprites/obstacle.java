package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screen.PlayScreen;

import java.awt.Rectangle;

public abstract class obstacle extends Sprite {

    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public obstacle(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineTruck();

//        super(screen,bounds);
//        BodyDef bdef = new BodyDef();
//        FixtureDef  fdef= new FixtureDef();
//        PolygonShape shape = new PolygonShape();
    }

    protected abstract void defineTruck();


}
