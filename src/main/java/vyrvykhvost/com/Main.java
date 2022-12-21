package vyrvykhvost.com;

public class Main {
    private static final ExpressionService EXPRESSION_SERVICE = new ExpressionService();
    private static final ExpressionJDBC EXPRESSION_JDBC = new ExpressionJDBC();

    public static void main(String[] args) {
        EXPRESSION_SERVICE.getAllExpression();
        EXPRESSION_SERVICE.deleteExpression("1");
        EXPRESSION_SERVICE.getAllExpression();
        EXPRESSION_SERVICE.updateExpression(EXPRESSION_JDBC.getAll().get(1));
        EXPRESSION_SERVICE.addExpression("2+9*10");
        EXPRESSION_SERVICE.searchExpression("4");


    }
}
