package springBoot.ems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springBoot.ems.Entity.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
	int findByStudentId = 0;
	public List<Club> findByClubNameAndClubPassword(String clubName, String clubPassword);
	public List<Club> findByClubName(String clubName);
	public boolean findByStudentIdAndStudentPassword11(String studentId, String studentPassword);


	
}
