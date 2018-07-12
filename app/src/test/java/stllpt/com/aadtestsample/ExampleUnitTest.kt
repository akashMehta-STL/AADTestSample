package stllpt.com.aadtestsample

import org.junit.Test
import org.hamcrest.Matchers.*
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertThat(4 == (2+2), `is`(true))
    }
}
