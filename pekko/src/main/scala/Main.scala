package com.github.rrickson.scalapocs
import org.apache.pekko.actor.{Actor, ActorSystem, Props}


class SimpleActor extends Actor {
  def receive = {
    case msg: String => println(s"Received message: $msg")
  }
}

object Main extends App {
  val system = ActorSystem("MyActorSystem")
  val actor = system.actorOf(Props[SimpleActor], "simpleActor")
  actor ! "Hello, Pekko!"
}
