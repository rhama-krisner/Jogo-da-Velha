package app.jogodavelha;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private int quantidade;
    private int jogador;
    private int matriz[][] = new int[3][3];
    private Button button[] = new Button[9];
    private String vencedor;
    private String jogador01;
    private String jogador02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setValoresDeInicio();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = R.id.novoJogo;
        if (item.getItemId() == id) {
            Toast.makeText(this, "Novo Jogo", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setValoresDeInicio() {
        quantidade = 1;
        jogador = 1;
        button = new Button[9];
        for (int i = 0; i < 9; i++) {
            String buttonID = "bt0" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            button[i] = findViewById(resID);
        }
    }



}