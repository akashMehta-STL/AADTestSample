package stllpt.com.aadtestsample

import android.content.Context
import android.util.Patterns
import org.junit.Test

import org.mockito.Mockito.*

import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @Mock
    lateinit var context: Context

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    )

    @Test
    fun addition_isCorrect() {
        assertThat(EMAIL_ADDRESS_PATTERN.matcher("a@g.c").matches(), `is`(true))
    }

    /**
     * when method will be used to predefine value for a method call.
     */
    @Test
    fun readStringFromContext() {
        `when`(context.getString(R.string.app_name)).thenReturn("AADTestSample")
        val mainActivity = MainActivity()
        val result = mainActivity.appString(context)
        assertThat(result, `is`("AADTestSample"))
    }
}
