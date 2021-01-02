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

import org.openapitools.client.models.User

import org.openapitools.client.infrastructure.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.serialization.json.Json
import kotlin.js.Promise

public open class UserApiAsync : UserApi {
    public val coroutineScope: CoroutineScope

    public constructor(
        baseUrl: String = "http://petstore.swagger.io/v2",
        httpClientEngine: HttpClientEngine? = null,
        json: Json = Json {},
        coroutineScope: CoroutineScope = GlobalScope,
    ) : super(baseUrl, httpClientEngine, json) {
        this.coroutineScope = coroutineScope
    }

    internal constructor(
        baseUrl: String,
        client: HttpClient,
        serializer: KotlinxSerializer,
        coroutineScope: CoroutineScope,
    ) : super(
        baseUrl,
        client,
        serializer,
    ) {
        this.coroutineScope = coroutineScope
    }

    /**
     * Create user
     * This can only be done by the logged in user.
     * @param body Created user object 
     * @return void
     */
    public fun createUserAsync(
        body: User,
    ): Promise<HttpResponse<Unit>> {
        return coroutineScope.promise {
            createUser(
                body = body,
            )
        }
    }
    /**
     * Creates list of users with given input array
     * 
     * @param body List of user object 
     * @return void
     */
    public fun createUsersWithArrayInputAsync(
        body: kotlin.Array<User>,
    ): Promise<HttpResponse<Unit>> {
        return coroutineScope.promise {
            createUsersWithArrayInput(
                body = body,
            )
        }
    }
    /**
     * Creates list of users with given input array
     * 
     * @param body List of user object 
     * @return void
     */
    public fun createUsersWithListInputAsync(
        body: kotlin.Array<User>,
    ): Promise<HttpResponse<Unit>> {
        return coroutineScope.promise {
            createUsersWithListInput(
                body = body,
            )
        }
    }
    /**
     * Delete user
     * This can only be done by the logged in user.
     * @param username The name that needs to be deleted 
     * @return void
     */
    public fun deleteUserAsync(
        username: kotlin.String,
    ): Promise<HttpResponse<Unit>> {
        return coroutineScope.promise {
            deleteUser(
                username = username,
            )
        }
    }
    /**
     * Get user by user name
     * 
     * @param username The name that needs to be fetched. Use user1 for testing. 
     * @return User
     */
    public fun getUserByNameAsync(
        username: kotlin.String,
    ): Promise<HttpResponse<User>> {
        return coroutineScope.promise {
            getUserByName(
                username = username,
            )
        }
    }
    /**
     * Logs user into the system
     * 
     * @param username The user name for login 
     * @param password The password for login in clear text 
     * @return kotlin.String
     */
    public fun loginUserAsync(
        username: kotlin.String,
        password: kotlin.String,
    ): Promise<HttpResponse<kotlin.String>> {
        return coroutineScope.promise {
            loginUser(
                username = username,
                password = password,
            )
        }
    }
    /**
     * Logs out current logged in user session
     * 
     * @return void
     */
    public fun logoutUserAsync(
    ): Promise<HttpResponse<Unit>> {
        return coroutineScope.promise {
            logoutUser(
            )
        }
    }
    /**
     * Updated user
     * This can only be done by the logged in user.
     * @param username name that need to be deleted 
     * @param body Updated user object 
     * @return void
     */
    public fun updateUserAsync(
        username: kotlin.String,
        body: User,
    ): Promise<HttpResponse<Unit>> {
        return coroutineScope.promise {
            updateUser(
                username = username,
                body = body,
            )
        }
    }
}
