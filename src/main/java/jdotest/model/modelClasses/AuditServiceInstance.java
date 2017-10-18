package jdotest.model.modelClasses;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import jdotest.dto.AuditServiceInstancesMap;

@PersistenceCapable(table = "AuditsServiceInstances")
public class AuditServiceInstance {
    
    @PrimaryKey
    @Column(name="AuditId")
    @SuppressWarnings("FieldMayBeFinal")
    private Audit audit;
    @SuppressWarnings("FieldMayBeFinal")
    private String IpAddress;
    @SuppressWarnings("FieldMayBeFinal")
    private String DockerImage;
    
    public AuditServiceInstance(Audit audit, String ipAddress, String dockerImage) {
        this.audit = audit;
        this.IpAddress = ipAddress;
        this.DockerImage = dockerImage;
    }

    public AuditServiceInstancesMap toAuditServiceInstancesMap() {
        return new AuditServiceInstancesMap(audit.getId(), audit.getCreateDateTime(), audit.getAuditType(), IpAddress, DockerImage);
    }

}
