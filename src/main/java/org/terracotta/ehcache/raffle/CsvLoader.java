package org.terracotta.ehcache.raffle;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.loader.CacheLoader;

import org.terracotta.ehcache.raffle.model.Participant;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Alex Snaps
 */
public class CsvLoader implements CacheLoader {

  private final File file;

  public CsvLoader(final File file) {
    this.file = file;
  }

  public Object load(final Object o) throws CacheException {

    BufferedReader in;

    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
    } catch (Exception e1) {
      throw new CacheException("Couldn't open source file " + file, e1);
    }
    int location = 0;

    String line;
    try {
      while ((line = in.readLine()) != null)
        if (o.equals(location++)) {
          try {
            Thread.sleep(250L);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          return parseParticipant(line);
        }
    } catch (IOException e) {
      throw new CacheException(e);
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        //
      }
    }

    return null;
  }

  private Participant parseParticipant(final String line) {
    final Participant participant = new Participant();
    final StringTokenizer st = new StringTokenizer(line, "\"", false);
    int position = 0;
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (!",".equals(token)) {
        switch (position++) {
          case 0:
            participant.setFirstName(token);
            break;
          case 1:
            participant.setLastName(token);
            break;
          case 2:
            participant.setCompany(token);
            break;
          case 3:
            participant.setEmail(token);
            break;
          case 4:
            participant.setAtScale(Boolean.valueOf(token));
            break;
          case 5:
            participant.setJobTitle(token);
            break;
          case 6:
            participant.setUses(token);
            break;
        }
      }
    }

    return participant;
  }

  public Map loadAll(final Collection collection) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(collection.size());
    for (Object key : collection) {
      hashMap.put(key, load(key));
    }
    return hashMap;
  }

  public Object load(final Object o, final Object o1) {
    return load(o);
  }

  public Map loadAll(final Collection collection, final Object o) {
    return loadAll(collection);
  }

  public String getName() {
    return "CsvLoader for " + file;
  }

  public CacheLoader clone(final Ehcache ehcache) throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  public void init() {
  }

  public void dispose() throws CacheException {
  }

  public Status getStatus() {
    return Status.STATUS_ALIVE;
  }
}
