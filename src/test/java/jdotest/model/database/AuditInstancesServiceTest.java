package jdotest.model.database;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.AuditType;
import jdotest.dto.enums.NameValuePairType;
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

    @Test
    public void testNameValuePairs() {
        System.out.println("CreateCompany");
        AuditInstancesService instance = new AuditInstancesService();
        AuditServiceInstancesMap result = instance.CreateInstancesAudit(new AuditServiceInstancesMapBase("ip address", "docker"));
        long instanceId = result.getAuditId();

        AuditService auditService = new AuditService();
        Map<String, String> results = auditService.GetAuditNameValuePairs(instanceId, NameValuePairType.HttpRequestHeaders);
        assertTrue(results.isEmpty());

        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        auditService.SetAuditNameValuePairs(instanceId, NameValuePairType.HttpRequestHeaders, map);
        results = auditService.GetAuditNameValuePairs(instanceId, NameValuePairType.HttpRequestHeaders);
        assertFalse(results.isEmpty());

        map.clear();
        auditService.SetAuditNameValuePairs(instanceId, NameValuePairType.HttpRequestHeaders, map);
        results = auditService.GetAuditNameValuePairs(instanceId, NameValuePairType.HttpRequestHeaders);
        assertTrue(results.isEmpty());

    }
}
