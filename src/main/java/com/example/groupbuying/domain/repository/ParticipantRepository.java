package com.example.groupbuying.domain.repository;

import com.example.domain.Member;
import com.example.groupbuying.domain.entity.Board;
import com.example.groupbuying.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    boolean existsByBoardRoomIdAndMember_LoginId(Integer boardId, String loginId);
    List<Participant> findByBoard(Board board);
    Optional<Participant> findByBoardRoomIdAndMember_LoginId(Integer boardId, String loginId);

    Optional<Participant> findByBoardRoomId(Integer boardId);

    Participant findByMember_Name(String name);

    //서경원 추가 부분
    List<Participant> findByMemberId(Long memberId);
}
