package com.beshton.audio.repos;

import com.beshton.audio.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SoundRepository extends JpaRepository<Sound, Long> {
}