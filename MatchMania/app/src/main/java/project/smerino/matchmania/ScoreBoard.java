package project.smerino.matchmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class ScoreBoard extends AppCompatActivity {

    private RecyclerView listScores;
    private Button playAgain;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> userScores = new ArrayList<>();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String userInfo;
    private String head = "Scoreboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        playAgain = findViewById(R.id.playAgain);

        listScores = findViewById(R.id.listScores);
        listScores.setAdapter(new RecyclerAdapter(this, items));
        listScores.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        userInfo = intent.getExtras().getString("userName");

        if(userInfo.length() > 0){
            Item tmp = new Item(userInfo);
            addScore(tmp);
        } else {
            // Only show play again button when player has completed the game
            playAgain.setVisibility(View.INVISIBLE);
        }

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //________________________//
    private void addScore(Item item){
        try {
            String path = URLEncoder.encode(item.getDesc(), String.valueOf(StandardCharsets.UTF_8));
            DatabaseReference ref = database.getReference(head + "/" + path);
            ref.setValue(item);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void getScores(){
        DatabaseReference ref = database.getReference(head);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                Iterator<DataSnapshot> itr = snapshot.getChildren().iterator();
                while(itr.hasNext()){
                    HashMap val = (HashMap) itr.next().getValue();
                    Item tmp = new Item(val.get("desc").toString());
                    items.add(tmp);
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
        for(int i=0; i < items.size() - 1; i++){
            extract = items.get(i).toString();
            infoSplit = extract.split(":");
            userName = infoSplit[0];
            score1 = extract.replaceAll("\\D+","");
            userScores.add(Integer.parseInt(score1));

            for(int j=i+1; j < items.size(); j++) {
                extract = items.get(j).toString();
                infoSplit = extract.split(":");
                tmpUserName = infoSplit[0];
                score2 = extract.replaceAll("\\D+", "");

                if(userName.equals(tmpUserName)){
                    userScores.add(Integer.parseInt(score2));
                    if(userScores.get(0) < userScores.get(1)) {
                        items.remove(j);
                    } else{
                        items.remove(i);
                    }
                }
            }
            userScores.clear();
        }
    }

    // Order the scores based on attempts
    private void orderScores() {
        String extract, temp, string1, string2;
        Item tmpi, tmpj, tmpk;
        for(int i=0; i < items.size() - 1; i++){
            for(int j=i+1; j < items.size(); j++) {
                tmpi = items.get(i);
                extract = tmpi.toString();
                string1 = extract.replaceAll("\\D+","");

                tmpj = items.get(j);
                extract = tmpj.toString();
                string2 = extract.replaceAll("\\D+", "");

                if(Integer.parseInt(string1) > Integer.parseInt(string2)){
                    tmpk = items.get(i);
                    items.set(i, tmpj);
                    items.set(j, tmpk);

                }
            }

        }
    }
}