package com.jacaranda.eventia.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacaranda.eventia.dto.EventDTO;
import com.jacaranda.eventia.dto.NotificationDTO;
import com.jacaranda.eventia.dto.RegisterDTO;
import com.jacaranda.eventia.dto.UserDTO;
import com.jacaranda.eventia.exception.ExceptionCredentialNotValid;
import com.jacaranda.eventia.model.User;
import com.jacaranda.eventia.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<User> result =  userRepository.findByEmail(username);
		// Como busco por username aunque la id se la primary key
		// comprueba que no hay dos usuario con el mismo usernam, si lo hay
		// devuelvo error.
		if (result != null && result.size()== 1)
			return result.get(0);
		else
			throw new ExceptionCredentialNotValid("Usuario no encontrado username: " + username);
		
	}
	
	public RegisterDTO addUser(User userDTO) {
	    // Validaciones de los campos
	    if (userDTO.getName() == null || userDTO.getName().isEmpty() ||
	        userDTO.getEmail() == null || userDTO.getEmail().isEmpty() ||
	        userDTO.getPassword() == null || userDTO.getPassword().isEmpty() ||
	        userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
	        throw new ExceptionCredentialNotValid("All fields must be non-null and non-empty");
	    }

	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
	    userDTO.setPassword(encodedPassword);

	    String username = userDTO.getName();
	    if (userRepository.findByEmail(userDTO.getEmail()).isEmpty()) {
	        try {
	            User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
	            userRepository.save(user);
	            registerEmail(user);
	            RegisterDTO user2 = new RegisterDTO(user.getId(),userDTO.getName(), userDTO.getEmail(), userDTO.getRole());
	            return user2;
	        } catch (Exception e) {
	            throw new ExceptionCredentialNotValid("Impossible to register");
	        }
	    } else {
	        throw new ExceptionCredentialNotValid("Email has been used");
	    }
	}
	
	private void registerEmail(User user)
			throws MessagingException, UnsupportedEncodingException {
					String toAddress = user.getEmail();
					String fromAddress = "alepalma211n@gmail.com";
					String senderName = "Eventia";
					String subject = "Eventia register verification";
					String content = "Dear [[username]],<br>" + "you've been register in Eventia. <br>"+"Enjoy the experience!";
					
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message);
					
					helper.setFrom(fromAddress, senderName);
					helper.setTo(toAddress);
					helper.setSubject(subject);
					
					content = content.replace("[[username]]", user.getName());
					
					helper.setText(content, true);
					mailSender.send(message);
				}
	
	public List<UserDTO> getUserByEmailDTO(String email) {
		return UserDTO.convertUserToDTO(userRepository.findByEmail(email));
	}
	
	public UserDTO getUser(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		UserDTO userDTO = new UserDTO(id,user.getName(),user.getEmail(),user.getRole(),EventDTO.convertEventToDTO(user.getEvents()),NotificationDTO.convertOrderToDTO(user.getNotifications()));
		return userDTO;
	}
	
}
