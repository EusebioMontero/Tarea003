package com.emc.tarea03;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emc.tarea03.capaBBDD.AdaptadorBBDD;
import com.emc.tarea03.capaBBDD.StringsBBDD;
import com.emc.tarea03.capaNegocio.ClassCoche;
import com.emc.tarea03.capaNegocio.ClassConductor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ois on 18/10/2016.
 */

public class AdaptadorRcvConductores extends RecyclerView.Adapter<AdaptadorRcvConductores.Elementos> {
    List<ClassConductor> listConductores; //ArrayList con la lista de los conductores
    ActivityGestionConductores activityGestionConductores;
    Dialog dialog = null;
    ClassConductor conductorSeleccionado;
    AdaptadorBBDD adaptadorBBDD;


    AdaptadorRcvConductores(ActivityGestionConductores activityGestionConductores) {
        // this.listConductores = listConductores;
        this.activityGestionConductores = activityGestionConductores;
        adaptadorBBDD = new AdaptadorBBDD(activityGestionConductores);
        insertarTablaConductores();
    }


    //Al crear el adaptador se infla la vista
    @Override
    public AdaptadorRcvConductores.Elementos onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_cardview_conductores, parent, false);
        Elementos adaptador = new Elementos(view);

        return adaptador;
    }

    //Obtenemos los datos de la lista y rellenamos una cardView por cada uno de los objetos en la lista
    @Override
    public void onBindViewHolder(AdaptadorRcvConductores.Elementos elementos, final int position) {
        elementos.txtNombre.setText(listConductores.get(position).getStrNombre());
        elementos.txtDireccion.setText(listConductores.get(position).getStrDireccion());
        elementos.txtDNI.setText(listConductores.get(position).getStrDNI());
        elementos.txtMail.setText(listConductores.get(position).getStrMail());
        elementos.txtTelefono.setText(String.valueOf(listConductores.get(position).getIntTelefono()));
        //listener del LinearLayout
        elementos.lytDatos.setOnClickListener(new View.OnClickListener() {
            //Al pulsar sobre el CardView...
            @Override
            public void onClick(View v) {
                ClassConductor conductorSeleccionado = listConductores.get(position);
                activityGestionConductores.rellenarEditText(conductorSeleccionado);
            }
        });
        elementos.lytCochesAsignados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activityGestionConductores, "Has pulsado el LinearLayout de los coches asignados", Toast.LENGTH_SHORT).show();
            }
        });
        elementos.lytDatos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mostarDialog(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listConductores.size();
    }

    //Localizamos los atributos de cada objeto de la lista
    public class Elementos extends RecyclerView.ViewHolder {

        LinearLayout lytDatos, lytCochesAsignados;
        CardView cardView;
        TextView txtNombre, txtDireccion, txtDNI, txtMail, txtTelefono;

        public Elementos(View itemView) {
            super(itemView);
            lytDatos = (LinearLayout) itemView.findViewById(R.id.lytDatos);
            lytCochesAsignados = (LinearLayout) itemView.findViewById(R.id.lytCochesAsignados);
            cardView = (CardView) itemView.findViewById(R.id.cardViewConductores);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtDireccion = (TextView) itemView.findViewById(R.id.txtDireccion);
            txtDNI = (TextView) itemView.findViewById(R.id.txtDNI);
            txtMail = (TextView) itemView.findViewById(R.id.txtMail);
            txtTelefono = (TextView) itemView.findViewById(R.id.txtTelefono);
        }
    }

    private void mostarDialog(final int position) {
        dialog = new Dialog(activityGestionConductores);
        LayoutInflater inflater = activityGestionConductores.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_dialog, null);
        //elimina la barra de titulo
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        conductorSeleccionado = listConductores.get(position);

        ((Button) dialog.findViewById(R.id.btnEditar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // edit(cocheSeleccionado);
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.btnBorrar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(conductorSeleccionado);
                insertarTablaConductores();
                actualizarListado();
                dialog.dismiss();
            }
        });

        //hace el marco del dialogo transparente
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    public void actualizarListado() {

        this.notifyDataSetChanged();

    }

    public void remove(ClassConductor conductor) {
        long resultadoDelete;
        int idConductorSelecionado = conductor.getId();
        resultadoDelete = adaptadorBBDD.deleteConductor(idConductorSelecionado);
        // insertarTablaCoches();
        Log.d("Borrando  Resultado=> ", String.valueOf(resultadoDelete));
        //actualizarListado();
    }

    public void insertarTablaConductores() {
        if (listConductores == null) {
            listConductores = new ArrayList<ClassConductor>();
        }
        Cursor cursor = (adaptadorBBDD.getAllTabla(StringsBBDD.TABLA_CONDUCTORES.NOMBRE_TABLA));

        if (cursor != null && cursor.moveToFirst()) {
            listConductores = new ArrayList<ClassConductor>();
            do {
                ClassConductor conductor = ClassConductor.cursorToClassConductor(cursor);
                listConductores.add(conductor);
            } while (cursor.moveToNext());
        } else {//si no hay entradas en la BD se crea una lista nueva vacia
            listConductores = new ArrayList<ClassConductor>();
        }
    }
}


