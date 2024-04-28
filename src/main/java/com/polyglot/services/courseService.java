package com.polyglot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polyglot.models.courses;
import com.polyglot.repository.courseRepository;
import com.polyglot.repository.userRepository;

@Service
public class courseService {
	private final courseRepository CourseRepository;

	@Autowired
	public courseService(courseRepository CourseRepository) {
		this.CourseRepository = CourseRepository;
	}

	public List<courses> getAllCourses() {
		// TODO Auto-generated method stub
		return CourseRepository.findAll();
	}

	public courses getCourseById(Long courseId) {
		// TODO Auto-generated method stub

		return CourseRepository.getReferenceById(courseId);
	}

	public void save(courses course) {
		// TODO Auto-generated method stub
		CourseRepository.save(course);

	}

	public courses getCourseByName(String name) {
		return CourseRepository.findBycourseName(name);

	}

	public void deleteCourseById(Long id) {
		CourseRepository.deleteById(id);

	}

	public void addCourses() {
		courses course1 = new courses("Django",
				"Python framework: Learn to build web applications using the Django framework.", null, 1L, 0,
				"images/Courses/django.png",
				"Python framework~Learn to build web applications using the Django framework.`Introduction to Django~Exploring Django architecture.`Django models~Understanding Django models.`Django templates~Creating dynamic templates in Django.`Django forms~Building forms in Django.");
		courses course2 = new courses("Machine Learning",
				"Machine Learning is a field of artificial intelligence that focuses on developing algorithms and models that enable computers to learn from data with the correct outputs, make predictions, and improve performance over time.",
				null, 2L, 0, "/images/Courses/15.avif",
				"Machine Learning~Field of artificial intelligence that focuses on developing algorithms and models.`Supervised Learning~Learn about supervised learning algorithms.`Unsupervised Learning~Explore unsupervised learning techniques.`Deep Learning~Introduction to deep learning.`Reinforcement Learning~Understanding reinforcement learning.");
		courses course3 = new courses("Web Development",
				"Web development involves creating and maintaining websites or web applications. It encompasses various technologies, including HTML, CSS, JavaScript, and server-side programming languages, to build interactive and dynamic online experiences.",
				null, 3L, 0, "/images/Courses/16.jpg",
				"Web development~Involves creating and maintaining websites or web applications.`HTML Basics~Learn the basics of HTML.`CSS Styling~Explore CSS styling techniques.`JavaScript Fundamentals~Introduction to JavaScript.`Server-side Programming~Understanding server-side programming.");
		courses course4 = new courses("Python Programming",
				"Master one of the most versatile and widely-used programming languages. Learn syntax, data structures, object-oriented principles, and explore its vast ecosystem for web development, data analysis, and automation tasks.",
				null, 4L, 0, "/images/Courses/Python.jpg",
				"Python Programming~Master one of the most versatile and widely-used programming languages.`Data Structures in Python~Learn about data structures in Python.`Object-Oriented Programming~Introduction to object-oriented programming in Python.`Web Scraping with Python~Explore web scraping techniques using Python.`GUI Development~Building graphical user interfaces in Python.");
		courses course5 = new courses("C++ Programming",
				"Learn the fundamentals and advanced concepts of C++, including data types, control structures, object-oriented programming, memory management,control structures, object-oriented programming,  and algorithmic problem-solving techniques.",
				null, 5L, 0, "/images/Courses/C++.jpg",
				"C++ Programming~Learn the fundamentals and advanced concepts of C++.`Control Structures~Understand control structures in C++.`Object-Oriented Programming Basics~Introduction to object-oriented programming.`Memory Management~Explore memory management in C++.`STL Containers~Working with STL containers.");
		courses course6 = new courses("React.js",
				"React.js is a powerful JavaScript library for building dynamic user interfaces. It offers component-based architecture, virtual DOM, and state management, enabling efficient and scalable web development with rich interactive experiences.",
				null, 6L, 0, "/images/Courses/react.png",
				"React.js~Powerful JavaScript library for building dynamic user interfaces.`Component-Based Architecture~Understanding React's component-based architecture.`State Management~Managing state in React.`Routing in React~Implementing routing in React applications.`Hooks in React~Using hooks for functional components.");
		courses course7 = new courses("SQL Database",
				"A structured query language database system used for managing, storing, and retrieving data. It enables efficient data manipulation, organization, and retrieval for various applications and industries worldwide.",
				null, 7L, 0, "/images/Courses/sql.jpeg",
				"SQL Database~Structured query language database system used for managing, storing, and retrieving data.`Data Manipulation~Manipulating data in SQL.`Database Organization~Organizing databases effectively.`Joins and Subqueries~Understanding joins and subqueries in SQL.`Database Security~Implementing security measures in databases.");
		courses course8 = new courses("Android App Development",
				"Learn to build feature-rich mobile applications for the Android platform using Java or Kotlin, covering UI design, data management, networking, and deploying apps to the Google Play Store.",
				null, 8L, 0, "/images/Courses/Android dev.webp",
				"Android App Development~Learn to build feature-rich mobile applications for the Android platform using Java or Kotlin, covering UI design, data management, networking, and deploying apps to the Google Play Store.`UI Design Basics~Introduction to UI design for Android.`Data Management in Android~Managing data in Android applications.`Networking in Android~Implementing networking features in Android.`Publishing Apps~Deploying apps to the Google Play Store.");
		courses course9 = new courses("Docker Essentials",
				"Docker Essentials covers the fundamentals of containerization technology, including container deployment, management, and orchestration, enabling efficient and scalable application development and deployment in diverse computing environments.",
				null, 9L, 0, "/images/Courses/Docker essentials.png",
				"Docker Essentials~Covers the fundamentals of containerization technology, including container deployment, management, and orchestration, enabling efficient and scalable application development and deployment in diverse computing environments.`Container Deployment~Deploying containers using Docker.`Container Orchestration~Managing containerized applications.`Docker Networking~Implementing networking in Docker.`Security in Docker~Securing Docker containers.");
		courses course10 = new courses("AWS Cloud Computing",
				"AWS Cloud Computing course covers essential AWS services such as EC2, S3, Lambda, and more. Learn to design, deploy, and manage scalable and secure cloud solutions on the Amazon Web Services platform.",
				null, 10L, 0, "/images/Courses/AWS-Cloud.png",
				"AWS Cloud Computing~Covers essential AWS services such as EC2, S3, Lambda, and more.`Designing Cloud Solutions~Designing scalable and resilient cloud solutions.`Managing Scalable Cloud Solutions~Managing scalability in AWS.`Securing Cloud Solutions~Implementing security measures in AWS.`Deploying Applications~Deploying applications on AWS.");
		courses course11 = new courses("DevOps Fundamentals",
				"DevOps Fundamentals covers principles and practices essential for efficient software development and deployment. Topics include continuous integration, delivery, automation, and collaboration between development and operations teams.",
				null, 11L, 0, "/images/Courses/Devops.jpg",
				"DevOps Fundamentals~Covers principles and practices essential for efficient software development and deployment.`Continuous Integration~Implementing continuous integration pipelines.`Continuous Delivery~Automating continuous delivery.`Infrastructure as Code~Managing infrastructure using code.`Monitoring and Logging~Implementing monitoring and logging solutions.");
		courses course12 = new courses("Blockchain Fundamentals",
				"Blockchain Fundamentals covers the foundational concepts of blockchain technology, including decentralized ledgers, and consensus mechanisms, essential for understanding blockchain's applications and implications in various industries.",
				null, 12L, 0, "/images/Courses/Blockchain.png",
				"Blockchain Fundamentals~Covers the foundational concepts of blockchain technology, including decentralized ledgers, and consensus mechanisms, essential for understanding blockchain's applications and implications in various industries.`Decentralized Ledgers~Understanding decentralized ledgers.`Consensus Mechanisms~Exploring consensus mechanisms.`Smart Contracts~Implementing smart contracts.`Blockchain Security~Securing blockchain networks.");
		courses course13 = new courses("Cybersecurity",
				"Cybersecurity involves safeguarding digital systems, networks, and data from malicious attacks or unauthorized access. It encompasses various measures such as encryption, firewalls, and security protocols to ensure protection and resilience.",
				null, 13L, 0, "/images/Courses/Cybersecurity.png",
				"Cybersecurity~Involves safeguarding digital systems, networks, and data from malicious attacks or unauthorized access.`Encryption Techniques~Implementing encryption techniques.`Firewall Implementation~Deploying firewalls.`Intrusion Detection Systems~Setting up intrusion detection systems.`Incident Response~Managing incident response processes.");
		courses course14 = new courses("Data Science",
				"Data Science involves extracting insights and knowledge from structured and unstructured data using various techniques, including data analysis, machine learning, and statistical modeling, to drive informed decision-making and innovation.",
				null, 14L, 0, "/images/Courses/data science.jpg",
				"Data Science~Involves extracting insights and knowledge from structured and unstructured data using various techniques, including data analysis, machine learning, and statistical modeling, to drive informed decision-making and innovation.`Data Analysis Techniques~Exploring data analysis techniques.`Machine Learning Algorithms~Understanding machine learning algorithms.`Statistical Modeling~Applying statistical modeling techniques.`Data Visualization~Visualizing data effectively.");
		courses course15 = new courses("Ethical Hacking",
				"Ethical Hacking: Learn cybersecurity techniques, tools, and methodologies to identify and address vulnerabilities in computer systems, networks, and applications, ensuring digital security and integrity.",
				null, 15L, 0, "/images/Courses/Ethical Hacking.jpg",
				"Ethical Hacking~Learn cybersecurity techniques, tools, and methodologies to identify and address vulnerabilities in computer systems, networks, and applications, ensuring digital security and integrity.`Vulnerability Assessment~Conducting vulnerability assessments.`Penetration Testing~Performing penetration tests.`Network Security~Implementing network security measures.`Web Application Security~Securing web applications.");
		// Add more courses as needed

		// Save courses to the database
		CourseRepository.save(course1);
		CourseRepository.save(course2);
		CourseRepository.save(course3);
		CourseRepository.save(course4);
		CourseRepository.save(course5);
		CourseRepository.save(course6);
		CourseRepository.save(course7);
		CourseRepository.save(course8);
		CourseRepository.save(course9);
		CourseRepository.save(course10);
		CourseRepository.save(course11);
		CourseRepository.save(course12);
		CourseRepository.save(course13);
		CourseRepository.save(course14);
		CourseRepository.save(course15);
	}

}
