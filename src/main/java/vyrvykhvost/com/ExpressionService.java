package vyrvykhvost.com;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionService {

    private static final ExpressionJDBC EXPRESSION_JDBC = new ExpressionJDBC();

    public void addExpression(String body) {
        if (Tokenizer.isExpressionValid(body)) {
            double value = Parser.evaluate(body);
            EXPRESSION_JDBC.create(new Expression(null, body, value));
        }
    }

    public void deleteExpression(String id) {
        if (id != null) {
            EXPRESSION_JDBC.delete(id);
        }
    }

    public void updateExpression(Expression expression) {
        if (Tokenizer.isExpressionValid(expression.getBody())) {
            double value = Parser.evaluate(expression.getBody());
            EXPRESSION_JDBC.update(new Expression( expression.getId(), expression.getBody(), value));
        }
        System.out.println("Expression updated");
    }

    public void searchExpression(String value) {
        Pattern pattern = Pattern.compile("[\\d\\-+.]+");
        Matcher matcher = pattern.matcher(value);
        List<Expression> result = new ArrayList<>();
        if (matcher.matches()) {
            List<Expression> items = EXPRESSION_JDBC.findByValue(value);
            if (items.size() > 0) {
                for (Expression expression : items) {
                    result.add(expression);
                }
            }
        }
        for (Expression elem : result) {
            System.out.println(elem);
        }
    }

    public void getAllExpression() {
        ExpressionJDBC expressionJDBC = new ExpressionJDBC();
        System.out.println(expressionJDBC.getAll());
    }
}

