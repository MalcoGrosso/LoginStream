package com.mng.loginsharedpreference.UI;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mng.loginsharedpreference.Modelo.Usuario;
import com.mng.loginsharedpreference.Request.ApiClient;
import com.mng.loginsharedpreference.UI.Registro.Registro;
import com.mng.loginsharedpreference.UI.Registro.Editar;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Integer> cartelAvisoMutable;
    private MutableLiveData<String> avisoMutable;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<Integer> getCartelAviso() {
        if (cartelAvisoMutable == null) {
            cartelAvisoMutable = new MutableLiveData<>();
        }
        return cartelAvisoMutable;
    }

    public LiveData<String> getAviso() {
        if (avisoMutable == null) {
            avisoMutable = new MutableLiveData<>();
        }
        return avisoMutable;
    }

    public void Login(String mail, String password) {
        Usuario usu = ApiClient.login(context, mail, password);
            if (usu == null) {
            Toast.makeText(context, "Usuario no Encontrado", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(context.getApplicationContext(), Editar.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void Registrarse() {
        Intent intent = new Intent(context, Registro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
