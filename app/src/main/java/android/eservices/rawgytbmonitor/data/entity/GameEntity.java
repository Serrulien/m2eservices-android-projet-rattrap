package android.eservices.rawgytbmonitor.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GameEntity {
    @NonNull
    @PrimaryKey
    public int id;
    public String name;
    public float rating;
    public String imageUrl;
}
