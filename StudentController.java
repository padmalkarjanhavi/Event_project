package springBoot.ems.Controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import springBoot.ems.Entity.Event;
import springBoot.ems.Entity.Student;
import springBoot.ems.Service.EventService;
import springBoot.ems.Service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final EventService eventService;

    @Autowired
    public StudentController(StudentService studentService, EventService eventService) {
        this.studentService = studentService;
        this.eventService = eventService;
    }

    // Login page
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn() {
        return "studentSignIn";
    }


    // Dashboard
    @RequestMapping(value = "/studentDashboard", method = RequestMethod.GET)
    public String showDashboard(HttpServletRequest request, ModelMap modelMap) {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student == null) {
            return "redirect:/student/signIn";
        }

        Set<Event> participatedEvents = new HashSet<>(studentService.getParticipatedEvents(student.getsId()));
        Set<Event> liveEvents = new HashSet<>(eventService.getAllLiveEvents());
        liveEvents.removeAll(participatedEvents);

        modelMap.addAttribute("student", student);
        modelMap.addAttribute("liveEvents", liveEvents);
        modelMap.addAttribute("expiredEvents", eventService.getAllExpiredEvents());
        modelMap.addAttribute("date", LocalDate.now());

        return "studentDashboard";
    }

    // Add student (registration)
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@RequestParam String studentId,
                             @RequestParam String studentName,
                             @RequestParam String studentPassword,
                             @RequestParam String studentBranch,
                             @RequestParam int studentSem,
                             ModelMap modelMap) {
        if (!studentService.existsByStudentId(studentId)) {
            Student newStudent = new Student(studentId, studentName, studentBranch, studentSem, studentPassword);
            studentService.addStudent(newStudent);
            modelMap.addAttribute("msg", "Sign in with the registered credentials");
            return "studentSignIn";
        }
        modelMap.addAttribute("msg", "The student ID is already registered. Please login.");
        return "registerStudent";
    }

    // Registration page
    @RequestMapping(value = "/registerStudent", method = RequestMethod.GET)
    public String registerStudent() {
        return "registerStudent";
    }

    // Participate in event
    @RequestMapping(value = "/participate/{eId}", method = RequestMethod.GET)
    public String participate(HttpServletRequest request, @PathVariable int eId) {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student != null) {
            Event event = eventService.getEventById(eId);
            studentService.participateInEvent(student.getsId(), event);
        }
        return "redirect:/student/studentDashboard";
    }

    // Participated events page
    @RequestMapping(value = "/participatedEvents", method = RequestMethod.GET)
    public String participatedEvents(HttpServletRequest request, ModelMap modelMap) {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student == null) {
            return "redirect:/student/signIn";
        }

        Set<Event> participatedEvents = new HashSet<>(studentService.getParticipatedEvents(student.getsId()));
        Set<Event> liveEvents = new HashSet<>(eventService.getAllLiveEvents());
        Set<Event> expiredEvents = new HashSet<>(eventService.getAllExpiredEvents());

        Set<Event> participatedLive = new HashSet<>(participatedEvents);
        participatedLive.retainAll(liveEvents);

        Set<Event> participatedExpired = new HashSet<>(participatedEvents);
        participatedExpired.retainAll(expiredEvents);

        modelMap.addAttribute("participatedEvents", participatedLive);
        modelMap.addAttribute("expiredEvents", participatedExpired);

        return "participatedEvents";
    }

    // Cancel participation
    @GetMapping("/cancelParticipation/{eId}")
    public String cancelParticipation(HttpServletRequest request, @PathVariable int eId) {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student != null) {
            Event event = eventService.getEventById(eId);
            studentService.cancelParticipation(student.getsId(), event);
        }
        return "redirect:/student/participatedEvents";
    }

    // Logout
    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/student/signIn";
    }
}
