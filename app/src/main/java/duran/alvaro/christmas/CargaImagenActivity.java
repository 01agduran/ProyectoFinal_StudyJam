package duran.alvaro.christmas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class CargaImagenActivity extends AppCompatActivity {
    private static final int ACTIVITY_SELECT_IMAGE = 1020;
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private static String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    private final int PHOTO_CODE = 1200;
    private final int SELECT_PICTURE = 1020;
    private Bitmap b;
    private ImageView imagen;
    boolean sw=true,sw2=true;


    private TextView Mensaje;
    private EditText Texto;
    private int coun_imagenes = 0;
    private int coun_imagenes2 = 0;
    private Integer [] imagenes={
            R.drawable.n02,
            R.drawable.n03,
            R.drawable.n04,
            R.drawable.n05,
            R.drawable.n05b,
            R.drawable.n06,
            R.drawable.n06b,
            R.drawable.n07,
            R.drawable.n07b,
            R.drawable.n08,
            R.drawable.n08b,
            R.drawable.n09,
            R.drawable.n09b,
            R.drawable.n10,
            R.drawable.n11,
            R.drawable.n11b,
            R.drawable.n12,
            R.drawable.n12b,
            R.drawable.n13,
            R.drawable.n13b,
            R.drawable.n14,
            R.drawable.n14b};

    //-----------TEMPORAL

    //====================


    //BOTONESSSSSSSSSSSSSSSSS
    private Button addText;
    private Button saveImage;
private ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_imagen);
        imagen = (ImageView) findViewById(R.id.iv_carga_imagen);
        addText = (Button) findViewById(R.id.btn_pone_texto);
        saveImage = (Button) findViewById(R.id.btn_guarda_imagen);
        atras=(ImageButton)findViewById(R.id.btn_atras);
        imagen.setImageResource(R.drawable.christmas);
        saveImage.setVisibility(View.INVISIBLE);
        addText.setVisibility(View.INVISIBLE);
        atras.setVisibility(View.INVISIBLE);


    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    String dir_photo_taken = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir_photo_taken);
                }
                break;
            case SELECT_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri path_from_galery = data.getData();
                    imagen.setImageURI(path_from_galery);
                }
                break;
        }
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        imagen.setImageBitmap(bitmap);
    }

    public class CanvasWithText extends View {

        String str;

        public CanvasWithText(Context context, String str) {
            super(context);
            this.str = str;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint pBackground = new Paint();
            pBackground.setColor(Color.WHITE);
            canvas.drawRect(0, 0, 512, 512, pBackground);
            Paint pText = new Paint();
            pText.setColor(Color.BLACK);
            pText.setTextSize(20);
            canvas.drawText(str, 100, 100, pText);
        }
    }

    public void poneTexto(View v) {
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(CargaImagenActivity.this);
        final EditText edittext = new EditText(CargaImagenActivity.this);
        alert.setTitle(getResources().getString(R.string.act_canvas_demo));
        alert.setMessage(getResources().getString(R.string.str_add_text));
        alert.setView(edittext);
        alert.setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String str = edittext.getText().toString();
                Bitmap bm = BitmapFactory.decodeResource(getResources(), imagenes[coun_imagenes]);
                Bitmap.Config config = bm.getConfig();
                int width = bm.getWidth();
                int height = bm.getHeight();
                Bitmap newImage = Bitmap.createBitmap(width, height, config);
                Canvas c = new Canvas(newImage);
                c.drawBitmap(bm, 0, 0, null);
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(80);

                int tam_cadena=str.length();
                if (tam_cadena>27)
                {
                    int con_esp_y=100;
                    int cont_cad=0;
                    int div=tam_cadena/27;
                    for (int a=1;a<=div;a++)
                    {
                        String tem=str.substring(cont_cad,cont_cad+27);
                        c.drawText(tem, 0, con_esp_y, paint);
                        con_esp_y=con_esp_y+100;
                        cont_cad=cont_cad+27;
                        if (a==div)
                        {
                             tem=str.substring(cont_cad,str.length());
                            c.drawText(tem, 0, con_esp_y, paint);
                        }
                    }
                }
                b = newImage;
                imagen.setImageBitmap(newImage);
//*********************************************************************************************************
            }
        });
        alert.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();
    }


    public void guardaImagen(View v) {

        Calendar c = Calendar.getInstance();
        String Dia=Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String Mes=Integer.toString(c.get(Calendar.MONTH)+1);
        String Anio=Integer.toString(c.get(Calendar.YEAR));
        String Hora=Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String Minutos=Integer.toString(c.get(Calendar.MINUTE));
        String sDate =Dia+"-"+Mes+"-"+Anio+"-"+Hora+"-"+Minutos;
//

        //    final File dir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
        final File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "AlvaroDuran");
        if (!dir.exists()) {
            dir.mkdirs();
        }


        File output = new File(dir, sDate+".jpg");
        OutputStream os = null;

        try {
            os = new FileOutputStream(output);
            b.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();

            final Handler handler = new Handler();

            //this code will scan the image so that it will appear in your gallery when you open next time
            MediaScannerConnection.scanFile(CargaImagenActivity.this, new String[]{output.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(CargaImagenActivity.this, CargaImagenActivity.this.getResources().getString(R.string.str_save_image_text) + dir.getPath(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
            );
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void cambiaImagenSiguiente(View v)
    {

        imagen.setVisibility(View.VISIBLE);
        saveImage.setVisibility(View.VISIBLE);
        addText.setVisibility(View.VISIBLE);
        atras.setVisibility(View.VISIBLE);
        int top_ima=imagenes.length;
        sw2=true;
        if(coun_imagenes2==top_ima-1&&sw)
        {
            Toast toast=Toast.makeText(getApplicationContext(),"Se ha llegado a la ultima imagen",Toast.LENGTH_SHORT);
            toast.show();
            imagen.setImageResource(imagenes[coun_imagenes2]);
          sw=false;
       }
        else
        {
            if(sw)
            {
                imagen.setImageResource(imagenes[coun_imagenes2]);
                coun_imagenes2++;
                coun_imagenes=coun_imagenes2-1;
            }

        }
    }
    public void cambiaImagenAtras(View v)
    {sw=true;


            if (sw2)
            {
                coun_imagenes--;
                imagen.setImageResource(imagenes[coun_imagenes]);
                sw2=true;
                if (coun_imagenes==0)
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Se ha llegado a la Primera imagen",Toast.LENGTH_SHORT);
                    toast.show();
                    sw2=false;
                    coun_imagenes=0;
                    coun_imagenes2=0;
                }
            }

     }


}
