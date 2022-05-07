package project.smerino.matchmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = findViewById(R.id.txtUserName);
    }

    public void onPlayClicked(View view){
        String userName = txtUserName.getText().toString();
        if(userName.length() < 13 && userName.length() != 0){
            Intent intent = new Intent(this, MatchMania.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        } else if(userName.length() == 0){
            Snackbar snackbar = Snackbar.make(txtUserName,
                    "Please enter a username",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(txtUserName);
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar.make(txtUserName,
                    "Username can only be up to 7 characters long",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(txtUserName);
            snackbar.show();
        }
    }

    public void onScoreClicked(View view){
        Intent intent = new Intent(this, ScoreBoard.class);
        intent.putExtra("userName", "");
        startActivity(intent);
    }
}