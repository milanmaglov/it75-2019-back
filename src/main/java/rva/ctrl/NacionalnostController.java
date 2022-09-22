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
import rva.model.Nacionalnost;
import rva.repositories.NacionalnostRepository;

@RestController
@CrossOrigin
@Api(value= "CRUD operations on Liga table")
public class NacionalnostController {
	
	@Autowired
	private NacionalnostRepository nacionalnostRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/nacionalnost")
	@ApiOperation(value="Returns all rows from the Nacionalnost table")
	public Collection<Nacionalnost> getAllNacionalnost() {
		return nacionalnostRepository.findAll();
	}
	
	@GetMapping("/nacionalnost/{id}")
	@ApiOperation(value="Returns 1 specific row by ID from the Nacionalnost table")
	public Nacionalnost getNacionalnostById(@PathVariable int id) {
		return nacionalnostRepository.getById(id);
	}
	
	@GetMapping("/nacionalnost/naziv/{naziv}")
	@ApiOperation(value="Returns all rows with the specified name from the Nacionalnost table")
	public Collection<Nacionalnost> getNacionalnostByNaziv(@PathVariable String naziv){
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("/nacionalnost")
	@ApiOperation(value="Creates a new row in the Nacionalnost table")
	public ResponseEntity<Nacionalnost> createNacionalnost(@RequestBody Nacionalnost nacionalnost){
		if(!nacionalnostRepository.existsById(nacionalnost.getId())) {
			Nacionalnost temp = nacionalnostRepository.save(nacionalnost);
			return new ResponseEntity<Nacionalnost>(temp,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Nacionalnost>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/nacionalnost")
	@ApiOperation(value="Updates a existing row in the Nacionalnost table")
	public ResponseEntity<Nacionalnost> updateNacionalnost(@RequestBody Nacionalnost nacionalnost){
		if(nacionalnostRepository.existsById(nacionalnost.getId())) {
			nacionalnostRepository.save(nacionalnost);
			return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Nacionalnost>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/nacionalnost/{id}")
	@ApiOperation(value="Deletes a row from the Nacionalnost table")
	public ResponseEntity<Nacionalnost> deleteNacionalnost(@PathVariable int id){
		if(nacionalnostRepository.existsById(id)) {
			if(id == -100) {
				nacionalnostRepository.deleteById(id);
				jdbcTemplate.execute("Insert into nacionalnost(id,\"naziv\",\"skracenica\")"
						+ "values(-100, 'test naziv', 'test skracenica')");
				return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
			}else {
				nacionalnostRepository.deleteById(id);
				return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<Nacionalnost>(HttpStatus.NOT_FOUND);
		}
		
	}
}
