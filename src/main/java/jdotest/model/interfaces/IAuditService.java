package jdotest.model.interfaces;

import java.util.Map;
import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.enums.DiagnosticType;
import jdotest.dto.enums.NameValuePairType;

public interface IAuditService {
    
    IAuditInstancesService CreateInstancesAudit();
    IAuditHttpRequestsService CreateHttpRequest();
    
    void SetAuditNameValuePairs(long auditId, NameValuePairType dataType, Map<String,String> pairs);
    void AuditDiagnostics(long auditId, DiagnosticType diagnosticType, String message);

    AuditHttpRequestMap GetHttpRequest(long auditId) throws JDOObjectNotFoundException;
    AuditServiceInstancesMap GetInstancesAudit(long auditId) throws JDOObjectNotFoundException;
    Map<String,String> GetAuditNameValuePairs(long auditId, NameValuePairType dataType);
}
