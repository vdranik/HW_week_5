package ua.com.vdranik.hw_week_5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static final String TOOLBAR_PART_NAME = "Selected: ";
    public static final int START_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(TOOLBAR_PART_NAME + START_POSITION);
        setSupportActionBar(toolbar);

    }
}
