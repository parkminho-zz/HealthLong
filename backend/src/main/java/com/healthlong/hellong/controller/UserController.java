package com.healthlong.hellong.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthlong.hellong.model.dto.Board;
import com.healthlong.hellong.model.dto.Payment;
import com.healthlong.hellong.model.dto.SearchCondition;
import com.healthlong.hellong.model.dto.Ticket;
import com.healthlong.hellong.model.dto.User;
import com.healthlong.hellong.model.service.BoardService;
import com.healthlong.hellong.model.service.PaymentService;
import com.healthlong.hellong.model.service.TicketService;
import com.healthlong.hellong.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	// 응답을 편하게 하기 위해 상수로 지정
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final UserService userService;
	private final TicketService ticketService;
	private final PaymentService paymentService;
	@Autowired
	public UserController(UserService userService, TicketService ticketService, PaymentService paymentService) {
		this.userService = userService;
		this.ticketService = ticketService;
		this.paymentService = paymentService;
	}
	
	// 카카오회원가입 		localhost:8080/user/regist
	@PostMapping("/regist")
	public ResponseEntity<?> write(@RequestBody User user) {
		
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+[]{}|;:,.<>?";
		
		StringBuilder st = new StringBuilder(20);
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(CHARACTERS.length());
            st.append(CHARACTERS.charAt(index));
        }
		
        String randomPassword = st.toString();
		user.setPassword(randomPassword);
        user.setUserType(2);
//		System.out.println(user); 
		
		if(userService.isEmailCheck(user.getEmail())) {
			if(userService.isUserCheck(user.getEmail()) == 1) {
				return ResponseEntity.badRequest().body("이미 사용중인 이메일");
			}
		}
		
		userService.registKakaoUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

		
	@PostMapping("")
	public ResponseEntity<?> updateUser(@RequestBody Map<String, String> user) {
		int identifyUser = Integer.parseInt(user.get("type"));
		String email = user.get("email");
		String name = user.get("name");
		if(identifyUser == 2) {
			//카카오사용자의 수정
			System.out.println("카카오사용자 정보수정");
			userService.modifyKakaoUser(email, name);
		}else{
			//일반 사용 자의 수정
			String password = user.get("password");
			System.out.println(user);
			System.out.println("일반사용자 정보수정");
			userService.modifyUser(email, name, password);
		}
		return new ResponseEntity<Map<String,String>>(user, HttpStatus.CREATED);
	}
		
//	// 이용권 시작			localhost:8080/user/starttime
	@PostMapping("starttime")
	public ResponseEntity<?> startTime(@RequestBody Map<String, String> emailParts) {
		String email = emailParts.get("username") + "@" + emailParts.get("domain");

		//이메일 값 받아서 해당 이메일에 있는 시작시간에 현재시간을 넣는다.
		
		//그리고 유저 반환시켜준다.
		//그럼 메인페이지에는 User 라는  반환된 값들이 들어있다. 
		// if( 1시간 이상 남아있다면 실행)
		// 	 if (세션이있다면 계속유지)
		//		if(현재시간 - 시작시간이 유저안에있는 남은시간을 초과하게된다면 바로 종료버튼 실행)
		// 			유저안에있는 시간에 시작했다라는 뷰 표시
		// 		 	끝나는시간이 null이라면 해당 계정은 계송ㄱ해서
		ticketService.startTime(email);
		LocalDateTime getUserStartTime = userService.isStartTimeCheck(email);

		return new ResponseEntity<LocalDateTime>(getUserStartTime, HttpStatus.OK);
	}

	
	// 이용권 끝			localhost:8080/user/endtime
	@PostMapping("endtime")
	public ResponseEntity<?> endTime(@RequestBody Map<String, String> emailParts, Ticket ticket) {
		
		String email = emailParts.get("username") + "@" + emailParts.get("domain");
		
		String ticketStartString = emailParts.get("ticketStart");
		LocalDateTime ticketStart = LocalDateTime.parse(ticketStartString);
		
		// TICKET : 티켓이용내역에 email, 시작시간, 끝시간, 사용시간 저장해주는 매퍼 생성
		ticket.setGymName("싸피헬스장");
		ticket.setTicketEnd(ticketStart);
		ticket.setTicketId(0);
		ticket.setTicketStart(ticketStart);
		ticket.setTicketUid(email);
		ticket.setTicketUse(0);
		ticketService.endTime(ticket);

		int remainTime = ticketService.isUseTimeCheck(email, ticketStart);
		String strRemainTime= remainTime + "";
		System.out.println(strRemainTime);
		userService.updateRemainTime(email, -(Integer.parseInt(strRemainTime)));
		userService.updateEndTime(email);
		int time = userService.isRemainTimeCheck(email);
		// USER : 사용자에 있는 remain 시간에서 티켓이용내역에있는 사용시간 뺴주는 매퍼 생성
		
		//리턴 remainTime 하나 
		return new ResponseEntity<Integer>(time, HttpStatus.OK);
	}

	
	// 게시글 (검색) 조회
	@PostMapping("/payment")
	public ResponseEntity<?> paymentlist(@RequestBody Map<String, String> emailParts) {
		String email = emailParts.get("username") + "@" + emailParts.get("domain");
		List<Payment> list = paymentService.searchPayment(email); // 검색 조회
		
		System.out.println(list);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		System.out.println(list);
		
		return new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
	}
	
	// 내정보			localhost:8080/user/myinfo
	
	
	// 게시글 (검색) 조회
	@PostMapping("/usegym")
	public ResponseEntity<?> usegymlist(@RequestBody Map<String, String> emailParts) {
		String email = emailParts.get("username") + "@" + emailParts.get("domain");
		List<Ticket> list = ticketService.searchUse(email); // 검색 조회
		
		System.out.println(list);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		System.out.println(list);
		
		return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
	}
	
}


