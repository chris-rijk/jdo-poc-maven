package jdotest.model.modelClasses;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;

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
    
    public AuditServiceInstance(Audit audit, AuditServiceInstancesMapBase serviceInstance) {
        this.audit = audit;
        this.IpAddress = serviceInstance.getIpAddress();
        this.DockerImage = serviceInstance.getDockerImage();
    }

    public AuditServiceInstancesMap toAuditServiceInstancesMap() {
        return new AuditServiceInstancesMap(audit.getId(), audit.getCreateDateTime(), audit.getAuditType(), IpAddress, DockerImage);
    }

}
