package net.unibave.biblioteca.api.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "livros")
public class Livro implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String titulo;
    
    private String subtitulo;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "livro_autor_relacao",
        joinColumns = @JoinColumn(name = "id_livro"),
        foreignKey = @ForeignKey(name = "fk_livro"),
        inverseJoinColumns = @JoinColumn(name = "id_autor"),
        inverseForeignKey = @ForeignKey(name = "fk_autor")
    )
    private List<Autor> autores;

    public Livro() {
    } 
    
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }  
    
}
