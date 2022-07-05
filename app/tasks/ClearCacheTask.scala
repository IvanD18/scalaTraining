package tasks

import akka.actor.ActorSystem
import services.Cache

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

class ClearCacheTask @Inject()(cache: Cache, actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.scheduleAtFixedRate(initialDelay = 0.seconds, interval = 20.seconds) { () =>
    cache.clearCache
    actorSystem.log.info("Cache cleared")
  }

}
