package com.emc.tarea03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emc.tarea03.capaBBDD.AdaptadorBBDD;
import com.emc.tarea03.capaNegocio.ClassCoche;
import com.emc.tarea03.capaNegocio.ClassConductor;

import java.util.ArrayList;

/**
 * Created by ois on 17/10/2016.
 */

public class ActivityGestionConductores extends AppCompatActivity implements View.OnClickListener {

    //Adaptador que completa el ReciclerView con los datos de la lista
    AdaptadorRcvConductores adaptadorConductores;
    RecyclerView rcvConductores;
    // lista con contiene los datos
   // ArrayList<ClassConductor> listConductores;

    //ClassConductor conductor;

    AdaptadorBBDD adaptadorBBDD;

    Button btnOK, btnLimpiar;
    EditText editTxtNombre, editTxtDir, editTxtDir2, editTxtDNI, editTxtMail, editTxtTlfn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asignarVista();
        //Inicamos el adaptador de la BD que abriria el Helper y este crearia la BD, si no esta creada
        //iniciado despues la lista con la tabla cohes y conductores
        adaptadorBBDD = new AdaptadorBBDD(ActivityGestionConductores.this);
        //Cargamos el ReciclerView con la lista
        adaptadorConductores = new AdaptadorRcvConductores(this );
        rcvConductores.setAdapter(adaptadorConductores);

        registrarListener();

    }



    private void asignarVista() {
        //Seleccionamos la vista
        setContentView(R.layout.layout_conductores);
        //Instanciamos el  ReciclerView
        rcvConductores = (RecyclerView) findViewById(R.id.reciclerViewConductores);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvConductores.setLayoutManager(layoutManager);

        btnOK = (Button) findViewById(R.id.btnConductoresOK);
        btnLimpiar = (Button) findViewById(R.id.btnConductoresLimpiar);

        editTxtNombre = (EditText) findViewById(R.id.editNombre);
        editTxtDir = (EditText) findViewById(R.id.editTxtDir);
        editTxtDNI = (EditText) findViewById(R.id.editTxtDni);
        editTxtMail = (EditText) findViewById(R.id.editTxtMail);
        editTxtTlfn = (EditText) findViewById(R.id.editTxtTlfn);
    }
    private void registrarListener() {
        btnOK.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);
    }

    public void rellenarEditText(ClassConductor conductor){

        editTxtNombre.setText(conductor.getStrNombre());
        editTxtDir.setText(conductor.getStrDireccion());
        editTxtDNI.setText(conductor.getStrDNI());
        editTxtMail.setText(conductor.getStrMail());
        editTxtTlfn.setText(String.valueOf(conductor.getIntTelefono()));
    }
    private boolean validarCajasTexto (){
        boolean ok=false;

        boolean okNombre=(editTxtNombre.getText().toString().equals(""))?false:true;
        boolean okDir=(editTxtDir.getText().toString().equals(""))?false:true;
        boolean okDNI=(editTxtDNI.getText().toString().equals(""))?false:true;
        boolean okMail=(editTxtMail.getText().toString().equals(""))?false:true;
        boolean okTlfn=(editTxtTlfn.getText().toString().equals(""))?false:true;

        ok=(okNombre && okDir && okDNI && okMail && okTlfn)?true:false;

        return ok;
    }
    private void limpiarCajasTxt(){
        editTxtNombre.setText("");
        editTxtDir.setText("");
        editTxtDNI.setText("");
        editTxtMail.setText("");
        editTxtTlfn.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConductoresOK:
                if(validarCajasTexto()){
                    ClassConductor conductor=new ClassConductor(
                            editTxtNombre.getText().toString(),
                            editTxtDir.getText().toString(),
                            editTxtDNI.getText().toString(),
                            editTxtMail.getText().toString(),
                            Integer.parseInt(editTxtTlfn.getText().toString()),
                            0);
                    adaptadorBBDD.setConductor(conductor);
                    adaptadorConductores.insertarTablaConductores();

                    rcvConductores.getAdapter().notifyDataSetChanged();

                    //
                }else{
                    Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.btnConductoresLimpiar:
                limpiarCajasTxt();
                break;
        }

    }
}
