package com.emc.tarea03.capaNegocio;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.Settings;

import com.emc.tarea03.capaBBDD.StringsBBDD;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ois on 21/09/2016.
 */
public class ClassCoche implements Serializable {
    private String strModelo, strColor;
    private  int intAsientos, id;



    public ClassCoche(String strModelo, String strColor, int intAsientos, int id) {
        this.strModelo = strModelo;
        this.strColor = strColor;
        this.intAsientos = intAsientos;
        this.id=id;

    }
    public ClassCoche(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrModelo() {
        return strModelo;
    }

    public void setStrModelo(String strModelo) {
        this.strModelo = strModelo;
    }

    public String getStrColor() {
        return strColor;
    }

    public void setStrColor(String strColor) {
        this.strColor = strColor;
    }

    public int getIntAsientos() {
        return intAsientos;
    }

    public void setIntAsientos(int intAsientos) {
        this.intAsientos = intAsientos;
    }


    public static ClassCoche cursorToClassCoche(Cursor cursor) {
        ClassCoche coche = null;

        if (cursor != null) {
            coche = new ClassCoche();
            if (cursor.isNull(0)) {coche.setId(0);}
            else {coche.setId(cursor.getInt(0));}
            if (cursor.isNull(1)) {coche.setStrModelo("");}
            else {coche.setStrModelo(cursor.getString(1));}
            if (cursor.isNull(2)) {coche.setStrColor("");}
            else {coche.setStrColor(cursor.getString(2));}
            if (cursor.isNull(3)) {coche.setIntAsientos(0);}
            else {coche.setIntAsientos(cursor.getInt(3));}
        }
        return coche;
    }

    public ContentValues ClassCochetoContentValues() {
        ContentValues values = new ContentValues();
       // values.put(StringsBBDD.TABLA_COCHES.COL_ID, id);
        values.put(StringsBBDD.TABLA_COCHES.COL_MODELO, strModelo);
        values.put(StringsBBDD.TABLA_COCHES.COL_COLOR, strColor);
        values.put(StringsBBDD.TABLA_COCHES.COL_ASIENTOS, intAsientos);
        return values;
    }

}
