package app.jogodavelha;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogo_activity);

        quantidade = 1;
        jogador = 1;

        definicaoDeBotoes();

    }

    private void definicaoDeBotoes(){
        int[] buttonIds = {R.id.bt01, R.id.bt02, R.id.bt03, R.id.bt04, R.id.bt05, R.id.bt06, R.id.bt07, R.id.bt08, R.id.bt09};
        int[][] buttonPositions = {{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}};

        for (int i = 0; i < botao.length; i++) {
            botao[i] = findViewById(buttonIds[i]);
            final int finalI = i;
            botao[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogada(botao[finalI], buttonPositions[finalI][0], buttonPositions[finalI][1]);
                }
            });
        }

        // buttonIds para armazenam os IDs dos botões e
        // buttonPositions para armazenar as posições correspondentes dos botões.
        // o loop for para percorre cada botão.
        // Para cada botão o OnClickListener chama a função jogada com os parâmetros apropriados.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.novoJogo) {
            limpar();
            final EditText editText2 = new EditText(this);
            AlertDialog.Builder segundoJogador = new AlertDialog.Builder(this);
            segundoJogador.setMessage("Digite o nome do jogador 2: ");
            segundoJogador.setTitle("JOGADOR 2");
            segundoJogador.setView(editText2);
            segundoJogador.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    jogador02 = editText2.getText().toString();
                }
            });
            segundoJogador.create();
            segundoJogador.show();

            final EditText editText = new EditText(this);
            AlertDialog.Builder primeiroJogador = new AlertDialog.Builder(this);
            primeiroJogador.setMessage("Digite o nome do jogador 1: ");
            primeiroJogador.setTitle("JOGADOR 1");
            primeiroJogador.setView(editText);
            primeiroJogador.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    jogador01 = editText.getText().toString();
                }
            });
            primeiroJogador.create();
            primeiroJogador.show();


            //Toast.makeText(getApplicationContext(),"inicializa novo jogo",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void jogada(Button b,int x, int y){
        b.setEnabled(true);
        if(jogador==1){
            matriz[x][y]=1;
            b.setText("X");
            jogador=2;
            vencedor=jogador01;
            checarJogada(1);
        }
        else{
            matriz[x][y]=2;
            b.setText("O");
            jogador=1;
            vencedor=jogador02;
            checarJogada(2);

        }
        quantidade++;
    }

    public boolean vitoria(int x){

        for(int i=0;i<matriz.length;i++){

            if(matriz[i][0]==x && matriz[i][1]==x && matriz[i][2]==x){
                return true;
            }
            if(matriz[0][i]==x && matriz[1][i]==x && matriz[2][i]==x){
                return true;
            }
        }
        if(matriz[0][0]==x && matriz[1][1]==x && matriz[2][2]==x){
            return true;
        }
        if(matriz[0][2]==x && matriz[1][1]==x && matriz[2][0]==x){
            return true;
        }
        return false;

    }

    public void checarJogada(int x){

        if(vitoria(x)){

            AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
            alertaVenceu.setTitle("VITÓRIA");
            alertaVenceu.setMessage("O jogador " + vencedor + " venceu!");
            alertaVenceu.setIcon(android.R.drawable.star_on);
            alertaVenceu.setPositiveButton("OK", null);
            alertaVenceu.create();
            alertaVenceu.show();
            fimJogo();
        }

    }

    public void fimJogo(){
        for(int i=0;i<9;i++){
            botao[i].setEnabled(false);
        }
    }

    public void limpar(){
        for(int i=0;i<9;i++){
            botao[i].setEnabled(true);
            botao[i].setText("");
        }

        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                matriz[x][y]=0;
            }
        }
        jogador=1;
        jogador01="";
        jogador02="";
        vencedor="";
    }
}
