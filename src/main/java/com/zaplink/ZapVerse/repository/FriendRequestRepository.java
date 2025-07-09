package com.zaplink.ZapVerse.repository;

import com.zaplink.ZapVerse.model.FriendRequest;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {
    List<FriendRequest> findByProfileAndReceiverAndStatus(Profile profile, Profile receiver, RequestStatus status);

    List<FriendRequest> findByReceiverAndStatus(Profile receiver, RequestStatus status);

    List<FriendRequest> findByProfileAndStatus(Profile profile, RequestStatus status);
}
