package filters

import exceptions.WrongInputParameters
import play.api.mvc.{EssentialAction, EssentialFilter, RequestHeader}

import javax.inject.Inject
import scala.util.Try

class RequestFilter @Inject()() extends EssentialFilter {
  def apply(next: EssentialAction) = new EssentialAction {
    def apply(request: RequestHeader) = {
      if (request.path.startsWith("/calculate")) {
        if (request.getQueryString("a").isEmpty ||
          request.getQueryString("b").isEmpty ||
          Try(request.getQueryString("a").get.toInt).isSuccess &&
            Try(request.getQueryString("b").get.toInt).isSuccess
        ) {
          next(request)
        } else {
          throw new WrongInputParameters("Parameters must be integers")
        }
      }
      else {
        next(request)
      }
    }
  }
}
