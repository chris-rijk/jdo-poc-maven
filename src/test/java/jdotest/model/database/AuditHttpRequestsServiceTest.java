package jdotest.model.database;

import java.time.Instant;
import jdotest.dto.AuditHttpRequestsMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.HttpRequestSourceType;
import jdotest.dto.enums.HttpRequestType;
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
public class AuditHttpRequestsServiceTest {

    public AuditHttpRequestsServiceTest() {
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
    public void testCreateHttpRequestsService() {
        System.out.println("testCreateHttpRequestsService");

        AuditServiceInstancesMap serviceInstance = new AuditInstancesService().CreateInstancesAudit(new AuditServiceInstancesMapBase("ip address", "docker"));

        AuditHttpRequestsService instance = new AuditHttpRequestsService();

        Instant before = Instant.now();
        AuditHttpRequestsMap result = instance.CreateHttpRequest(new AuditHttpRequestsMapBase(serviceInstance.getAuditId(), "url", "body", HttpRequestType.Unknown, HttpRequestSourceType.Test));
        Instant after = Instant.now();

        assertTrue(result.getAuditId() > 0);
        TestUtils.assertInRange(before, result.getCreateDateTime(), after);
        assertEquals("url", result.getURL());
        assertEquals("body", result.getBody());
        assertEquals(HttpRequestType.Unknown, result.getRequestType());
        assertEquals(HttpRequestSourceType.Test, result.getRequestSourceType());

        AuditHttpRequestsMap lookup = instance.GetHttpRequest(result.getAuditId());
        assertEquals(result, lookup);
    }
}
