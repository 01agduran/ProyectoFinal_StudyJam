package duran.alvaro.christmas;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OpcionesActivity extends AppCompatActivity {

    private TextView Titulo;
    private TextView Galeria;
    private TextView Camara;
    private TextView Imagenes;
    private static final int ACTIVITY_SELECT_IMAGE = 1020,ACTIVITY_SELECT_FROM_CAMERA = 1040, ACTIVITY_SHARE = 1030;;
    private AlertDialog _photoDialog;
    private Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Titulo=(TextView)findViewById(R.id.tv_titulo_opciones);
        Typeface xmas=Typeface.createFromAsset(getAssets(),"fonts/xmas.ttf");
        Titulo.setTypeface(xmas);
        Camara=(TextView)findViewById(R.id.tv_camara);
        Galeria=(TextView)findViewById(R.id.tv_galeria);
        Imagenes=(TextView)findViewById(R.id.tv_imagenes);
        Typeface kr=Typeface.createFromAsset(getAssets(),"fonts/kr.TTF");
        Camara.setTypeface(kr);
        Typeface xmasregular=Typeface.createFromAsset(getAssets(),"fonts/xmasregular.ttf");
        Galeria.setTypeface(kr);
        Imagenes.setTypeface(xmas);
    }

public void  abiriGaleria(View v)
{
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    intent.setType("image/*");
    startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);

}

    public  void otraventana(View v)
    {
        Intent nuevo= new Intent(getApplicationContext(),CargaImagenActivity.class);
        startActivity(nuevo);

    }



}
