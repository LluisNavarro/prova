package org.udg.pds.simpleapp_javaee.service;

import org.udg.pds.simpleapp_javaee.model.Task;
import org.udg.pds.simpleapp_javaee.model.User;
import org.udg.pds.simpleapp_javaee.rest.RESTService;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Date;

@Stateless
@LocalBean
public class TaskService {

  @PersistenceContext
  protected EntityManager em;

  @EJB
  protected UserService userService;

  public Collection<Task> getTasks(Long id) {
    Collection<Task> tl = null;
    try {
      User u = em.find(User.class, id);
      tl = u.getTasks();
      return tl;
    } catch (Exception ex) {
      // Very important: if you want that an exception reaches the EJB caller, you have to throw an EJBException
      // We catch the normal exception and then transform it in a EJBException
      throw new EJBException(ex);
    }
  }

  public Task getTask(Long userId, Long id) {
    try {
      Task t = em.find(Task.class, id);
      if (t.getUser().getId() != userId)
        throw new Exception("User does not own this task");
      return t;
    } catch (Exception ex) {
      // Very important: if you want that an exception reaches the EJB caller, you have to throw an EJBException
      // We catch the normal exception and then transform it in a EJBException
      throw new EJBException(ex);
    }
  }

  public Task getTaskComplete(Long id) {
    try {
      Task t = em.find(Task.class, id);
      String email = t.getUser().getEmail();
      return t;
    } catch (Exception ex) {
      // Very important: if you want that an exception reaches the EJB caller, you have to throw an EJBException
      // We catch the normal exception and then transform it in a EJBException
      throw new EJBException(ex);
    }
  }


  public Task addTask(String text, Long userId,
                      Date created, Date limit) {
    try {
      User user = em.find(User.class, userId);

      Task task = new Task(created, limit, false, text);

      task.setUser(user);

      user.addTask(task);

      em.persist(task);
      return task;
    } catch (Exception ex) {
      // Very important: if you want that an exception reaches the EJB caller, you have to throw an EJBException
      // We catch the normal exception and then transform it in a EJBException
      throw new EJBException(ex);
    }
  }

  public RESTService.ID remove(Long taskId) {
    Task t = em.find(Task.class, taskId);
    em.remove(t);
    return new RESTService.ID(taskId);
  }

}
