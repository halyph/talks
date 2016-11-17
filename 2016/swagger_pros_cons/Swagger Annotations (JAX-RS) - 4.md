```java
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ApiModel(value="metadata", description="Sample model for the documentation")
public class Metadata {

    @ApiModelProperty(value = "Used language", required = true, example = "en")
    String language;

    @ApiModelProperty(value = "API call transaction ID", required = true, example = "13739148009334525")
    String transaction_id;

    @ApiModelProperty(value = "API version number", example = "2")
    String version;

    @ApiModelProperty(value = "Unit of measure", required = true, allowableValues = "e, m, h, s", example = "m")
    String units;

    @ApiModelProperty(value = "Absolute expiration time and used to implement a common, system wide method of data and cache expiration.",
                      required = true,
                      example = "1373914800")
    long expire_time_gmt;

    @ApiModelProperty(value = "Response status code",
                        required = true,
                        example = "200")
    int status_code;
}
```