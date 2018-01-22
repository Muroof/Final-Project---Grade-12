/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameStates;
// imports ppm variable

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
import main.MainClass;
import main.Player;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
public class Play extends GameState {

    // instance variables
    private World world;
    // renders bodies
    private Box2DDebugRenderer b2dr;
    private Body playerBody;
    private RayHandler handle;

    private OrthographicCamera b2dcamscale;
    private ConeLight flashlight;
    
    // constructor
    public Play(GameStateManager gsm){


    // constructor for Play class
    public Play(GameStateManager gsm) {
        // calls on GameStateManager created in GameStateManager class

        super(gsm);

        // this was an attempt to create a player and be able to "draw" it on screen
        Player p1 = new Player(5, 5);
        p1.create();

        // create a world
        // VECTOR 2 IS GRAVITY (x is gravity left and right), true means body is asleep
        world = new World(new Vector2(0, -1020f), true);
        b2dr = new Box2DDebugRenderer();
       
        
        JointDef ff = new JointDef();
        ff.type = JointType.RevoluteJoint;
        
        
       
        // creating a platform
        BodyDef bdef = new BodyDef();
        bdef.position.set(600/PPM, 200/PPM);
        bdef.type = BodyType.StaticBody;

        // AN EXPLANATION ON THE DIFFERENT TYPES OF BODIES
        // static body - doesn't move, unaffected by forces
        // kinematic body - isn't affected by forces, but velocitys can be set (a moving platform essentially)
        // dynamic body - affected by all forces (possibly a player)
        
        
        // creating a body
        // have to tell world body is created using body definition
        Body body = world.createBody(bdef);
        // create a shape
        PolygonShape shape = new PolygonShape();

        // set the shape to be a box

        shape.setAsBox(80/PPM,10/PPM);
        // define fixtures
        FixtureDef fdef = new FixtureDef();
        // set fixture as box
        fdef.shape = shape;  
        fdef.friction = 0.1f;
        // cretae a fixture on body using fixture defintion
        body.createFixture(fdef);
        
        BodyDef floorBody = new BodyDef();
        floorBody.position.set(-400/PPM, 0/PPM);

        shape.setAsBox(80, 10);
        // define fixtures
        FixtureDef fdef = new FixtureDef();
        // set fixture as box
        fdef.shape = shape;
        // create a fixture on the body using fixture defintion
        body.createFixture(fdef);

        bdef.position.set(-400, 0);

        // make floor FIX SCREEN DIMENSIONS NEED TO OBTAIN FROM VARIABLE
        Body floor = world.createBody(floorBody);
        // create shape
        PolygonShape shapeFloor = new PolygonShape();
        // set the poision of the body

        // set as box
        shapeFloor.setAsBox(2560/PPM, 10/PPM);
        // set shape of fixture
        fdef.shape = shapeFloor;
        fdef.friction = 0.1f;
        floor.createFixture(fdef);

        // creating the wall on the right
        bdef.type = BodyType.StaticBody;
        bdef.position.set(1200/PPM, 1400/PPM);
        Body rightWall = world.createBody(bdef);
        PolygonShape wallRight = new PolygonShape();
        wallRight.setAsBox(10/PPM, 1400/PPM);
        fdef.shape = wallRight;
        rightWall.createFixture(fdef);

        // crete 2 random falling cirlces on each side (Kinematic BODIES)
        // set body poistion

        BodyDef fallCircle = new BodyDef();
        fallCircle.type = BodyType.DynamicBody;
        fallCircle.position.set(1000/PPM, 700/PPM);
        
        Body circle = world.createBody(fallCircle);
        // create sha[e
        
        CircleShape Circle = new CircleShape();
        // set as circle
        Circle.setRadius(2f);
       
        
        
        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = Circle;
circleFixture.density = 1f;
      circleFixture.friction = 0.02f;
   circleFixture.restitution = 0.8f;
        circle.createFixture(circleFixture);
        
        
        
        // create falling box (PLAYER)
        // can reuse body definitions
        bdef.position.set(620/PPM,500/PPM);
        // make dynmic

        bdef.type = BodyType.DynamicBody;
        bdef.position.set(1000, 700);

        Body circle = world.createBody(bdef);

        // create shape
        CircleShape Circle = new CircleShape();
        // set as circle
        Circle.setRadius(100f);
        fdef.restitution = 1f;
        fdef.shape = Circle;

        circle.createFixture(fdef);

        // create falling box (PLAYER)
        // can reuse body definitions
        bdef.position.set(620, 500);
        // make dynamic

        bdef.type = BodyType.DynamicBody;
        playerBody = world.createBody(bdef);
        // make it a box
        shape.setAsBox(15/PPM, 15/PPM);
        // set the defintion to the shape
        fdef.shape = shape;

        fdef.density = 0.8f;
        fdef.friction=0.8f;
        // 1f means bounces to same spot
        b2dcamscale = new OrthographicCamera();
        // MAKE MAIN CLASS V_WIDTH AND HEIGHT LATER!!!!!
        cam.setToOrtho(false, 1280/PPM,720/PPM);
        // create the fixture around the player body
        playerBody.createFixture(fdef);
        // WORK ON IMPLEMRNTING LIGHT IN OTHER CLASSES MORE SPECIFICLLY LINK RAY HANDLER BETWEEN GAME STATE MANAGER, GAME STATE, AND PLAY
         handle = new RayHandler(world);
         handle.setCombinedMatrix(cam.combined);
         // shadows
         handle.setShadows(true);

         ConeLight circleLight = new ConeLight(handle, 100, Color.CORAL, 500, circle.getPosition().x/PPM, circle.getPosition().y/PPM, 0, 45);
         circleLight.setActive(true);
         circleLight.attachToBody(circle);
         
         
         // apply a light to the player
        flashlight = new ConeLight(handle, 100, Color.LIME, 500, playerBody.getPosition().x/PPM, playerBody.getPosition().y/PPM, -136, 45);

        // 1f means the shape will bounce perfectly back to drop point
        fdef.restitution = 0.9f;
        // create the fixture around the player body
        playerBody.createFixture(fdef);
        // WORK ON IMPLEMRNTING LIGHT IN OTHER CLASSES MORE SPECIFICLLY LINK RAY HANDLER BETWEEN GAME STATE MANAGER, GAME STATE, AND PLAY
        handle = new RayHandler(world);
        handle.setCombinedMatrix(cam.combined);
        // shadows
        handle.setShadows(true);
        // apply a light to the player
        ConeLight flashlight = new ConeLight(handle, 100, Color.BLUE, 500, playerBody.getPosition().x, playerBody.getPosition().y, 0, 45);

        flashlight.setActive(true);

        flashlight.attachToBody(playerBody);
    }

    
    public void handleInput(){
        // if right is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            // apply force to centre parametrs (xforce, yforce,
            playerBody.applyForceToCenter(20, 10, true);
                    
        }
        // if left is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            // apply force to centre parametrs (xforce, yforce,
            playerBody.applyForceToCenter(-20, 10, true);
          
            
        }
        // if up is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            // apply force to centre parametrs (xforce, yforce,
            playerBody.applyForceToCenter(0, 100, true);
        }
        
        
        
        
    }
     public void update(float dt){
         handleInput();
// 2nd parameter is accuracy of collision (velocity iteration) (six is good)
         // 3rd parameter accuracy of setting body position after colliosion (2 or 3) (position iteration)
         world.step(dt, 6, 2);
     }
     public void render(){
         // clear screen
         Gdx.gl.glClearColor(0, 0, 0, 1);
     Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
         
          // render light
          handle.updateAndRender();
// draw box 2d world
          b2dr.render(world, cam.combined);
     }
     public void dispose(){
                   

     }


    public void handleInput() {
        // if the 'D' key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // apply force to centre parameters (xforce, yforce)
            playerBody.applyForceToCenter(1000, 0, true);
        }
        // if the 'A' key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // apply force to centre parameters (xforce, yforce)
            playerBody.applyForceToCenter(-1000, 0, true);
        }
        // if the 'W' key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            // apply force to centre parameters (xforce, yforce)
            playerBody.applyForceToCenter(0, 2000, true);
        }

    }

    public void update(float dt) {
        handleInput();
        // 2nd parameter is accuracy of collision (velocity iteration) (six is good)
        // 3rd parameter is the accuracy of setting body position after colliosion (2 or 3) (position iteration)
        world.step(dt, 6, 2);
    }

    public void render() {
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // render light
        handle.updateAndRender();
        // draw box 2d world
        b2dr.render(world, cam.combined);
    }

    public void dispose() {

    }
}
