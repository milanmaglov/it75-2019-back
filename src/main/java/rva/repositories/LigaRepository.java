package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Liga;

public interface LigaRepository extends JpaRepository<Liga, Integer> {

	Collection<Liga> findByNazivContainingIgnoreCase(String naziv);

}
