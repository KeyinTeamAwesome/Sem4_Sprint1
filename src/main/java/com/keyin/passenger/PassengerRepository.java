package com.keyin.passenger;

// Java repositories are also known as JPAs (Java Persistence API) based repositories used under Java spring framework.
// @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository
        extends CrudRepository<Passenger, Long> {
}
