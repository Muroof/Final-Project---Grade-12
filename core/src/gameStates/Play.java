/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameStates;

import main.CharacterSuper;
import box2dLight.ConeLight;
import box2dLight.Light;
import box2dLight.RayHandler;
import static handlers.box2dvairables.WIDTH;
import static handlers.box2dvairables.HEIGHT;
import static handlers.box2dvairables.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import handlers.GameStateManager;
import java.awt.Font;
import main.EnemySub;
import main.MainClass;
import main.MyContactListener;
import main.PlatformAndFloorSub;
import main.PlayerSub;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public class Play extends GameState {

    // instance variables
    // initialize a World called world
    private World world;
    // renders bodies
    private Box2DDebugRenderer b2dr;
    // initialize a body called playerBody
    private Body playerBody;
    // initialize a RayHandler called handle, this is used to manage flashlights
    private RayHandler handle;
    // initialize a ConeLight called flashlight, used for flashlights of course
    private ConeLight flashlight;
    // initialize the enemy's light
    private ConeLight enemyLight;
    private Body squareBody;
    private BodyDef bodyDef;
    private PolygonShape shapes;
    private FixtureDef fixtureDef;
    private PlayerSub player;
    // create a private variable to allow time to be tracked
    private long startTime;
    private SpriteBatch batch;

    /**
     * constructor for Play Play is the class that will be the screen the user
     * always sees
     *
     * @param gsm
     */
    public Play(GameStateManager gsm) {
        // pass in the gsm from the GameState class
        super(gsm);

        // create a world
        // 'Vector2' is GRAVITY, 0 is a force on a objects x and y, -9.8 is the gravity on all objects
        world = new World(new Vector2(0, -9.8f), true);

        // create a b2dr, required to render bodies
        b2dr = new Box2DDebugRenderer();

        // set the orthographic camera's screen size
        cam.setToOrtho(false, WIDTH, HEIGHT);
        // initialize the sprite batch
        batch = new SpriteBatch();
        // define the floor fixtures
        FixtureDef floorFixtureDef = new FixtureDef();
        // define the floor body defintions
        BodyDef floorBodyDef = new BodyDef();
        // set the position of the floor, in a floor like position
        floorBodyDef.position.set(-400 / PPM, 0 / PPM);
        // set the floor's bodyDef to be static, as it will not move
        floorBodyDef.type = BodyType.StaticBody;
        // create a body called floor, pass in the floor's body def
        Body floor = world.createBody(floorBodyDef);

        // create the floors shape
        PolygonShape floorShape = new PolygonShape();
        // set the shape: floorShape to be a box
        floorShape.setAsBox(1820 / PPM, 10 / PPM);

        // set the floor's fixture def's shape as the shape we created: floorShape
        floorFixtureDef.shape = floorShape;
        // set the friction of the floor's fixture def 
        floorFixtureDef.friction = 0.1f;
        // set the bodys fixture as the fixture we created: floorFixtureDef
        floor.createFixture(floorFixtureDef).setUserData("floor");
        // create a player and enemy, and pass in the required parameters
        player = new PlayerSub(50, 500, 15, 15, world, squareBody, bodyDef, shapes, fixtureDef);

        // construction of a single rectangular platform (all of this could have been done through the PlatformAndFloorSub class, we were just experienceing difficulties)
        BodyDef platformDef = new BodyDef();
        platformDef.position.set(700 / PPM, 200 / PPM);
        platformDef.type = BodyDef.BodyType.StaticBody;
        Body platform = world.createBody(platformDef);
        PolygonShape rectPlatform = new PolygonShape();
        rectPlatform.setAsBox(500 / PPM, 25 / PPM);
        // Creating a platform above light 
        FixtureDef platformFix = new FixtureDef();
        // set the rectangle created to represent the fixture
        platformFix.shape = rectPlatform;
        // set the friction to it
        platformFix.friction = 0.1f;
        platformFix.density = 1f;
        // link the fixture to the body
        platform.createFixture(platformFix);

        // create a new ray handler to deal with lights
        handle = new RayHandler(world);
        handle.setCombinedMatrix(cam.combined);
        // dim the background
        handle.setAmbientLight(0.05f);
        // create a light for the player
        flashlight = new ConeLight(handle, 60, Color.TAN, 6, player.getXPosition(), player.getYPosition(), -136, 25);
        // turn on the light
        flashlight.setActive(true);

        // attach the conelight/flashlight to the player
        flashlight.attachToBody(player.getBody());
        // create a light whcih you have to avoid
        enemyLight = new ConeLight(handle, 60, Color.BLUE, 6, 7, 0, 90, 45);
        // turn the light on
        enemyLight.setActive(true);
    }

    /**
     * Constantly updates various tasks such as handling input
     *
     * @param dt
     */
    public void update(float dt) {
        // swithing the light on and off at intervals
        startTime = System.currentTimeMillis() / 1000;
        if (startTime % 2 < 1) {
            // set the light to be off
            enemyLight.setActive(false);
        } else {
            // set the light to be on
            enemyLight.setActive(true);
        }

        // move player
        player.handleMovement();

        // update x and y positions
        player.updateXPosition(player.getBody().getPosition().x);
        player.updateYPosition(player.getBody().getPosition().y);
        // LIGHT DETECTIOM
        player.ifSeenByLight(enemyLight);
        // update the handler
        handle.update();

        // 2nd parameter is accuracy of collision (velocity iteration) (six is good)
        // 3rd parameter accuracy of setting body position after colliosion (2 or 3) (position iteration)
        world.step(dt, 7, 3);
        
    }

    /**
     *
     */
    public void render() {
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        // draw box 2d world
        b2dr.render(world, cam.combined);
        // render light
        handle.updateAndRender();
        
    }

    /**
     *
     */
    public void dispose() {
        // dispose the worlf
        world.dispose();
        // dispose the handler
        handle.dispose();
        
    }
}
