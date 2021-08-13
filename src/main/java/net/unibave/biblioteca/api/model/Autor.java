/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unibave.biblioteca.api.model;

/**
 *
 * @author Aula
 */
public class Autor {
    
    private Long id;
    private String nome;
    private String nacionalidade;
    private Genero genero;

    public Autor() {
    }

    public Autor(Long id, String nome, String nacionalidade, Genero genero) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.genero = genero;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
}
