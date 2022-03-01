package org.izv.flora.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.izv.flora.MainActivity;
import org.izv.flora.R;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.viewmodel.AddFloraViewModel;

public class AddFloraActivity extends AppCompatActivity {

    private EditText etNombre, etFamilia, etIdentificacion, etAltitud, etHabitat, etFitosociologia,
            etBiotipo, etBiologiareproductiva, etFloracion, etFructificacion, etExpresionsexual,
            etPolinizacion, etDispersion, etNumeroCromosomatico, etReproduccionAsexual,
            etDistribucion, etBiologia, etDemografia, etAmenazas, etMedidasPropuestas;

    private Button btAdd, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flora);
        initialize();
    }

    private void initialize() {
        AddFloraViewModel avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
        avm.getAddFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong > 0) {
                    finish();
                } else {
                    Toast.makeText(AddFloraActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFlora();
            }
        });

        btCancel = findViewById(R.id.btCancelAdd);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addFlora(){
        etNombre = findViewById(R.id.etNombre);
        etFamilia = findViewById(R.id.etFamilia);
        etIdentificacion = findViewById(R.id.etIdentificacion);
        etAltitud = findViewById(R.id.etAltitud);
        etHabitat = findViewById(R.id.etHabitat);
        etFitosociologia = findViewById(R.id.etFitosociologia);
        etBiotipo = findViewById(R.id.etBiotipo);
        etBiologiareproductiva = findViewById(R.id.etBiologiareproductiva);
        etFloracion = findViewById(R.id.etFloracion);
        etFructificacion = findViewById(R.id.etFructificacion);
        etExpresionsexual = findViewById(R.id.etExpresionSexual);
        etPolinizacion = findViewById(R.id.etPolinizacion);
        etDispersion = findViewById(R.id.etDispersion);
        etNumeroCromosomatico = findViewById(R.id.etNumeroCromosomatico);
        etReproduccionAsexual = findViewById(R.id.etReproduccionAsexual);
        etDistribucion = findViewById(R.id.etDistribucion);
        etBiologia = findViewById(R.id.etBiologia);
        etDemografia = findViewById(R.id.etDemografia);
        etAmenazas = findViewById(R.id.etAmenazas);
        etMedidasPropuestas = findViewById(R.id.etMedidasPropuestas);

        TextInputLayout tpNombre = findViewById(R.id.layoutNombre);
        TextInputLayout tpFamilia = findViewById(R.id.layoutFamily);
        TextInputLayout tpIdentificacion = findViewById(R.id.layoutIdentificacion);
        TextInputLayout tpAltitud = findViewById(R.id.layoutAltitud);
        TextInputLayout tpHabitat = findViewById(R.id.layoutHabitat);
        TextInputLayout tpFitosociologia = findViewById(R.id.layoutfitosociologia);
        TextInputLayout tpBiotipo = findViewById(R.id.layoutBiotipo);
        TextInputLayout tpBiologiareproductiva = findViewById(R.id.layoutBiologiareproductiva);
        TextInputLayout tpFloracion = findViewById(R.id.layoutFloracion);
        TextInputLayout tpFructificacion = findViewById(R.id.layoutFructificacion);
        TextInputLayout tpExpresionsexual = findViewById(R.id.layoutExpresionSexual);
        TextInputLayout tpPolinizacion = findViewById(R.id.layoutPolinizacion);
        TextInputLayout tpDispersion = findViewById(R.id.layoutDispersion);
        TextInputLayout tpNumeroCromosomatico = findViewById(R.id.layoutNumeroCromosomatico);
        TextInputLayout tpReproduccionAsexual = findViewById(R.id.layoutReproduccionAsexual);
        TextInputLayout tpDistribucion = findViewById(R.id.layoutDistribucion);
        TextInputLayout tpBiologia = findViewById(R.id.layoutBiologia);
        TextInputLayout tpDemografia = findViewById(R.id.layoutDemografia);
        TextInputLayout tpAmenazas = findViewById(R.id.layoutAmenazas);
        TextInputLayout tpMedidasPropuestas = findViewById(R.id.layoutMedidasPropuestas);

        if(etNombre.getText().toString().trim().equals("") || etFamilia.getText().toString().trim().equals("")
                || etIdentificacion.getText().toString().trim().equals("") || etAltitud.getText().toString().trim().equals("")
                || etHabitat.getText().toString().trim().equals("") || etFitosociologia.getText().toString().trim().equals("")
                || etBiotipo.getText().toString().trim().equals("") || etBiologiareproductiva.getText().toString().trim().equals("")
                || etFloracion.getText().toString().trim().equals("") || etFructificacion.getText().toString().trim().equals("")
                || etExpresionsexual.getText().toString().trim().equals("") || etPolinizacion.getText().toString().trim().equals("")
                || etDispersion.getText().toString().trim().equals("") || etNumeroCromosomatico.getText().toString().trim().equals("")
                || etReproduccionAsexual.getText().toString().trim().equals("") || etDistribucion.getText().toString().trim().equals("")
                || etBiologia.getText().toString().trim().equals("") || etDemografia.getText().toString().trim().equals("")
                || etAmenazas.getText().toString().trim().equals("") || etMedidasPropuestas.getText().toString().trim().equals("")){

            if(etNombre.getText().toString().trim().equals("")){
                tpNombre.setError("Debe poner el nombre");
            }
            if(etFamilia.getText().toString().trim().equals("")){
                tpFamilia.setError("Debe poner una familia");
            }
            if(etIdentificacion.getText().toString().trim().equals("")){
                tpIdentificacion.setError("Indica una identificacion");
            }
            if(etAltitud.getText().toString().trim().equals("")){
                tpAltitud.setError("Debe poner una altitud");
            }
            if(etHabitat.getText().toString().trim().equals("")){
                tpHabitat.setError("Debe poner un habitat");
            }
            if(etFitosociologia.getText().toString().trim().equals("")){
                tpFitosociologia.setError("Debe poner una fitosociologia");
            }
            if(etBiotipo.getText().toString().trim().equals("")){
                tpBiotipo.setError("Debe poner el biotipo");
            }
            if(etBiologiareproductiva.getText().toString().trim().equals("")){
                tpBiologiareproductiva.setError("Debe poner su biologia reproductiva");
            }
            if(etFloracion.getText().toString().trim().equals("")){
                tpFloracion.setError("Debe poner la floracion");
            }
            if(etFructificacion.getText().toString().trim().equals("")){
                tpFructificacion.setError("Debe poner la fructificación");
            }
            if(etExpresionsexual.getText().toString().trim().equals("")){
                tpExpresionsexual.setError("Debe poner su expresión sexual");
            }
            if(etPolinizacion.getText().toString().trim().equals("")){
                tpPolinizacion.setError("Debe poner la polinización");
            }
            if(etDispersion.getText().toString().trim().equals("")){
                tpDispersion.setError("Debe poner la dispersión");
            }
            if(etNumeroCromosomatico.getText().toString().trim().equals("")){
                tpNumeroCromosomatico.setError("Debe poner el número cromosomático");
            }
            if(etReproduccionAsexual.getText().toString().trim().equals("")){
                tpReproduccionAsexual.setError("Debe poner si tiene reproducción asexual");
            }
            if(etDistribucion.getText().toString().trim().equals("")){
                tpDistribucion.setError("Debe poner su distribución");
            }
            if(etBiologia.getText().toString().trim().equals("")){
                tpBiologia.setError("Debe poner su biologia");
            }
            if(etDemografia.getText().toString().trim().equals("")){
                tpDemografia.setError("Debe poner la demografia");
            }
            if(etAmenazas.getText().toString().trim().equals("")){
                tpAmenazas.setError("Debe poner las amenazas");
            }
            if(etMedidasPropuestas.getText().toString().trim().equals("")){
                tpMedidasPropuestas.setError("Debe proponer alguna medida");
            }

            Toast toast = Toast.makeText(AddFloraActivity.this, "Debe completar todos los campos", Toast.LENGTH_LONG);
            toast.show();

        }else{
            AddFloraViewModel avm = new ViewModelProvider(this).get(AddFloraViewModel.class);
            Flora flora = new Flora();
            flora.setNombre(etNombre.getText().toString());
            flora.setFamilia(etFamilia.getText().toString());
            flora.setIdentificacion(etIdentificacion.getText().toString());
            flora.setAltitud(etAltitud.getText().toString());
            flora.setHabitat(etHabitat.getText().toString());
            flora.setFitosociologia(etFitosociologia.getText().toString());
            flora.setBiotipo(etBiotipo.getText().toString());
            flora.setBiologia_reproductiva(etBiologiareproductiva.getText().toString());
            flora.setFloracion(etFloracion.getText().toString());
            flora.setFructificacion(etFructificacion.getText().toString());
            flora.setExpresion_sexual(etExpresionsexual.getText().toString());
            flora.setPolinizacion(etPolinizacion.getText().toString());
            flora.setDispersion(etDispersion.getText().toString());
            flora.setNumero_cromosomatico(etNumeroCromosomatico.getText().toString());
            flora.setReproduccion_asexual(etReproduccionAsexual.getText().toString());
            flora.setDistribucion(etDistribucion.getText().toString());
            flora.setBiologia(etBiologia.getText().toString());
            flora.setDemografia(etDemografia.getText().toString());
            flora.setAmenazas(etAmenazas.getText().toString());
            flora.setMedidas_propuestas(etMedidasPropuestas.getText().toString());
            avm.createFlora(flora);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}