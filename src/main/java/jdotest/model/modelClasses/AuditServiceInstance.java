package jdotest.model.modelClasses;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;

@PersistenceCapable(table = "AuditsServiceInstances")
public class AuditServiceInstance {
    
    @PrimaryKey
    long AuditId;

    @SuppressWarnings("FieldMayBeFinal")
    private String IpAddress;

    @SuppressWarnings("FieldMayBeFinal")
    private String DockerImage;
    
    public AuditServiceInstance(long auditId, AuditServiceInstancesMapBase serviceInstance) {
        this.AuditId = auditId;
        this.IpAddress = serviceInstance.getIpAddress();
        this.DockerImage = serviceInstance.getDockerImage();
    }

    public AuditServiceInstancesMap toAuditServiceInstancesMap(Audit audit) {
        return new AuditServiceInstancesMap(audit.getId(), audit.getCreateDateTime(), audit.getAuditType(), IpAddress, DockerImage);
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public String getDockerImage() {
        return DockerImage;
    }
}
