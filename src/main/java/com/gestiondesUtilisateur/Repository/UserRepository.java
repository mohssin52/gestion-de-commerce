package com.gestiondesUtilisateur.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestiondesUtilisateur.Entities.UserEntity;
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
 UserEntity findByEmail(String email);
 UserEntity findByUserId(String userId);
 @Query("select user from UserEntity user")
  Page<UserEntity> findAllUser(Pageable  pageable);
 @Query(value="SELECT * FROM users u WHERE (u.firstname LIKE %:search% OR u.lastname LIKE %:search%) AND  email_verification_status =:status ", nativeQuery=true)
 Page<UserEntity>findUsers(Pageable pageable,@Param ("search") String search,@Param("status") int status); 
}
