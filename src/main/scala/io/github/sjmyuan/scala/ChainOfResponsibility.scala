package io.github.sjmyuan.scala

enum LogLevel:
  case DEBUG, INFO, ERROR

case class Message(level: LogLevel, content: String)

object ChainOfResponsibility extends App {

  val debugLogger: PartialFunction[Message, Unit] = {
    case Message(LogLevel.DEBUG, content) =>
      println(s"[DEBUG] - ${content}")
  }

  val infoLogger: PartialFunction[Message, Unit] = {
    case Message(LogLevel.INFO, content) =>
      println(s"[INFO] - ${content}")
  }

  val errorLogger: PartialFunction[Message, Unit] = {
    case Message(LogLevel.ERROR, content) =>
      println(s"[ERROR] - ${content}")
  }

  val logger = debugLogger orElse infoLogger orElse errorLogger

  logger(Message(LogLevel.DEBUG, "This is a debug message"))
  logger(Message(LogLevel.INFO, "This is an information"))
  logger(Message(LogLevel.ERROR, "This is a error message"))
}
