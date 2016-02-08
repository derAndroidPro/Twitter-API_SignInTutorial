package derandroidpro.de.twitter.api.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Diese beiden Codes solltet ihr geheim halten, wenn ihr eure App veröffentlicht.
    private static final String TWITTER_KEY = "vfgjnMm7HyFFHj2LjLTa7ncwJ";
    private static final String TWITTER_SECRET = "8UuWgSXnC0j20vGhf65JnLe8bincUkMdwPZTuc69XmMQWqWt0U";

    TwitterSession twitterSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        twitterSession = Twitter.getSessionManager().getActiveSession();
        if(twitterSession == null){
            startActivity(new Intent(MainActivity.this, TwitterLoginActivity.class));
            finish();
        } else {
            // Etwas mit der Twitter API tun
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_logout  && twitterSession != null){

            Twitter.logOut();
            Toast.makeText(MainActivity.this, "Von Twitter abgemeldet.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, TwitterLoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
