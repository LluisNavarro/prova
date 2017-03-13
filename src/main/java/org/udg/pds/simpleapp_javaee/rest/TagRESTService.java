package org.udg.pds.simpleapp_javaee.rest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.udg.pds.simpleapp_javaee.rest.serializer.JsonDateDeserializer;
import org.udg.pds.simpleapp_javaee.service.TagService;
import org.udg.pds.simpleapp_javaee.service.TaskService;
import org.udg.pds.simpleapp_javaee.util.ToJSON;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Jesús on 06/03/2017.
 */

@Path("/tags")
@RequestScoped
public class TagRESTService extends RESTService {


    @EJB
    TagService tagService;

    @Inject
    ToJSON toJSON;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTag(TagRESTService.R_Tag tag, @Context HttpServletRequest req) throws ParseException {

        if (tag.name == null) {
            throw new WebApplicationException("No name");
        }
        if (tag.description == null) {
            throw new WebApplicationException("No description");
        }

        return buildResponse(tagService.addTag(tag.name, tag.description));
    }


    static class R_Tag {

        public String name;

        public String description;
    }
}
