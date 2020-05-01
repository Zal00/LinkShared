package com.apps.rizalmaulana.sharedpreferences;

/** 
Rizal Maulana 
10117191
IF5

waktu pengerjaan 
28/04/2020
membuat link shared
*/
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apps.rizalmaulana.R;
import com.apps.rizalmaulana.sharedpreference.model.UserModel;
import com.apps.rizalmaulana.sharedpreference.utils.Preferences;

public class Login extends AppCompatActivity {

    private TextView txtMasuk;
    private TextView txtRegister;
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);

        declareView();

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Register.class));
            }
        });
    }

    /**
     * ke MainActivity jika data Status Login dari Data Preferences bernilai true
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), Home.class));
            finish();
        }
    }


    private void declareView() {

        txtRegister = findViewById(R.id.txt_log_regis);
       txtMasuk = findViewById(R.id.button);
        edtUsername = findViewById(R.id.txt_log_user);
        edtPassword = findViewById(R.id.txt_log_pass);

    }

    private void validasiLogin() {

        // Mereset semua Error dan fokus menjadi default
        edtUsername.setError(null);
        edtPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        //Set Input Value dari View
        String userName = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();


        // Jika form user kosong atau memenuhi kriteria di Method cekUser() maka, Set error di Form-
        // User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(userName)) {
            edtUsername.setError("Harus diisi");
            fokus = edtUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            edtUsername.setError("Username Tidak Ditemukan");
            fokus = edtUsername;
            cancel = true;
        }

        // Jika form password kosong dan memenuhi kriteria di Method cekPassword maka,
        // Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Harus Diisi");
            fokus = edtPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            edtPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = edtPassword;
            cancel = true;
        }

        // Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
        // Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel) {
            fokus.requestFocus();
        } else {
            // Deklarasi Model
            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);
            // Simpan data ke shared preferences
            Preferences.setUserPreferences(getBaseContext(), userModel);
            Preferences.setLoggedInStatus(getBaseContext(), true);
            //Pindah Halaman ke home
            startActivity(new Intent(getBaseContext(), Home.class));
            finish();
        }

    }

    // True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    // True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPassword(getBaseContext()));
    }

}
