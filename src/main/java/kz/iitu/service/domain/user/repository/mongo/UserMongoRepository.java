package kz.iitu.service.domain.user.repository.mongo;

import kz.iitu.service.domain.user.model.Status;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.domain.user.model.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
    List<UserMongo> findAllByStatus(Status status);
}
