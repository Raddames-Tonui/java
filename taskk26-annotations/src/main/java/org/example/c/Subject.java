package org.example.c;


@EntityInfo(label = "Subjects Table", entityType = EntityType.TABLE, dataType = DataType.TEXT)
public class Subject {
    @EntityInfo(label = "Subject ID", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
    private long subjectId;

    @EntityInfo(label = "Subject Code", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String subjectCode;

    @EntityInfo(label = "Subject Name", entityType =  EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String subjectName;

    @EntityInfo(label = "Date Created", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
    private String dateCreated;

    @EntityInfo(label = "Date Modified", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
    private String dateModified;
}
