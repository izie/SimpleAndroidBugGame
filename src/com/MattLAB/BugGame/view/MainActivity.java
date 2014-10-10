package com.MattLAB.BugGame.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.MattLAB.AndroidBugGame.R;
import com.MattLAB.BugGame.controller.BugManager;
import com.MattLAB.BugGame.controller.MeManager;
import com.MattLAB.BugGame.model.CPoint;
import android.view.SurfaceHolder.Callback;

public class MainActivity extends Activity {
    public static final int GAME_STATE_NORMAL =  0;
    public static final int GAME_STATE_WIN =  1;
    public static final int GAME_STATE_LOSE =  2;

    private BugManager bugManager;
    private MeManager meManager;
    private GameView gv;

    private final int game_speed = 1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Create Super View
        setContentView(R.layout.main);

        // 2. Create Game View
        gv = new GameView(this);

        // 3. Add Game View into FrameLayout which is located under mainView
        FrameLayout fl = (FrameLayout)findViewById(R.id.layout);
        fl.addView(gv,1);

        LinearLayout ln = (LinearLayout)findViewById(R.id.gameFrame);
        ln.bringToFront(); // Bring to front the control buttons

        // Button Event Handler

        // 1. Left Move Button
        Button lButton = (Button)findViewById(R.id.button);
        lButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    meManager.rotate(meManager.getMe().getArc() - 5);
                    return true;
                }

                return false;
            }
        });

        // 2. Right Move Button
        Button rButton = (Button)findViewById(R.id.button3);
        rButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    meManager.rotate(meManager.getMe().getArc() + 5);
                    return true;
                }

                return false;
            }
        });

        // 3. Shoot Button
        Button cButton = (Button)findViewById(R.id.button2);
        cButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    meManager.addBall();
                    return true;
                }

                return false;
            }
        });

        // 4. Game Reset Button
        Button rsButton = (Button)findViewById(R.id.button4);
        rsButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    meManager.restart();
                    meManager.clear();
                    bugManager.clearBugs(5 * meManager.getMe().getLevel(),
                            7 * meManager.getMe().getLevel(),
                            10 * meManager.getMe().getLevel(),
                            50, 70, 90);

                    return true;
                }

                return false;
            }
        });
    }

    // GameView extends SurfaceView for double buffering
    class GameView extends SurfaceView implements Callback {
        private GameThread gameThread;
        private SurfaceHolder holder;
        private Point deviceSize = new Point();
        private Paint paint = new Paint();
        private Context context;

        // Constructor
        public GameView(Context context) {
            super(context);
            this.context = context;
            //this.setWillNotDraw(false);
            holder = getHolder();
            holder.addCallback(this);
            // start the animation:

            // Get Device's Dimension
            Display display = getWindowManager().getDefaultDisplay();
            
            display.getSize(deviceSize);
            deviceSize.y *=0.8;

            // Set Default Manager
            bugManager = new BugManager();
            meManager = new MeManager();
            bugManager.setDefaultBugs(
                    5 * meManager.getMe().getLevel(),
                    7 * meManager.getMe().getLevel(),
                    10 * meManager.getMe().getLevel(),
                    50, 70, 90,
                    new CPoint(deviceSize.x/2, deviceSize.y/2),
                    (float)(deviceSize.x*0.2)
            );
            meManager.setDefault(new CPoint(deviceSize.x / 2, deviceSize.y / 2), (float)(deviceSize.x*0.2));
        }
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        public void surfaceCreated(SurfaceHolder holder) {
            gameThread = new GameThread(holder, this);
            gameThread.setFlag(true);
            gameThread.start();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);
            // Clear Background Color
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            // Check game's status
            if(meManager.getMe().getWin_or_lose() == GAME_STATE_NORMAL) {
                update();
            }else{
                meManager.getMe().setMsg("Game Over!!");
            }
            // Draw All Resources
            draw(canvas);

            // Check bug's status
            if(bugManager.isAllClear()){
                // if all bugs are killed, level will be up
                meManager.getMe().upLevel();
                bugManager.clearBugs(5 * meManager.getMe().getLevel(),
                        7 * meManager.getMe().getLevel(),
                        10 * meManager.getMe().getLevel(),
                        50, 70, 90);
                meManager.clear();
            }

        }

        public void draw(Canvas canvas) {

            // Draw
            bugManager.drawAll(canvas);
            meManager.drawAll(canvas);
        }

        public void update() {
            bugManager.update(meManager, game_speed + meManager.getMe().getLevel());
            meManager.update();
        }


        // Using thread for stablization
        class GameThread extends Thread {
            boolean flag ;
            SurfaceHolder myHolder;
            GameView gameView;
            public GameThread(SurfaceHolder holder , GameView drawMain) {
                myHolder = holder;
                gameView = drawMain;
            }

            public void setFlag (boolean myFlag) {
                flag = myFlag;
            }
            public void run(){
                Canvas canvas = null;
                while(flag) {
                    try {
                        canvas = myHolder.lockCanvas(null);
                        gameView.onDraw(canvas);
                    }
                    finally{
                        if(canvas != null){
                            myHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
    }
}
