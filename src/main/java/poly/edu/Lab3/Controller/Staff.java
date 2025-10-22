package poly.edu.Lab3.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    private String id;
    private String fullname;
    @Builder.Default
    private String photo = "photo.jpg";
    @Builder.Default
    private boolean gender = true;
    @Builder.Default
    private Date birthday = new Date(System.currentTimeMillis());
    @Builder.Default
    private Double salary = 12345.6789;
    @Builder.Default
    private Integer level = 0;

}
