import java.util.Map;
import java.util.TreeMap;

public class Table {
    private int tableId;
    private String tableName;
    private Map<Integer, Column> columns; // Map key: column position (Integer), value: Column object

    public Table(int tableId, String tableName ){
        this.tableId = tableId;
        this.tableName = tableName;
        this.columns = new TreeMap<>();// Sorted by column position (Integer natural order)
    }

    // Getters and setters
    public int getTableId(){
        return tableId;
    }

    public void setTableId(int tableId){
        this.tableId = tableId;
    }

    public String getTableName(){
        return tableName;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    public Map<Integer, Column> getColumns(){
        return columns;
    }

    public void setColumns(Map<Integer, Column> columns){
        this.columns = columns;
    }
    
    // Add or update a column at a given position
    public void addOrUpdateColumn(int position, Column column){
        columns.put(position, column);
    }

    // Remove a column by position
    public void removeColumn(int position){
        columns.remove(position);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table[id=%d, name='%s']\n", tableId,tableName));
        columns.forEach((pos, col) -> sb.append(" Pos ").append(pos).append(": ").append(col).append("\n"));
        return sb.toString();
    }

}


