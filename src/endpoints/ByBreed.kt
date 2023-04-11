package endpoints

import com.ellation.resttest.setters.Setter
import com.ellation.resttest.setters.Setters.Companion.baseUri
import com.ellation.resttest.setters.Setters.Companion.pathParam
import io.restassured.http.Method
import io.restassured.http.Method.GET
import support.Variables.HOST

class ByBreed() : BreedEndpoint() {
	companion object {
		private const val PATH = "breed/{breed}/images"
	}

	init {
		set(baseUri(HOST))
	}

	fun get(account: String, vararg setters: Setter) =
		request(GET, account, *setters)

	private fun request(method: Method, breed: String, vararg setters: Setter) =
		send(method, PATH, pathParam("breed", breed), *setters)
}
