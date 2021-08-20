/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unibave.biblioteca.api.resources;

import java.util.List;
import javax.ejb.Stateless;
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
import javax.ws.rs.core.MediaType;
import net.unibave.biblioteca.api.model.Autor;

/**
 *
 * @author Aula
 */
@Stateless
@Path("autores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorResource {

    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager entityManager;

    @GET
    public List<Autor> findAll() {
        return entityManager
                .createQuery("SELECT a FROM Autor a", Autor.class)
                //.createNativeQuery("SELECT * FROM autores", Autor.class);;
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Autor findById(@PathParam("id") Long id) {
        return entityManager.find(Autor.class, id);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Autor autor = findById(id);
        entityManager.remove(autor);
    }

    @POST
    public Autor add(Autor autor) {
        entityManager.persist(autor);
        return autor;
    }

    @PUT
    @Path("{id}")
    public Autor update(@PathParam("id") Long id, Autor autor) {
        autor.setId(id);
        return entityManager.merge(autor);
    }

}
