package endpoints

import com.ellation.resttest.setters.Setter
import com.ellation.resttest.setters.Setters.Companion.baseUri
import io.restassured.http.Method.GET
import io.restassured.http.Method
import support.Variables.HOST

class ListAllBreads : BreedEndpoint() {

	companion object {
		private const val PATH = "breeds/list/all"
	}

	init {
		set(baseUri(HOST))
	}

	fun get(vararg setters: Setter) =
		request(GET, *setters)

	private fun request(method: Method, vararg setters: Setter) =
		send(method, PATH, *setters)
}
