package life.league.challenge.java.common.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "post_table", indices = {@Index(value = {"post_id"}, unique = true)})
public class Post {
    @PrimaryKey(autoGenerate = true)
    public int uId;
    @ColumnInfo(name = "post_id")
    public int postId;
    @ColumnInfo(name = "user_id")
    public int userId;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "description")
    public String description;

    public Post(int postId, int userId, String title, String description) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }
}