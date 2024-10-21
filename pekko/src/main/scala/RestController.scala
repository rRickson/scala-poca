package com.github.rrickson.scalapocs
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.stream.ActorMaterializer

import java.sql.DriverManager.println
import scala.io.StdIn

// Case class to represent the data in JSON
final case class Greeting(message: String)

// JSON marshalling setup
trait JsonSupport extends spray.json.DefaultJsonProtocol {
  implicit val greetingFormat = jsonFormat1(Greeting)
}

object RestController extends App with JsonSupport {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  // Define your REST routes
  val route: Route =
    path("greet") {
      get {
        complete(Greeting("Hello from Pekko HTTP!"))
      }
    } ~
      path("health") {
        get {
          complete(StatusCodes.OK, "Healthy")
        }
      }

  // Bind the route to an HTTP server
  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // Press Enter to stop the server

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}