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
@file:UseSerializers(InstantSerializer::class, LocalDateTimeSerializer::class, LocalDateSerializer::class)
@file:Suppress("UnusedImport", "RemoveRedundantQualifierName")

package org.openapitools.client.models

import kotlinx.serialization.UseSerializers
import org.openapitools.client.infrastructure.LocalDateTimeSerializer
import org.openapitools.client.infrastructure.InstantSerializer
import org.openapitools.client.infrastructure.LocalDateSerializer

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A User who is purchasing from the pet store
 *
 * @param id 
 * @param username 
 * @param firstName 
 * @param lastName 
 * @param email 
 * @param password 
 * @param phone 
 * @param userStatus User Status
 */
@Suppress("ArrayInDataClass")
@Serializable
public data class User(
    @SerialName(value = "id")
    val id: kotlin.Long? = null,
    @SerialName(value = "username")
    val username: kotlin.String? = null,
    @SerialName(value = "firstName")
    val firstName: kotlin.String? = null,
    @SerialName(value = "lastName")
    val lastName: kotlin.String? = null,
    @SerialName(value = "email")
    val email: kotlin.String? = null,
    @SerialName(value = "password")
    val password: kotlin.String? = null,
    @SerialName(value = "phone")
    val phone: kotlin.String? = null,
    /**
     * User Status
     */
    @SerialName(value = "userStatus")
    val userStatus: kotlin.Int? = null
)
