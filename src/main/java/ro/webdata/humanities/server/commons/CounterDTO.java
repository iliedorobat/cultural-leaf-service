package ro.webdata.humanities.server.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

// TODO: add a new member for the case of an error
public class CounterDTO {
    private Integer count;

    public CounterDTO(ArrayNode nodes) {
        if (nodes.size() == 0) {
            setCount(0);
        } else {
            JsonNode jsonNode = nodes.get(0);
            Integer count = JsonUtils.getNodeValueAsInteger(jsonNode, "count");

            setCount(count);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
