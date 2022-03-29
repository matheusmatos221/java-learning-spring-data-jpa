package br.com.matheus.spring.data.orm;

import javax.persistence.*;

@Entity
@Table(name = "unidades")
public class UnidadeDeTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String endereco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "UnidadeDeTrabalho{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
