package stllpt.com.aadtestsample

import android.content.Context
import org.junit.Test

import org.mockito.Mockito.*

import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    private val notes = ArrayList(Arrays.asList(
            MainPresenter.Note("Book1","Author1"),
            MainPresenter.Note("Book2","Author2"),
            MainPresenter.Note("Book3","Author3"),
            MainPresenter.Note("Book4","Author4")
    ))

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var mView: MainPresenter.View

    private lateinit var mainPresenter: MainPresenter

    @Mock
    private lateinit var linkedList: LinkedList<String>

    @Captor
    lateinit var listener: ArgumentCaptor<MainPresenter.LoadRepositoryData>

    companion object {
        private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        )
    }

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
        mainPresenter = MainPresenter(mView)
    }

    /**
     * verify method will verify the method
     */
    @Test
    fun test_addNoteCall() {
        mainPresenter.insertNote()

        verify(mView).addNote()
    }

    @Test
    fun test_stubbing() {
        `when`(linkedList[ArgumentMatchers.anyInt()]).thenReturn("Hello")

        `when`(linkedList.contains(ArgumentMatchers.argThat(isValida()))).thenReturn(true)
        linkedList.add("Hello")
        verify(linkedList).add(argThat { someString -> someString.length >= 5 })
    }

    private fun isValida(): ArgumentMatcher<String>? {
        return ArgumentMatcher {
            it.contains("Hello")
        }
    }

    @Test
    fun test_argumentCaptor() {
        mainPresenter.loadNotes()

        verify(mView).loadNotes(listener.capture())

        listener.value.onDataLoaded(notes)

        verify(mView).showProgressView(false)
        verify(mView).showNotes(notes)
    }

}
