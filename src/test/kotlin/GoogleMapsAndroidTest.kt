import io.appium.java_client.MobileBy
import org.junit.Test
import org.openqa.selenium.interactions.Interaction
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class GoogleNewsAndroidTest: TestBase() {
    override var caps: DesiredCapabilities? = ProjectCapabilities.AndroidBaseCapabilities()
    private val headlinesTabButton: String = "com.google.android.apps.magazines:id/tab_headlines"

    @Test
    fun headlinesScrollTest() {

        // Set an explicit wait of 10 seconds
        val wait = WebDriverWait(driver?.let { it }, 10)

        // Tap on the Headlines tab button
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(headlinesTabButton))).click()

        // Scroll Down
        val finger: PointerInput = PointerInput(PointerInput.Kind.TOUCH, "finger")
        val moveToStart: Interaction = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 726, 2452)
        val pressDown: Interaction = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        val moveToEnd: Interaction = finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 726, 660)
        val pressUp: Interaction = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())

        val swipe = Sequence(finger, 0)
        swipe.addAction(moveToStart)
        swipe.addAction(pressDown)
        swipe.addAction(moveToEnd)
        swipe.addAction(pressUp)

        driver?.let { it.perform(arrayListOf(swipe)) }

    }
}