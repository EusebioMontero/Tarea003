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

import com.emc.tarea03.capaBBDD.AdaptadorBBDD;
import com.emc.tarea03.capaBBDD.StringsBBDD;
import com.emc.tarea03.capaNegocio.ClassCoche;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ois on 18/10/2016.
 */

public class AdaptadorRcvCoches extends RecyclerView.Adapter<AdaptadorRcvCoches.Elementos> {

    List<ClassCoche>listCoches; //ArrayList con la lista de los coches
    ActivityGestionCoches activityGestionCoches;
    Dialog dialog = null;
    ClassCoche cocheSeleccionado;
    AdaptadorBBDD adaptadorBBDD;


    AdaptadorRcvCoches(ActivityGestionCoches activityGestionCoches) {
       // this.listCoches = listCoches;

        this.activityGestionCoches = activityGestionCoches;
        adaptadorBBDD = new AdaptadorBBDD(activityGestionCoches);
        insertarTablaCoches();
    }


    //Al crear el adaptador se infla la vista
    @Override
    public AdaptadorRcvCoches.Elementos onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_cardview_coches, parent,false);
        Elementos adaptador = new Elementos(view);
        return adaptador;
    }
    //Obtenemos los datos de la lista y rellenamos una cardView por cada uno de los objetos en la lista
    @Override
    public void onBindViewHolder(AdaptadorRcvCoches.Elementos elementos, final int position) {
        elementos.txtModelo.setText(listCoches.get(position).getStrModelo());
        elementos.txtColor.setText(listCoches.get(position).getStrColor());
        elementos.txtAsientos.setText(String.valueOf(listCoches.get(position).getIntAsientos()));
        //listener del LinearLayout
        elementos.linearLayoutCrvCoches.setOnClickListener(new View.OnClickListener() {
            //Al pulsar sobre el CardView...
            @Override
            public void onClick(View v) {
                ClassCoche cocheSeleccionado=listCoches.get(position);
                activityGestionCoches.rellenarEditText(cocheSeleccionado);
            }
        });
        elementos.linearLayoutCrvCoches.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mostarDialog(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCoches.size();
    }

    //Localizamos los atributos de cada objeto de la lista
    public class Elementos extends RecyclerView.ViewHolder{
        LinearLayout linearLayoutCrvCoches;
        CardView cardView;
        TextView txtModelo, txtColor, txtAsientos;
        public Elementos(View itemView) {
            super(itemView);
            linearLayoutCrvCoches = (LinearLayout) itemView.findViewById(R.id.linearLayoutCrvCoches);
            cardView = (CardView) itemView.findViewById(R.id.cardViewCoches);
            txtModelo = (TextView) itemView.findViewById(R.id.txtModelo);
            txtColor = (TextView) itemView.findViewById(R.id.txtColor);
            txtAsientos = (TextView) itemView.findViewById(R.id.txtAsientos);
        }
    }


    private void mostarDialog(final int position) {
        dialog = new Dialog(activityGestionCoches);
        LayoutInflater inflater = activityGestionCoches.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_dialog, null);
        //elimina la barra de titulo
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        cocheSeleccionado = listCoches.get(position);

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
                remove(cocheSeleccionado);
                insertarTablaCoches();
                actualizarListado();
                dialog.dismiss();
            }
        });

        //hace el marco del dialogo transparente
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    public void actualizarListado(){

        this.notifyDataSetChanged();

    }

    public void remove(ClassCoche coche) {
        long resultadoDelete;
        int idCocheSelecionado = coche.getId();
        resultadoDelete=adaptadorBBDD.deleteCoche(idCocheSelecionado);
       // insertarTablaCoches();
        Log.d("Borrando  Resultado=> ", String.valueOf(resultadoDelete));
        //actualizarListado();
    }

    public void insertarTablaCoches() {
        if(listCoches==null){
            listCoches= new ArrayList<ClassCoche>();
        }
        Cursor cursor=(adaptadorBBDD.getAllTabla(StringsBBDD.TABLA_COCHES.NOMBRE_TABLA));

        if (cursor != null && cursor.moveToFirst()) {
            listCoches = new ArrayList<ClassCoche>();
            do {
                ClassCoche coche = ClassCoche.cursorToClassCoche(cursor);
                listCoches.add(coche);
            } while (cursor.moveToNext());
        }else{//si no hay entradas en la BD se crea una lista nueva vacia
            listCoches = new ArrayList<ClassCoche>();
        }
    }
}


