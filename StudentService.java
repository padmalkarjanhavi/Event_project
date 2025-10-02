package springBoot.ems.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import springBoot.ems.Entity.Event;
import springBoot.ems.Entity.Student;
import springBoot.ems.Repository.EventRepository;


public class StudentService {

   
    private EventRepository studentRepository;

    public boolean validateLogin(String studentId, String studentPassword) {
        // Trim input
        studentId = studentId.trim();
        studentPassword = studentPassword.trim();

        // Check if student exists with matching ID and password
        return studentRepository.findByStudentIdAndStudentPassword(studentId, studentPassword);
    }

	public boolean existsByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	public Object participateInEvent(String string, Event e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<? extends Event> getParticipatedEvents(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cancelParticipation(String string, Event eventById) {
		// TODO Auto-generated method stub
		
	}

	public boolean validateStudent(String studentId, String studentPassword) {
		// TODO Auto-generated method stub
		return false;
	}
}
