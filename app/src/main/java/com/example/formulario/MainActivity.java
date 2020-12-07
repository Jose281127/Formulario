package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulario.Model.Alumno;
import com.example.formulario.Model.Dialogo;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Dialogo.ExampleDialogListener {
    int a,p,md,mm,my,m2,m1,m0,anios;
    int w=0;
    String saludo,carr;
    Alumno N;
    TextView Fech;
    EditText etNom,etcuenta,etapellido;
    DatePicker picker;
    Spinner Spin;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=50;

        etcuenta=findViewById(R.id.Numcuen);
        picker=(DatePicker)findViewById(R.id.datePicker1);

        etapellido=findViewById(R.id.Apellidos);
        etNom=findViewById(R.id.Nombres);
        Fech=findViewById(R.id.Fecha);
        Spin=(Spinner) findViewById(R.id.spinner);
       // picker.setMaxDate(System.currentTimeMillis());

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Carreras, R.layout.textview);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        Spin.setAdapter(adapter);
        Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                if(position!=0) {
                    Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    carr=(String) adapterView.getItemAtPosition(position);
                    p=position;
                }
                else {
                    p=0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });
    }

    public void clic(View view){
        if(validacion()) {
            N=new Alumno(etNom.getText().toString(),etcuenta.getText().toString(),etapellido.getText().toString(),String.valueOf(conseguianios()),carr,String.valueOf(p));


            Intent intent = new Intent(this, MainActivity2.class);
            Bundle bundle = new Bundle();
            bundle.putInt("a", a);
            bundle.putString("saludo", saludo);
            bundle.putSerializable("alumno",N);
            intent.putExtras(bundle);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
    public boolean validacion(){
        Pattern arr[];

        boolean ban=true,conf=true,confapp=true,coffec=true;
        arr=new Pattern[3];
        arr[0] = Pattern.compile("((Á|É|Í|Ó|Ú|Ñ)|[A-Z])([a-z]|(á|é|í|ú|ó|ñ))*|((Á|É|Í|Ó|Ú|Ñ)|[A-Z])([a-z]|(á|é|í|ú|ó|ñ))*\\s((Á|É|Í|Ó|Ú|Ñ)|[A-Z])([a-z]|(á|é|í|ú|ó|ñ))*" );
        arr [1] = Pattern.compile("((Á|É|Í|Ó|Ú|Ñ)|[A-Z]|[a-z]|(á|é|í|ú|ó|ñ))+|((Á|É|Í|Ó|Ú|Ñ)|[A-Z]|[a-z]|(á|é|í|ú|ó|ñ))+\\s((Á|É|Í|Ó|Ú|Ñ)|[A-Z]|[a-z]|(á|é|í|ú|ó|ñ))+");
        arr[2] =Pattern.compile("([A-Z]|Ñ)[a-z]*(á|é|í|ú|ó)?ñ?[a-z]*|(Á|É|Í|Ó|Ú)[a-z]*ñ?[a-z]*|([A-Z]|Ñ)[a-z]*(á|é|í|ú|ó)?ñ?[a-z]*\\s([A-Z]|Ñ)[a-z]*(á|é|í|ú|ó)?ñ?[a-z]*|(Á|É|Í|Ó|Ú)[a-z]*ñ?[a-z]*\\s(Á|É|Í|Ó|Ú)[a-z]*ñ?[a-z]*|([A-Z]|Ñ)[a-z]*(á|é|í|ú|ó)?ñ?[a-z]*\\s(Á|É|Í|Ó|Ú)[a-z]*ñ?[a-z]*|(Á|É|Í|Ó|Ú)[a-z]*ñ?[a-z]*\\s([A-Z]|Ñ)[a-z]*(á|é|í|ú|ó)?ñ?[a-z]*");
        Matcher mat = arr[1].matcher(etNom.getText());
        if(etNom.getText().toString().equals("")){
            etNom.setError(getResources().getString(R.string.ErrorName));
            ban= false;
            conf=false;
        }

        if(!mat.matches()&&conf){
            etNom.setError(getResources().getString(R.string.ErrorName2));
            ban= false;
            conf=false;
        }
        if(etapellido.getText().toString().equals("")){
            etapellido.setError(getResources().getString(R.string.ErrorApellido));
            ban= false;
            confapp=false;
        }
        mat = arr[1].matcher(etapellido.getText());
        if(!mat.matches()&&confapp){
            etapellido.setError(getResources().getString(R.string.ErrorApellido2));
            ban= false;
            confapp=false;
        }
        mat=arr[0].matcher(etNom.getText());
        if(!mat.matches()&&conf){
            etNom.setError(getResources().getString(R.string.ErrorName3));
            conf=false;
            ban=false;
        }
        mat=arr[0].matcher(etapellido.getText());
        if(!mat.matches()&&confapp){
            etapellido.setError(getResources().getString(R.string.ErrorApellido3));
            conf=false;
            ban=false;
        }
        mat=arr[2].matcher(etNom.getText());
        if(!mat.matches()&&conf){
            etNom.setError(getResources().getString(R.string.ErrorName4));
            conf=false;
            ban=false;
        }
        mat=arr[2].matcher(etapellido.getText());
        if(!mat.matches()&&confapp){
            etapellido.setError(getResources().getString(R.string.ErrorApellido4));
            conf=false;
            ban=false;
        }
       if (p==0){
           ((TextView)Spin.getSelectedView()).setError(getResources().getString(R.string.Errorcarrera));
           Toast.makeText(this,getResources().getString(R.string.Errorcarrera),Toast.LENGTH_LONG).show();
           ban= false;
       }

        if(etcuenta.getText().toString().equals("")||etcuenta.getText().toString().length()<9){
            etcuenta.setError(getResources().getString(R.string.Errorcuenta));
            ban= false;
        }
        if(Fech.getText().toString().equals(getResources().getString(R.string.IngFecha))&&coffec){
            //Fech.setError(getResources().getString(R.string.Errorfec));
            Toast.makeText(this,getResources().getString(R.string.Errorfec),Toast.LENGTH_LONG).show();
            ban=false;
            coffec=false;
        }


if(coffec) {
    if ((m0 - my) <= 10) {
        if (m0 - my == 10) {
            if (m1 - mm <= 0) {
                if (m2 - md < 0) {
                    //Fech.setError(getResources().getString(R.string.Errorfec2));
                    Toast.makeText(this, getResources().getString(R.string.Errorfec2), Toast.LENGTH_LONG).show();

                    ban = false;
                    coffec=false;
                }
            }
        } else {
           // Fech.setError(getResources().getString(R.string.Errorfec2));
            Toast.makeText(this, getResources().getString(R.string.Errorfec2), Toast.LENGTH_LONG).show();
            ban = false;
            coffec=false;
        }

    }
}
        if(!ban){
            mp=MediaPlayer.create(this,R.raw.error);
            mp.start();
        }
        else{
            mp=MediaPlayer.create(this,R.raw.jump);
            mp.start();
        }
        return ban;
    }
    public void abrirdialogo(){
        Dialogo ejemplo=new Dialogo();
        ejemplo.show(getSupportFragmentManager(),"Dialogo");
        //Fech.setText(picker.getDayOfMonth() + "/" + (picker.getMonth() + 1) + "/" + picker.getYear());

    }
   public int conseguianios(){

        if(m1-mm<=0){
            if (m2-md<0){
                return m0-my-1;
            }
            else{
                return m0-my;
            }
        }
        else {
            return m0-my;
        }
    }
    public void clic_fecha(View view) {
        final Calendar now = Calendar.getInstance();
        abrirdialogo();
        now.getTime();
        SimpleDateFormat dfDate_day= new SimpleDateFormat("E, dd/MM/yyyy HH:mm:ss");


        String[] an= dfDate_day.format(now.getTime()).split(" ");

        //int omega=Calendar.YEAR;
        //int Omega=now.get(Calendar.YEAR);
        String[] bn=an[1].split("/");
       //Toast.makeText(this,bn[2],Toast.LENGTH_SHORT).show();
        m2=Integer.parseInt(bn[0]);
        m1=Integer.parseInt(bn[1]);
        m0=Integer.parseInt(bn[2]);

    }


    @Override
    public void texto(String Dia, String Mes, String anio) {
        if(w==0){
            Fech.setTextSize(TypedValue.COMPLEX_UNIT_SP,Fech.getTextSize()-15);
            w=1;
        }
        Fech.setText(Dia+"/"+Mes+"/"+anio);

       // Toast.makeText(this,anio,Toast.LENGTH_SHORT).show();
        my=Integer.parseInt(anio);
        mm=Integer.parseInt(Mes);
        md=Integer.parseInt(Dia);
    }
}
