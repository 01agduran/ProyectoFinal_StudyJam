package duran.alvaro.christmas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView tvNombre;
    private TextView TvTitulo;
    private Button BtnCambiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNombre = (TextView) findViewById(R.id.tv_tiyulo);
        Typeface tf_bold=Typeface.createFromAsset(getAssets(),"fonts/xmas.ttf");
        tvNombre.setTypeface(tf_bold);
        tvNombre.setText("Feliz Navidad!!!");
      //  BtnCambiar=(Button)findViewById(R.id.btn_carga_imagen);

   }

    public void CambiaOpciones(View v)
    {
        try {
            Intent cambia= new Intent(getApplicationContext(),OpcionesActivity.class);
            startActivity(cambia);
        }
        catch (Exception e)
        {
            e.getMessage();
            System.out.println(e.getMessage().toString());
        }



    }

    public  void reseta_picana(View v)
    {
        Intent inten= new Intent(getApplicationContext(),CargaImagenActivity.class);
        startActivity(inten);


    }




}
