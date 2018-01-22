/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameStates;
// imports ppm variable
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
    
    private World world;
    // renders bofies
    private Box2DDebugRenderer b2dr;
    private Body playerBody;
    private RayHandler handle;
    private ConeLight flashlight;
    
    
    // my shii
    private Body squareBody;
    private BodyDef bodyDef;
    private PolygonShape shapes;
    private FixtureDef fixtureDef;
    private PlayerSub player;
private EnemySub enemy;
    
    // constructor
    public Play(GameStateManager gsm){
        super(gsm);
        // DEFINING BODY
        // VECTOR 2 IS GRAVITY (x is gravity ;eft and right), true means body is asleep
        world = new World(new Vector2(0,-1020f), true);
        b2dr = new Box2DDebugRenderer();
        // MAKE MAIN CLASS V_WIDTH AND HEIGHT LATER!!!!!
       cam.setToOrtho(false, 1280/PPM,720/PPM);
        
        
        
        
        
        
        JointDef ff = new JointDef();
        ff.type = JointType.RevoluteJoint;
        
        
        
        
        
        // creating a platform
        BodyDef bdef = new BodyDef();
        bdef.position.set(600/PPM, 200/PPM);
        bdef.type = BodyType.StaticBody;
   
        // static body - doesnt move, unaffected by forces
        // kinematic body (moving platform!!!)- dont get affected by forces, but velocitys can bes set
// dynamic body - always affected by force
         // creating body
        // have to tell world body is created using body definition
        Body body = world.createBody(bdef);
        // create shape
        PolygonShape shape= new PolygonShape();
        
        
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
        
        
        // wall on the right
        bdef.type = BodyType.StaticBody;
        bdef.position.set(1200/PPM, 1400/PPM);
        Body rightWall = world.createBody(bdef);
        PolygonShape wallRight = new PolygonShape();
        wallRight.setAsBox(10/PPM, 1400/PPM);
        fdef.shape = wallRight;
        rightWall.createFixture(fdef);
        
        
        
        
        
        
        
        
        
        // crete 2 random fallin cirlces on each side (Kinematic BODIES)
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
         player = new PlayerSub(620,500, 15,15,world, squareBody, bodyDef, shapes,  fixtureDef);
enemy = new EnemySub(200,500, 15,15,world, squareBody, bodyDef, shapes,  fixtureDef);
  
//        

//        // WORK ON IMPLEMRNTING LIGHT IN OTHER CLASSES MORE SPECIFICLLY LINK RAY HANDLER BETWEEN GAME STATE MANAGER, GAME STATE, AND PLAY
     handle = new RayHandler(world);
       handle.setCombinedMatrix(cam.combined);
//         // shadows
       handle.setShadows(true);

         ConeLight circleLight = new ConeLight(handle, 100, Color.CORAL, 500, circle.getPosition().x/PPM, circle.getPosition().y/PPM, 0, 45);
         circleLight.setActive(true);
         circleLight.attachToBody(circle);
         
         
         // apply a light to the player
        flashlight = new ConeLight(handle, 100, Color.LIME, 500, player.getXPosition(), player.getYPosition(), -136, 45);
        flashlight.setActive(true);
        
  flashlight.attachToBody(player.getBody());
        
  
    }
    
    
    
    @Override
     public void update(float dt){
         // LIGHT DETECTION
         player.handleMovement();
         enemy.handleMovement();
         //update x posiitons
         player.updateXPosition(player.getBody().getPosition().x);
                  player.updateYPosition(player.getBody().getPosition().y);
         enemy.updateXPosition(enemy.getBody().getPosition().x);
         enemy.updateYPosition(enemy.getBody().getPosition().y);

// 2nd parameter is accuracy of collision (velocity iteration) (six is good)
         // 3rd parameter accuracy of setting body position after colliosion (2 or 3) (position iteration)
         world.step(dt, 7, 3);
         System.out.println(player.getXPosition());
     }
     
    @Override
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
        world.dispose();

     }
}
