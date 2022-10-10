package com.mng.loginsharedpreference.UI.Registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mng.loginsharedpreference.R;
import com.mng.loginsharedpreference.Modelo.Usuario;

import kotlin.text.UStringsKt;

public class Registro extends AppCompatActivity {

    private RegistroViewModel viewModel;
    private TextView tvAviso;
    private EditText etMail, etPass, etDni, etNombre, etApellido;
    private Button btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);

        inicializarViews();

        viewModel.getAvisoMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvAviso.setText(s);
            }
        });

        viewModel.getAvisoCartelMutable().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                tvAviso.setVisibility(visibility);
            }
        });
    }

    private void inicializarViews() {
        btGuardar = findViewById(R.id.btGuardar);
        etMail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        etDni = findViewById(R.id.etDni);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        tvAviso = findViewById(R.id.tvAviso);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            private String regexStr = "^[0-9]*$";


            @Override
            public void onClick(View view) {

                if(etDni.getText().toString().trim().matches(regexStr))
                {
                    if(etDni.getText().toString().trim().isEmpty()){
                        Toast.makeText(Registro.this, "DNI no puede estar vacio", Toast.LENGTH_SHORT).show();
                    }else {
                        Usuario usu = new Usuario(
                                Integer.parseInt(etDni.getText().toString()),
                                etNombre.getText().toString(),
                                etApellido.getText().toString(),
                                etMail.getText().toString(),
                                etPass.getText().toString()
                        );
                        viewModel.Guardar(usu);
                    }
                }
                else{
                    Toast.makeText(Registro.this, "Introducir Numeros para el Dni", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}
