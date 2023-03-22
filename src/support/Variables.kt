package support

import java.lang.System.getProperty

object Variables {

    //    POSITIVE VARIABLES
    val INTERNAL_HOST = getProperty("internalHost")!!
    private val JENKINS = getProperty("jenkins").toBoolean()
    val HOST = if (JENKINS) INTERNAL_HOST else getProperty("host")!!
}
