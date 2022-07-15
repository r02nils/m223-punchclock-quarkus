package ch.zli.m223.punchclock.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 * EntryController
 * @author Nils Rothenbühler
 * @version 1.0
 * @date: 2022-07-15
 */
@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @Inject
    JsonWebToken jwt; 

    /**
     * gibt alle Zeiteinträge in einem JSON format zurück
     * @return List<Entry>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries", description = "")
    public List<Entry> list() {
        return entryService.findAll();
    }

    /**
     * Erstellt einen neuen Zeiteintrag
     * @param entry
     * @return Entry
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
        return entryService.createEntry(entry);
    }

    /**
     * Fügt zu einem Zeiteintrag eine Kategorie hinzu
     * @param eid
     * @param id
     * @return Entry
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addCategory/{entry_id}/{category_id}")
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry addCategory(@PathParam("entry_id") long eid, @PathParam("category_id") long id) {
       return entryService.addCategory(eid, id);
    }

    /**
     * löscht ein bestimmter Zeiteintrag
     * @param id
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id){
        entryService.deleteEntry(id);
    }

    /**
     * ersetzt ein existierender Zeiteintrag
     * @param entry
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public void update(Entry entry){
        entryService.updateEntry(entry);
    }

}
