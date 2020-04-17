package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FrogGame;
import com.mygdx.game.Scene.Hud;
import com.mygdx.game.Sprites.Froggy;
import com.mygdx.game.Sprites.Truck;
import com.mygdx.game.Stuff.B2WorldCreator;
import com.mygdx.game.Stuff.WorldContactListener;

public class PlayScreen implements Screen {

    private FrogGame game;
    private TextureAtlas atlas;
    private Music music;



    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //Frog player
    private Froggy player;
    private Truck truck;



    public PlayScreen(FrogGame game){

        atlas = new TextureAtlas("froggyand Truck.pack");


        this.game =game;


        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(FrogGame.V_WITDH / FrogGame.PPM ,FrogGame.V_HEIGHT / FrogGame.PPM, gamecam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("street.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ FrogGame.PPM);

        //set gamecam to be centered correctly at the start of of
        gamecam.position.set(gamePort.getWorldWidth()/2 , gamePort.getWorldHeight()/2 , 0);

        world =new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(this);



        //create Frog in street game
        player = new Froggy(this);

        world.setContactListener(new WorldContactListener());

        music = FrogGame.musicManager.get("audio/music/Netherplace.ogg", Music.class);
        music.setLooping(true);
        music.play();

        truck = new Truck(this, .32f, .32f);


    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        //if(Gdx.input.isTouched())
          //  gamecam.position.x += 100* dt;

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
           player.b2body.applyLinearImpulse(new Vector2(0, 0.1f), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
           player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
           player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.b2body.applyLinearImpulse(new Vector2(0, -0.1f), player.b2body.getWorldCenter(), true);



    }

    public void update(float dt){
        handleInput(dt);
        truck.update(dt);

        //go 1 step in physics simulation (60/1s)
        world.step(1/60f, 6, 2);

        player.update(dt);

        hud.update(dt);

        // gamecam.position.x = player.b2body.getPosition().x;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);



        //clear game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render game map
        renderer.render();

        //renderer Box2DDebuggerLines
        b2dr.render(world, gamecam.combined);


        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        truck.draw(game.batch);
        game.batch.end();

        //set the batch to draw what Hud shows
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);
    }


    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
