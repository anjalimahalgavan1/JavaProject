package dataApp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dataApp.Model.SbPersonInfo;

@Repository
public interface SbPersonInfoRepository extends MongoRepository<SbPersonInfo, String> {
	public SbPersonInfo findByFirstName(String firstName);

	public List<SbPersonInfo> findByAge(int age);
}
