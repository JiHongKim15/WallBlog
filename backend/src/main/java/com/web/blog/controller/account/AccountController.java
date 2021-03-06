package com.web.blog.controller.account;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.dao.user.UserSignup;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.EmailCheck;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.Users;
import com.web.blog.service.EmailService;
import com.web.blog.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {
	final private String profileImgPath = "/backend/src/main/resources/profile/";

	@Autowired
	UserSignup userSignup;

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;


	@PostMapping("/login")
	public Object login(@Valid @RequestBody Users loginData, HttpServletRequest req) {

		Users result = userService.login(loginData);
		if (result == null) {
			return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
		}

		HttpSession session = req.getSession();
		session.setAttribute("uid", result.getUid());
		return new ResponseEntity<String>(result.getUid(), HttpStatus.OK);
	}

//	Get ????????????
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@Autowired
	JavaMailSender sender;

	// ????????????
	@PostMapping("/user")
	@ApiOperation(value = "????????????")
	public Object signup(
			@Valid @RequestBody SignupRequest request/* , @RequestParam("profileImg")MultipartFile profileImg */) {

		// ======== ????????? ????????? ?????? ?????? ?????? ========
//        if (profileImg != null) {
//            String fileName = saveImg(profileImg, profileImgPath);
//            if (fileName.isEmpty()) // ?????? ????????? ????????? ????????? ?????? ?????? = ?????? ??????!
//                return new ResponseEntity<>("fail", HttpStatus.CONFLICT);
//            request.setImage(fileName);
//        }
		// ======== ????????? ????????? ?????? ?????? ?????? ========
		if(userSignup.emailselect(request.getEmail()) == null) {
			return new ResponseEntity<>("false", HttpStatus.I_AM_A_TEAPOT);
		}
		EmailCheck ec = userSignup.emailselect(request.getEmail());
		if (ec.getConfirm() == 1) {
			if(userSignup.selectUid(request.getUid()) != null) {
				return new ResponseEntity<>("false", HttpStatus.FAILED_DEPENDENCY);
			}
			if(userSignup.selectEmail(request.getEmail()) != null) {
				return new ResponseEntity<>("false", HttpStatus.CONFLICT);
			}
			if (userSignup.insert(request)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("false", HttpStatus.CONFLICT);
			}
		}
		else
			return new ResponseEntity<>("false", HttpStatus.I_AM_A_TEAPOT);
	}

	@PostMapping("/user/emailsend")
	@ApiOperation(value = "??????????????????")
	public Object emailCheck(@Valid @RequestBody String to) {
		String[] a = to.split(":");
		a = a[1].split("\"");
		to = a[1];
		String html = "http://i3c201.p.ssafy.io:8081/api/user/emailOk";
		
		if(userSignup.emailselect(a[1]) != null && userSignup.emailselect(a[1]).getConfirm() == 1) {
			return new ResponseEntity<>("false", HttpStatus.CONFLICT);
		}
		try {
			if(userSignup.emailselect(a[1]) != null) {
				userSignup.emaildelete(a[1]);
			}
			emailService.sendSimpleMessage(a[1], "[???????????????] SSAFY ESCAPE NO.1 ?????? ??????", html);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (MessagingException e) {
			e.printStackTrace();
			return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/emailOk")
	@ApiOperation(value = "?????????????????????")
	public Object emailsend(String email, String code) {
		EmailCheck emailcheck = userSignup.emailselect(email);
		if (emailcheck.getCode().equals(code)) {
			userSignup.emailupdate(new EmailCheck(emailcheck.getEmail(), emailcheck.getCode(), 1));
			
			return new ResponseEntity<>("????????? ????????? ?????????????????????.", HttpStatus.OK);
		} else
			return new ResponseEntity<>("false", HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/user/uid")
	@ApiOperation(value = "??????id -> ?????? ??????")
	public Object useruid(@RequestParam(value = "uid") String uid) {
		SignupRequest user = userSignup.selectUid(uid);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/user/searchPw")
	public Object sendEmailAction(@Valid @RequestBody String email)
			throws Exception {
		
		String[] a = email.split(":");
		a = a[1].split("\"");
		email = a[1];
		
		SignupRequest user = userSignup.selectEmail(email);
		if(user == null) {
			return new ResponseEntity<>("false", HttpStatus.I_AM_A_TEAPOT);
		}
		try {
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

			messageHelper.setSubject(user.getUid() + "??? SSAFY ESCAPE No.1 ???????????? ?????? ???????????????.");
			messageHelper.setText("??????????????? " + user.getPassword() + "?????????.\n???????????????.");
			messageHelper.setTo(user.getEmail());
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			sender.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	// ????????????
	@DeleteMapping("/user")
	@ApiOperation(value = "????????????")
	public Object dropout(@RequestBody String uid) {
		if (userSignup.delete(uid))
			return new ResponseEntity<>("success", HttpStatus.OK);
		else
			return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ????????????
	@PostMapping("/user/update")
	@ApiOperation(value = "???????????? ?????? ??????")
	public Object updateInfo(@Valid @RequestBody String uid) {
		SignupRequest user = userSignup.selectUid(uid);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// ????????????
	@PutMapping("/user")
	@ApiOperation(value = "????????????")
	public Object update(@Valid @RequestBody SignupRequest request) {

		System.out.println(request.getImage());
		SignupRequest ifin = userSignup.selectUid(request.getUid());
		
		if (ifin == null) {
			return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
		} else if (userSignup.update(request)) {
			userSignup.uploadImg(request);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}