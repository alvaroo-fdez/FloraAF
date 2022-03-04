package org.izv.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.adapter.FloraAdapter;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.entity.Imagen;
import org.izv.flora.viewmodel.AddImagenViewModel;
import org.izv.flora.viewmodel.DeleteFloraViewModel;
import org.izv.flora.viewmodel.DeleteImagenViewModel;
import org.izv.flora.viewmodel.EditFloraViewModel;

public class EditFloraActivity extends AppCompatActivity {

    private EditText etNombre, etFamilia, etIdentificacion, etAltitiud, etHabitat, etFitosociologia, etBiotopo, etBiologiaReproducctiva, etFloracion, etFructificacion,
            etExpresionSexual, etPolinizacion, etDispersion, etNumeroCromatico,etReproduccionAsexual,
            etDistribucion,etBiologia,etDemografia,etMedidasPropuestas,etAmenazas;
    private Button btEdit, btDelete, btCancel;
    private AddImagenViewModel aivm;
    Flora flora = new Flora();
    Imagen imagen = new Imagen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flora);
        initialize();
    }

    private void initialize() {
        FloraAdapter floraAdapter = new FloraAdapter(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        flora = getIntent().getExtras().getParcelable("flora");

        etNombre = findViewById(R.id.etNombre2);
        etFamilia = findViewById(R.id.etFamilia2);
        etIdentificacion = findViewById(R.id.etIdentificacion2);
        etAltitiud = findViewById(R.id.etAltitud2);
        etHabitat = findViewById(R.id.etHabitat2);
        etFitosociologia = findViewById(R.id.etFitosociologia2);
        etBiotopo = findViewById(R.id.etBiotipo2);
        etBiologiaReproducctiva = findViewById(R.id.etBiologiareproductiva2);
        etFloracion = findViewById(R.id.etFloracion2);
        etFructificacion = findViewById(R.id.etFructificacion2);
        etExpresionSexual = findViewById(R.id.etExpresionSexual2);
        etPolinizacion = findViewById(R.id.etPolinizacion2);
        etDispersion = findViewById(R.id.etDispersion2);
        etNumeroCromatico = findViewById(R.id.etNumeroCromosomatico2);
        etReproduccionAsexual = findViewById(R.id.etReproduccionAsexual2);
        etDistribucion = findViewById(R.id.etDistribucion2);
        etBiologia = findViewById(R.id.etBiologia2);
        etDemografia = findViewById(R.id.etDemografia2);
        etMedidasPropuestas = findViewById(R.id.etMedidasPropuestas2);
        etAmenazas = findViewById(R.id.etAmenazas2);

        aivm = new ViewModelProvider(this).get(AddImagenViewModel.class);

        EditFloraViewModel evm = new ViewModelProvider(this).get(EditFloraViewModel.class);
        evm.getEditFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v("xyzyx", "respuesta " + aLong);
                if(aLong > 0) {
                    finish();
                }
            }
        });

        etNombre.setText(flora.getNombre());
        etFamilia.setText(flora.getFamilia());
        etIdentificacion.setText(flora.getIdentificacion());
        etAltitiud.setText(flora.getAltitud());
        etHabitat.setText(flora.getHabitat());
        etFitosociologia.setText(flora.getFitosociologia());
        etBiotopo.setText(flora.getBiotipo());
        etBiologiaReproducctiva.setText(flora.getBiologia_reproductiva());
        etFloracion.setText(flora.getFloracion());
        etFructificacion.setText(flora.getFructificacion());
        etExpresionSexual.setText(flora.getExpresion_sexual());
        etPolinizacion.setText(flora.getPolinizacion());
        etDispersion.setText(flora.getDispersion());
        etNumeroCromatico.setText(flora.getNumero_cromosomatico());
        etReproduccionAsexual.setText(flora.getReproduccion_asexual());
        etDistribucion.setText(flora.getDistribucion());
        etBiologia.setText(flora.getBiologia());
        etDemografia.setText(flora.getDemografia());
        etMedidasPropuestas.setText(flora.getMedidas_propuestas());
        etAmenazas.setText(flora.getAmenazas());

        btEdit = findViewById(R.id.btEdit);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFlora();
                Log.v("xyzyx", String.valueOf(getFlora()));
                evm.editFlora(flora.getId(),getFlora());
                floraAdapter.notifyDataSetChanged();
                Intent intent = new Intent(EditFloraActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        DeleteFloraViewModel dvm = new ViewModelProvider(this).get(DeleteFloraViewModel.class);
        dvm.getDeleteFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v("xyzyx", "respuesta " + aLong);
                if(aLong > 0) {
                    finish();
                }
            }
        });

        btCancel = findViewById(R.id.bCancelEdit);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btDelete = findViewById(R.id.btDelete);

        DeleteImagenViewModel dim = new ViewModelProvider(this).get(DeleteImagenViewModel.class);
        dim.getDeleteImagenLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong > 0) {
                    finish();
                }
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("¿Desea borrar la flora seleccionada?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getFlora();
                                dvm.deleteFlora(flora.getId());
                                dim.deleteImagen(imagen.getId());

                                floraAdapter.notifyDataSetChanged();
                                Intent intent = new Intent(EditFloraActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) { }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Borrando Flora...");
                alert.show();
            }
        });


    }

    private Flora getFlora(){
        Flora flora = new Flora();
        flora.setNombre(etNombre.getText().toString());
        flora.setFamilia(etFamilia.getText().toString());
        flora.setIdentificacion(etIdentificacion.getText().toString());
        flora.setAltitud(etAltitiud.getText().toString());
        flora.setHabitat(etHabitat.getText().toString());
        flora.setFitosociologia(etFitosociologia.getText().toString());
        flora.setBiologia(etBiologia.getText().toString());
        flora.setBiologia_reproductiva(etBiologiaReproducctiva.getText().toString());
        flora.setFloracion(etFloracion.getText().toString());
        flora.setFructificacion(etFructificacion.getText().toString());
        flora.setExpresion_sexual(etExpresionSexual.getText().toString());
        flora.setPolinizacion(etPolinizacion.getText().toString());
        flora.setDispersion(etDispersion.getText().toString());
        flora.setNumero_cromosomatico(etNumeroCromatico.getText().toString());
        flora.setReproduccion_asexual(etReproduccionAsexual.getText().toString());
        flora.setDistribucion(etDistribucion.getText().toString());
        flora.setBiologia(etBiologia.getText().toString());
        flora.setDemografia(etDemografia.getText().toString());
        flora.setAmenazas(etAmenazas.getText().toString());
        flora.setMedidas_propuestas(etMedidasPropuestas.getText().toString());
        return flora;
    }




}