package app.jogodavelha;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    Button botaoComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        novaPartida();
    }

    private void novaPartida(){
        botaoComecar = findViewById(R.id.comecar_partida);
        botaoComecar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, JogoActivity.class);
            startActivity(intent);
        });
    }

}