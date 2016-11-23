package com.emc.tarea03;

import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emc.tarea03.capaBBDD.AdaptadorBBDD;
import com.emc.tarea03.capaBBDD.StringsBBDD;
import com.emc.tarea03.capaNegocio.ClassCoche;

import java.util.ArrayList;

/**
 * Created by ois on 17/10/2016.
 */

public class ActivityGestionCoches extends AppCompatActivity implements View.OnClickListener {

    //Adaptador que completa el ReciclerView con los datos de la lista
    AdaptadorRcvCoches adaptadorCoches;
    RecyclerView rcvCoches;
    // lista con contiene los datos
   // ArrayList<ClassCoche> listCoche;

   //ClassCoche coche;

    AdaptadorBBDD adaptadorBBDD;

    Button btnOK, btnLimpiar;
    EditText editTxtModelo, editTxtColor, editTxtAsientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asignarVista();

        //Inicamos el adaptador de la BD que abre el Helper y este crearia la BD, si no esta creada
        //iniciado despues la lista con la tabla cohes y conductores
        adaptadorBBDD = new AdaptadorBBDD(ActivityGestionCoches.this);
        //Cargamos el ReciclerView con la lista
        adaptadorCoches = new AdaptadorRcvCoches(this);
        rcvCoches.setAdapter(adaptadorCoches);

        registrarListener();
    }



    private void asignarVista() {
        //Seleccionamos la vista
        setContentView(R.layout.layout_coches);
        //Instanciamos el  ReciclerView
        rcvCoches = (RecyclerView) findViewById(R.id.reciclerViewCoches);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvCoches.setLayoutManager(layoutManager);

        btnOK = (Button) findViewById(R.id.btnCochesOK);
        btnLimpiar = (Button) findViewById(R.id.btnCochesLimpiar);

        editTxtModelo = (EditText) findViewById(R.id.editTxtModelo);
        editTxtColor = (EditText) findViewById(R.id.editTxtColor);
        editTxtAsientos = (EditText) findViewById(R.id.editTxtAsientos);


    }
    private void registrarListener() {
        btnOK.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);
    }




    public void rellenarEditText(ClassCoche coche){
        editTxtModelo.setText(coche.getStrModelo());
        editTxtColor.setText(coche.getStrColor());
        editTxtAsientos.setText(String.valueOf(coche.getIntAsientos()));
    }

    private boolean validarCajasTexto (){
        boolean ok=false;
       
        boolean okModelo=(editTxtModelo.getText().toString().equals(""))?false:true;
        boolean okColor=(editTxtColor.getText().toString().equals(""))?false:true;
        boolean okAsientos=(editTxtAsientos.getText().toString().equals(""))?false:true;

        ok=(okModelo && okColor && okAsientos)?true:false;

        return ok;
    }


    private void limpiarCajasTxt(){
        editTxtModelo.setText("");
        editTxtColor.setText("");
        editTxtAsientos.setText("");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCochesOK:
                if(validarCajasTexto()){
                    ClassCoche coche=new ClassCoche(
                            editTxtModelo.getText().toString(),
                            editTxtColor.getText().toString(),
                            Integer.parseInt(editTxtAsientos.getText().toString()),
                            0);
                    adaptadorBBDD.setCoche(coche);
                    adaptadorCoches.insertarTablaCoches();

                    rcvCoches.getAdapter().notifyDataSetChanged();

                   //
                }else{
                    Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.btnCochesLimpiar:
                limpiarCajasTxt();
                break;
        }
    }


}
