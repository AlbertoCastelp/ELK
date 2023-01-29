package comunir.unirproducts.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;


@Document(indexName = "products", createIndex = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ElasticProduct {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "name"),
        otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
    private String name;

    @Field(type = FieldType.Keyword, name = "country")
    private String country;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Boolean, name = "visible")
    private Boolean visible;
    // Getter and Setter
}