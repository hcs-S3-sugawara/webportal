package jp.ac.hcs.s3a117.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/user/list")
	
		public String getUser(Principal principal,Model model) {
			 
			UserEntity userEntity = userService.selectAll();
			model.addAttribute("userEntity",userEntity);
			 
			 return"user/userList";
			 
		 }
	
		@GetMapping("/user/insert")
		public String getUserInsert(UserForm form,Model model) {
			return "user/insert";
		}

			
			
		@PostMapping("/user/insert")
		public String getUserInsert(@ModelAttribute  @Validated UserForm form,
				BindingResult bindingResult,
				Principal principal,
				Model model){
			
			if (bindingResult.hasErrors()){
				return getUserInsert(form,model);
		}
			
			UserData userData = userService.refillToData(form);
			boolean result = userService.insertOne(userData);
			 
			 return getUser(principal,model);
			 
		 }
		
		
		@GetMapping("/user/detail/{id}")
		public String getUserDetail(@PathVariable("id") String user_id,Principal principal,Model model) {
			
			UserData data = new UserData();
			data = userService.selectOne(user_id);
		
			
			model.addAttribute("userData",data);
			return "user/detail";
			
		}
		
		@PostMapping("/user/delete")
		public String userDelete(@RequestParam("delete") String user_id,Principal principal,Model model) {
			
	
			userService.deleteOne(user_id);
		
			
			return getUser(principal,model);
			
		}

	
}
