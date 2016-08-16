package com.example.hwhong.flashlightapp;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ToggleButton button;
    private Camera camera;
    private Camera.Parameters params;
    private boolean flash = false;
    private boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ToggleButton) findViewById(R.id.toggleButton);
        textView = (TextView) findViewById(R.id.textView);

        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            camera = Camera.open();
            params = camera.getParameters();
            flash = true;


        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flash) {

                    //if the flash light is off, need to turn in it on
                    if(!on) {
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(params);
                        camera.startPreview();
                        on = true;
                    } else {
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(params);
                        camera.stopPreview();
                        on = false;
                    }

                } else {
                    textView.setText("No Flash feature on this phone :(");
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera != null) {
            camera.release();
            camera = null;
        }
    }
}
