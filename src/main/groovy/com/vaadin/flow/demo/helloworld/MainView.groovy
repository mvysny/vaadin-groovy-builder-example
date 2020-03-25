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

import com.github.mvysny.vaadingroovybuilder.v14.GComposite
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.textfield.TextField
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
class MainView extends GComposite {
    private TextField name
    private Button sayHello
    private def root = ui {
        verticalLayout {
            // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
            addClassName("centered-content")

            // Use TextField for standard text input
            name = textField("Your name") {}

            // Button click listeners can be defined as lambda expressions
            sayHello = button("Say hello") {
                setPrimary(); addClickShortcut(Key.ENTER)
            }
        }
    }

    MainView() {
        sayHello.addClickListener {
            Notification.show("Hello, ${name.value}")
        }
    }
}
