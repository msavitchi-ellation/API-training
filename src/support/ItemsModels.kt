package support

import com.ellation.testing.api.etp.in_app_messages.payloads.request.MessageCreatePayload
import support.Variables.EPISODE_ID
import support.Variables.EPISODE_NAME
import support.Variables.IMAGE
import support.Variables.LINK
import support.Variables.SERIAL_TITLE

object ItemsModels {

	val BASE_MESSAGE: MessageCreatePayload.() -> Unit = {
		templateId = 2
		userId = null
		type = "notification"
		startsAt = "2018-09-22T12:42:31Z"
		endsAt = "2024-09-22T12:42:31Z"
		payload = mapOf(
			"imageUrl" to IMAGE,
			"seriesTitle" to SERIAL_TITLE,
			"episodeName" to EPISODE_NAME,
			"episode" to EPISODE_ID,
			"linkUrl" to LINK)
	}

	inline fun <reified T> (T.() -> Unit).and(crossinline additional: T.() -> Unit): T.() -> Unit =
		{
			this@and()
			additional()
		}
}
