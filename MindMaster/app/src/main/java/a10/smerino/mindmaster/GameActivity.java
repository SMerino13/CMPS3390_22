package a10.smerino.mindmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

// MainActivty transfer here
public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        //object of gameview
        gameView = new GameView(this, screenSize);
        setContentView(gameView);
    }

    // onPause needs to call GameView pause
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    // OnResume needs to call GameView resume
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}