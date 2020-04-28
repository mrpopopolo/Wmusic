package fr.pops.wmusic.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pops.wmusic.business.entity.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

	List<Music> findByNameStartingWith(String name);
	//List<Chara> findByElement(String element);
	//User findOneByUsername(String username);
}
