package wild.yellow;

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
                double value = Double.parseDouble(input);
                return new DefinedType(DefinedType.Type.DOUBLE, value);
            } else {
                int value = Integer.parseInt(input);
                return new DefinedType(DefinedType.Type.INTEGER, value);
            }
        } catch (NumberFormatException e) {
            return new DefinedType(DefinedType.Type.STRING, input);
        }
    }
}
