package crte.com.radio

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import crte.com.radio.dao.WorkModel
import crte.com.radio.ui.activity.WorkListActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("crte.com.radio", appContext.packageName)
        var works = WorkModel.select()
        for(work in  works){
            System.out.println(work.toString())
        }
        appContext.startActivity(Intent(appContext,WorkListActivity::class.java))
    }
}
