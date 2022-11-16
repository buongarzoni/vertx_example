import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import verticles.ServerVerticle
import kotlin.test.assertEquals

private const val VERTICAL_PORT = 8081
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerKtMust {
    private val vertx = Vertx.vertx()
    private lateinit var client: WebClient

    @BeforeAll
    fun setup() {
        runBlocking {
            vertx.deployVerticle(ServerVerticle()).await()
            client = WebClient.create(
                vertx,
                WebClientOptions().setDefaultPort(VERTICAL_PORT),
            )
        }
    }

    @AfterAll
    fun tearDown() {
        vertx.close()
    }

    @Test
    fun `return 200 for status route`() {
        runBlocking {
            val response = client.get("/status").send().await()
            assertEquals(200, response.statusCode())
        }
    }
}
