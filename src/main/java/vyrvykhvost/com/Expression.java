package vyrvykhvost.com;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Expression {
    private String id;
    private String body;
    private double value;

    public Expression(String id, String body, double value) {
        this.id = id;
        this.body = body;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", value=" + value +
                '}';
    }
}
