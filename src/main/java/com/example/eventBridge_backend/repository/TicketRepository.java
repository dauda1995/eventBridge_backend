package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public List<Ticket> findTicketsByCustomer_PersonId(Long customerId);

    public List<Ticket> findTicketsByEventEventID(Long eventId);

    public List<Ticket> findTicketsByEventCategories(Categories category);

}
