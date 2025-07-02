package taskk26_Annotations.c;

@EntityInfo(label = "Pupils Table", entityType = EntityType.TABLE, dataType = DataType.TEXT)
public class Pupil {

    @EntityInfo(label = "Pupil ID", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
    private long pupilId;

    @EntityInfo(label = "First Name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String pupilFirstname;

    @EntityInfo(label = "Last Name", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String pupilLastname;

    @EntityInfo(label = "Email Address", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String pupilEmail;

    @EntityInfo(label = "Gender", entityType = EntityType.COLUMN, dataType = DataType.VARCHAR)
    private String gender;

    @EntityInfo(label = "Class ID (FK)", entityType = EntityType.COLUMN, dataType = DataType.BIGINT)
    private long classId;

    @EntityInfo(label = "Date Created", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
    private String dateCreated;

    @EntityInfo(label = "Date Modified", entityType = EntityType.COLUMN, dataType = DataType.TEXT)
    private String dateModified;

}
