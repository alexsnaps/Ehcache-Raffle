package org.terracotta.ehcache.raffle;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;

import java.io.File;
import java.util.Properties;

/**
 * @author Alex Snaps
 */
public class CsvWriterFactory extends CacheWriterFactory {

  private static final String PATH = System.getProperty("user.home");

  @Override
  public CacheWriter createCacheWriter(final Ehcache ehcache, final Properties properties) {
    return new CsvWriter(new File(PATH + "/" + ehcache.getName() + ".csv"));

  }
}
