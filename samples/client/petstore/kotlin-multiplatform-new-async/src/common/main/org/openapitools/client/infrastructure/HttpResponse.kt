package org.openapitools.client.infrastructure

import io.ktor.client.call.TypeInfo
import io.ktor.client.call.typeInfo
import io.ktor.http.Headers
import io.ktor.http.isSuccess
import io.ktor.client.statement.HttpResponse as KtorHttpResponse

@Suppress("unused")
public open class HttpResponse<T : Any> internal constructor(public val response: KtorHttpResponse, internal val provider: BodyProvider<T>) {
    public val status: Int = response.status.value
    public val success: Boolean = response.status.isSuccess()
    public val headers: Map<String, List<String>> = response.headers.mapEntries()
    public suspend fun body(): T = provider.body(response)
    public suspend fun <V : Any> typedBody(type: TypeInfo): V = provider.typedBody(response, type)

    public companion object {
        private fun Headers.mapEntries(): Map<String, List<String>> {
            val result = mutableMapOf<String, List<String>>()
            entries().forEach { result[it.key] = it.value }
            return result
        }
    }
}

internal interface BodyProvider<T : Any> {
    suspend fun body(response: KtorHttpResponse): T
    suspend fun <V : Any> typedBody(response: KtorHttpResponse, type: TypeInfo): V
}

internal class TypedBodyProvider<T : Any>(private val type: TypeInfo) : BodyProvider<T> {
    @Suppress("UNCHECKED_CAST")
    override suspend fun body(response: KtorHttpResponse): T =
        response.call.receive(type) as T

    @Suppress("UNCHECKED_CAST")
    override suspend fun <V : Any> typedBody(response: KtorHttpResponse, type: TypeInfo): V =
        response.call.receive(type) as V
}

internal class MappedBodyProvider<S : Any, T : Any>(private val provider: BodyProvider<S>, private val block: S.() -> T) :
    BodyProvider<T> {
    override suspend fun body(response: KtorHttpResponse): T =
        block(provider.body(response))

    override suspend fun <V : Any> typedBody(response: KtorHttpResponse, type: TypeInfo): V =
        provider.typedBody(response, type)
}

internal inline fun <reified T : Any> KtorHttpResponse.wrap(): HttpResponse<T> =
        HttpResponse(this, TypedBodyProvider(typeInfo<T>()))

internal fun <T : Any, V : Any> HttpResponse<T>.map(block: T.() -> V): HttpResponse<V> =
        HttpResponse(response, MappedBodyProvider(provider, block))
