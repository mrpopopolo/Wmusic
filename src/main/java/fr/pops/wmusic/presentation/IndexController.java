package fr.pops.wmusic.presentation;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.pops.wmusic.business.entity.Music;
import fr.pops.wmusic.business.service.MusicService;

@Controller
public class IndexController {
	
	@Autowired
	private MusicService mServ;
	
	@GetMapping({"/index","/"})
	public String index(Model model) {
		model.addAttribute("musics", mServ.getMusicList());
		return "index.html";
	}
	
	@PostMapping("/player")
	public String player(Model model, @RequestParam(value = "choice") String music) {
		Base64.Encoder encoder = Base64.getEncoder();
		model.addAttribute("musics", 
				mServ.getMusics(music).stream()
					 .map(m -> "data:audio/mp3;base64,"+encoder.encodeToString(m.getData()))
					 .collect(Collectors.toList())
					 );
		return "music.html";
	}
	
	@GetMapping("/newMusic")
	public String newMusic() {
		return "form.html";
	}
	
	@PostMapping("addMusics")
	public String addMusics(@RequestParam(value = "music") MultipartFile music, @RequestParam(value = "epicMusic") MultipartFile epic) {
		Music mus = new Music();
		Music eMus = new Music();
		mus.setName(music.getOriginalFilename()); 
		eMus.setName(epic.getOriginalFilename());
		try {
			mus.setData(music.getBytes());
			eMus.setData(epic.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mServ.saveMusics(new Music[] {mus, eMus});
		return "redirect:/index.html";
	}

}
