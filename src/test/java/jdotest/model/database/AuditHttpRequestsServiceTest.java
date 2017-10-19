package jdotest.model.database;

import java.time.Instant;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.HttpRequestSourceType;
import jdotest.dto.enums.HttpRequestType;
import jdotest.dto.enums.HttpResponseType;
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
        AuditHttpRequestMap httpRequest = instance.CreateHttpRequest(new AuditHttpRequestsMapBase(serviceInstance.getAuditId(), "url", "body", HttpRequestType.Unknown, HttpRequestSourceType.Test));
        Instant after = Instant.now();
       
        assertTrue(httpRequest.getAuditId() > 0);
        TestUtils.assertInRange(before, httpRequest.getCreateDateTime(), after);
        assertEquals("url", httpRequest.getURL());
        assertEquals("body", httpRequest.getBody());
        assertEquals(HttpRequestType.Unknown, httpRequest.getRequestType());
        assertEquals(HttpRequestSourceType.Test, httpRequest.getRequestSourceType());

        AuditHttpRequestMap lookup = instance.GetHttpRequest(httpRequest.getAuditId());
        assertEquals(httpRequest, lookup);

        before = after;
        AuditHttpResponseMap httpResponse = instance.SetHttpResponse(httpRequest.getAuditId(), new AuditHttpResponseMapBase(HttpResponseType.Unknown, 200, "response body"));
        after = Instant.now();
        assertTrue(httpResponse.getRequestAuditId() > 0);
        TestUtils.assertInRange(before, httpResponse.getResponseTime(), after);
        assertEquals("response body", httpResponse.getBody());
        assertEquals(200, httpResponse.getStatusCode());
        assertEquals(HttpResponseType.Unknown, httpResponse.getHttpResponseType());
    }
}
