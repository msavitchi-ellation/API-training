package steps

import com.ellation.testing.api.Account
import com.ellation.testing.api.ApiClient.Companion.inAppMessages
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAmount
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import support.ItemsModels.BASE_MESSAGE
import support.ItemsModels.and
import support.Variables.DATE_FORMAT

object MessageSteps {
	private const val POSITIVE_SCHEMA = "schemas/messagesSchema.json"

	@Suppress("LongParameterList")
	fun createMessage(
		account: Account,
		templateMessageId: Int? = null,
		messageType: String? = null,
		startAt: String? = null,
		endAt: String? = null,
		payloads: Any? = null
	): String {

		val messageId = inAppMessages.createMessage(account.token.accessToken, BASE_MESSAGE.and {
			userId = account.token.jwt.etpId
			if (templateMessageId != null) templateId = templateMessageId
			if (messageType != null) type = messageType
			if (startAt != null) startsAt = startAt
			if (endAt != null) endsAt = endAt
			if (payloads != null) payload = payloads
		})

		assertThat(messageId, notNullValue())
		return messageId
	}

	@Suppress("LongParameterList")
	fun createMessage(
		account: Account,
		startAt: TemporalAmount?,
		endAt: TemporalAmount?,
		templateMessageId: Int? = null,
		messageType: String? = null,
		payloads: Any? = null
	): String {
		val start = if (startAt == null) null
		else formattedDate(now().plus(startAt))

		val end = if (endAt == null) null
		else formattedDate(now().plus(endAt))

		return createMessage(account, templateMessageId, messageType, start, end, payloads)
	}

	fun formattedDate(date: LocalDateTime, format: String = DATE_FORMAT): String {
		val dateFormat = DateTimeFormatter.ofPattern(format)
		return date.format(dateFormat)
	}
}
