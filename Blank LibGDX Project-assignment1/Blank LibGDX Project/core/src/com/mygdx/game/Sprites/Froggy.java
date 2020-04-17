package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FrogGame;
import com.mygdx.game.Screen.PlayScreen;

public class Froggy extends Sprite implements InputProcessor{

    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 2;

    Texture walkSheet;      // #4
    SpriteBatch spriteBatch;        // #6
    TextureRegion walkFrames[];

    Animation<TextureRegion> walkAnimation;
    TextureRegion currentFrame;
    int frameIndex;
    float stateTime;

    boolean movingright = false;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        setPosition(screenX,Gdx.graphics.getHeight()- screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public enum State{STANDING, SQUASH};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion Frognormal;


    private Animation<TextureRegion> FroggyStand;
    private Animation<TextureRegion> FroggySquash;
    private float stateTimer;





    public Froggy(PlayScreen screen) {

        //super(screen.getAtlas().findRegion("froggy"));

        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        boolean moving = true;


        walkSheet = new Texture(Gdx.files.internal("froggy.png"));
        spriteBatch = new SpriteBatch();                // #12
        TextureRegion[][] temp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = temp[i][j]; } }
        walkAnimation = new Animation<TextureRegion>(0.33f, walkFrames);
        stateTime = 0.0f;



        defineFroggy();

        Frognormal = new TextureRegion(screen.getAtlas().findRegion("froggy"),0,0,48,48);
        setBounds(0,0,32/ FrogGame.PPM ,32/ FrogGame.PPM);
        setRegion(Frognormal);
        Gdx.input.setInputProcessor(this);
    }

        public void update ( float dt){
            setPosition(b2body.getPosition().x - getWidth() / 2 , b2body.getPosition().y - getHeight() / 3);
            setRegion(getFrame(dt));

        }



        public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch (currentState){
            case STANDING:
                region = walkAnimation.getKeyFrame(stateTimer,true);
                break;
            //case SQUASH:
            default:
                region = walkAnimation.getKeyFrame(stateTimer, true);
                break;
        }
        stateTimer = currentState ==previousState ? stateTimer + dt :0;
        previousState = currentState;
        return region;
        }

        public State getState(){
                return State.STANDING;
        }

        public void defineFroggy() {
            BodyDef bdef = new BodyDef();
            bdef.position.set(400 / FrogGame.PPM, 32 / FrogGame.PPM);
            bdef.type = BodyDef.BodyType.DynamicBody;
            b2body = world.createBody(bdef);

            FixtureDef fdef = new FixtureDef();
//            Rectangle rectCol = new Rectangle(0,0,48,48);

            CircleShape shape = new CircleShape();
            shape.setRadius(7 / FrogGame.PPM);

            fdef.shape = shape;
            b2body.createFixture(fdef);

            EdgeShape head = new EdgeShape();
            head.set(new Vector2(-2/ FrogGame.PPM, 7/ FrogGame.PPM), new Vector2(2/ FrogGame.PPM, 7/ FrogGame.PPM));
            fdef.shape = head;
            fdef.isSensor = true;

            b2body.createFixture(fdef).setUserData("head");
        }



    }
