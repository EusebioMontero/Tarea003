package com.emc.tarea03.capaNegocio;

import android.content.ContentValues;
import android.database.Cursor;

import com.emc.tarea03.capaBBDD.StringsBBDD;

import java.io.Serializable;

/**
 * Created by ois on 21/09/2016.
 */
public class ClassConductor implements Serializable {
    private String strNombre, strDireccion, strDNI, strMail;
    private  int intTelefono, id;


    public ClassConductor(String strNombre, String strDireccion, String strDNI, String strMail, int intTelefono, int id) {

        this.strNombre = strNombre;
        this.strDireccion = strDireccion;
        this.strDNI = strDNI;
        this.strMail = strMail;
        this.intTelefono = intTelefono;
        this.id=id;

    }

    public ClassConductor(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrDireccion() {
        return strDireccion;
    }

    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    public String getStrMail() {
        return strMail;
    }

    public void setStrMail(String strMail) {
        this.strMail = strMail;
    }

    public String getStrDNI() {
        return strDNI;
    }

    public void setStrDNI(String strDNI) {
        this.strDNI = strDNI;
    }

    public int getIntTelefono() {
        return intTelefono;
    }

    public void setIntTelefono(int intTelefono) {
        this.intTelefono = intTelefono;
    }

    public static ClassConductor cursorToClassConductor(Cursor cursor) {
        ClassConductor conductor = null;

        if (cursor != null) {
            conductor = new ClassConductor();
            if (cursor.isNull(0)) {conductor.setId(0);}
            else {conductor.setId(cursor.getInt(0));}

            if (cursor.isNull(1)) {conductor.setStrNombre("");}
            else {conductor.setStrNombre(cursor.getString(1));}

            if (cursor.isNull(2)) {conductor.setStrDireccion("");}
            else {conductor.setStrDireccion(cursor.getString(2));}

            if (cursor.isNull(3)) {conductor.setStrDNI("");}
            else {conductor.setStrDNI(cursor.getString(3));}

            if (cursor.isNull(4)) {conductor.setStrMail("");}
            else {conductor.setStrMail(cursor.getString(4));}

            if (cursor.isNull(5)) {conductor.setIntTelefono(0);}
            else {conductor.setIntTelefono(cursor.getInt(5));}
        }
        return conductor;
    }

    public ContentValues ClassCochetoContentValues() {
        ContentValues values = new ContentValues();
        // values.put(StringsBBDD.TABLA_COCHES.COL_ID, id);
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_NOMBRE, strNombre);
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_DIRECCION, strDireccion);
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_DNI, strDNI);
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_MAIL, strMail);
        values.put(StringsBBDD.TABLA_CONDUCTORES.COL_TELEFONO, intTelefono);
        return values;
    }
}
