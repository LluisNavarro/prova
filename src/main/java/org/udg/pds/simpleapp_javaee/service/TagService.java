package org.udg.pds.simpleapp_javaee.service;

import org.udg.pds.simpleapp_javaee.model.Tag;
import org.udg.pds.simpleapp_javaee.model.Task;
import org.udg.pds.simpleapp_javaee.model.User;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by Jesús on 06/03/2017.
 */

@Stateless
@LocalBean
public class TagService {

    @PersistenceContext
    private EntityManager em;

    public Tag addTag(String name, String description) {
        try {
            Tag tag = new Tag(name,description);
            em.persist(tag);
            return tag;
        } catch (Exception ex) {
            // Very important: if you want that an exception reaches the EJB caller, you have to throw an EJBException
            // We catch the normal exception and then transform it in a EJBException
            throw new EJBException(ex);
        }
    }
}
