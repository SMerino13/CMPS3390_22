package project.smerino.matchmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ScoreBoard extends AppCompatActivity {

    private RecyclerView listScores;
    private Button playAgain;
    private ArrayList<Scores> scores = new ArrayList<>();
    private ArrayList<String> userScores = new ArrayList<>();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String userInfo;
    private String head = "Scoreboard";
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        playAgain = findViewById(R.id.playAgain);

        listScores = findViewById(R.id.listScores);
        listScores.setAdapter(new RecyclerAdapter(this, scores));
        listScores.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        userInfo = intent.getExtras().getString("userName");

        auth = FirebaseAuth.getInstance();

        if(userInfo.length() > 0){
            Scores tmp = new Scores(userInfo);
            addScore(tmp);
        } else {
            // Only show play again button when player has completed the game
            playAgain.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();

        // message to log in to view scoreboard

        // get scores
        getScores();
    }


    public void onPlayAgain(View view){
        String[] infoSplit = userInfo.split(":");
        String userName = infoSplit[0];
        Intent intent = new Intent(this, MatchMania.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //________________________//
    private void addScore(Scores scores){
        try {
            String path = URLEncoder.encode(scores.getDesc(), String.valueOf(StandardCharsets.UTF_8));
            DatabaseReference ref = database.getReference(head + "/" + path);
            ref.setValue(scores);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void getScores(){
        DatabaseReference ref = database.getReference(head);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                scores.clear();
                Iterator<DataSnapshot> itr = snapshot.getChildren().iterator();
                while(itr.hasNext()){
                    HashMap val = (HashMap) itr.next().getValue();
                    Scores tmp = new Scores(val.get("desc").toString());
                    scores.add(tmp);
                }
                //remove any duplicate users
                checkDuplicates();
                // Order the scores
                orderScores();

                listScores.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkDuplicates() {
        String extract, userName, tmpUserName, score1, score2;
        String[] infoSplit;
        ArrayList<Integer> userScores = new ArrayList<>();
        for(int i = 0; i < scores.size() - 1; i++){
            extract = scores.get(i).toString();
            infoSplit = extract.split(":");
            userName = infoSplit[0];
            score1 = extract.replaceAll("\\D+","");
            userScores.add(Integer.parseInt(score1));

            for(int j = i+1; j < scores.size(); j++) {
                extract = scores.get(j).toString();
                infoSplit = extract.split(":");
                tmpUserName = infoSplit[0];
                score2 = extract.replaceAll("\\D+", "");

                if(userName.equals(tmpUserName)){
                    userScores.add(Integer.parseInt(score2));
                    if(userScores.get(0) < userScores.get(1)) {
                        scores.remove(j);
                    } else{
                        scores.remove(i);
                    }
                }
            }
            userScores.clear();
        }
    }

    // Order the scores based on attempts
    private void orderScores() {
        String extract, temp, string1, string2;
        Scores tmpi, tmpj, tmpk;
        for(int i = 0; i < scores.size() - 1; i++){
            for(int j = i+1; j < scores.size(); j++) {
                tmpi = scores.get(i);
                extract = tmpi.toString();
                string1 = extract.replaceAll("\\D+","");

                tmpj = scores.get(j);
                extract = tmpj.toString();
                string2 = extract.replaceAll("\\D+", "");

                if(Integer.parseInt(string1) > Integer.parseInt(string2)){
                    tmpk = scores.get(i);
                    scores.set(i, tmpj);
                    scores.set(j, tmpk);

                }
            }

        }
    }
}