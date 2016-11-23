package com.emc.tarea03.capaBBDD;

import android.app.DownloadManager;
import android.provider.BaseColumns;

/**
 * Created by ois on 02/11/2016.
 */

public class StringsBBDD{

    public static final int VERSION = 2;
    public static final String NOMBRE_BD = "tarea03BBDD.db";

    public static final String SQL_CREA_TABLA_CONDUCTORES = "CREATE TABLE IF NOT EXISTS " +
            TABLA_CONDUCTORES.NOMBRE_TABLA+"("+
            TABLA_CONDUCTORES.COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TABLA_CONDUCTORES.COL_NOMBRE+ " TEXT, "+
            TABLA_CONDUCTORES.COL_DIRECCION+ " TEXT, "+
            TABLA_CONDUCTORES.COL_DNI+ " TEXT, "+
            TABLA_CONDUCTORES.COL_MAIL+ " TEXT, "+
            TABLA_CONDUCTORES.COL_TELEFONO+ " TEXT ) ";

    public static final String SQL_CREA_TABLA_COCHES = "CREATE TABLE IF NOT EXISTS " +
            TABLA_COCHES.NOMBRE_TABLA + "(" +
            TABLA_COCHES.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TABLA_COCHES.COL_MODELO + " TEXT, " +
            TABLA_COCHES.COL_COLOR + " TEXT, " +
            TABLA_COCHES.COL_ASIENTOS + " TEXT )";



    public static abstract class TABLA_CONDUCTORES implements BaseColumns{
        public static  String NOMBRE_TABLA="conductores";
        public static  String COL_ID="id";
        public static  String COL_NOMBRE="nombre";
        public static  String COL_DIRECCION="direccion";
        public static  String COL_DNI="dni";
        public static  String COL_MAIL="mail";
        public static  String COL_TELEFONO="telefono";
    }

    public static abstract class TABLA_COCHES implements BaseColumns{
        public static  String NOMBRE_TABLA="coches";
        public static  String COL_ID="id";
        public static  String COL_MODELO="modelo";
        public static  String COL_COLOR="color";
        public static  String COL_ASIENTOS="asientos";
    }
}
