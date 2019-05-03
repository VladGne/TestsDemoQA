package framework.models;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class Order extends User {
    private String   name;
    private Category category;
    private int      quantity = 1;
    private Size     size     = Size.S;
    private String   color;
    private String   price;

    @Getter @ToString
    public enum Size{
        S,
        M,
        L
    }

    @Getter @ToString
    public enum Category{
        TOP,
        DRESS
    }
}
