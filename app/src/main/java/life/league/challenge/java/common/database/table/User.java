package life.league.challenge.java.common.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uId;
    @ColumnInfo(name = "user_id")
    public int userId;
    @ColumnInfo(name = "avatar")
    public String avatar;
    @ColumnInfo(name = "user_name")
    public String username;

    public User(int userId, String avatar, String username) {
        this.userId = userId;
        this.avatar = avatar;
        this.username = username;
    }
}