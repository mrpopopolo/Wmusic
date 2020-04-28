package fr.pops.wmusic.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pops.wmusic.business.entity.Music;
import fr.pops.wmusic.persistence.MusicRepository;

@Service
public class MusicService {

	@Autowired
	private MusicRepository mRepo;
	
	public List<String> getMusicList(){
		return mRepo.findAll().stream().filter(m -> !m.getName().matches(".*((Storm)|(Roar)|(Fire)).")).map(m -> m.getName()).collect(Collectors.toList());
	}
	
	public List<Music> getMusics(String name){
		return mRepo.findByNameStartingWith(name);
	}
	
	public void saveMusics(Music[] musics) {
		for(Music music: musics) {
			music.setName(music.getName().replace(".mp3", ""));
			mRepo.save(music);
		}
	}
}
