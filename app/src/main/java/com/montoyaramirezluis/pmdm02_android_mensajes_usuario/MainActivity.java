package com.montoyaramirezluis.pmdm02_android_mensajes_usuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.contenedor);
        Button boton = findViewById(R.id.btnGrabar);
        boton.setOnClickListener(botonOnClick);

    }

    /**
     * OnClick Boton.
     */
    private final View.OnClickListener botonOnClick = view -> {
        actionSnackbar(layout);
        guardar();
    };

    /**
     * Guardar datos.
     */
    private void guardar() {
        if (validarEntrada()) {

            // Preguntamos si queremos guardar...
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(R.string.confirmar);
            builder.setMessage(R.string.pregunta);
            builder.setPositiveButton(R.string.confirmar, (dialogInterface, i) ->
                    Toast.makeText(MainActivity.this, R.string.registroguardado, Toast.LENGTH_SHORT).show());
            builder.setNegativeButton(R.string.cancelar, (dialogInterface, i) ->
                    Toast.makeText(MainActivity.this, R.string.registrocancelado, Toast.LENGTH_SHORT).show());

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * Validar entrada de datos.
     */
    private boolean validarEntrada() {
        boolean bOk = true;

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etTelefono = findViewById(R.id.etTelefono);
        EditText etEmail = findViewById(R.id.etEmail);

        if (etNombre.getText().toString().isEmpty()) {
            etNombre.setError(getString(R.string.requerido));
            bOk = false;
        }
        if (etTelefono.getText().toString().isEmpty()) {
            etTelefono.setError(getString(R.string.requerido));
            bOk = false;
        }
        if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError(getString(R.string.requerido));
            bOk = false;
        }

        return bOk;
    }

    private void actionSnackbar(View view) {
        Snackbar.make(view, getString(R.string.guardando), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.anular), view1 ->
                        Toast.makeText(MainActivity.this, R.string.registrocancelado, Toast.LENGTH_SHORT).show()).show();
    }
}