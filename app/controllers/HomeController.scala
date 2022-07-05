package controllers

import play.api.mvc.{AbstractController, ControllerComponents}
import services.{AreaCalculateService, Cache}

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cache: Cache, cc: ControllerComponents) extends AbstractController(cc) {

  val areaCalculateService = new AreaCalculateService

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def calculate(a: Int, b: Int) = Action {
    val res = areaCalculateService.calculate(a, b)

    cache.addInCache(a.toString + "*" + b.toString + "=" + res.toString+"\r\n")
    Ok(views.html.calculate(res, a, b, cache.cache))
  }

}
