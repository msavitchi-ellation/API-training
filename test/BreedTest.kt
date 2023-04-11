import com.ellation.resttest.http.StatusCode.OK
import endpoints.ByBreed
import endpoints.ListAllBreads
import org.junit.jupiter.api.Test

class BreedTest {

	@Test
	fun test() {
		val response = ListAllBreads().get()
			.assertThat(OK)
	}

	@Test
	fun test1() {
		val response = ByBreed().get("hound")
			.assertThat(OK)
	}
}
