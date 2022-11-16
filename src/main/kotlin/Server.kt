import io.vertx.core.Vertx

fun main() {
    val vertx = Vertx.vertx()
    vertx.createHttpServer().requestHandler{
        it.response().end("OK :D")
    }.listen(8081)
    println("open http://localhost:8081")
}