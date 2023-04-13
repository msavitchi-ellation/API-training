package steps

import com.ellation.testing.api.AccountImpl
import com.ellation.testing.api.ApiClient.Companion.cr
import com.ellation.testing.api.ApiClient.Companion.createAccount
import com.ellation.testing.api.etp.cr.CrClient.Companion.login
import com.ellation.testing.api.utils.Random.email
import com.ellation.testing.api.utils.Random.uuid
import support.Variables.ACCESS_TOKEN
import support.Variables.CR_STATIC_TYPE

object AccountSteps {

    fun createAccountAndLogin(email: String = email(), password: String = uuid()): AccountImpl {
        val session = cr.createSession(CR_STATIC_TYPE, ACCESS_TOKEN).data.sessionId
        val account = createAccount(email, password)
        login(session, email, password)
        return account
    }
}
