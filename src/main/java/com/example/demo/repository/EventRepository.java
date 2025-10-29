package com.example.demo.repository;


import com.example.demo.entity.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("""
            SELECT e FROM Event e
            WHERE e.eventId = :eventId AND e.createdBy.userId = :organizerId
            """)
    Optional<Event> getEvent(UUID organizerId, UUID eventId);

    @Transactional
    @Modifying
    @Query("""
            DELETE FROM Event e
            WHERE e.eventId = :eventId AND e.createdBy.userId = :organizerId
            """)
    void deleteEvent(UUID organizerId, UUID eventId);

    @Query("""
            SELECT e FROM Event e
            WHERE e.createdBy.userId = :organizerId
            """)
    Page<Event> getAllEvents(Pageable pageable, UUID organizerId);

    @Query("""
            SELECT e FROM Event e
            JOIN FETCH e.invitations i
            JOIN FETCH i.user u
            WHERE e.eventId = :eventId AND u.userId = :userId
            """)
    Optional<Event> getEventWhereGuestExist(UUID userId, UUID eventId);
}
