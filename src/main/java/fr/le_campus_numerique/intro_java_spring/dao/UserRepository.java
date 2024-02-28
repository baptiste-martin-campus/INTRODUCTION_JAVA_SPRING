package fr.le_campus_numerique.intro_java_spring.dao;


import fr.le_campus_numerique.intro_java_spring.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
interface UserRepository extends CrudRepository<User, Integer> {

}
