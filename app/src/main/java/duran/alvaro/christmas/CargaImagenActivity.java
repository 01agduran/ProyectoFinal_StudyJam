package duran.alvaro.christmas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class CargaImagenActivity extends AppCompatActivity {
    private static final int ACTIVITY_SELECT_IMAGE = 1020;
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private static String TEMPORAL_PICTURE_NAME ="temporal.jpg";

    private final int  PHOTO_CODE=1200;
    private final int  SELECT_PICTURE=1020;

    private  ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_imagen);
        imagen=(ImageView)findViewById(R.id.iv_carga_imagen);

    }

    public void cargaImagen(View v)
    {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);


    }
    public  void cargaImagen2(View v)
    {
        final CharSequence [] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder= new  AlertDialog.Builder(CargaImagenActivity.this);
         builder.setTitle("Elige una opcion");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
            if (opciones[seleccion]=="Tomar Foto"){
                abrirCamara();
            }
                else
            {
                if (opciones[seleccion]=="Elegir de Galeria"){
                    Intent intentca=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intentca.setType("image/*");
                   startActivityForResult(intentca.createChooser(intentca,"Selecciona una imagen"),SELECT_PICTURE);

                }
                else
                {
                    if (opciones[seleccion]=="Cancelar")
                    {
                        dialog.dismiss();

                    }
                }
            }
            }
        });
        builder.show();
    }

    private void abrirCamara() {
    File file= new File(Environment.getExternalStorageDirectory(),MEDIA_DIRECTORY);
       file.mkdirs();
        String path=Environment.getExternalStorageDirectory()+File.separator+MEDIA_DIRECTORY+File.separator+TEMPORAL_PICTURE_NAME;
    File  newfile=new File(path);
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newfile));
        startActivityForResult(intent,PHOTO_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case PHOTO_CODE:
                if (resultCode==RESULT_OK)
                {
                String dir=Environment.getExternalStorageDirectory()+File.separator+MEDIA_DIRECTORY+File.separator+TEMPORAL_PICTURE_NAME;
                decodeBitmap(dir);
                }
                break;
            case SELECT_PICTURE:
                if (resultCode==RESULT_OK)
                {
                    Uri path=data.getData();
                    imagen.setImageURI(path);
                }
                break;
            }
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap= BitmapFactory.decodeFile(dir);
        imagen.setImageBitmap(bitmap);
    }
}
