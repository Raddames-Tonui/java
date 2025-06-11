

//   Represents a column in a database table.
public class Column {
    private int columnId;
    private String columName;
    private String dataType;

    
    public Column(int columnId, String columName, String dataType) {
        this.columnId = columnId;
        this.columName = columName;
        this.dataType = dataType;
    }


    // Getters and Setters
    public int getColumnId() {
        return columnId;
    }
    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }
    public String getColumName() {
        return columName;
    }
    public void setColumName(String columName) {
        this.columName = columName;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    @Override
    public String toString(){
        return String.format("Column[id=%d, name='%s', type='%s']", columnId, columName, dataType);
    }

}