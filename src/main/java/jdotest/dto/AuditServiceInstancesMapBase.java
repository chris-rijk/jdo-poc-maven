package jdotest.dto;

public class AuditServiceInstancesMapBase {
    private final String IpAddress;
    private final String DockerImage;

    public AuditServiceInstancesMapBase(String IpAddress, String DockerImage) {
        this.IpAddress = IpAddress;
        this.DockerImage = DockerImage;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public String getDockerImage() {
        return DockerImage;
    }
}
