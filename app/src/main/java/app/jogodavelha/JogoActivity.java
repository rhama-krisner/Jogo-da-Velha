package app.jogodavelha;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class JogoActivity extends AppCompatActivity {

    private int quantidade;
    private int jogador;
    private final int[][] matriz = new int[3][3];
    private final Button[] botao = new Button[9];
    private String vencedor;
    private String jogador01;
    private String jogador02;

    private int vermelho = Color.parseColor("#EF5350");

    private int azul = Color.parseColor("#0336FF");
    private TextView nomeJogadorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogo_activity);

        quantidade = 1;
        jogador = 1;

        nomeJogadorTextView = findViewById(R.id.nome_jogador_da_vez);

        definicaoDeBotoes();
        recomecar();
        novoJogo();

        atualizarNomeJogador();

    }


    private void definicaoDeBotoes() {
        int[] buttonIds = {R.id.bt01, R.id.bt02, R.id.bt03, R.id.bt04, R.id.bt05, R.id.bt06, R.id.bt07, R.id.bt08, R.id.bt09};
        int[][] buttonPositions = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

        for (int i = 0; i < botao.length; i++) {
            botao[i] = findViewById(buttonIds[i]);
            final int finalI = i;
            botao[i].setOnClickListener(view -> {
                jogada(botao[finalI], buttonPositions[finalI][0], buttonPositions[finalI][1]);
            });
        }

        // buttonIds para armazenam os IDs dos botões e
        // buttonPositions para armazenar as posições correspondentes dos botões.
        // o loop for para percorre cada botão.
        // Para cada botão o OnClickListener chama a função jogada com os parâmetros apropriados.
    }


    public void jogada(Button b, int x, int y) {
        Intent intent = getIntent();
        String jogador1 = intent.getStringExtra("jogador01");
        String jogador2 = intent.getStringExtra("jogador02");

        b.setEnabled(true);
        if (jogador == 1) {
            matriz[x][y] = 1;
            b.setText("X");
            b.setTextColor(vermelho);
            b.setTextSize(70);
            jogador = 2;
            vencedor = jogador1;
            checarJogada(1);
        } else {
            matriz[x][y] = 2;
            b.setText("O");
            b.setTextColor(azul);
            b.setTextSize(70);
            jogador = 1;
            vencedor = jogador2;
            checarJogada(2);

        }
        quantidade++;
        atualizarNomeJogador();

        b.setEnabled(false);
    }

    public boolean vitoria(int x) {

        for (int i = 0; i < matriz.length; i++) {

            if (matriz[i][0] == x && matriz[i][1] == x && matriz[i][2] == x) {
                return true;
            }
            if (matriz[0][i] == x && matriz[1][i] == x && matriz[2][i] == x) {
                return true;
            }
        }
        if (matriz[0][0] == x && matriz[1][1] == x && matriz[2][2] == x) {
            return true;
        }
        if (matriz[0][2] == x && matriz[1][1] == x && matriz[2][0] == x) {
            return true;
        }
        return false;

    }

    public void checarJogada(int x) {

        if (vitoria(x)) {

            AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
            alertaVenceu.setTitle("VITÓRIA");
            alertaVenceu.setMessage("O jogador " + vencedor + " venceu!");
            alertaVenceu.setIcon(android.R.drawable.star_on);
            alertaVenceu.setPositiveButton("OK", null);
            alertaVenceu.create();
            alertaVenceu.show();
            fimJogo();
        } else if (quantidade == 9) {
            if (!vitoria(1) && !vitoria(2)){
                AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
                alertaVenceu.setTitle("EMPATE");
                alertaVenceu.setMessage("Não houve vencedor.");
                alertaVenceu.setIcon(android.R.drawable.star_on);
                alertaVenceu.setPositiveButton("OK", null);
                alertaVenceu.create();
                alertaVenceu.show();
                fimJogo();
            }
        }

    }

    public void fimJogo() {
        for (int i = 0; i < 9; i++) {
            botao[i].setEnabled(false);
        }
    }

    public void limpar() {
        for (int i = 0; i < 9; i++) {
            botao[i].setEnabled(true);
            botao[i].setText("");
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                matriz[x][y] = 0;
            }
        }
        jogador = 1;
        jogador01 = "";
        jogador02 = "";
        vencedor = "";
    }

    private void recomecar() {
        Button btn_recomecar = findViewById(R.id.btn_recomecar);
        btn_recomecar.setOnClickListener(view -> {
            limpar();
        });
    }

    private void novoJogo() {
        Button btn_novo = findViewById(R.id.btn_novo_jogo);
        btn_novo.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void atualizarNomeJogador() {
        Intent intent = getIntent();
        String jogador1 = intent.getStringExtra("jogador01");
        String jogador2 = intent.getStringExtra("jogador02");

        if (jogador == 1) {
            nomeJogadorTextView.setText(jogador1);
            nomeJogadorTextView.setTextColor(vermelho);
        } else {
            nomeJogadorTextView.setText(jogador2);
            nomeJogadorTextView.setTextColor(azul);
        }
    }
}
