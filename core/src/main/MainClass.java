package main;

/**
 * @author Beshoy
 * @author Kiran
 * @author Maloof
 */
import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
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
import static handlers.box2dvairables.WIDTH;
import static handlers.box2dvairables.HEIGHT;
import java.awt.event.KeyListener;

public class MainClass extends ApplicationAdapter {
    // initialize private variables
    private OrthographicCamera camera;
    private World world;
    private float width, height;
    private Box2DDebugRenderer renderer;
 
    private KeyListener keyboard;

    // STUFF I ADDED FROM TUTORIAL
    public static final float STEP = 1 / 60f;
    // keep track of how much time has gone by
    private float accum;

    private SpriteBatch sb;
    private OrthographicCamera cam;
    private OrthographicCamera hudCam;
    private GameStateManager gsm;

    /**
     *
     */
    @Override
    public void create() {
        // initialize spritebatch
        sb = new SpriteBatch();
        // create a new camera
        cam = new OrthographicCamera();
        // set the camera to the width and height of screen
        cam.setToOrtho(false, WIDTH, HEIGHT);    
       // create a new game state manager
        gsm = new GameStateManager(this);

    }

    /**
     *
     */
    @Override
    public void render() {
        // accumulate time gone by
        accum += Gdx.graphics.getDeltaTime();
        // only render if enough time has passedd with a step
        while (accum >= STEP) {
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }

    }

    /**
     *
     * @return the spritebatch
     */
    public SpriteBatch getSpriteBatch() {
        return sb;
    }

    /**
     *
     * @return the orothographic camera
     */
    public OrthographicCamera getCamera() {
        return cam;

    }

   

}
