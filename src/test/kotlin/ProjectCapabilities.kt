import org.openqa.selenium.remote.DesiredCapabilities

class ProjectCapabilities {
    companion object {
        fun AndroidBaseCapabilities(): DesiredCapabilities {
            val caps = DesiredCapabilities()
            caps.setCapability("autoAcceptAlerts", true)
            caps.setCapability("platformName", "Android")
            caps.setCapability("automationName", "UiAutomator2")
            caps.setCapability("deviceName", "Android Emulator")
            caps.setCapability("platformVersion", "10")
            caps.setCapability("appPackage", "com.google.android.apps.maps")
            caps.setCapability("appActivity", "com.google.android.maps.MapsActivity")
            return caps
        }
    }
}