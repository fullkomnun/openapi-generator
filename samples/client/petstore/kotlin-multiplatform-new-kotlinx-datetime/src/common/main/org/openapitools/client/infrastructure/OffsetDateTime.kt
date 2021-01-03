package org.openapitools.client.infrastructure

import kotlinx.datetime.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents a [LocalDateTime] and the respective [TimeZone] of it.
 */
@Serializable(with = OffsetDateTime.Companion::class)
public data class OffsetDateTime(val dateTime: LocalDateTime, val timeZone: TimeZone) {
    override fun toString(): String {
        return if (timeZone == TimeZone.UTC) {
            "${dateTime}Z"
        } else {
            "$dateTime$timeZone"
        }
    }

    /**
     * Converts the [OffsetDateTime] to an [Instant]. This looses the [TimeZone] information, because the date and time
     * is converted to UTC in the process.
     */
    public fun toInstant(): Instant = dateTime.toInstant(timeZone)

    /**
     * Creates an [OffsetDateTime] from an [Instant] in a given [TimeZone] ([TimeZone.UTC] by default).
     */
    public fun fromInstant(instant: Instant, timeZone: TimeZone = TimeZone.UTC): OffsetDateTime = OffsetDateTime(
        instant.toLocalDateTime(timeZone),
        timeZone,
    )

    /**
     * Returns a new [OffsetDateTime] with the given [TimeZone].
     */
    public fun inTimeZone(newTimeZone: TimeZone): OffsetDateTime {
        val instant = dateTime.toInstant(timeZone)
        val newDateTime = instant.toLocalDateTime(newTimeZone)
        return OffsetDateTime(newDateTime, newTimeZone)
    }

    /**
     * Returns a new [OffsetDateTime] with the time zone [TimeZone.UTC].
     */
    public fun utc(): OffsetDateTime = inTimeZone(TimeZone.UTC)

    public companion object : KSerializer<OffsetDateTime> {
        private val zoneRegex by lazy {
            Regex("""[+\-][0-9]{2}:[0-9]{2}""")
        }

        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("org.openapitools.client.infrastructure.OffsetDateTime", PrimitiveKind.STRING)

        /**
         * Parses an [OffsetDateTime] from a RFC3339 compatible string.
         */
        public fun parse(string: String): OffsetDateTime = when {
            string.contains('Z') -> OffsetDateTime(
                LocalDateTime.parse(string.substringBefore('Z')),
                TimeZone.UTC,
            )
            string.contains('z') -> OffsetDateTime(
                LocalDateTime.parse(string.substringBefore('z')),
                TimeZone.UTC,
            )
            zoneRegex.matches(string) -> OffsetDateTime(
                LocalDateTime.parse(string.substring(0, string.length - 6)),
                TimeZone.of(string.substring(string.length - 6)),
            )
            else -> throw IllegalArgumentException("Date \"$string\" is not RFC3339 compatible")
        }

        override fun deserialize(decoder: Decoder): OffsetDateTime {
            val string = decoder.decodeString()
            return parse(string)
        }

        override fun serialize(encoder: Encoder, value: OffsetDateTime) {
            encoder.encodeString(value.toString())
        }
    }
}
