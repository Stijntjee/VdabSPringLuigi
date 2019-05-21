
package be.vdab.luigi.controllers;

import be.vdab.luigi.domain.Pizza;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PizzaControllerTest {
    private PizzaController controller;
    @Before
    public void before() {
        controller = new PizzaController();
    }
    @Test
    public void pizzasGebruiktDeThymeleafPaginaPizzas() {
        assertThat(controller.pizzas().getViewName()).isEqualTo("pizzas");
    }
    @Test
    public void pizzasGeeftPizzasDoorAanDeThymeleafPagina() {
        assertThat(controller.pizzas().getModel().get("pizzas") instanceof Pizza[])
                .isTrue();
    }
    @Test
    public void pizzaGebruiktDeThymeleafPaginaPizza() {
        assertThat(controller.pizza(1).getViewName()).isEqualTo("pizza");
    }
    @Test
    public void pizzaGeeftGevondenPizzaDoorAanDeThymeleafPagina() {
        Pizza pizza = (Pizza) controller.pizza(1).getModel().get("pizza");
        assertThat(pizza.getId()).isEqualTo(1);
    }
    @Test
    public void pizzaGeeftOnbestaandePizzaNietDoorAanDeThymeleafPagina() {
        assertThat(controller.pizza(-1).getModel()).doesNotContainKeys("pizza");
    }
}