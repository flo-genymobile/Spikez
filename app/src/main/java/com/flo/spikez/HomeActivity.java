package com.flo.spikez;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flo.spikez.animation.SpecialEditionActivity;
import com.flo.spikez.monitoring.MonitoringActivity;

public class HomeActivity extends AppCompatActivity {

    private Button goToMonitoring;
    private Button goToAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        goToAnimation = (Button) findViewById(R.id.goto_animation);
        goToAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(SpecialEditionActivity.class);
            }
        });
        goToMonitoring = (Button) findViewById(R.id.goto_monitoring);
        goToMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(MonitoringActivity.class);
            }
        });
    }

    private void goToActivity(Class clazz) {
        startActivity(new Intent(getApplicationContext(), clazz));
    }
}
