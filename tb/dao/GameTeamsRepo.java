package com.project.tb.dao;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.tb.models.GameTeams;

@Repository
public interface GameTeamsRepo extends CrudRepository <GameTeams , Long> {
	    @Transactional
		@Modifying
		@Query(value="INSERT INTO game_teams (guest, host,game_id) VALUES (:guest ,:host, :game_id)",nativeQuery = true)
		void addGameTeam(@Param("guest")int guestId,@Param("host")int hostId,@Param("game_id") Long game_id);
	    @Transactional
		@Modifying
		@Query(value="delete  from game_teams where game_id=:game_id",nativeQuery = true)
		void deleteGameTeams(@Param("game_id") Long game_id);
	    @Override
		@Query(value="select *  from game_teams where game_id=:game_id",nativeQuery = true)
	    Optional<GameTeams> findById(@Param("game_id") Long game_id);
	    @Transactional
		@Modifying
		@Query("UPDATE GameTeams set host = :hostId ,guest=:guestId where game_id=:game_id")
		void updateGameTeamsByGameId(int hostId, int guestId, int game_id);
}
