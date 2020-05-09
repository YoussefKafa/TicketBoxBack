package com.project.tb.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.tb.models.*;

@Repository
public interface GameRepo extends CrudRepository <Game , Long>{
	 Optional<Game> findById(Long id);
	 @Transactional
		@Modifying
		@Query(value="INSERT INTO game_teams (game_id, team_id) VALUES (:gameId, :teamId)",nativeQuery = true)
		void addTeam(@Param("gameId")Long gameId,@Param("teamId") Long teamId);
	 @Transactional
		@Modifying
		@Query(value="UPDATE game SET fk_stadium=:stadiumId where game_id=:gameId",nativeQuery = true)
		void addStadium(@Param("gameId") Long gameId,@Param("stadiumId") Long stadiumId);
	 @Transactional
		@Modifying
		@Query(value="DELETE from game_teams where game_id=:gameId",nativeQuery = true)
	void deleteTeams(@Param("gameId") Long gameId);
	 @Transactional
		@Modifying
		@Query(value="DELETE from game_teams where game_id=:gameId AND team_Id=:teamId",nativeQuery = true)
	void deleteTeam(@Param("gameId") Long gameId,@Param("teamId") Long teamId);
}