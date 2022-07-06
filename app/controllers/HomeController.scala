package controllers

import exceptions.WrongInputParameters
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
    cache.synchronized {
      var res = ""
      val key = a.toString + "*" + b.toString + "="
      if (cache.cache.containsKey(key)) res = cache.findInCache(key)
      else {
        res = areaCalculateService.calculate(a, b)
      }
      cache.addInCache(key, res)
      Ok(views.html.calculate(res, key, cache.cache))
    }
  }

}
