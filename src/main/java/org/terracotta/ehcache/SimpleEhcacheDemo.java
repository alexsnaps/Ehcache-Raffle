package org.terracotta.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Random;

/**
 * @author Alex Snaps
 */
public class SimpleEhcacheDemo {


  private CacheManager cacheManager;

  public static void main(String[] args) {
    SimpleEhcacheDemo demo = new SimpleEhcacheDemo();
    demo.run();
  }

  public SimpleEhcacheDemo() {
    cacheManager = new CacheManager(this.getClass().getResourceAsStream("/simple-ehcache.xml"));
  }

  private void run() {
    for (String cacheName : cacheManager.getCacheNames()) {

      Cache cache = cacheManager.getCache(cacheName);

      // Initial size
      System.out.println(cacheName + " with " + cache.getSize() + " elements");

      // Populating
      for(int i = 0; i < 1000; i ++) {
        cache.put(new Element(i, "entry#"+i));
      }
    }

    Cache lastCache = null;
    int lastSize = 0;

    // Checking new sizes
    for (String cacheName : cacheManager.getCacheNames()) {
      lastCache = cacheManager.getCache(cacheName);
      lastSize = lastCache.getSize();
      System.out.println(cacheName + " with " + lastSize + " elements");
    }

    int key = 1000 - new Random().nextInt(lastSize);
    System.out.println("Element for key " + key + " is " + lastCache.get(key));

  }
}
