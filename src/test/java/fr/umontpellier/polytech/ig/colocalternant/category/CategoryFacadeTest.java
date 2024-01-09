package fr.umontpellier.polytech.ig.colocalternant.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryFacadeTest {
    private CategoryFacade categoryFacade;

    @BeforeEach
    void setUp() {
        categoryFacade = CategoryFacade.getInstance();
    }

    @Test
    void getAllCategories() {
        // Assuming DAOFactory and DAOSQLiteFactory are properly implemented and configured
        assertNotNull(categoryFacade.getAllCategories());
    }
}
