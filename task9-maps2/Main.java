import java.util.List;

public class Main {
    public static void main(String[] args) {
        TableManager manager = new TableManager();

        // Create a Table and add columns
        Table users = new Table(1, "Users");
        users.addOrUpdateColumn(1, new Column(101, "User_id", "INT"));
        users.addOrUpdateColumn(2, new Column(102, "username", "VARCHAR" ));
        users.addOrUpdateColumn(3,new Column(103,"date_created", "DATE"));

        //  Add table to manager
        manager.addTable(users);

        // Create orders table
        Table orders = new Table(2, "Orders");
        orders.addOrUpdateColumn(1, new Column(201, "order_id", "INT"));
        orders.addOrUpdateColumn(2, new Column(202, "user_id", "INT"));
        orders.addOrUpdateColumn(3, new Column(203, "date_created", "DATE"));

        manager.addTable(orders);

        // Add a new column to Users table
        manager.addOrUpdateColumn("Users", 4, new Column(104, "email", "VARCHAR"));

        // Edit a column in Orders table (update data type of order_id)
        manager.addOrUpdateColumn("Orders", 1, new Column(201, "order_id", "BIGINT"));

        // Remove a column from Users table
        manager.removeColumn("Users", 2); // Removes 'username'

        // Display all tables and columns
        System.out.println("All tables:");
        manager.displayAllTables();

        // Find tables that have a column named 'date_created'
        System.out.println("Tables with 'date_created' column:");
        List<Table> tablesWithDate = manager.getTablesWithColumnName("date_created");
        tablesWithDate.forEach(table -> System.out.println(" - " + table.getTableName()));
    }

}
