package com.example.demo.repository;

import com.example.demo.entity.EventInvitation;
import com.example.demo.entity.EventInvitationId;
import com.example.demo.enums.EmailStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvitationRepository extends JpaRepository<EventInvitation, EventInvitationId> {

    @Query("""
            SELECT ei FROM EventInvitation ei
            JOIN FETCH ei.user
            JOIN FETCH ei.event e
            JOIN FETCH e.createdBy
            WHERE invitationId = :invitationId
            """)
    Optional<EventInvitation> getInvitationDetails(EventInvitationId invitationId);

    @Query("""
            SELECT ei FROM EventInvitation ei
            JOIN FETCH ei.event e
            JOIN FETCH e.createdBy
            WHERE ei.user.userId = :userId
            """)
    Page<EventInvitation> getInvitationDetailsByUserId(UUID userId, Pageable pageable); // by userId

    @Query("""
            SELECT ei FROM EventInvitation ei
            JOIN FETCH ei.user
            JOIN FETCH ei.event e
            JOIN FETCH e.createdBy
            WHERE e.eventId = :eventId
            """)
    Page<EventInvitation> getInvitationDetailsByEventId(UUID eventId, Pageable pageable); // by eventId

    @Modifying
    @Query("""
            DELETE FROM EventInvitation ei
            WHERE ei.event.eventId = :eventId
            """)
    void deleteByEventId(UUID eventId);

    @Query("""
            SELECT ei FROM EventInvitation ei
            JOIN FETCH ei.user
            JOIN FETCH ei.event e
            JOIN FETCH e.createdBy
            WHERE e.eventId = :eventId
            """)
    List<EventInvitation> getAllInvitationByEventId(UUID eventId);

    @Query("""
            SELECT ei FROM EventInvitation ei
            WHERE ei.user.email = :email
            """)
    Optional<EventInvitation> getInvitationByGuestEmail(String email);

    @Transactional
    @Modifying
    @Query("""
            UPDATE EventInvitation ei
            SET ei.emailStatus = :status
            WHERE invitationId = :invitationId
            """)
    void updateInvitationEmailStatus(EventInvitationId invitationId, EmailStatus status);
}
