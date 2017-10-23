package jersey.companies;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;

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

    public Company(String name, String platform) {
        super(name, platform);
        this.createDate = Instant.now();
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
