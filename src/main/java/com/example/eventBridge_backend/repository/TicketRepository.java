package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Ticket;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.TicketDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public List<Ticket> findTicketsByCustomer_PersonId(Long customerId);

    public List<Ticket> findTicketsByEventEventID(Long eventId);

    public List<Ticket> findTicketsByEventCategories(Categories category);

    public Ticket findTicketByCustomer_PersonIdAndEvent_EventID(Long eventId, Long customerId);



    public List<Ticket> findTicketsByCustomer_PersonIdAndEvent_Categories_Preference(Long personId, String preference);
}
