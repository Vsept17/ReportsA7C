package com.a7c.reportsa7c.AdminActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a7c.reportsa7c.R;

public class Kinerja_Ahp extends AppCompatActivity {
    Button tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinerja_ahp);

        tes = (Button) findViewById(R.id.test);

        tes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kinerja_Ahp.this, MainAdmin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
