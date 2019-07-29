package redudant.tmdbmovie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import redudant.tmdbmovie.R;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
    }

    public void btn_Getstarted(View view) {

        Intent getStarted = new Intent(GetStartedActivity.this, MainActivity.class);
        startActivity(getStarted);
        finish();
    }
}
