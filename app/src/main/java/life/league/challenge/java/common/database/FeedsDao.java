package life.league.challenge.java.common.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;
import life.league.challenge.java.modules.models.UserFeed;

@Dao
public interface FeedsDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeed(Post... post);

    @Query("SELECT * FROM post_table INNER JOIN user_table ON user_table.user_id = post_table.user_id")
    LiveData<List<UserFeed>> getAllFeeds();

    @Query("SELECT * FROM user_table WHERE user_id= :userId")
    User fetchUserInfo(int userId);

    @Query("UPDATE user_table SET avatar = :mAvatar ,user_name= :mUsername WHERE user_id LIKE :userId ")
    void updateUser(String mAvatar, String mUsername, int userId);

    @Query("SELECT * FROM post_table WHERE post_id= :postId")
    Post fetchFeedInfo(int postId);

    @Query("UPDATE post_table SET title= :mTitle,description= :mDescription WHERE post_id LIKE :postId")
    void updateFeed(String mTitle, String mDescription, int postId);

    @Query("DELETE FROM post_table")
    void deleteAll();

    /**
     * Function for Inserting/Updating User Information To Users Table
     *
     * @param user
     */
    default void insertOrUpdateUser(User user) {
        User itemsFromDB = fetchUserInfo(user.userId);
        if (itemsFromDB == null) {
            insertUser(user);
        } else {
            updateUser(user.avatar, user.username, user.userId);
        }
    }

    /**
     * Function for Inserting/Updating Post Information To Feeds Table
     *
     * @param post
     */
    default void insertOrUpdateFeed(Post post) {
        Post itemsFromDB = fetchFeedInfo(post.postId);
        if (itemsFromDB == null) {
            insertFeed(post);
        } else {
            updateFeed(post.title, post.description, post.postId);
        }
    }
}