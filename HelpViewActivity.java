package f.lucia.tournamentmakerapp;

/**
 * Created by Nel on 2015-12-06.
 * Shows information to guide user through the app
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ScrollView;

public class HelpViewActivity extends Activity {

    private ScrollView FAQ;
    private ScrollView Light;
    private ScrollView Depth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_view);
        String InfoView= getIntent().getStringExtra("info");
        FAQ= (ScrollView) findViewById(R.id.scrollViewFAQ);
        Light= (ScrollView) findViewById(R.id.scrollViewLight);
        Depth= (ScrollView) findViewById(R.id.scrollViewDepth);

        if(InfoView.equals("FAQ"))
        {
            showFAQ();
        }
        else if(InfoView.equals("Light"))
        {
            showLight();
        }
        else if(InfoView.equals("Depth"))
        {
            showDepth();
        }
    }

    private void showDepth() {
        Depth.setVisibility(View.VISIBLE);
    }

    private void showLight() {
        Light.setVisibility(View.VISIBLE);

    }

    private void showFAQ() {
        FAQ.setVisibility(View.VISIBLE);
    }


}
