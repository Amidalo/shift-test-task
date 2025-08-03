package wild.yellow;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DataTypeDefiner {

    public record DefinedType(DataTypeDefiner.DefinedType.Type type, Object value) {

            enum Type {
                INTEGER,
                DOUBLE,
                STRING
            }

    }

    public static DefinedType define(String input) {

        if (input == null || input.trim().isEmpty())
            return new DefinedType(DefinedType.Type.STRING, input);

        try {
            if (input.contains(".")) {
                BigDecimal value = new BigDecimal(input);
                return new DefinedType(DefinedType.Type.DOUBLE, value);
            } else {
                BigInteger value = new BigInteger(input);
                return new DefinedType(DefinedType.Type.INTEGER, value);
            }
        } catch (NumberFormatException e) {
            return new DefinedType(DefinedType.Type.STRING, input);
        }
    }
}
