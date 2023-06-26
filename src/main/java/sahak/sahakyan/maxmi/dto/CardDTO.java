package sahak.sahakyan.maxmi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardDTO {
    @NotEmpty(message = "Error")
    @NotBlank(message = "Error")
    @Size(min = 4,max = 4)
    private String value1;
    @NotEmpty(message = "Error")
    @NotBlank(message = "Error")
    @Size(min = 4,max = 4)
    private String value2;
    @NotEmpty(message = "Error")
    @NotBlank(message = "Error")
    @Size(min = 4,max = 4)
    private String value3;
    private String value4;
    @NotEmpty(message = "Error")
    @NotBlank(message = "Error")
    @Size(min = 2,max = 2)
    private String expirationDate1;
    @NotEmpty(message = "Error")
    @NotBlank(message = "Error")
    @Size(min = 2,max = 2)
    private String expirationDate2;
    @NotEmpty(message = "Error")
    @NotBlank(message = "Error")
    @Size(min = 3,max = 3)
    private String cardHolder;
}
