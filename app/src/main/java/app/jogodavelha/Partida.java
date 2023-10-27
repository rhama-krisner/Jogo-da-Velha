package app.jogodavelha;

public class Partida {
    private String nomeJogador01;
    private String nomeJogador02;
    private String vencedor;

    public Partida() {
    }

    public Partida(String nomeJogador01, String nomeJogador02, String vencedor) {
        this.nomeJogador01 = nomeJogador01;
        this.nomeJogador02 = nomeJogador02;
        this.vencedor = vencedor;
    }

    public String getNomeJogador01() {
        return nomeJogador01;
    }

    public void setNomeJogador01(String nomeJogador01) {
        this.nomeJogador01 = nomeJogador01;
    }

    public String getNomeJogador02() {
        return nomeJogador02;
    }

    public void setNomeJogador02(String nomeJogador02) {
        this.nomeJogador02 = nomeJogador02;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }
}
