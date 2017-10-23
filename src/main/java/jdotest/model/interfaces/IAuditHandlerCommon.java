package jdotest.model.interfaces;

import java.util.Map;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.NameValuePairType;

/**
 *
 * @author crijk
 */
public interface IAuditHandlerCommon {
    long GetAuditId();

    void SetAuditNameValuePairs(NameValuePairType dataType, Map<String, String> pairs);

    void AuditDiagnostics(DiagnosticAuditMapBase diagnostic);
}
