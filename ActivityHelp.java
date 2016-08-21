package f.lucia.tournamentmakerapp;

/**
 * Created by Nel on 2015-12-06.
 * This page shows help-based information for the app
*/
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ActivityHelp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void onFAQClick(View view)
    {
        Intent viewIntent = new Intent(getApplicationContext(), HelpViewActivity.class).putExtra("info","FAQ");//send to help view to show FAQ info
        startActivityForResult(viewIntent, 0);
    }
    public void onLightClick(View view)
    {
        Intent viewIntent = new Intent(getApplicationContext(), HelpViewActivity.class).putExtra("info","Light");//send to help view to show Light-app info
        startActivityForResult(viewIntent, 0);
    }
    public void onDepthClick(View view)
    {
        Intent viewIntent = new Intent(getApplicationContext(), HelpViewActivity.class).putExtra("info","Depth");//send to help view to show in-dept info
        startActivityForResult(viewIntent, 0);
    }


}
