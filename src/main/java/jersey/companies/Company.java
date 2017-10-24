package jersey.companies;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import jdotest.dto.CompanyMap;

@ApiModel
public class Company extends CompanyBase {

    @ApiModelProperty(required = true, value = "Unique company ID")
    private long id;
    @ApiModelProperty(required = true, value = "Creation date", dataType = "dateTime")
    private Instant createDate;
    @ApiModelProperty(required = true, value = "Last modified date", dataType = "dateTime")
    private Instant lastModified;

    public Company() {
    }

    public Company(long id, String name, String platform) {
        super(name, platform);
        this.id = id;
        this.createDate = Instant.now();
        this.lastModified = Instant.now();
    }

    public Company(CompanyMap map) {
        super(map.getName(), map.getPlatform());
        this.id = map.getId();
        this.createDate = map.getCreateDateTime();
        this.lastModified = Instant.now();
    }

    public long getId() {
        return id;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Instant getLastModified() {
        return lastModified;
    }

}
