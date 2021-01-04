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
 * Describes the result of uploading an image resource
 *
 * @param code 
 * @param type 
 * @param message 
 */
@Suppress("ArrayInDataClass")
@Serializable
public data class ApiResponse(
    @SerialName(value = "code")
    val code: kotlin.Int? = null,
    @SerialName(value = "type")
    val type: kotlin.String? = null,
    @SerialName(value = "message")
    val message: kotlin.String? = null
)
