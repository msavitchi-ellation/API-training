package support

import java.lang.System.getProperty

object Variables {
	val HOST = getProperty("host")!!

	const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

	// CR constants
	const val CR_STATIC_TYPE = "com.crunchyroll.static"
	const val ACCESS_TOKEN = "giKq5eY27ny3cqz"

	// Example Message object
	const val IMAGE = "https://img1.ak.crunchyroll.com/i/spire1-tmb/fabf8c81e61251108996956788dabfc41636447449_wide.jpg"
	const val SERIAL_TITLE = "takt op.Destiny"
	const val EPISODE_NAME = "Sunrise -Rooster-"
	const val EPISODE_ID = "6"
	const val LINK = "https://www.crunchyroll.com/takt-opdestiny/episode-6-sunrise-rooster-819680"
}
