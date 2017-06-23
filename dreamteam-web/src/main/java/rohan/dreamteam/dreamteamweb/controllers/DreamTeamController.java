package rohan.dreamteam.dreamteamweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DreamTeamController {

	@GetMapping("/")
	public String getHome(){
		return "index";
	}
	
}
