/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unibave.biblioteca.api.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.unibave.biblioteca.api.model.Autor;
import net.unibave.biblioteca.api.model.Livro;
import net.unibave.biblioteca.api.model.Erro;

/**
 *
 * @author Aula
 */
@Stateless
@Path("livros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LivroResource {

    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager entityManager;
    
    private AutorResource autorResource;

    public LivroResource() {
    }

    @Inject
    public LivroResource(AutorResource autorResource) {
       this.autorResource = autorResource;
    }
    
    @GET
    public List<Livro> findAll(@QueryParam("titulo") String titulo) {
        if(titulo != null) {
            return entityManager
                .createQuery("SELECT a FROM Livro a WHERE LOWER(a.titulo) LIKE LOWER(:titulo)", Livro.class)
                .setParameter("titulo", new StringBuilder("%").append(titulo).append("%").toString())
                .getResultList(); 
        }
        
        return entityManager
                .createQuery("SELECT a FROM Livro a", Livro.class)
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Livro findById(@PathParam("id") Long id) {
        Livro livro = entityManager.find(Livro.class, id);
        if(livro == null) {
            String mensagem = new StringBuilder()
                    .append("Livro ")
                    .append(String.valueOf(id))
                    .append(" não encontrado")
                    .toString();
            
            Response response = Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new Erro(mensagem))
                    .build();
            
            throw new WebApplicationException(response);
        }
        return livro;
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Livro livro = findById(id);
        entityManager.remove(livro);
    }

    @POST
    public Livro add(Livro livro) {
        entityManager.persist(livro);
        return livro;
    }

    @PUT
    @Path("{id}")
    public Livro update(@PathParam("id") Long id, Livro livro) {
        findById(id);
        livro.setId(id);
        return entityManager.merge(livro);
    }

}
