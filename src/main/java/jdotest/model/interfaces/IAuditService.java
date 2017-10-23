package jdotest.model.interfaces;

import java.util.List;
import java.util.Map;
import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.DiagnosticAuditMap;
import jdotest.dto.enums.NameValuePairType;

public interface IAuditService {
    
    IAuditInstancesService CreateInstancesAudit();
    IAuditHttpRequestsService CreateHttpRequest();
    
    AuditHttpRequestMap GetHttpRequest(long auditId) throws JDOObjectNotFoundException;
    AuditServiceInstancesMap GetInstancesAudit(long auditId) throws JDOObjectNotFoundException;
    Map<String,String> GetAuditNameValuePairs(long auditId, NameValuePairType dataType);
    List<DiagnosticAuditMap> GetDiagnosticAudits(long auditId);
}
