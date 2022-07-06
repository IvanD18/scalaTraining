package exceptions

case class WrongInputParameters(message: String) extends Exception{
  override def getMessage: String = message
}
