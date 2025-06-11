import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TableManager {
    // Map key: table name, value: Table object
    private Map<String, Table> tables;

    public TableManager(){
        this.tables = new TreeMap<>();
    }

    //  Add a new table. Overwrites if table with the same name exists.
    public void addTable(Table table){
        tables.put(table.getTableName(), table);
    }

    // Add or update a column in a specified table by position.
    public void addOrUpdateColumn(String tableName, int position, Column column){
        Table table = tables.get(tableName);
        if (table != null){
            table.addOrUpdateColumn(position, column);
        }else{
            System.out.println("Table "+ tableName + " not found.");
        }
    }

    // Find tables containing a column with a given name.
    public List<Table> getTablesWithColumnName(String columnName){
        List<Table> result = new ArrayList<>();
        for(Table table : tables.values()){
            boolean found = table.getColumns().values()
                                 .stream()
                                 .anyMatch(column -> column.getColumName().equalsIgnoreCase(columnName));
        if (found){
            result.add(table);
        }
        }
        return result;
    }

    // Remove a column from a specified table by position.
    public void removeColumn(String tableName, int position) {
        Table table = tables.get(tableName);
        if (table != null) {
            table.removeColumn(position);
        } else {
            System.out.println("Table " + tableName + " not found.");
        }
    }
    
    // Display all tables and their columns.    
    public void displayAllTables(){
        if(tables.isEmpty()){
            System.out.println("No tables available.");
            return;
        }
        tables.values().forEach(table -> System.out.println(table));
    }

    // map of tables (useful for external querying if needed).
    public Map<String, Table> getTables(){
        return tables;
    };

}
