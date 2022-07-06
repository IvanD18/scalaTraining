package services

import java.util
import javax.inject.Singleton

@Singleton
class Cache {
  val cache = new util.TreeMap[String, String]

  def addInCache(key: String, value: String): Unit = {
    cache.put(key, value)
  }

  def findInCache(key: String): String = {
    cache.get(key)
  }

  def clearCache = {
    cache.clear()
  }
}
