package org.example.c;

@EntityInfo(label = "Exams Table", entityType = EntityType.TABLE, dataType = DataType.TEXT)
public class Exam {

    @EntityInfo(label = "Exam ID", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
    private long examId;

    @EntityInfo(label = "Exam Title", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String examTitle;

    @EntityInfo(label = "Teacher ID (FK)", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
    private Long teacherId;  // Nullable in SQL, so use `Long` (wrapper class)

    @EntityInfo(label = "Subject ID (FK)", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
    private long subjectId;

    @EntityInfo(label = "Date Created", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
    private String dateCreated;

    @EntityInfo(label = "Date Modified", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
    private String dateModified;

}
