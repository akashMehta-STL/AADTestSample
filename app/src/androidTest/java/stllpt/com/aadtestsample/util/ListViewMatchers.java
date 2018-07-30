package stllpt.com.aadtestsample.util;

import android.support.test.espresso.intent.matcher.BundleMatchers;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.internal.util.Checks;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by stllpt031 on 30/7/18.
 */
public class ListViewMatchers {
    /**
     * Creates a matcher against the text stored in R.id.item_content. This text is roughly
     * "item: $row_number".
     */
    @SuppressWarnings("rawtypes")
    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher, final int position) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        Checks.checkNotNull(itemTextMatcher);
        return new BoundedMatcher<Object, ArrayList>(ArrayList.class) {
            @Override
            public boolean matchesSafely(ArrayList list) {
                return itemTextMatcher.matches(ViewMatchers.withText((String) list.get(position) ));
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }
}
