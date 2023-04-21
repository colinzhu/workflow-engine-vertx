package colinzhu.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event<R>{
    private final String name;
    private final R request;

    public Event(String name) {
        this.name = name;
        this.request = null;
    }
}
