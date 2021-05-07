package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //Member variables for holding the score
    private int mScore1;
    private int mScore2;

    private TextView mScoreText1;
    private TextView mScoreText2;

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fin the TextViews By Id
        mScoreText1 = (TextView)findViewById(R.id.score_1);
        mScoreText2 = (TextView)findViewById(R.id.score_2);

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //checka si el item correcto se le dio click
        if (item.getItemId()==R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect.
            recreate();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que maneja el evento click al presionar los botones de decremento
     * @param view El botón en la vista que fue clickeado
     */
    public void decreaseScore(View view) {
        //obtenemos el id del botton cuando se le da click
        int viewID = view.getId();

        switch(viewID){
            //Si fue el equipo 1
            case R.id.decreaseTeam1:
                //Decrementar el marcador y actualizar la caja de texto
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2:
                //Decrementar el marcador y actualizar la caja de texto
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
        }//fin del switch
    }

    /**
     * Metodo que maneja el evento click al presionar los botones de incremento
     * @param view El botón en la vista que fue clickeado
     */
    public void increaseScore(View view) {
        //obtenemos el id del botton cuando se le da click
        int viewID = view.getId();

        switch(viewID){
            //Si fue el equipo 1
            case R.id.increaseTeam1:
                //Incrementar el marcador y actualizar la caja de texto
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //Si fue el equipo 2
            case R.id.increaseTeam2:
                //Incrementar el marcador y actualizar la caja de texto
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
        }//fin del switch
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Save the scores.
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}