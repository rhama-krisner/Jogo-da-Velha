package app.jogodavelha;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {
    private List<Partida> partidaList = new ArrayList<>();

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.historico_activity);
        toobarConfig();
        carregaLista();
    }

    private void toobarConfig(){
        Toolbar toolbar = findViewById(R.id.toolbar_historico);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_voltar);
    }

    private void carregaLista(){
        Partida partida = new Partida("Rhama Krisner", "Josiane", "Josiane");
        partidaList.add(partida);
    }

}
