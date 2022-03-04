package org.izv.flora.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddImagenViewModel;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddImagenActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private Intent resultadoImagen = null;
    private EditText etNombreImagen, etDescripcion, etIdFlora2;
    private AddImagenViewModel aivm;
    private ImageView btSelectImage;
    private Bitmap btp_img = null;
    private InputStream in_stream;
    private MainActivityViewModel mavm;
    private TextView tvIdFlora;
    private long id;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imagen);
        initialize();
    }

    private void initialize() {
        id = -1;
        pos = 0;
        tvIdFlora = findViewById(R.id.tvIdFlora);
        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class); //obtengo viewmodel de la actividad
        MutableLiveData<ArrayList<Flora>> floraList = mavm.getFloraLiveData();
        mavm.getFlora();

        floraList.observe(this,  floras -> { //cuando se llene la lista veremos los cambios
            if(MainActivity.dondepasa==1){
                tvIdFlora.setText(floras.get(mavm.getFloraLiveData().getValue().size()-1).getNombre()+"");
                id= floras.get(mavm.getFloraLiveData().getValue().size()-1).getId();
            }else if(MainActivity.dondepasa==2){
                tvIdFlora.setText(MainActivity.nombre);
                id = MainActivity.id;
            }

        });

        launcher = getLauncher();
        etDescripcion = findViewById(R.id.etDescripcion);
        etNombreImagen = findViewById(R.id.etNombreImagen);
        btSelectImage = findViewById(R.id.btSelectImage);
        btSelectImage.setOnClickListener(v -> {
            try {
                selectImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button btAddImagen = findViewById(R.id.btAddImagen);
        TextInputLayout tpNombre = findViewById(R.id.textInputLayoutNombre);
        btAddImagen.setOnClickListener(v -> {
            if(etNombreImagen.getText().toString().trim().equals("")){
                tpNombre.setError("Debe introducir un nombre para la imagen");
            }else{
                uploadDataImage();
            }
        });
        Button btCancel = findViewById(R.id.btCancel);
        btCancel.setOnClickListener(v-> {
            finish();
        });
        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);
    }

    private void uploadDataImage() {
        String nombre = etNombreImagen.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        TextInputLayout tpNombreImagen = findViewById(R.id.textInputLayoutNombre);


        if(!(nombre.trim().isEmpty() ||
                id<0 ||
                resultadoImagen == null)) {
            Imagen imagen = new Imagen();
            imagen.nombre = nombre;
            imagen.descr = descripcion;
            imagen.idflora = id;
            aivm.saveImagen(resultadoImagen, imagen);
            Intent intent = new Intent(AddImagenActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            if(nombre.trim().isEmpty()){
                tpNombreImagen.setError("Introduzca un nombre");
            }
        }
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        //copyData(result.getData());
                        resultadoImagen = result.getData();

                        try {
                            in_stream = getContentResolver().openInputStream(resultadoImagen.getData());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        btp_img = BitmapFactory.decodeStream(in_stream);
                        resultadoImagen.toString();
                        try {
                            in_stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        btSelectImage.setImageBitmap(btp_img);
                    }
                }
        );
    }

    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    void selectImage() throws IOException {
        Intent intent = getContentIntent();
        launcher.launch(intent);
    }
}