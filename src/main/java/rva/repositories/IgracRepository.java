package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Igrac;
import rva.model.Tim;

public interface IgracRepository extends JpaRepository<Igrac, Integer> {
	Collection<Igrac> findByTim(Tim tim);

	Collection<Igrac> findByImeContainingIgnoreCase(String ime);
}
