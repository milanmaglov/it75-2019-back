package rva.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.model.Liga;
import rva.repositories.LigaRepository;

@RestController
@CrossOrigin
@Api(value= "CRUD operations on Liga table")
public class LigaController {
	
	@Autowired
	private LigaRepository ligaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/liga")
	@ApiOperation(value="Returns all rows from the Liga table")
	public Collection<Liga> getAllLiga() {
		return ligaRepository.findAll();
	}
	
	@GetMapping("/liga/{id}")
	@ApiOperation(value="Returns 1 specific row by ID from the Liga table")
	public Liga getLigaById(@PathVariable int id) {
		return ligaRepository.getById(id);
	}
	
	@GetMapping("/liga/naziv/{naziv}")
	@ApiOperation(value="Returns all rows with the specified name from the Liga table")
	public Collection<Liga> getLigaByNaziv(@PathVariable String naziv){
		return ligaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("/liga")
	@ApiOperation(value="Creates a new row in the Liga table")
	public ResponseEntity<Liga> createLiga(@RequestBody Liga liga){
		if(!ligaRepository.existsById(liga.getId())) {
			Liga temp = ligaRepository.save(liga);
			return new ResponseEntity<Liga>(temp,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Liga>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/liga")
	@ApiOperation(value="Updates a existing row in the Liga table")
	public ResponseEntity<Liga> updateLiga(@RequestBody Liga liga){
		if(ligaRepository.existsById(liga.getId())) {
			ligaRepository.save(liga);
			return new ResponseEntity<Liga>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Liga>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/liga/{id}")
	@ApiOperation(value="Deletes a row from the Liga table")
	public ResponseEntity<Liga> deleteLiga(@PathVariable int id){
		if(ligaRepository.existsById(id)) {
			if(id == -100) {
				ligaRepository.deleteById(id);
				jdbcTemplate.execute("Insert into liga(id,\"naziv\",\"oznaka\")"
						+ "values(-100, 'test naziv', 'test oznaka')");
				return new ResponseEntity<Liga>(HttpStatus.OK);
			}else {
				ligaRepository.deleteById(id);
				return new ResponseEntity<Liga>(HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<Liga>(HttpStatus.NOT_FOUND);
		}
		
	}
}
