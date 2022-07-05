package services

import java.util.ArrayList
import javax.inject.Singleton

@Singleton
class Cache {
  val cache = new ArrayList[String]

  def addInCache(expression: String): Unit = {
    cache.add(expression)
  }

  def clearCache = {
    cache.clear()
  }
}
