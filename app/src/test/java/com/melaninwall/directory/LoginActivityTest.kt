package com.melaninwall.directory

import com.melaninwall.directory.view.LoginActivity
import org.junit.Test

class LoginActivityTest {

    @Test
    fun `when performSignIn is called with  null username or password, then warning is return`() {

        loginActivity.performSignIn()



    }

    val loginActivity = LoginActivity()


}

