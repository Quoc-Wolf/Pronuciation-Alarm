package asiantech.quocnp.pronuciation_alarm.models.database;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author quocnp
 *         Created by quocnp on 17/11/2015.
 */

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Vocabulary {
    @SerializedName("name")
    private String nameWord;
    @SerializedName("pronunciation")
    private String pronunciationWord;
    @SerializedName("icon")
    private String iconWord;
}
