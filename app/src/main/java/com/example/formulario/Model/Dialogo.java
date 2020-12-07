package com.example.formulario.Model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.formulario.R;

public class Dialogo extends AppCompatDialogFragment {
    DatePicker Picker;
    private ExampleDialogListener Listener;
    private Button Bot;
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.fecha,null);
        Picker= view.findViewById(R.id.datePicker1);
        builder.setView(view).setTitle("Ingrese la fecha").setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Picker.setMaxDate(System.currentTimeMillis());
                String Dia=String.valueOf(Picker.getDayOfMonth());
                String Mes=String.valueOf(Picker.getMonth()+1);
                String anio=String.valueOf(Picker.getYear());
                Listener.texto(Dia,Mes,anio);
            }
        });
        return builder.create();
    }
    public interface ExampleDialogListener{
        void texto(String Dia,String Mes,String anio);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Listener=(ExampleDialogListener)context;
    }
}
