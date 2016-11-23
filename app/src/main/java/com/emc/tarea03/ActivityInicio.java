package com.emc.tarea03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emc.tarea03.capaBBDD.AdaptadorBBDD;
import com.emc.tarea03.capaBBDD.OpenHelperBBDD;
import com.emc.tarea03.capaBBDD.StringsBBDD;

public class ActivityInicio extends AppCompatActivity implements View.OnClickListener {

    Button btnGestionCoches;
    Button btnGestionConductores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_inicio);
        asignarVista();
        registrarListener();


    }

    private void asignarVista() {
        btnGestionCoches = (Button) findViewById(R.id.btnCoches);
        btnGestionConductores = (Button) findViewById(R.id.btnConductores);
    }


    private void registrarListener() {
        btnGestionCoches.setOnClickListener(this);
        btnGestionConductores.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCoches:
                Intent intentCoches = new Intent(ActivityInicio.this, ActivityGestionCoches.class);
                startActivity(intentCoches);
                break;

            case R.id.btnConductores:
                Intent intentConductor = new Intent(ActivityInicio.this, ActivityGestionConductores.class);
                startActivity(intentConductor);
                break;
        }
    }
}


