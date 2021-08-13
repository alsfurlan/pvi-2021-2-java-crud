/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unibave.biblioteca.api.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import net.unibave.biblioteca.api.model.Genero;

/**
 *
 * @author Aula
 */
@Path("autores")
public class AutorResource {

    static Autor autor1
            = new Autor(1L, "Maurício de Souza", "Brasileiro", Genero.MASCULINO);
    static Autor autor2
            = new Autor(2L, "JK Rowling", "Inglesa", Genero.FEMININO);

    static List<Autor> autores = new ArrayList<>(
            Arrays.asList(autor1, autor2)
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> findAll() {
        return autores;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor findById(@PathParam("id") Long id) {
//        for(Autor a : autores) {
//            if(a.getId().equals(id)) {
//                return a;
//            }
//        }        
//        return null;
        return autores.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Autor autor = this.findById(id);
        autores.remove(autor);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Autor add(Autor autor) {
        autor.setId(Long.valueOf(autores.size() + 1));
        autores.add(autor);
        return autor;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Autor update(@PathParam("id") Long id, Autor autor) {
        autor.setId(id);
        Integer index = null;
        for (int i = 0; i < autores.size(); i++) {
            Autor a = autores.get(i);
            if(a.getId().equals(id)) {
                index = i;
                break;
            }
        }
        if(index == null) {
            return null;
        } 
        autores.set(index, autor);
        return autores.get(index);
    }

}
