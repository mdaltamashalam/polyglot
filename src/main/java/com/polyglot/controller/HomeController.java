package com.polyglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.polyglot.models.*;
import com.polyglot.services.courseService;
import com.polyglot.services.userService;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Controller
public class HomeController {

    @Autowired
    private userService UserService;
    @Autowired
    private courseService CourseService;
    

    @GetMapping("/")
    public String index() {
        // Return the name of the HTML file without extension
        return "index";
    }
    @GetMapping("/FAQs")
    public String FAQs() {
        // Return the name of the HTML file without extension
        return "FAQs";
    }
    @GetMapping("/Features")
    public String Features() {
        // Return the name of the HTML file without extension
        return "Features";
    }
    @GetMapping("/Benefits")
    public String Benifits() {
        // Return the name of the HTML file without extension
        return "Benefits";
    }
    @GetMapping("/FAQsProfile")
    public String FAQsProfile(Model model, HttpSession session) {
        // Return the name of the HTML file without extension
    	String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from database based on username
        	session.setAttribute("username", username);
            user user = UserService.getUserByUsername(username);
            // Add user details to the model
            model.addAttribute("user", user);
            return "FAQsProfile";
        } else {
            // If user is not authenticated, redirect to login page
            return "redirect:/login";
        }
    }
    @GetMapping("/FeaturesProfile")
    public String FeaturesProfile(Model model, HttpSession session) {
        // Return the name of the HTML file without extension
    	String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from database based on username
        	session.setAttribute("username", username);
            user user = UserService.getUserByUsername(username);
            // Add user details to the model
            model.addAttribute("user", user);
            return "FeaturesProfile";
        } else {
            // If user is not authenticated, redirect to login page
            return "redirect:/login";
        }
    }
    @GetMapping("/BenefitsProfile")
    public String BenifitsProfile(Model model, HttpSession session) {
        // Return the name of the HTML file without extension
    	String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from database based on username
        	session.setAttribute("username", username);
            user user = UserService.getUserByUsername(username);
            // Add user details to the model
            model.addAttribute("user", user);
            return "BenefitsProfile";
        } else {
            // If user is not authenticated, redirect to login page
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login() {
        // Return the name of the login HTML file without extension
        return "Login";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        // Retrieve the authenticated user's username from session
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from database based on username
            session.setAttribute("username", username);
            user user = UserService.getUserByUsername(username);
            if (user.getStatus() != null && user.getStatus().equals("blocked")) {
                model.addAttribute("error", "Your account has been blocked. Please contact the administrator.");
                return "Login";
                
            }
            // Add user details to the model
            model.addAttribute("user", user);
            // Return the profile.html template
            return "profile";
        } else {
            // If user is not authenticated, redirect to login page
            return "redirect:/login";
        }
    }


    @PostMapping("/login")
    public String verifyLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        List<user> users = UserService.getUserByIdPass(username, password);
        if (users != null && !users.isEmpty()) {
            // If user exists and password matches, store username in session
            session.setAttribute("username", username);
            return "redirect:/profile"; // Redirect to profile page without username parameter in URL
        } else {
            // If user doesn't exist or password doesn't match, add an error message and redirect back to login page
            model.addAttribute("error", "Invalid username or password");
            return "Login";
        }
    }
   
    
    @GetMapping("/userprofile")
    public String userProfile(Model model, HttpSession session) {
        // Fetch user details from database based on username
        String username = (String) session.getAttribute("username");

        user user = UserService.getUserByUsername(username);
        // Add user details to the model
        model.addAttribute("user", user);
        // Return the userprofile.html template
        return "UserProfile";
    }
    @GetMapping("/register")
    public String registerUser() {
    	return "Register";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam("DOB") LocalDate dateOfBirth,
                               @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                               Model model) {
        // Check if email already exists in the database
        user existingUser = UserService.getUserByEmail(email);
        if (existingUser != null) {
            model.addAttribute("error", "Email already exists. Please choose a different one.");
            return "Register"; // Return to registration page with error message
        }

        // Check if username already exists in the database
        user existingUsername = UserService.getUserByUsername(username);
        if (existingUsername != null) {
            model.addAttribute("error", "Username already exists. Please choose a different one.");
            return "Register"; // Return to registration page with error message
        }
        String user_profie_path="/images/User Images.jpg";
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/images/user_profile/";
                String fileName = username + "." + profilePicture.getOriginalFilename().split("\\.")[1]; // Assuming the file extension is always present
                Path uploadPath = Paths.get(uploadDir + fileName);

                Files.createDirectories(uploadPath.getParent());
                Files.write(uploadPath, profilePicture.getBytes());
                user_profie_path="images/user_profile/" + fileName;
                
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "something went wrong with the profile image");
                return "Register";
            }
        }

        // If email and username are unique, proceed with registration
        user newUser = new user();
        newUser.setUsername(username); // Set the username
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setDob(dateOfBirth);
        newUser.setUser_profile(user_profie_path); 

        // Save the user data to the database
        UserService.saveUser(newUser);

        // Redirect to login page after successful registration
        return        "redirect:/login";
    }
    @GetMapping("/learningIndex")
    public String indexLearning(Model model) {
        List<courses> courses = CourseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "indexLearning"; // This corresponds to the Thymeleaf template file (index.html)
    }
    @GetMapping("/courses")
    public String Courses(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from the database based on username
            user user = UserService.getUserByUsername(username);
            if (user != null) {
                // Check if coursesEnrolled is null
            	model.addAttribute("user", user);
                String coursesEnrolled = user.getCoursesEnrolled();
                if (coursesEnrolled != null) {
                    // Split the coursesEnrolled string by comma to get the course ids
                    String[] courseIds = coursesEnrolled.split(",");
                    // Fetch the details of each enrolled course from the database
                    List<courses> enrolledCourses = new ArrayList<>();
                    for (String courseId : courseIds) {
                        Long id = Long.parseLong(courseId.trim());
                        courses course = CourseService.getCourseById(id);
                        if (course != null) {
                            enrolledCourses.add(course);
                        }
                    }
                    model.addAttribute("enrolledCourses", enrolledCourses);
                } else {
                    // If coursesEnrolled is null, add an empty list to the model
                    model.addAttribute("enrolledCourses", new ArrayList<courses>());
                }
            }
        } else {
            // Redirect to login page if user is not authenticated
            return "redirect:/login";
        }
        
        // Fetch all courses from the database
        List<courses> courses = CourseService.getAllCourses();
        model.addAttribute("courses", courses);
        
        return "Learning";
    }


    @PostMapping("/enroll/{courseId}")
    public String enrollCourse(@PathVariable Long courseId, HttpSession session) {
        // Get the authenticated user's username from the session
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from the database based on username
            user user = UserService.getUserByUsername(username);
            if (user != null) {
                // Fetch the course based on courseId
                courses course = CourseService.getCourseById(courseId);
                if (course != null) {
                    // Concatenate the courseId to the coursesEnrolled string in the user's record
                    String coursesEnrolled = user.getCoursesEnrolled();
                    if (coursesEnrolled != null) {
                        coursesEnrolled += "," + courseId;
                    } else {
                        coursesEnrolled = String.valueOf(courseId);
                    }
                    // Update the user's coursesEnrolled in the database
                    user.setCoursesEnrolled(coursesEnrolled);

                    // Check if complition_percentage and pages are null
                    String complitionPercentage = user.getComplitionPercentage();
                    String pages = user.getPage();
                    if (complitionPercentage == null) {
                        user.setComplitionPercentage("0");
                    } else {
                        user.setComplitionPercentage(complitionPercentage + ",0");
                    }
                    if (pages == null) {
                        user.setPage("0");
                    } else {
                        user.setPage(pages + ",0");
                    }

                    UserService.saveUser(user);
                    // Redirect to a page indicating successful enrollment
                    return "redirect:/courses"; // Change this to the appropriate URL
                }
            }
        }
        // Redirect to login page if user is not authenticated or course not found
        return "redirect:/login";
    }
    

    public class ListUtils {

        public static <T> int indexOf(List<T> list, T element) {
            return list.indexOf(element);
        }
    }
    

    @Configuration
    public class AppConfig {

        @Bean
        public ListUtils listUtils() {
            return new ListUtils();
        }
    }
    


    @GetMapping("/myLearnings")
    public String myLearnings(Model model, HttpSession session) {
        // Get the authenticated user's username from the session
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from the database based on username
        	session.setAttribute("username", username);
            user user = UserService.getUserByUsername(username);
            if (user != null) {
                // Check if coursesEnrolled is null
            	model.addAttribute("user", user);
                String coursesEnrolled = user.getCoursesEnrolled();
                if (coursesEnrolled != null) {
                    // Split the coursesEnrolled string by comma to get the course ids
                    String[] courseIds = coursesEnrolled.split(",");
                    // Fetch the details of each enrolled course from the database
                    List<courses> enrolledCourses = new ArrayList<>();
                    for (String courseId : courseIds) {
                        Long id = Long.parseLong(courseId.trim());
                        courses course = CourseService.getCourseById(id);
                        if (course != null) {
                            enrolledCourses.add(course);
                        }
                    }
                    String enrolledCoursesStr = user.getCoursesEnrolled();
                    String metringPercentagesStr = user.getComplitionPercentage();
                    
                    // Split the enrolled courses, pages, and metring percentages strings
                    List<String> EnrolledCourses = Arrays.asList(enrolledCoursesStr.split(","));
                    List<String> metringPercentages = Arrays.asList(metringPercentagesStr.split(","));
                    model.addAttribute("enrolledCourses", enrolledCourses);
                    model.addAttribute("EnrolledCourses", EnrolledCourses);
                    model.addAttribute("metringPercentages", metringPercentages);
                } else {
                    // If coursesEnrolled is null, add an empty list to the model
                    model.addAttribute("enrolledCourses", new ArrayList<>());
                }
                return "MyLearning"; // Return the template to display enrolled courses
            }
        }
        // Redirect to login page if user is not authenticated or enrolled courses not found
        return "redirect:/login";
    }
    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // Fetch user details from the database based on username
            user user = UserService.getUserByUsername(username);
            if (user != null) {
            	session.setAttribute("username", username);
                // Check if the user has the role "admin"
                if (user.getRole().equals("admin")) {
                    // If user is an admin, add user details to the model and return the admin view
                    model.addAttribute("user", user);
                    
                    // Check if success message exists in the session
                    String successMessage = (String) session.getAttribute("successMessage");
                    if (successMessage != null) {
                        // If success message exists, add it to the model and remove it from the session
                        model.addAttribute("successMessage", successMessage);
                        session.removeAttribute("successMessage");
                    }
                    
                    // Check if error message exists in the session
                    String errorMessage = (String) session.getAttribute("errorMessage");
                    if (errorMessage != null) {
                        // If error message exists, add it to the model and remove it from the session
                        model.addAttribute("errorMessage", errorMessage);
                        session.removeAttribute("errorMessage");
                    }
                    
                    return "Admin";
                } else {
                    // If user is not an admin, redirect to the profile page
                    return "redirect:/profile";
                }
            }
        }
        // If user is not logged in or user details not found, redirect to the login page
        return "redirect:/login";
    }


    @PostMapping("/addCourse")
    public String addCourse(@RequestParam("course_name") String courseName,
                            @RequestParam("course_about") String courseAbout,
                            @RequestParam("course_file") MultipartFile courseFile,
                            HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
        	session.setAttribute("username", username);
        	user user = UserService.getUserByUsername(username);
        	if ((!user.getRole().equals("admin"))||(user.getRole() == null)) {
        		 return "redirect:/profile";
            }
        	
            if (CourseService.getCourseByName(courseName) != null) {
                session.setAttribute("errorMessage", "Course with the same name already exists!");
                return "redirect:/admin";
            }

            String user_profile_path = "/images/Polyglot2.jpg";
            if (courseFile != null && !courseFile.isEmpty()) {
                try {
                    String uploadDir = "src/main/resources/static/images/Courses/";
                    String fileName = courseName + "." + courseFile.getOriginalFilename().split("\\.")[1];
                    Path uploadPath = Paths.get(uploadDir + fileName);

                    Files.createDirectories(uploadPath.getParent());
                    Files.write(uploadPath, courseFile.getBytes());
                    user_profile_path = "images/Courses/" + fileName;
                } catch (IOException e) {
                    e.printStackTrace();
                    session.setAttribute("errorMessage", "Something went wrong with the profile image");
                    return "redirect:/admin";
                }
            }
            courses course = new courses();
            course.setCourseName(courseName);
            course.setCourseAbout(courseAbout);
            course.setCourseImageLink(user_profile_path);

            CourseService.save(course);

            session.setAttribute("errorMessage", "Course added successfully!");
            return "redirect:/ContentManagement";
        }
        return "redirect:/login";
    }
    

        // Get method to fetch the course details by id
    @GetMapping("/updateCourse/{id}")
    public String updateCourseDetails(@PathVariable Long id, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
        	session.setAttribute("username", username);
        	user user = UserService.getUserByUsername(username);
        	if ((!user.getRole().equals("admin"))||(user.getRole() == null)) {
        		 return "redirect:/profile";
            }
            courses course = CourseService.getCourseById(id);
            if (course != null) {
                model.addAttribute("course", course);
                return "UpdateCourse";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/course/updateCourses/{id}")
    public String updateCourse(@PathVariable Long id, @RequestParam String course_name, @RequestParam String course_about,
                                @RequestParam(required = false) MultipartFile course_file, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
        	session.setAttribute("username", username);
        	user user = UserService.getUserByUsername(username);
        	if ((!user.getRole().equals("admin"))||(user.getRole() == null)) {
        		 return "redirect:/profile";
            }
            courses course = CourseService.getCourseById(id);
            if (course != null) {
                if (course_name != null && !course_name.isEmpty()) {
                    course.setCourseName(course_name);
                }
                if (course_about != null && !course_about.isEmpty()) {
                    course.setCourseAbout(course_about);
                }
                if (course_file != null && !course_file.isEmpty()) {
                    try {
                        String uploadDir = "src/main/resources/static/images/Courses/";
                        String fileName = id + "." + course_file.getOriginalFilename().split("\\.")[1];
                        Path uploadPath = Paths.get(uploadDir + fileName);
                        Files.createDirectories(uploadPath.getParent());
                        Files.write(uploadPath, course_file.getBytes());
                        course.setCourseImageLink("/images/Courses/" + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("error", "Something went wrong with the image upload");
                        return "redirect:/updateCourse/"+ id;
                    }
                }
                CourseService.save(course);
                return "redirect:/ContentManagement";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/courses/{id}/delete")
    public String removeCourse(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
        	session.setAttribute("username", username);
        	user user = UserService.getUserByUsername(username);
        	if ((!user.getRole().equals("admin"))||(user.getRole() == null)) {
        		 return "redirect:/profile";
            }
            courses course = CourseService.getCourseById(id);
            if (course != null) {
                CourseService.deleteCourseById(id);
                return "redirect:/ContentManagement";
            }
        }
        return "redirect:/login";
    }
        @GetMapping("/ContentManagement")
        public String ContentManagement(Model model, HttpSession session) {
        	String username = (String) session.getAttribute("username");
            if (username == null) {
                return "redirect:/login";
            }
            user user = UserService.getUserByUsername(username);
        	if ((!user.getRole().equals("admin"))||(user.getRole() == null)) {
            		 return "redirect:/profile";
                }
            session.setAttribute("username", username);
        	
            List<courses> courses = CourseService.getAllCourses();
            model.addAttribute("courses", courses);
            return "Contentmanagement";
        }
        @GetMapping("/UserManagement")
        public String UserManagement(Model model, HttpSession session) {
        	String username = (String) session.getAttribute("username");
            if (username == null) {
                return "redirect:/login";
            }
            user user = UserService.getUserByUsername(username);
        	if ((!user.getRole().equals("admin"))||(user.getRole() == null)) {
            		 return "redirect:/profile";
                }
            session.setAttribute("username", username);
           List<user> users = UserService.getAllUsers();
           model.addAttribute("user", users);
           return "UserManagement";
        }

            // Other methods...

            // Method to set the role of a user as admin
            @PostMapping("{userId}/setAdmin")
            public String setUserAsAdmin(@PathVariable Long userId, HttpSession session) {
            	String username = (String) session.getAttribute("username");
                if (username == null) {
                    return "redirect:/login";
                }
                user User = UserService.getUserByUsername(username);
                if ((!User.getRole().equals("admin"))||(User.getRole() == null)) {
                		 return "redirect:/profile";
                    }
                session.setAttribute("username", username);
                // Find the user by ID
                user user = UserService.getUserById(userId);
                if (user != null) {
                    // Set the role of the user as admin
                    user.setRole("admin");
                    // Update the user in the database
                    UserService.saveUser(user);
                    // Redirect to a success page or a user profile page
                    return "redirect:/UserManagement";
                } else {
                    // If user is not found, redirect to an error page or return an error message
                    return "redirect:/UserManagement"; // Replace "error" with appropriate error handling mechanism
                }
            }

            // Method to remove the admin role from a user
            @PostMapping("{userId}/removeAdmin")
            public String removeAdminRoleFromUser(@PathVariable Long userId, HttpSession session) {
                // Find the user by ID
            	String username = (String) session.getAttribute("username");
                if (username == null) {
                    return "redirect:/login";
                }
                user User = UserService.getUserByUsername(username);
                if ((!User.getRole().equals("admin"))||(User.getRole() == null)) {
                		 return "redirect:/profile";
                    }
                session.setAttribute("username", username);
                user user = UserService.getUserById(userId);
                if (user != null) {
                    // Remove the admin role from the user
                    user.setRole(null); // Assuming "user" is the default role
                    // Update the user in the database
                    UserService.saveUser(user);
                    // Redirect to a success page or a user profile page
                    return "redirect:/UserManagement";
                } else {
                    // If user is not found, redirect to an error page or return an error message
                    return "redirect:/UserManagement";
                }
            } 	
            @PostMapping("{userId}/blockUser")
            public String blockUser(@PathVariable Long userId, HttpSession session) {
            	String username = (String) session.getAttribute("username");
                if (username == null) {
                    return "redirect:/login";
                }
                user User = UserService.getUserByUsername(username);
                if ((!User.getRole().equals("admin"))||(User.getRole() == null)) {
                		 return "redirect:/profile";
                    }
                // Find the user by ID
                user user = UserService.getUserById(userId);
                if (user != null) {
                    // Set the status of the user to "blocked"
                    user.setStatus("blocked");
                    // Update the user in the database
                    UserService.saveUser(user);
                    // Redirect to a success page or a user profile page
                    return "redirect:/UserManagement";
                } else {
                    // If user is not found, redirect to an error page or return an error message
                    return "redirect:/UserManagement"; // Replace "error" with appropriate error handling mechanism
                }
            }

            @PostMapping("{userId}/unblockUser")
            public String unblockUser(@PathVariable Long userId, HttpSession session) {
            	String username = (String) session.getAttribute("username");
                if (username == null) {
                    return "redirect:/login";
                }
                user User = UserService.getUserByUsername(username);
                if ((!User.getRole().equals("admin"))||(User.getRole() == null)) {
                		 return "redirect:/profile";
                    }
                session.setAttribute("username", username);
                // Find the user by ID
                user user = UserService.getUserById(userId);
                if (user != null) {
                    // Set the status of the user to null to unblock
                    user.setStatus(null);
                    // Update the user in the database
                    UserService.saveUser(user);
                    // Redirect to a success page or a user profile page
                    return "redirect:/UserManagement";
                } else {
                    // If user is not found, redirect to an error page or return an error message
                    return "redirect:/UserManagement";
                }
            }
            @GetMapping("/Credits")
            public  String credits() {
            	return "Credits ";
            }
            @GetMapping("/content/{id}")
            public String getContent(@PathVariable Long id, HttpSession session, Model model) {
                // Retrieve user's enrollment status, courses, pages, and metring percentages from session
            	String username = (String) session.getAttribute("username");
            	user user = UserService.getUserByUsername(username);
            	String enrolledCoursesStr = user.getCoursesEnrolled();
                String pagesStr = user.getPage();
                String metringPercentagesStr = user.getComplitionPercentage();
                
                // Split the enrolled courses, pages, and metring percentages strings
                List<String> enrolledCourses = Arrays.asList(enrolledCoursesStr.split(","));
                List<String> pages = Arrays.asList(pagesStr.split(","));
                List<String> metringPercentages = Arrays.asList(metringPercentagesStr.split(","));
                
                // Get the index of the current course ID in the enrolled courses list
                int courseIndex = enrolledCourses.indexOf(String.valueOf(id));
                
                // Get the page and metring percentage at the same index as the course ID
                int currentPage =  Integer.parseInt(pages.get(courseIndex));
                Double metringPercentage = Double.parseDouble(metringPercentages.get(courseIndex));
                
                // Get the course by ID
                courses course = CourseService.getCourseById(id);
                // Check if the course has content description
                if (course.getContectDiscription() == null) {
                    return "redirect:/MyLearning"; // Redirect to MyLearning page if no content description
                }

                // Split the description string by backtick to get pairs of heading and description
                String[] pairs = course.getContectDiscription().split("`");
                List<List<String>> contentList = new ArrayList<>();

                // Loop through each pair and add to content list
                for (String pair : pairs) {
                    // Split the pair by tilde to separate heading and description
                    String[] parts = pair.split("~");
                    // Create a list for the heading and description pair
                    List<String> pairList = Arrays.asList(parts);
                    // Add the pair list to the content list
                    contentList.add(pairList);
                }
                session.setAttribute("totalpage",contentList.size() );

                // Set the models for the view
                session.setAttribute("username", username);

                model.addAttribute("contentList", contentList.get(currentPage));
                model.addAttribute("metringPercentage", metringPercentage);
                model.addAttribute("user",user);
                model.addAttribute("course",course);

                // in this list first index 0 is the content heading and index 1 is the content paragraph;
                model.addAttribute("content",contentList.get(Integer.parseInt(pages.get(courseIndex))));

                // Return the view name
                return "content";
            }

            
            @PostMapping("/next/{id}")
            public String nextPage(@PathVariable Long id, HttpSession session) {
                // Retrieve user's enrollment status, courses, pages, and metring percentages from session
                String username = (String) session.getAttribute("username");
                user user = UserService.getUserByUsername(username);
                String enrolledCoursesStr = user.getCoursesEnrolled();
                String pagesStr = user.getPage();
                String metringPercentagesStr = user.getComplitionPercentage();
                
                // Split the enrolled courses, pages, and metring percentages strings
                List<String> enrolledCourses = Arrays.asList(enrolledCoursesStr.split(","));
                List<String> pages = Arrays.asList(pagesStr.split(","));
                List<String> metringPercentages = Arrays.asList(metringPercentagesStr.split(","));
                
                // Get the index of the current course ID in the enrolled courses list
                int courseIndex = enrolledCourses.indexOf(String.valueOf(id));
                
                // Increment the page count if not already at max length
                
                    int currentPage = Integer.parseInt(pages.get(courseIndex));
                    int totalPages = (int) session.getAttribute("totalpage"); // Assuming you have a method to get total pages
                    if (currentPage < totalPages - 1) {
                        currentPage++;
                        pages.set(courseIndex, String.valueOf(currentPage));
                    
                    
                    // Calculate metrix percentage
                    double metrixPercentage = Double.parseDouble(metringPercentages.get(courseIndex));
                    if (metrixPercentage < 100) {
                        metrixPercentage += 100.0 / totalPages;
                        // Ensure metrix does not exceed 100
                        metrixPercentage = Math.min(metrixPercentage, 100.0);
                        metringPercentages.set(courseIndex, String.valueOf(metrixPercentage));
                    }
                }

                // Update the session attributes
                user.setPage(String.join(",", pages));
                user.setComplitionPercentage(String.join(",", metringPercentages));
                UserService.saveUser(user);
                session.setAttribute("username", username);
                
                // Redirect to the content page for the updated course
                return "redirect:/content/" + id;
            }

            @PostMapping("/back/{id}")
            public String previousPage(@PathVariable Long id, HttpSession session) {
                // Retrieve user's enrollment status, courses, pages, and metring percentages from session
                String username = (String) session.getAttribute("username");
                user user = UserService.getUserByUsername(username);
                String enrolledCoursesStr = user.getCoursesEnrolled();
                String pagesStr = user.getPage();
                String metringPercentagesStr = user.getComplitionPercentage();
                
                // Split the enrolled courses, pages, and metring percentages strings
                List<String> enrolledCourses = Arrays.asList(enrolledCoursesStr.split(","));
                List<String> pages = Arrays.asList(pagesStr.split(","));
                List<String> metringPercentages = Arrays.asList(metringPercentagesStr.split(","));
                
                // Get the index of the current course ID in the enrolled courses list
                int courseIndex = enrolledCourses.indexOf(String.valueOf(id));
                
                // Decrement the page count if not already at zero
                
                    int currentPage = Integer.parseInt(pages.get(courseIndex));
                    if (currentPage > 0) {
                        currentPage--;
                        pages.set(courseIndex, String.valueOf(currentPage));
                    }
                

                // Update the session attributes
                user.setPage(String.join(",", pages));
                UserService.saveUser(user);
                session.setAttribute("username", username);

                // Redirect to the content page for the updated course
                return "redirect:/content/" + id;
            }


}
            	
            	
            
           /* @GetMapping("/Search")
            public String UserManagement(@RequestParam(name = "search", required = false) String search,
                                         Model model, HttpSession session) {
                String username = (String) session.getAttribute("username");
                if (username == null) {
                    return "redirect:/login";
                }
                user user = UserService.getUserByUsername(username);
                if ((!user.getRole().equals("admin")) || (user.getRole() == null)) {
                    return "redirect:/profile";
                }
                    Long longValue = typecastToLong(search);

                    // Function 1: Check if input is not null and pass it to function 1
                    if (longValue != null) {
                        List<user> function1Result = UserService.getAllUsersById(longValue);
                        if (function1Result != null) {
                            model.addAttribute("user", function1Result); 
                            return "UserManagement";

                        }
                    }

                    // Function 2
                    List<user> function2Result =  UserService.getAllUsersByName(search);
                    if (function2Result != null) {
                    	model.addAttribute("user",function2Result);
                        return "UserManagement";

                    }

                    // Function 3
                    List<user> function3Result = UserService.getAllUsersByUsername(search);
                    if (function3Result != null) {
                    	model.addAttribute("user", function3Result);
                        return "UserManagement";

                    }

                    // Not found
                    model.addAttribute("error", "No such user found");
                    
                    return "UserManagement";
                }

                // Typecast input to long
            public static Long typecastToLong(String input) {
            		try {
                        return Long.parseLong(input);
                    } catch (NumberFormatException e) {
                        return null; // Not a number or cannot be parsed to long
                    }
                }*/

    	






