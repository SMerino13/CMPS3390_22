package project.smerino.matchmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView userEmail, userPassword;
    private Button loginBtn;
    private String userName;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = (TextView) findViewById(R.id.userEmail);
        userPassword = (TextView) findViewById(R.id.userPassword);
        loginBtn = findViewById(R.id.loginBtn);

        // Get instance
        auth = FirebaseAuth.getInstance();

        // Retrieve userName from Main Activity so it can be passed to Match Mania Activity
        Intent intent = getIntent();
        userName = intent.getExtras().getString("userName");

        checkLoginStatus();

    }

    // Check if user is logged in already
    public void checkLoginStatus(){
        FirebaseUser user = auth.getCurrentUser();
        // if user is already logged in, go to Match Mania Activity
        if(user != null){
            Intent intent = new Intent(Login.this, MatchMania.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        }
    }

    public void onClickedLogin(View view){
        // Retrieve credentials
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        // Check if fields are empty
        if (TextUtils.isEmpty(email)){
            userEmail.setError("Enter an email");
            userEmail.requestFocus();
        } else if(TextUtils.isEmpty(password)){
            userPassword.setError("Enter a password");
            userPassword.requestFocus();
        } else {
            // Attempt to login
            auth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "User registered successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, MatchMania.class);
                        intent.putExtra("userName", userName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}