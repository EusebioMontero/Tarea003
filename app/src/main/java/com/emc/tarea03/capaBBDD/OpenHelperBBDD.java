package com.emc.tarea03.capaBBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.widget.Toast;

import com.emc.tarea03.ActivityInicio;
import com.emc.tarea03.capaNegocio.ClassCoche;


/**
 * Created by ois on 02/11/2016.
 * http://www.hermosaprogramacion.com/2014/10/android-sqlite-bases-de-datos/
 * http://www.hermosaprogramacion.com/2016/01/base-de-datos-sqlite-en-android-con-multiples-tablas/
 */

public class OpenHelperBBDD extends SQLiteOpenHelper {

    int version;

    public OpenHelperBBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        crearTablaCoches(db);
        crearTablaConductores(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private void crearTablaCoches(SQLiteDatabase db) {
        db.execSQL(StringsBBDD.SQL_CREA_TABLA_COCHES);
        if (version == 1) {
            crearCochesPrueba(db);
        }
    }

    private void crearTablaConductores(SQLiteDatabase db) {
        db.execSQL(StringsBBDD.SQL_CREA_TABLA_CONDUCTORES);
        if (version == 1) {
            crearConductoresPrueba(db);
        }
    }

    private void crearCochesPrueba(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(StringsBBDD.TABLA_COCHES.COL_ID, 0);
        values.put(StringsBBDD.TABLA_COCHES.COL_MODELO, "Modelo");
        values.put(StringsBBDD.TABLA_COCHES.COL_COLOR, "Color");
        values.put(StringsBBDD.TABLA_COCHES.COL_ASIENTOS, 0);

        db.insert(StringsBBDD.TABLA_COCHES.NOMBRE_TABLA, null, values);
    }

    private void crearConductoresPrueba(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_ID, 0);
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_NOMBRE, "Nombre");
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_DIRECCION, "Dirección");
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_DNI, "000000000-X");
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_MAIL, "mail@mail.com");
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_TELEFONO, 666666666);

        db.insert(StringsBBDD.TABLA_CONDUCTORES.NOMBRE_TABLA, null, values);
    }

}
