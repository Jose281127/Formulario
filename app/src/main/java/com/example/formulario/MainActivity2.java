package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulario.Model.Alumno;

public class MainActivity2 extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle=getIntent().getExtras();
        String saludo;
        TextView nom,anio,carrera,numcuenta;
        int a;
        Resources res = getResources();
        nom=findViewById(R.id.Nombre);
        anio=findViewById(R.id.anio);
        carrera=findViewById(R.id.carrera);
        numcuenta=findViewById(R.id.cuenta);
        TypedArray icons = res.obtainTypedArray(R.array.imagenes);
        Alumno Alumn;
        ImageView Imagen=(ImageView)findViewById(R.id.Imagen);

       if(bundle!=null){
            Alumn=(Alumno)bundle.getSerializable("alumno");
            saludo=bundle.getString("saludo","");
            a=bundle.getInt("a",0);
            //Toast.makeText(this,"El Nombre del alumno recibido es: "+Alumn.getNombre()+" y su numero de cuenta es "+Alumn.getNumCuenta()+"su carrera ees"+Alumn.getCarr()+"mm"+Alumn.getId(),Toast.LENGTH_LONG).show();
            nom.setText(getResources().getString(R.string.nombre)+":"+Alumn.getNombre()+" "+Alumn.getApellido());
            numcuenta.setText(getResources().getString(R.string.Cuenta)+":"+Alumn.getNumCuenta());
            anio.setText(Alumn.getFec()+getResources().getString(R.string.anio));
            carrera.setText(Alumn.getCarr());
            Imagen.setImageResource(icons.getResourceId(Integer.parseInt(Alumn.getId())-1,0));


        }

    }
}