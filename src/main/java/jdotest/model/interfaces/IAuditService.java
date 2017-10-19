package jdotest.model.interfaces;

import java.util.Map;
import jdotest.dto.enums.NameValuePairType;

public interface IAuditService {
    void SetAuditNameValuePairs(long auditId, NameValuePairType dataType, Map<String,String> pairs);
    Map<String,String> GetAuditNameValuePairs(long auditId, NameValuePairType dataType);
}
