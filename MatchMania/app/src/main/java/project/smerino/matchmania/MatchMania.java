package project.smerino.matchmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;


public class MatchMania extends AppCompatActivity {

    private ImageView[] IMGS = new ImageView[10];
    private String userName;

    TextView score, passedUser;

    // card array
    Integer[] cards = {11, 12, 13, 14, 15, 21, 22, 23, 24, 25};
    //images
    private int img11, img12, img13, img14, img15, img21, img22, img23, img24, img25;

    private int firstCard, secondCard;
    private int firstClick, secondClick;
    private int cardNum = 1;
    private int currScore = 0;
    private int attempts = 10;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userName = intent.getExtras().getString("userName");

        setContentView(R.layout.activity_match_mania);

        score = (TextView) findViewById(R.id.score);

        passedUser = (TextView) findViewById(R.id.passedUser);
        passedUser.setText("Current Player: " + userName);

        IMGS[0] = (ImageView) findViewById(R.id.back1);
        IMGS[1] = (ImageView) findViewById(R.id.back2);
        IMGS[2] = (ImageView) findViewById(R.id.back3);
        IMGS[3] = (ImageView) findViewById(R.id.back4);
        IMGS[4] = (ImageView) findViewById(R.id.back5);
        IMGS[5] = (ImageView) findViewById(R.id.back6);
        IMGS[6] = (ImageView) findViewById(R.id.back7);
        IMGS[7] = (ImageView) findViewById(R.id.back8);
        IMGS[8] = (ImageView) findViewById(R.id.back9);
        IMGS[9] = (ImageView) findViewById(R.id.back10);

        for(int i = 0; i < 10; i++) {
            IMGS[i].setTag(String.valueOf(i));
        }

        // Load cards
        frontCards();

        Collections.shuffle(Arrays.asList(cards));

        IMGS[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[0], imgNum);
                checkImage(IMGS[0], imgNum);
            }
        });

        IMGS[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[1], imgNum);
                checkImage(IMGS[1], imgNum);
            }
        });

        IMGS[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[2], imgNum);
                checkImage(IMGS[2], imgNum);
            }
        });

        IMGS[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[3], imgNum);
                checkImage(IMGS[3], imgNum);
            }
        });

        IMGS[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[4], imgNum);
                checkImage(IMGS[4], imgNum);
            }
        });

        IMGS[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[5], imgNum);
                checkImage(IMGS[5], imgNum);
            }
        });

        IMGS[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[6], imgNum);
                checkImage(IMGS[6], imgNum);
            }
        });

        IMGS[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[7], imgNum);
                checkImage(IMGS[7], imgNum);
            }
        });

        IMGS[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[8], imgNum);
                checkImage(IMGS[8], imgNum);
            }
        });

        IMGS[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgNum = Integer.valueOf((String) v.getTag());
                setFrontCard(IMGS[9], imgNum);
                checkImage(IMGS[9], imgNum);
            }
        });

    }

    private void setFrontCard(ImageView view, int card){
        if(cards[card] == 11){
            view.setImageResource(img11);
        } else if(cards[card] == 12){
            view.setImageResource(img12);
        } else if(cards[card] == 13){
            view.setImageResource(img13);
        } else if(cards[card] == 14){
            view.setImageResource(img14);
        } else if(cards[card] == 15){
            view.setImageResource(img15);
        } else if(cards[card] == 21){
            view.setImageResource(img21);
        } else if(cards[card] == 22){
            view.setImageResource(img22);
        } else if(cards[card] == 23){
            view.setImageResource(img23);
        } else if(cards[card] == 24){
            view.setImageResource(img24);
        } else if(cards[card] == 25){
            view.setImageResource(img25);
        }

    }

    private void checkImage(ImageView view, int card){
        if(cardNum == 1){
            firstCard = cards[card];
            if(firstCard > 20){
                firstCard = firstCard - 10;
            }

            cardNum += 1;
            firstClick = card;
            view.setEnabled(false);
        } else if (cardNum == 2){
            secondCard = cards[card];
            attempts--;
            score.setText("Attempts remaining: " + attempts + "  ");
            if(secondCard > 20){
                secondCard = secondCard - 10;
            }
            cardNum--;
            secondClick = card;

            //Disabled while checking
            for(int i = 0; i < 10; i++) {
                IMGS[i].setEnabled(false);
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1200);
        }
    }

    private void calculate(){
        if(firstCard == secondCard){
            if(firstClick == 0){
                IMGS[0].setVisibility(View.INVISIBLE);
            } else if(firstClick == 1){
                IMGS[1].setVisibility(View.INVISIBLE);
            } else if(firstClick == 2){
                IMGS[2].setVisibility(View.INVISIBLE);
            } else if(firstClick == 3){
                IMGS[3].setVisibility(View.INVISIBLE);
            } else if(firstClick == 4){
                IMGS[4].setVisibility(View.INVISIBLE);
            } else if(firstClick == 5){
                IMGS[5].setVisibility(View.INVISIBLE);
            } else if(firstClick == 6){
                IMGS[6].setVisibility(View.INVISIBLE);
            } else if(firstClick == 7){
                IMGS[7].setVisibility(View.INVISIBLE);
            } else if(firstClick == 8){
                IMGS[8].setVisibility(View.INVISIBLE);
            } else if(firstClick == 9){
                IMGS[9].setVisibility(View.INVISIBLE);
            }

            if(secondClick == 0){
                currScore++;
                IMGS[0].setVisibility(View.INVISIBLE);
            } else if(secondClick == 1){
                currScore++;
                IMGS[1].setVisibility(View.INVISIBLE);
            } else if(secondClick == 2){
                currScore++;
                IMGS[2].setVisibility(View.INVISIBLE);
            } else if(secondClick == 3){
                currScore++;
                IMGS[3].setVisibility(View.INVISIBLE);
            } else if(secondClick == 4){
                currScore++;
                IMGS[4].setVisibility(View.INVISIBLE);
            } else if(secondClick == 5){
                currScore++;
                IMGS[5].setVisibility(View.INVISIBLE);
            } else if(secondClick == 6){
                currScore++;
                IMGS[6].setVisibility(View.INVISIBLE);
            } else if(secondClick == 7){
                currScore++;
                IMGS[7].setVisibility(View.INVISIBLE);
            } else if(secondClick == 8){
                currScore++;
                IMGS[8].setVisibility(View.INVISIBLE);
            } else if(secondClick == 9){
                currScore++;
                IMGS[9].setVisibility(View.INVISIBLE);
            }
        } else{
            resetCards();
        }

        // SWITCH TO MAIN MENU IF ACTIVITY IS FINISHED
        if(attempts == 0){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        if(currScore == 5){
            String score = userName + ": Attempts " + (limit - attempts);
            Intent intent = new Intent(this, ScoreBoard.class);
            intent.putExtra("userName", score);
            startActivity(intent);
        }

        // Re-enable gameplay
        for(int i = 0; i < 10; i++) {
            IMGS[i].setEnabled(true);
        }

    }

    private void frontCards(){
        img11 = R.drawable.jack;
        img12 = R.drawable.joker;
        img13 = R.drawable.king;
        img14 = R.drawable.queen;
        img15 = R.drawable.spades;
        img21 = R.drawable.jack;
        img22 = R.drawable.joker;
        img23 = R.drawable.king;
        img24 = R.drawable.queen;
        img25 = R.drawable.spades;
    }

    // Reset cards to face down
    public void resetCards(){
        for(int i = 0; i < 10; i++) {
            IMGS[i].setImageResource(R.drawable.blueback);
        }
    }
}