package sn.xarandev.thymeleaf_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.xarandev.thymeleaf_springboot.dao.UserDao;
import sn.xarandev.thymeleaf_springboot.dto.UserDto;
import sn.xarandev.thymeleaf_springboot.entities.RoleEnum;
import sn.xarandev.thymeleaf_springboot.exception.DataNotFoundException;
import sn.xarandev.thymeleaf_springboot.exception.EntityNotFoundException;
import sn.xarandev.thymeleaf_springboot.service.IUserService;

@SpringBootApplication
public class ThymeleafSpringbootApplication implements CommandLineRunner   {


	public static final String NO_DATA_AVAILABLE = "no data available";
	private final IUserService userService;
	private final UserDao userDao;
	private final Logger logger = LoggerFactory.getLogger(ThymeleafSpringbootApplication.class);
	private final String password;

	public ThymeleafSpringbootApplication(IUserService userService, UserDao userDao, @Value("${app.user.password}") String password) {
		this.userService = userService;
		this.userDao = userDao;
		this.password = password;
	}

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Password : {}", password);
		userService.save(new UserDto(1L, "Ngor","SECK","seck@samanecorporation.com", new BCryptPasswordEncoder().encode(password), RoleEnum.IT));
		userService.save(new UserDto(2L, "Abdou","SENE","sene@samanecorporation.com", new BCryptPasswordEncoder().encode(password), RoleEnum.ADMIN));
		userService.save(new UserDto(3L, "Oumar","DIOUF","diouf@samanecorporation.com", new BCryptPasswordEncoder().encode(password), RoleEnum.FINANCE));
		userService.save(new UserDto(4L, "Moussa","DIATTA","diatta@samanecorporation.com", new BCryptPasswordEncoder().encode(password), RoleEnum.ADMIN));

		logger.info("=========================ORDER BY================================");
		userDao.allUserOrderByLastName()
				.map(userEntities -> {
					logger.info("User: {} The first : {}", userEntities.size(), userEntities.get(0).getLastName());
					return userEntities.get(0);
				}).orElseThrow(() -> new EntityNotFoundException("no user found"));

		logger.info("==========================COUNT===============================");
		userDao.countAllUsers()
				.map(number -> {
					logger.info("User count: {}", number);
					return number;
				}).orElseThrow(() -> new DataNotFoundException(NO_DATA_AVAILABLE));

		logger.info("==========================Projections===============================");
		userDao.allLastNameAndFirstName()
				.map(data -> {
					data.forEach(user -> logger.info("FistName : {}, LastName : {}", user.get(0), user.get(1)));
					return data;
				}).orElseThrow(() -> new DataNotFoundException(NO_DATA_AVAILABLE));


		logger.info("==========================IN Projections===============================");
		userDao.allUserInALastName()
				.map(userEntities -> {
					logger.info("{}",userEntities.size());
					return userEntities.get(0);
				}).orElseThrow(() -> new DataNotFoundException(NO_DATA_AVAILABLE));
	}
}
