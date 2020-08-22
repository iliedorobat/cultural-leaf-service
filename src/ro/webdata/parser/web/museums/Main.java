package ro.webdata.parser.web.museums;

import ro.webdata.parser.web.museums.commons.DataFormatter;
import ro.webdata.parser.web.museums.commons.constants.BackendAccessors;
import ro.webdata.parser.web.museums.core.Parser;

public class Main {
    public static void main(String[] args) {
        Parser.parse();
    }

    private static void test() {
        String phone = "; 0266.335.705";
        phone = "0244.367.461   si   2013344778";
        System.out.println(
                DataFormatter.format(BackendAccessors.CONTACT_PHONE, phone)
        );
    }
}
