package android.eservices.rawgytbmonitor.data.db;

import android.eservices.rawgytbmonitor.data.entity.GameEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GameEntity.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    public abstract GameDao bookDao();
}