package netid.iastate.edu.lab5.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    //TODO - create a @Query to get all events that returns a List<Event> type
    @Query("SELECT * FROM event")
    List<Event>getAll();

    //TODO - create a @Query to get an event by ID that returns a Event type
    @Query("SELECT * FROM event WHERE uid IN (:id)")
    Event findByID(int id);

    //TODO - create an @Insert for an event object
    @Insert
    void insertEvent(Event event);

    //TODO - create an @Delete for an event object
    @Delete
    void delete(Event event);
}
