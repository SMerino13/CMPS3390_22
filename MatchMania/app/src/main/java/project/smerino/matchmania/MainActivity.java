package project.smerino.matchmania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText txtUserName;
    private Button scoreBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = findViewById(R.id.txtUserName);
        scoreBtn = findViewById(R.id.scoreBtn);

        auth = FirebaseAuth.getInstance();
    }

    public void onPlayClicked(View view){
        // Usernames can only contain characters
        String userName = txtUserName.getText().toString();
        boolean valid = userName.matches("^\\w{3,13}[a-zA-Z]$");
        if(valid){
            Intent intent = new Intent(this, Login.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(txtUserName,
                    "Enter 3-13 Character username with no numbers or special characters",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(txtUserName);
            snackbar.show();
        }
    }

    public void onScoreClicked(View view){
        // User must be log in and play before attempting to view scoreboard
        FirebaseUser user = auth.getCurrentUser();
        if(user != null) {
            Intent intent = new Intent(this, ScoreBoard.class);
            intent.putExtra("userName", "");
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(scoreBtn,
                    "You must play first",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(scoreBtn);
            snackbar.show();
        }
    }
}