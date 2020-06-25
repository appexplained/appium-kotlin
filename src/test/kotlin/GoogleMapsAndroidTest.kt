import io.appium.java_client.MobileBy
import org.junit.Test
import org.openqa.selenium.interactions.Interaction
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class GoogleMapsAndroidTest: TestBase() {
    override var caps: DesiredCapabilities? = ProjectCapabilities.AndroidBaseCapabilities()

    // Point of Interest for Search
    private val pointOfInterest: String = "Lincoln Memorial Reflecting Pool"
    private val pointOfInterestTextAttribute: String = "Lincoln Memorial Circle Northwest, Washington, DC"

    // Elements
    private val firstLaunchSkipButton: String = """//*[@class="android.widget.Button" and @text="SKIP"]"""
    private val searchBox: String = "com.google.android.apps.maps:id/search_omnibox_text_box"
    private val searchBoxInput: String = "com.google.android.apps.maps:id/search_omnibox_edit_text"
    private val searchOptionListElement: String = """//*[@class="android.widget.TextView" and @text="$pointOfInterestTextAttribute"]"""
    private val resultCardTitle: String = "com.google.android.apps.maps:id/title"
    private val photosSection: String = "Photos"
    private val aboutSection: String = "About"


    @Test
    fun googleMapsTest() {

        // Set an explicit wait of 10 seconds
        val wait = WebDriverWait(driver?.let { it }, 10)

        // On first launch, press the SKIP button
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.xpath(firstLaunchSkipButton)))[0].click()
//        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(firstLaunchSkipButton)))

        // Click on the Search Box
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(searchBox))).click()

        // Enter point of interest
        val searchBoxInput = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(searchBoxInput)))
        searchBoxInput.sendKeys(pointOfInterest)

        // Tap the option
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(searchOptionListElement))).click()

        // Tap on the Card title
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(resultCardTitle))).click()

        // Tap on the Photos section
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(photosSection)))

        // Swipe Down
        val finger: PointerInput = PointerInput(PointerInput.Kind.TOUCH, "finger")
        val moveToStart: Interaction = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 533, 1449)
        val pressDown: Interaction = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        val moveToEnd: Interaction = finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), 553, 538)
        val pressUp: Interaction = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())

        val swipe = Sequence(finger, 0)
        swipe.addAction(moveToStart)
        swipe.addAction(pressDown)
        swipe.addAction(moveToEnd)
        swipe.addAction(pressUp)

        driver?.let { it.perform(arrayListOf(swipe)) }

        // Tap on the About section
        driver?.let { it.findElementByAccessibilityId(aboutSection).click() }












    }
}