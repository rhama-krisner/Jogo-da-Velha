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
        historico();
    }

    private void novaPartida(){
        botaoComecar = findViewById(R.id.comecar_partida);
        botaoComecar.setOnClickListener(view -> {
            EditText player01EditText = findViewById(R.id.edit_text_player1);
            EditText player02EditText = findViewById(R.id.edit_text_player2);

            String jogador01 = player01EditText.getText().toString();
            String jogador02 = player02EditText.getText().toString();

            Intent intent = new Intent(MainActivity.this, JogoActivity.class);
            intent.putExtra("jogador01", jogador01);
            intent.putExtra("jogador02", jogador02);

            startActivity(intent);
        });
    }

    private void historico(){
        Button btn_historico = findViewById(R.id.historico);
        Intent intent = new Intent(this, HistoricoActivity.class);
        btn_historico.setOnClickListener(view -> {
            startActivity(intent);
        });
    }

}