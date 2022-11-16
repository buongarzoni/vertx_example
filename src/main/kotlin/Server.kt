import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj

fun main() {
    val vertx = Vertx.vertx()
    val router = Router.router(vertx)

    router.get("/status").handler {
        it.okStatusResponse()
    }
    vertx.createHttpServer().requestHandler(router).listen(8081)

    println("open http://localhost:8081")
}

private fun RoutingContext.okStatusResponse() = response().setStatusCode(200).end(okStatus())
private fun okStatus() = json { obj("status" to "OK ^-^") }.toString()
