/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
@file:Suppress("UnusedImport")

package org.openapitools.client.apis

import org.openapitools.client.models.Order

import org.openapitools.client.infrastructure.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json

public open class StoreApiAsync : ApiClientBase {
    public val coroutineScope: CoroutineScope
    protected val coroutineClient: StoreApi

    public constructor(
        baseUrl: String = "http://petstore.swagger.io/v2",
        httpClientEngine: HttpClientEngine? = null,
        json: Json = Json {},
        coroutineScope: CoroutineScope = GlobalScope,
    ) : super(baseUrl, httpClientEngine, json) {
        this.coroutineScope = coroutineScope
        this.coroutineClient = StoreApi(baseUrl, httpClientEngine, json)
    }

    internal constructor(
        baseUrl: String,
        client: HttpClient,
        coroutineScope: CoroutineScope,
    ) : super(
        baseUrl,
        client,
    ) {
        this.coroutineScope = coroutineScope
        this.coroutineClient = StoreApi(baseUrl, client)
    }

    /**
     * Delete purchase order by ID
     * For valid response try integer IDs with value &lt; 1000. Anything above 1000 or nonintegers will generate API errors
     * @param orderId ID of the order that needs to be deleted 
     * @return void
     */
    public fun deleteOrderAsync(
        orderId: kotlin.String,
    ): Deferred<Unit> {
        return coroutineScope.async {
            this@StoreApiAsync.coroutineClient.deleteOrder(
                orderId = orderId,
            )
        }
    }
    /**
     * Returns pet inventories by status
     * Returns a map of status codes to quantities
     * @return kotlin.collections.Map<kotlin.String, kotlin.Int>
     */
    public fun getInventoryAsync(
    ): Deferred<kotlin.collections.Map<kotlin.String, kotlin.Int>> {
        return coroutineScope.async {
            this@StoreApiAsync.coroutineClient.getInventory(
            )
        }
    }
    /**
     * Find purchase order by ID
     * For valid response try integer IDs with value &lt;&#x3D; 5 or &gt; 10. Other values will generated exceptions
     * @param orderId ID of pet that needs to be fetched 
     * @return Order
     */
    public fun getOrderByIdAsync(
        orderId: kotlin.Long,
    ): Deferred<Order> {
        return coroutineScope.async {
            this@StoreApiAsync.coroutineClient.getOrderById(
                orderId = orderId,
            )
        }
    }
    /**
     * Place an order for a pet
     * 
     * @param body order placed for purchasing the pet 
     * @return Order
     */
    public fun placeOrderAsync(
        body: Order,
    ): Deferred<Order> {
        return coroutineScope.async {
            this@StoreApiAsync.coroutineClient.placeOrder(
                body = body,
            )
        }
    }
}
