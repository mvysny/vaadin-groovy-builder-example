/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.demo.helloworld

import com.vaadin.flow.component.Key
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.PWA
import groovy.transform.CompileStatic

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CompileStatic
class MainView extends VerticalLayout {
    MainView() {
        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content")

        // Use TextField for standard text input
        def textField = textField("Your name") {}

        // Button click listeners can be defined as lambda expressions
        button("Say hello") {
            setPrimary(); addClickShortcut(Key.ENTER)

            addClickListener {
                Notification.show("Hello, ${textField.value}")
            }
        }
    }
}
