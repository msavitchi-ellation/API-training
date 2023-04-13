import com.ellation.resttest.http.IsSortedMatcher.SortOrder.DESCENDING
import com.ellation.resttest.http.OAuth2.Companion.token
import com.ellation.resttest.http.Response
import com.ellation.resttest.http.RestTestMatcher.isSorted
import com.ellation.resttest.http.StatusCode.OK
import com.ellation.resttest.setters.Setters.Companion.header
import com.ellation.resttest.setters.Setters.Companion.param
import com.ellation.resttest.verifiers.Verifiers.Companion.path
import com.ellation.testing.api.Account
import com.ellation.testing.api.ApiClient
import com.ellation.testing.api.Tenant
import com.ellation.testing.api.etp.in_app_messages.endpoints.InAppMessagesMessages
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.hamcrest.Matchers.everyItem
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import steps.AccountSteps.createAccountAndLogin
import steps.MessageSteps.createMessage

class InAppMessagesTest {

	private lateinit var account: Account
	private lateinit var response: Response
	private lateinit var token: String
	private lateinit var etpId: String
	private val positiveSchema = "schemas/messagesSchema.json"

	@BeforeEach
	fun main() {
		account = createAccountAndLogin()
		createMessage(account, messageType = "notification", templateMessageId = 1)
		val message1 = createMessage(account, messageType = "notification", templateMessageId = 2)
		createMessage(account, messageType = "alert", templateMessageId = 3)
		val message2 = createMessage(account, messageType = "alert", templateMessageId = 4)
		ApiClient.inAppMessages.setViewed(account.token) {
			ids = listOf(message1, message2)
			viewed = true
		}

		token = account.token.accessToken
		etpId = account.token.jwt.etpId
	}


	@Test
	fun `Messages with full list of parameters`() {

		response = InAppMessagesMessages().get(etpId,
			token(token),
			header("X-Tenant", Tenant.CR.tenant),
			header("X-Consumer", "com.crunchyroll.cxweb"),
			header("Content-Language", "ru-RU"),
			param("locale", "en-US"),
			param("limit", "4"),
			param("page", "1"),
			param("sort_by", "createdAt"),
			param("sort_way", "desc"))
//			param("types['alert']", "0"),
//			param("types['notification']", "1"))

		response.assertThat(OK, positiveSchema,
			path("items.userId", everyItem(equalTo(etpId))),
			path("items.createdAt", isSorted(order = DESCENDING)),
			path("page", equalTo(1)),
			path("page_size", equalTo(4)),
			path("""items.findAll { it["type"] == ""alert"" }""",
				path(".", hasSize<Any>(equalTo(1))),
				path("viewed", everyItem(Matchers.equalTo(false)))),
			path("""items.findAll { it["type"] == "notification" }""",
				path(".", hasSize<Any>(equalTo(2)))))
	}
}
