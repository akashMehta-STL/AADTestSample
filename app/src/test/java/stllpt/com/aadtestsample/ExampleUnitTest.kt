package stllpt.com.aadtestsample

import android.content.Context
import org.junit.Test

import org.mockito.Mockito.*

import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Answers
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

    @Mock
    lateinit var mainActivity: MainActivity

    lateinit var mainPresenter: MainPresenter

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

    /**
     * Before : Used to initialize variables before starting any test.
     */
    @Before
    fun initialize() {
        mainPresenter = MainPresenter(mainActivity)
    }

    /**
     * verify method will verify the method
     */
    @Test
    fun test_addNoteCall() {
        mainPresenter.insertNote()

        verify(mainActivity).addNote()
        // Unable to make nested method call
        verify(mainActivity).noteAdded()
    }
}
