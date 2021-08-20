package life.league.challenge.java.common.datastore.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import life.league.challenge.java.R;
import life.league.challenge.java.common.database.FeedsDao;
import life.league.challenge.java.common.database.table.Post;
import life.league.challenge.java.common.database.table.User;

/**
 * Created by Pramod Selvaraj on 03-07-2021.
 * This class Manages The League App Database
 */
@Database(entities = {User.class, Post.class}, version = 1)
public abstract class LeagueDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile LeagueDatabase INSTANCE;

    public static LeagueDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LeagueDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LeagueDatabase.class, context.getResources().getString(R.string.database)).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract FeedsDao feedsDao();
}