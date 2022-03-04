package com.beshton.audio.repos;

import com.beshton.audio.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Long> {

}