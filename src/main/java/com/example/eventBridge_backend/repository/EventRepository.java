package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Event;
import com.example.eventBridge_backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findEventByOrganiser_PersonId(Long organiserId);
    public List<Event> findEventsByCategoriesPreference(String preference);

    public List<Event> findEventsByOrganiser_PersonIdAndCategories_Preference(Long organiserId, String preference);


}
