package org.izv.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.izv.flora.model.Repository;
import org.izv.flora.model.entity.Flora;

public class EditImagenViewModel extends AndroidViewModel {

    private Repository repository;

    public EditImagenViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Long> getEditFloraLiveData() {

        return repository.getEditFloraLiveData();
    }

    public void editImagen(long id,Flora flora) {
        repository.editFlora(id, flora);
    }
}
