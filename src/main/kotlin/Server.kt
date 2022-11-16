import io.vertx.core.Vertx
import verticles.ServerVerticle

fun main() {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(ServerVerticle())
}
