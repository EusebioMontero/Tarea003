package com.emc.tarea03.capaBBDD;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.widget.Toast;

import com.emc.tarea03.ActivityGestionCoches;
import com.emc.tarea03.capaNegocio.ClassCoche;
import com.emc.tarea03.capaNegocio.ClassConductor;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ois on 02/11/2016.
 * http://www.hermosaprogramacion.com/2014/10/android-sqlite-bases-de-datos/
 * http://www.hermosaprogramacion.com/2016/01/base-de-datos-sqlite-en-android-con-multiples-tablas/
 */

public class AdaptadorBBDD {

    OpenHelperBBDD openHelperBBDD;
    SQLiteDatabase db;
    Context context;

    public AdaptadorBBDD(Context context) {
        this.context = context;
        abrirHelper();
        abrirBD();
    }

    private void abrirHelper() {
        if (openHelperBBDD == null) {
            try {
                openHelperBBDD = new OpenHelperBBDD(context, StringsBBDD.NOMBRE_BD, null, StringsBBDD.VERSION);
                System.out.println("Se ha creado la BD!!!!");
            } catch (Exception ex) {
                System.out.println("ERROR!!!!! No se ha podido crear la BD " + ex);
            }
        }
    }

    private void abrirBD() {
        if (openHelperBBDD == null) {
            abrirHelper();
        }
        if (db == null) {
            try {
                db = openHelperBBDD.getWritableDatabase();
                System.out.println("Acceso a TOTAL a la BD!!!");
            } catch (Exception ex) {
                db = openHelperBBDD.getReadableDatabase();
                System.out.println("ERROR!!! No se ha podido abrir la BD " + ex);
            }
        }
    }

    public long setCoche(ClassCoche coche) {
        return db.insert(StringsBBDD.TABLA_COCHES.NOMBRE_TABLA, null, coche.ClassCochetoContentValues());
    }

    public long deleteCoche(int  idCocheSeleccionado){
        return  db.delete(StringsBBDD.TABLA_COCHES.NOMBRE_TABLA,
                StringsBBDD.TABLA_COCHES.COL_ID+"="+idCocheSeleccionado,
                null);
    }

    public long setConductor(ClassConductor conductor) {
        return db.insert(StringsBBDD.TABLA_CONDUCTORES.NOMBRE_TABLA, null, conductor.ClassCochetoContentValues());
    }

    public long deleteConductor(int  idConductorSelecionado){
        return  db.delete(StringsBBDD.TABLA_CONDUCTORES.NOMBRE_TABLA,
                StringsBBDD.TABLA_CONDUCTORES.COL_ID+"="+idConductorSelecionado,
                null);
    }

    public Cursor getAllTabla(String tabla) {
        Cursor consulta;
        try {
            consulta = db.query(tabla, null, null, null, null, null, null);
        }catch (Exception ex){
            System.out.println("ERROR!!! No se ha podido realizar la consulta " + ex);
            consulta=null;
        }
        return consulta;
    }

    public void actualizarListado(){



    }

}
