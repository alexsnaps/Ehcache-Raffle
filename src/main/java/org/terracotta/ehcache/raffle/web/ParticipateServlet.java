package org.terracotta.ehcache.raffle.web;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.terracotta.ehcache.raffle.model.Participant;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alex Snaps
 */
public class ParticipateServlet extends HttpServlet {

  private CacheManager cacheManager;

  @Override
  public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    this.cacheManager = CacheManager.getInstance();
  }

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

    Cache cache = cacheManager.getCache("participants");

    Participant participant = populateParticipant(req);

    if (participant.getEmail() != null
        && !cache.isKeyInCache(participant.getEmail())) {
      cache.putWithWriter(new Element(participant.getEmail(), participant));
      getServletConfig().getServletContext().getRequestDispatcher("/thanks.jsp").forward(req, resp);
    } else {
      getServletConfig().getServletContext().getRequestDispatcher("/notTwice.jsp").forward(req, resp);
    }
  }

  private Participant populateParticipant(final HttpServletRequest req) throws ServletException {

    final Participant participant = new Participant();

    for (Field field : participant.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        if (field.getType().equals(String.class)) {
          field.set(participant, req.getParameter(field.getName()));
        } else {
          field.set(participant, req.getParameter(field.getName()) != null);
        }
      } catch (IllegalAccessException e) {
        throw new ServletException("Error setting prop!", e);
      }
    }

    return participant;
  }

  @Override
  public void destroy() {
    super.destroy();
    this.cacheManager.shutdown();
  }
}
