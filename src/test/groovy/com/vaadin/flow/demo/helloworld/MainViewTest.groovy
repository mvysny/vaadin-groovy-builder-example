package com.vaadin.flow.demo.helloworld

import com.github.mvysny.kaributesting.v10.*
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.textfield.TextField
import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static com.github.mvysny.kaributesting.v10.NotificationsKt.*
import static com.github.mvysny.kaributesting.v10.groovy.LocatorG.*

/**
 * Tests the UI. Uses the Browserless testing approach as provided by the [Karibu Testing](https://github.com/mvysny/karibu-testing) library.
 */
@CompileStatic
class MainViewTest {
    private static Routes routes
    @BeforeAll
    static void discoverViews() {
        // Discovering the routes just once per test run speeds up runtime of consecutive tests considerably.
        // Discover the routes once and cache the result.
        routes = new Routes().autoDiscoverViews("com.vaadin.flow.demo")
    }

    @BeforeEach void setupVaadin() {
        // MockVaadin.setup() registers all @Routes, prepares the UI for us and navigates to the root route.
        MockVaadin.setup(routes)
    }
    @AfterEach void tearDownVaadin() { MockVaadin.tearDown() }

    @Test void "smoke test"() {
        // Smoke test is a quick test that at least basic stuff works.
        // The analogy would be to turn on an electric device (e.g. a coffee maker)
        // then turn it off immediately without even checking that it works,
        // and watch whether there is any smoke. If yes, the coffee maker is
        // probably burning from a short-circuit and any further tests are pointless.

        // The root route should be set directly in the UI; let's check whether it is so.
        // This demoes the direct access to the UI and its children and grand-children,
        // which encompasses all visible Vaadin components.
        def main = UI.current.children.findFirst().get() as MainView

        // however this kind of lookups quickly get pretty complicated. Let's use the _get() function instead,
        // which will walk the UI tree for us. See the next test for details.
    }

    @Test void "test greeting"() {
        // simulate an user input
        _get(TextField) { caption = "Your name" } ._value = "Martin"

        // simulate a button click as if clicked by the user
        _get(Button) { caption = "Say hello" } ._click()

        // look up the notification and assert on its value
        expectNotifications("Hello, Martin")
    }
}
