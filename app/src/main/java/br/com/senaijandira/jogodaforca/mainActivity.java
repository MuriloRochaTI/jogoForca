package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class mainActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    }
    public void iniciarJogo(View v){

        // ABRIR UMA NOVA TELA
        Intent intent = new Intent(this, inicioActivity.class);
        startActivity(intent);

    }
}
