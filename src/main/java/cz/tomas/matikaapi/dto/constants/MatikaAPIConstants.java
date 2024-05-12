package cz.tomas.matikaapi.dto.constants;

public class MatikaAPIConstants {

    private MatikaAPIConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Not meant to be instantiated");
    }

    public static final String PATH_SEPARATOR = "/";
    public static final String POST_PREFIX = PATH_SEPARATOR + "tasks" ;
    public static final String ADDITION_POST_PATH = POST_PREFIX + PATH_SEPARATOR + "scitani";
    public static final String SUBTRACTION_POST_PATH = POST_PREFIX + PATH_SEPARATOR + "odcitani";
    public static final String DIVISION_POST_PATH = POST_PREFIX + PATH_SEPARATOR + "deleni";
    public static final String MULTIPLICATION_POST_PATH = POST_PREFIX + PATH_SEPARATOR + "nasobeni";

}
