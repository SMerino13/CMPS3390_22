package a10.smerino.mindmaster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{
    private final int FPS = 1000/30; //30 fps
    private Thread thread;
    private boolean isPlaying;
    private Board board;

    public GameView(Context context, Point screenSize) {
        super(context);
        board = new Board(screenSize, getResources(), (GameActivity)context);
    }

    @Override
    public void run() {
        while(isPlaying) {
            // Game runs in a constant loop
            update();  // always updating variables
            draw();    // draws new frame
            sleep();  // sleeps for a time until it repeats
        }
    }

    private void update(){

    }

    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.BLACK);
            board.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void resume(){
        isPlaying = true;
        thread = new Thread(this); //thread takes this class since it implements runnable
        thread.start();

    }

    public void sleep(){
        try {
            Thread.sleep(FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            int x = Math.round(event.getX());
            int y = Math.round(event.getY());
            board.onCLick(x,y);
        }
        return true;
    }

}
