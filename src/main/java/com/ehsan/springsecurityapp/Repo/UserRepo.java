package com.ehsan.springsecurityapp.Repo;
import com.ehsan.springsecurityapp.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users, Integer>{
}
