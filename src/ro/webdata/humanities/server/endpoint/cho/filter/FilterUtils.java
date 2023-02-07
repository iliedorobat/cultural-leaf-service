package ro.webdata.humanities.server.endpoint.cho.filter;

public class FilterUtils {
    public static boolean isEmpty(String value, String varName) {
        return value == null || varName == null || value.trim().isEmpty() || varName.trim().isEmpty();
    }
}
