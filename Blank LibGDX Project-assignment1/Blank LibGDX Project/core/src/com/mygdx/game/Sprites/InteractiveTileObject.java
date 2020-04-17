package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screen.PlayScreen;

import java.awt.Rectangle;

public abstract class InteractiveTileObject {
    private World world;
    private TiledMap map;
    private Rectangle bounds;
    private TiledMapTile tile;
    private Body body;

    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen screen, Rectangle bounds ){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds= bounds;


    }
}
