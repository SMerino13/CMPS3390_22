package project.smerino.matchmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> userScores = new ArrayList<>();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String score;
    private String head = "Scoreboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        listScores = findViewById(R.id.listScores);
        listScores.setAdapter(new RecyclerAdapter(this, items));
        listScores.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        score = intent.getExtras().getString("userName");

        if(score.length() > 0){
            Item tmp = new Item(score);
            addScore(tmp);
        }

        getScores();


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
                // Order the scores
                orderScores();

                listScores.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Order the scores based on attempts
    // If you are seeing this, I apologies this could've been done alot better
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