package duran.alvaro.christmas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.text.TimeZoneFormat;
import android.icu.util.TimeZone;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

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

//    private Button BtnSiguiente;
//    private Button BtnAtras;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_imagen);
        imagen = (ImageView) findViewById(R.id.iv_carga_imagen);
        addText = (Button) findViewById(R.id.btn_pone_texto);
        saveImage = (Button) findViewById(R.id.btn_guarda_imagen);
        imagen.setVisibility(View.INVISIBLE);
        saveImage.setVisibility(View.INVISIBLE);
        addText.setVisibility(View.INVISIBLE);


    }

    public void cargaImagen(View v) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
    }

    public void cargaImagen2(View v) {
        final CharSequence[] opciones = {"Tomar Foto", "Elegir de Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(CargaImagenActivity.this);
        builder.setTitle("Elige una opcion");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (opciones[seleccion] == "Tomar Foto") {
                    abrirCamara();
                } else {
                    if (opciones[seleccion] == "Elegir de Galeria") {
                        Intent intentca = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intentca.setType("image/*");
                        startActivityForResult(intentca.createChooser(intentca, "Selecciona una imagen"), SELECT_PICTURE);

                    } else {
                        if (opciones[seleccion] == "Cancelar") {
                            dialog.dismiss();

                        }
                    }
                }
            }
        });
        builder.show();
    }

    private void abrirCamara() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();
        String path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
        File newfile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newfile));
        startActivityForResult(intent, PHOTO_CODE);

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

//AQUI ES EL PRIMER METODO PARA  GUARDAR LA IMAGEN CON  EL TEXTO ********************************************
//        View v = new CanvasWithText(getApplicationContext(), str);
//        Bitmap bitmap = Bitmap.createBitmap(500/*width*/, 500/*height*/, Bitmap.Config.ARGB_8888);
//
//        Canvas canvas = new Canvas(bitmap);
//        v.draw(canvas);
//        imagen=(ImageView)findViewById(R.id.iv_carga_imagen);
//        b = bitmap;     //for saving "b" to the sdcard
//        imagen.setImageBitmap(bitmap);
//***********************************************************************************************************

//SEGUNDO METODO **********************************************************************************

                Bitmap bm = BitmapFactory.decodeResource(getResources(), imagenes[coun_imagenes]);
                //  Bitmap bm = ((BitmapFactory.decodeResource(getResources(),imagen.getDrawable()).getBitmap());
                Bitmap.Config config = bm.getConfig();
                int width = bm.getWidth();
                int height = bm.getHeight();
                Bitmap newImage = Bitmap.createBitmap(width, height, config);
                Canvas c = new Canvas(newImage);
                c.drawBitmap(bm, 0, 0, null);
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(100);
                c.drawText(str, 250, 150, paint);
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

    public void poneTexto2(View v) {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.picana);
        Bitmap.Config config = bm.getConfig();
        int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newImage = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(newImage);
        canvas.drawBitmap(bm, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        canvas.drawText("Some Text", 0, 25, paint);
        imagen.setImageBitmap(newImage);
    }

    public void poneTexto3(View v) {
        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.picana);
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(CargaImagenActivity.this);
        final EditText edittext = new EditText(CargaImagenActivity.this);

        alert.setTitle(getResources().getString(R.string.act_canvas_demo));
        alert.setMessage(getResources().getString(R.string.str_add_text));

        alert.setView(edittext);
        alert.setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String str = edittext.getText().toString();
                View v = new CanvasWithText(getApplicationContext(), str);
                Bitmap bitmap = Bitmap.createBitmap(500/*width*/, 500/*height*/, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawBitmap(bm, 0, 0, null);
                v.draw(canvas);
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(20);
                //     canvas.drawText(str, 0, 25, paint);
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
        final File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "AAAAAAA");
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



    public void cambiarImagen(View v) {
        coun_imagenes=1;
                switch (coun_imagenes) {
            case 1:
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.n02);
                //  Bitmap bm = ((BitmapFactory.decodeResource(getResources(),imagen.getDrawable()).getBitmap());

                Bitmap.Config config = bm.getConfig();
                int width = bm.getWidth();
                int height = bm.getHeight();
//                Matrix matrix = new Matrix();
//                matrix.postScale(width, height);
                Bitmap newImage = Bitmap.createBitmap(300 , 300, Bitmap.Config.ARGB_8888);
                //Bitmap newImage = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
                Canvas c = new Canvas(newImage);
                c.drawBitmap(bm, 0, 0, null);
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(100);
             //   c.drawText(str, 250, 150, paint);
                b = newImage;
              //  imagen.setImageBitmap(newImage);
                imagen.setImageResource(R.drawable.n03);

                coun_imagenes++;
                break;
            case 2:
                 bm = BitmapFactory.decodeResource(getResources(), R.drawable.n02);
                //  Bitmap bm = ((BitmapFactory.decodeResource(getResources(),imagen.getDrawable()).getBitmap());

                config = bm.getConfig();
                width = bm.getWidth();
                height = bm.getHeight();
                newImage = Bitmap.createBitmap(2000 , 2000, Bitmap.Config.ARGB_8888);
                c = new Canvas(newImage);
                c.drawBitmap(bm, 0, 0, null);
                paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(100);
                //   c.drawText(str, 250, 150, paint);
                b = newImage;
                imagen.setImageBitmap(newImage);
                coun_imagenes++;
                break;
//            case 3:
//
//                imagen.setImageResource(R.drawable.f04);
//                c++;
//                break;
//            case 4:
//
//                imagen.setImageResource(R.drawable.n04);
//                c++;
//                break;
            default:
                imagen.setImageDrawable(null);
                break;


        }


         // imagen.setImageResource(R.drawable.f01);





    }
}
