package com.apps.rizalmaulana.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apps.rizalmaulana.R;
import com.apps.rizalmaulana.sharedpreference.utils.Preferences;


public class Home extends AppCompatActivity {

    private TextView txtKeluar;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home);

        declareView();
        txtKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Clear Set Preferences
                Preferences.setLogout(getBaseContext());

                //Pindah Halaman ke Login
                startActivity(new Intent(getBaseContext(), Login.class));
                finish();
            }
        });
    }

    private void declareView() {
        txtKeluar = findViewById(R.id.button3);
        txtName = findViewById(R.id.txt_name);

        txtName.setText(Preferences.getRegisteredUser(getBaseContext()));
    }
}