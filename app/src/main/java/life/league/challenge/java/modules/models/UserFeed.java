package life.league.challenge.java.modules.models;

import androidx.room.Entity;

@Entity(tableName = "user_table")
public class UserFeed {
    public int post_id;
    public int user_id;
    public String avatar;
    public String user_name;
    public String title;
    public String description;
}