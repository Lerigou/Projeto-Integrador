import java.util.Date;

public class Pesquisa extends Candidato {

    private int idPesquisa;
    private String uf;
    private Date data;
    private String fonte;
    private float porcentagem;
    private Candidato candidato;

    public int getIdPesquisa() {
        return idPesquisa;
    }

    public void setIdPesquisa(int idPesquisa) {
        this.idPesquisa = idPesquisa;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

}