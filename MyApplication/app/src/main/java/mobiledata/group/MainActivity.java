package mobiledata.group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ruokalistaActivity(View view) {
        Intent intent = new Intent(this, Ruokalista.class);
        startActivity(intent);

    }

    public void saaActivity(View view) {
        Intent intent = new Intent(this, saa.class);
        startActivity(intent);
    }
}
