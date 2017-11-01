package mobiledata.group;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class saa extends AppCompatActivity implements View.OnClickListener, WeatherEngine.WeatherDataAvailableInterface {

    WeatherEngine engine = new WeatherEngine(this);

    EditText editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saa);
        findViewById(R.id.button).setOnClickListener(this);

        editor = (EditText) findViewById(R.id.editText);

        SharedPreferences sharedPref= getSharedPreferences("mypref", MODE_PRIVATE);
        String savedString =sharedPref.getString("Saved String", null);
        editor.setText(savedString);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onClick(View v) {
        saveString("Saved String" , editor.getText().toString());
        engine.getWeatherData(editor.getText().toString());
    }

    protected void updateUI()
    {
        TextView temperatureTextView = (TextView) findViewById(R.id.textView);
        String formatted = String.format(getString(R.string.temp), engine.getTemperature());

        temperatureTextView.setText(formatted);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load("http://openweathermap.org/img/w/" + engine.getIconId() + ".png").into(img);
    }

    public void saveString(String nimi , String input) {
        // Create object of SharedPreferences.
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);
        //now get Editor
        SharedPreferences.Editor editor = sharedPref.edit();
        //put your value
        editor.putString(nimi, input);
        //commits your edits
        editor.commit();
    }

    @Override
    public void weatherDataAvailable() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }
}
