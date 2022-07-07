package controllers

import play.api.cache.AsyncCacheApi
import play.api.mvc.{AbstractController, ControllerComponents}
import services.AreaCalculateService

import javax.inject._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.DurationInt

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cache: AsyncCacheApi, cc: ControllerComponents)(implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  val areaCalculateService = new AreaCalculateService

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def calculate(a: Int, b: Int) = Action {

    val key = a.toString + "*" + b.toString + "="
    val futureRes: String = Await.result(cache.getOrElseUpdate(key, 1.minutes) {
      Future(areaCalculateService.calculate(a, b))
    }, 5.seconds)
    Ok(views.html.calculate(futureRes, key))
  }
}
