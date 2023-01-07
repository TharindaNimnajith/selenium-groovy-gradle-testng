package com.example.seleniumgroovygradletestng

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.testng.annotations.*
import static org.testng.Assert.*

import static com.codeborne.selenide.Condition.attribute
import static com.codeborne.selenide.Condition.visible
import static com.codeborne.selenide.Selenide.*

class MainPageTest {
    MainPage mainPage = new MainPage()

    @BeforeClass
    static void setUpAll() {
        Configuration.browserSize = "1280x800"
        SelenideLogger.addListener("allure", new AllureSelenide())
    }

    @BeforeMethod
    void setUp() {
        open("https://www.jetbrains.com/")
    }

    @Test
    void search() {
        mainPage.searchButton.click()

        $("[data-test='search-input']").sendKeys("Selenium")
        $("button[data-test='full-search-button']").click()

        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"))
    }

    @Test
    void toolsMenu() {
        mainPage.toolsMenu.click()

        $("div[data-test='main-submenu']").shouldBe(visible)
    }

    @Test
    void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click()
        mainPage.findYourToolsButton.click()

        $("#products-page").shouldBe(visible)
        assertEquals(Selenide.title(), "All Developer Tools and Products by JetBrains")
    }
}
