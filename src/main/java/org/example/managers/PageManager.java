package org.example.managers;

import org.example.pages.AddingPage;
import org.example.pages.FoodPage;
import org.example.pages.HomePage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка со списком товаров
     */
    private FoodPage foodPage;

    /**
     * Страничка добавления товара
     */
    private AddingPage addingPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Установка null для PageManager
     */
    public static void setPageManagerNull() {
        pageManager = null;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return HomePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link FoodPage}
     *
     * @return FoodPage
     */
    public FoodPage getFoodPage() {
        if (foodPage == null) {
            foodPage = new FoodPage();
        }
        return foodPage;
    }

    /**
     * Ленивая инициализация {@link AddingPage}
     *
     * @return AddingPage
     */
    public AddingPage getAddingPage() {
        if (addingPage == null) {
            addingPage = new AddingPage();
        }
        return addingPage;
    }
}