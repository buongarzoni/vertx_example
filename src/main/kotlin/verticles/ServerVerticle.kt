package verticles

import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle

class ServerVerticle : CoroutineVerticle() {
    override suspend fun start() {
        val router = router()
        vertx.createHttpServer().requestHandler(router).listen(8081)
    }

    private fun router() : Router {
        val router = Router.router(vertx)

        router.get("/status").handler { it.okStatusResponse() }
        router.route("/cats/*").subRouter(catsRouter())

        return router
    }

    private fun catsRouter(): Router {
        val router = Router.router(vertx)

        router.delete("/:id").handler {  println(" delete ${it.request().getParam("id")}")}
        router.get("/:id").handler { println(" get ${it.request().getParam("id")} ") }
        router.put("/:id").handler { println(" put ${it.request().getParam("id")}") }

        return router
    }

    private fun RoutingContext.okStatusResponse() = response().setStatusCode(200).end(okStatus())
    private fun okStatus() = json { obj("status" to "OK ^-^") }.toString()
}
