package main;

import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import handlers.GameStateManager;
import java.awt.event.KeyListener;

public class MainClass extends ApplicationAdapter {

    private OrthographicCamera camera;
    private World world;
    private float width, height;
    private FPSLogger logger;
    private Box2DDebugRenderer renderer;
    private Body circleBody;
    private Body squareBody;
    private RayHandler handler;
    private KeyListener keyboard;

    // STUFF I ADDED FROM TUTORIAL
    public static final float STEP = 1/60f;
    // keep track of how much time has gone by
    private float accum;
    
    
    
    
    
    private SpriteBatch sb;
    private OrthographicCamera cam;
    private OrthographicCamera hudCam;
    private RayHandler rayHandler;
    
    public SpriteBatch getSpriteBatch(){
        return sb;
    }
    
    public OrthographicCamera getCamera(){
        return cam;
       
    }
    
    public OrthographicCamera getHUDCamera(){
        return hudCam;
    }
    
    // get handler
    public RayHandler getRayHandler(){
        return rayHandler;
    }
    
    private GameStateManager gsm;
    @Override
    public void create() {
// STUFF I ADDED
        // initialize stuff
        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        rayHandler = new RayHandler(world);
        // set shadows!!!
        rayHandler.setShadows(true);
        // FIX BY LINKING IT TO A VARIABLE WHICH HAS SCREEN SIZE
        cam.setToOrtho(false, 1280, 720);
        hudCam = new OrthographicCamera();
        // FIX BY LINKING IT TO A VARIABLE WHICH HAS SCREEN SIZE
        hudCam.setToOrtho(false, 1280, 720);
        gsm = new GameStateManager(this);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        width = Gdx.graphics.getWidth() / 5;
//        height = Gdx.graphics.getHeight() / 5;
//
//        camera = new OrthographicCamera(width, height);
//        camera.position.set(width * 0.5f, height * 0.5f, 0);
//        camera.update();
//
//        world = new World(new Vector2(0, -50f), false);
//
//        renderer = new Box2DDebugRenderer();
//
//        logger = new FPSLogger();
//
//        //DYNAMIC CIRCLE BODY
//        BodyDef circleDef = new BodyDef();
//        circleDef.type = BodyType.DynamicBody;
//        circleDef.position.set(63, 90);
//
//        circleBody = world.createBody(circleDef);
//
//        CircleShape circleShape = new CircleShape();
//        circleShape.setRadius(5f);
//
//        FixtureDef circleFixture = new FixtureDef();
//        circleFixture.shape = circleShape;
//        circleFixture.density = 2f;
//        circleFixture.friction = 0.02f;
//        circleFixture.restitution = 0.8f;
//
//        circleBody.createFixture(circleFixture);
//
//        //DYNAMIC SQUARE BODY
//        BodyDef squareBodyDef = new BodyDef();
//        squareBodyDef.type = BodyDef.BodyType.DynamicBody;
//
//        //set position of square
//        squareBodyDef.position.set(40, 50);
//
//        //create square body and add it to world
//        squareBody = world.createBody(squareBodyDef);
//
//        //create the sqaure shape
//        PolygonShape squareBox = new PolygonShape();
//        squareBox.setAsBox(20, 20);
//
//        //create a fixture from the shape and add it to the body
//        FixtureDef squareFixture = new FixtureDef();
//        
//        
//        squareBody.createFixture(squareBox, 2.0f);
//
//
//
//
//        //STATIC BODY A.K.A. THE GROUND
//        BodyDef groundBodyDef = new BodyDef();
//        groundBodyDef.position.set(0, 3);
//
//        Body groundBody = world.createBody(groundBodyDef);
//
//        PolygonShape groundBox = new PolygonShape();
//        groundBox.setAsBox(camera.viewportWidth * 2, 3.0f);
//
//        groundBody.createFixture(groundBox, 0.0f);
//
//        //CONE LIGHTS
//        handler = new RayHandler(world);
//
//        handler.setCombinedMatrix(camera.combined);
//        //handler.setAmbientLight(Color.PURPLE);
//        handler.setShadows(true);
//
//        ConeLight cone = new ConeLight(handler, 100, Color.RED, 100, 10, 10, 0, 45);
//        cone.setActive(true);
//
//        ConeLight flashlight = new ConeLight(handler, 100, Color.YELLOW, 100, circleBody.getPosition().x, circleBody.getPosition().y, 0, 45);
//        flashlight.setActive(true);
//        flashlight.attachToBody(circleBody);
//
//        handler.setAmbientLight(Color.BLACK);


    }

  
    void update(float dt){
        
        
    }
    
    @Override
    public void render() {
//        
//        
//        
//        
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
//
//        renderer.render(world, camera.combined);
//        handler.updateAndRender();
//
//        world.step(1 / 60f, 6, 2);
//
//        logger.log();
// STUFF O ADDED
        // accumulate time gone by
        accum += Gdx.graphics.getDeltaTime();
        // only render if enough time has passedd with a step
        while (accum>= STEP) {
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }
        
    }

    @Override
    public void dispose() {
        world.dispose();
        handler.dispose();
        circleBody.destroyFixture(null);
    }
}
