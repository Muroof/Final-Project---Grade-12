/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameStates;

import main.CharacterSuper;
import box2dLight.ConeLight;
import box2dLight.RayHandler;
import static handlers.box2dvairables.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import handlers.GameStateManager;
import main.EnemySub;
import main.MainClass;
import main.PlayerSub;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public class Play extends GameState {

    // initialize a World called world
    private World world;

    // initialize integers for the aspect ratio of the game
    private int camWidth = 1280;
    private int camHeight = 720;

    // renders bodies
    private Box2DDebugRenderer b2dr;
    // initialize a body called playerBody
    private Body playerBody;
    // initialize a RayHandler called handle, this is used to manage flashlights
    private RayHandler handle;
    // initialize a ConeLight called flashlight, used for flashlights of course
    private ConeLight flashlight;

    private Body squareBody;
    private BodyDef bodyDef;
    private PolygonShape shapes;
    private FixtureDef fixtureDef;
    private PlayerSub player;
    private EnemySub enemy;

    /**
     * constructor for Play
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
        cam.setToOrtho(false, camWidth / PPM, camHeight / PPM);

        // define the floor fixtures
        FixtureDef floorFixtureDef = new FixtureDef();
        // define the floor body defintions
        BodyDef floorBodyDef = new BodyDef();
        // set the position of the floor, in a floor like position
        floorBodyDef.position.set(-400 / PPM, 0 / PPM);
        // set the floor's bodyDef to be static, as it will not move
        floorBodyDef.type = BodyType.StaticBody;

        // make floor FIX SCREEN DIMENSIONS NEED TO OBTAIN FROM VARIABLE
        // I'm not sure what this ^ means? - Maloof
        // create a body called floor, pass in the floor's body def
        Body floor = world.createBody(floorBodyDef);

        // create the floors shape
        PolygonShape floorShape = new PolygonShape();
        // set the shape: floorShape to be a box
        floorShape.setAsBox(1600 / PPM, 10 / PPM);

        // set the floor's fixture def's shape as the shape we created: floorShape
        floorFixtureDef.shape = floorShape;
        // set the friction of the floor's fixture def 
        floorFixtureDef.friction = 0.1f;
        // set the bodys fixture as the fixture we created: floorFixtureDef
        floor.createFixture(floorFixtureDef);

        // create a player and enemy, and pass in the required parameters
        player = new PlayerSub(620, 500, 15, 15, world, squareBody, bodyDef, shapes, fixtureDef);
        enemy = new EnemySub(200, 500, 15, 15, world, squareBody, bodyDef, shapes, fixtureDef);

        // WORK ON IMPLEMRNTING LIGHT IN OTHER CLASSES MORE SPECIFICLLY LINK RAY HANDLER BETWEEN GAME STATE MANAGER, GAME STATE, AND PLAY
        handle = new RayHandler(world);
        handle.setCombinedMatrix(cam.combined);

        // these were used to manage shadows with the circle
        // ConeLight circleLight = new ConeLight(handle, 100, Color.CORAL, 500, circle.getPosition().x / PPM, circle.getPosition().y / PPM, 0, 45);
        // circleLight.setActive(true);
        // circleLight.attachToBody(circle);
        // create a conelight and turn it "on"
        flashlight = new ConeLight(handle, 100, Color.LIME, 500, player.getXPosition(), player.getYPosition(), -136, 45);
        flashlight.setActive(true);

        // attach the conelight/flashlight to the player
        flashlight.attachToBody(player.getBody());

    }

    /**
     *
     * @param dt
     */
    @Override
    public void update(float dt) {
        // LIGHT DETECTION
        player.handleMovement();
        enemy.handleMovement();
        // update x positions
        player.updateXPosition(player.getBody().getPosition().x);
        player.updateYPosition(player.getBody().getPosition().y);
        enemy.updateXPosition(enemy.getBody().getPosition().x);
        enemy.updateYPosition(enemy.getBody().getPosition().y);

        // 2nd parameter is accuracy of collision (velocity iteration) (six is good)
        // 3rd parameter accuracy of setting body position after colliosion (2 or 3) (position iteration)
        world.step(dt, 7, 3);
        System.out.println(player.getXPosition());
    }

    /**
     *
     */
    @Override
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
        world.dispose();
        handle.dispose();

    }
}
