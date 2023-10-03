package model;

public class Compromisso {

    private Long id = 0l, fk = 0l;
    private String titulo, descricao, data;

    public Compromisso() {
    }

    public Compromisso(String titulo, String data) {
        this.titulo = titulo;
        this.data = data;
    }

    public Compromisso(Long fk, String titulo, String descricao, String data) {
        this.fk = fk;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
//teste
    public void show() {
        System.out.println("FK: " + getFk());
        System.out.println("ID: " + getId());
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Data: " + getData());
    }

}
