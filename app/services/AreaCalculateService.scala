package services

import exceptions.WrongInputParameters


class AreaCalculateService {
  def calculate(a: Int, b: Int): String = if (a < 0 || b < 0) throw new WrongInputParameters("Parameters cannot be negative") else (a * b).toString
}