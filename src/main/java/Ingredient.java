import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"ingredients"})
public class Ingredient {
    public static String[] ingredients;

    @JsonCreator
    public Ingredient(
        @JsonProperty("ingredients") String[] ingredients) {
        this.ingredients = ingredients;
    }

    @JsonGetter("ingredients")
    public static String[] getIngredients() {
        return ingredients;
    }

    public static void setIngredients(String[] ingredients) {
        Ingredient.ingredients = ingredients;
    }
}
