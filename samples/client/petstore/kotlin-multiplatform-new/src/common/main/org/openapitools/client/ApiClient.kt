package org.openapitools.client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import org.openapitools.client.apis.*
import org.openapitools.client.infrastructure.ApiClientBase

@Suppress("RemoveRedundantBackticks", "MemberVisibilityCanBePrivate", "unused")
public open class ApiClient(
    baseUrl: String = "http://petstore.swagger.io/v2",
    client: HttpClient
) {
    public constructor(baseUrl: String, httpClientEngine: HttpClientEngine? = null, json: Json = Json {}) :
        this(baseUrl, createHttpClient(httpClientEngine, KotlinxSerializer(json)))

    public val `pet`: PetApi by lazy { PetApi(baseUrl, client) }
    public val `store`: StoreApi by lazy { StoreApi(baseUrl, client) }
    public val `user`: UserApi by lazy { UserApi(baseUrl, client) }

    public val allClients: Set<ApiClientBase> by lazy {
        setOf(
            `pet`,
            `store`,
            `user`,
        )
    }

    /**
     * Set the username for the first HTTP basic authentication for all apis.
     *
     * @param username Username
     */
    public fun setUsername(username: String) {
        for (client in allClients) {
            client.setUsername(username)
        }
    }

    /**
     * Set the password for the first HTTP basic authentication for all apis.
     *
     * @param password Password
     */
    public fun setPassword(password: String) {
        for (client in allClients) {
            client.setPassword(password)
        }
    }

    /**
     * Set the API key value for the first API key authentication for all apis.
     *
     * @param apiKey API key
     * @param paramName The name of the API key parameter, or null or set the first key.
     */
    public fun setApiKey(apiKey: String, paramName: String? = null) {
        for (client in allClients) {
            client.setApiKey(apiKey, paramName)
        }
    }

    /**
     * Set the API key prefix for the first API key authentication for all apis.
     *
     * @param apiKeyPrefix API key prefix
     * @param paramName The name of the API key parameter, or null or set the first key.
     */
    public fun setApiKeyPrefix(apiKeyPrefix: String, paramName: String? = null) {
        for (client in allClients) {
            client.setApiKeyPrefix(apiKeyPrefix, paramName)
        }
    }

    /**
     * Set the access token for the first OAuth2 authentication for all apis.
     *
     * @param accessToken Access token
     */
    public fun setAccessToken(accessToken: String) {
        for (client in allClients) {
            client.setAccessToken(accessToken)
        }
    }

    /**
     * Set the access token for the first Bearer authentication for all apis.
     *
     * @param bearerToken The bearer token.
     */
    public fun setBearerToken(bearerToken: String) {
        for (client in allClients) {
            client.setBearerToken(bearerToken)
        }
    }
}

internal fun createHttpClient(httpClientEngine: HttpClientEngine? = null, serializer: KotlinxSerializer): HttpClient {
    val jsonConfig: JsonFeature.Config.() -> Unit = { this.serializer = serializer }
    val clientConfig: (HttpClientConfig<*>) -> Unit = { it.install(JsonFeature, jsonConfig) }
    return if (httpClientEngine == null) {
        HttpClient(clientConfig)
    } else {
        HttpClient(httpClientEngine, clientConfig)
    }
}
