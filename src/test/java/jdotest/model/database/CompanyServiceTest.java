package jdotest.model.database;

import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanyMapBase;
import jdotest.dto.enums.CompanyStatusType;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author crijk
 */
public class CompanyServiceTest {

    public CompanyServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of CreateCompany method, of class CompanyService.
     */
    @Test
    public void testCreateCompany() {
        System.out.println("CreateCompany");
        CompanyService instance = new CompanyService();
        CompanyMapBase company = new CompanyMapBase("Company name", CompanyStatusType.Enabled, "platform");
        CompanyMap result = instance.CreateCompany(company);

        assertTrue(result.getId() > 0);
        assertEquals(company.getStatusType(), result.getStatusType());
        assertEquals(company.getName(), result.getName());
        assertEquals(company.getPlatform(), result.getPlatform());

        CompanyMap lookup = instance.GetCompany(result.getId());
        assertEquals(result, lookup);
    }

    /**
     * Test of UpdateCompany method, of class CompanyService.
     */
    @Test
    public void testUpdateCompany() {
        CompanyMapBase company = new CompanyMapBase("Company name", CompanyStatusType.Enabled, "platform");
        CompanyMapBase updated = new CompanyMapBase("new name", CompanyStatusType.Disabled, "new platform");

        CompanyService instance = new CompanyService();
        CompanyMap result = instance.CreateCompany(company);
        boolean done = instance.UpdateCompany(result.getId(), updated);
        CompanyMap lookup = instance.GetCompany(result.getId());

        assertTrue(done);
        assertEquals(updated.getStatusType(), lookup.getStatusType());
        assertEquals(updated.getName(), lookup.getName());
        assertEquals(updated.getPlatform(), lookup.getPlatform());
    }

    /**
     * Test of GetCompany method, of class CompanyService.
     */
    @Test
    public void testGetCompany_NotFound_ReturnsNull() {
        System.out.println("GetCompany");
        CompanyService instance = new CompanyService();
        try {
            CompanyMap result = instance.GetCompany(Long.MAX_VALUE);
            assertNotNull(result);
            fail("look up should have thrown an error");
        } catch (JDOObjectNotFoundException ex) {
            assertNotNull(ex);
        }
    }
}
