package com.ju.cardapionovo.model;

import java.io.Serializable;

public class Cardapio implements Serializable {

    private Long id;
    private String nome;
    private String tipo;
    private double valor;
    private String descricao;
    private int rendimento;

    @Override
    public String toString() {
//        return "Cardapio{" +
//                "id=" + id +
//                ", nome='" + nome + '\'' +
//                ", tipo='" + tipo + '\'' +
//                ", valor=" + valor +
//                ", descricao='" + descricao + '\'' +
//                ", rendimento=" + rendimento +
//                '}';
        return nome.toString() + " R$"+ valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }

}
