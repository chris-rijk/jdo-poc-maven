package jdotest.model.database;

import java.time.Instant;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.AuditType;
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
public class AuditInstancesServiceTest {

    public AuditInstancesServiceTest() {
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
    public void testCreateAuditInstancesService() {
        System.out.println("testCreateAuditInstancesService");
        AuditInstancesService instance = new AuditInstancesService();

        Instant before = Instant.now();
        AuditServiceInstancesMap result = instance.CreateInstancesAudit(new AuditServiceInstancesMapBase("ip address", "docker"));
        Instant after = Instant.now();

        assertTrue(result.getAuditId() > 0);
        TestUtils.assertInRange(before, result.getCreateDateTime(), after);
        assertEquals("ip address", result.getIpAddress());
        assertEquals("docker", result.getDockerImage());
        assertEquals(AuditType.ServiceInstance, result.getAuditType());

        AuditServiceInstancesMap lookup = instance.GetInstancesAudit(result.getAuditId());
        assertEquals(result, lookup);
    }
}
