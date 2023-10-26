package app.jogodavelha;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        return true;
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

        //adicionar os 9 botoes sem precisar ficar repetindo código
        for (int i = 0; i < 9; i++) {
            String buttonID = "bt0" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            button[i] = findViewById(resID);
        }
    }

    private void jogada(Button button, int x, int y) {
        button.setEnabled(true);

        //Faz o processamento da vez de quem vai jogar
        if (jogador == 1) { //se for a vez do jogador 01
            matriz[x][y] = 1; //identifica ba matriz, qual foi o botão pressionado
            button.setText("X");
            jogador = 2; //assim que termina a vez dele, muda a vez plara o jogador 02
            vencedor = jogador01;
            checarJogada(1);
        } else {
            matriz[x][y] = 2;
            button.setText("O");
            jogador = 1;
            vencedor = jogador02;
            checarJogada(2);
        }

        quantidade++; // quantidade de jogadas são incrementadas até o limite de jogadas possíveis
    }

    public Boolean vitoria(int x){
        //Aqui verifica se na matriz de botões, se as posições têm o mesmo valor,
        //caso tenha, teremos um vencedor.
        for (int i = 0; i < matriz.length; i++){
            if (matriz[i][1] == x && matriz[i][1] == x && matriz[i][2] == x){
                return true;
            }
            if (matriz[0][i] == x && matriz[1][i] == x && matriz[2][i] == x){
                return true;
            }
        }

        if (matriz[0][0] == x && matriz[1][1] == x && matriz[2][2] == x){
            return true;
        }
        if (matriz[0][2] == x && matriz[1][1] == x && matriz[2][0] == x){
            return true;
        }

        return false;
    }

    public void checarJogada(int x){
        StringBuilder sb = new StringBuilder();
        if (vitoria(x)){
            AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
            alertaVenceu.setTitle("VITÓRIA!!");
            alertaVenceu.setMessage("O jogador " + vencedor + " venceu.");
            //alertaVenceu.setIcon(android.R.drawable.star_on) //adiciona icone
            alertaVenceu.setPositiveButton("Ok", null); // no lugar do null, posso colocar new DialogInterface.OnClickListener() para adicionar alguma função
            alertaVenceu.create();
            alertaVenceu.show();
        }
    }

    public void fimJogo(){
        for (int i = 0; i < 9; i++){
            button[i].setEnabled(false);
        }
    }

}