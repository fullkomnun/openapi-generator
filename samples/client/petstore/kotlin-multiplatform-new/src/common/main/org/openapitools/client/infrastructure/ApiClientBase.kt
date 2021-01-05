package org.openapitools.client.infrastructure

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import io.ktor.client.request.*

import org.openapitools.client.auth.*

public open class ApiClientBase {
    protected val baseUrl: String
    protected val client: HttpClient
    protected val apiKeyAuth: Map<String, ApiKeyAuth> by lazy {
        mapOf(
            "api_key" to ApiKeyAuth(ApiKeyAuth.Location.Header, "api_key"),
        )
    }
    protected val basicAuth: Map<String, HttpBasicAuth> by lazy {
        mapOf(
        )
    }
    protected val bearerAuth: Map<String, HttpBearerAuth> by lazy {
        mapOf(
        )
    }
    protected val oAuth: Map<String, OAuth> by lazy {
        mapOf(
            "petstore_auth" to OAuth(),
        )
    }

    internal constructor(baseUrl: String, httpClientEngine: HttpClientEngine?, json: Json = Json {}) {
        this.baseUrl = baseUrl
        val serializer = KotlinxSerializer(json)
        val jsonConfig: JsonFeature.Config.() -> Unit = { this.serializer = serializer }
        val clientConfig: (HttpClientConfig<*>) -> Unit = { it.install(JsonFeature, jsonConfig) }
        client = if (httpClientEngine == null) {
            HttpClient(clientConfig)
        } else {
            HttpClient(httpClientEngine, clientConfig)
        }
    }

    internal constructor(baseUrl: String, client: HttpClient) {
        this.baseUrl = baseUrl
        this.client = client
    }


    protected fun HttpRequestBuilder.addAuthentication(apiKeyAuths: List<String>, basicAuths: List<String>, bearerAuths: List<String>, oAuths: List<String>) {
        for (name in apiKeyAuths) {
            val auth = apiKeyAuth[name] ?: throw IllegalStateException("ApiKeyAuth \"$name\" was configured, but not found")
            if (auth.isConfigured) {
                auth.configure(this)
                return
            }
        }
        for (name in basicAuths) {
            val auth = basicAuth[name] ?: throw IllegalStateException("HttpBasicAuth \"$name\" was configured, but not found")
            if (auth.isConfigured) {
                auth.configure(this)
                return
            }
        }
        for (name in bearerAuths) {
            val auth = bearerAuth[name] ?: throw IllegalStateException("HttpBearerAuth \"$name\" was configured, but not found")
            if (auth.isConfigured) {
                auth.configure(this)
                return
            }
        }
        for (name in oAuths) {
            val auth = oAuth[name] ?: throw IllegalStateException("OAuth \"$name\" was configured, but not found")
            if (auth.isConfigured) {
                auth.configure(this)
                return
            }
        }
        throw IllegalStateException(
            """
            No valid authentication configured, please configure one of the following:
                API Key Authentication: ${apiKeyAuths.joinToString()}
                HTTP Bearer Authentication: ${apiKeyAuths.joinToString()}
                HTTP Basic Authentication: ${apiKeyAuths.joinToString()}
                OAuth: ${apiKeyAuths.joinToString()}
            """.trimIndent()
        )
    }
}
