package com.mygdx.game.Stuff;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.FrogGame;
import com.mygdx.game.Screen.PlayScreen;

public class B2WorldCreator{
    public B2WorldCreator(PlayScreen screen){

        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        // create Body and fixture Variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

//        for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle truckrect = ((RectangleMapObject) object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((truckrect.getX() + truckrect.getWidth() / 2)/ FrogGame.PPM, (truckrect.getY() + truckrect.getWidth() / 2 )/ FrogGame.PPM);
//            body = world.createBody(bdef);
//            shape.setAsBox(truckrect.getWidth()/ 2 /FrogGame.PPM, truckrect.getHeight()/ 2 / FrogGame.PPM);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }



    }
}
