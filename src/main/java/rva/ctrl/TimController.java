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
import rva.model.Tim;
import rva.repositories.TimRepository;

@RestController
@CrossOrigin
@Api(value= "CRUD operations on Liga table")
public class TimController {
	
	@Autowired
	private TimRepository timRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/tim")
	@ApiOperation(value="Returns all rows from the Tim table")
	public Collection<Tim> getAllTim() {
		return timRepository.findAll();
	}
	
	@GetMapping("/tim/{id}")
	@ApiOperation(value="Returns 1 specific row by ID from the Tim table")
	public Tim getTimById(@PathVariable int id) {
		return timRepository.getById(id);
	}
	
	@GetMapping("/tim/naziv/{naziv}")
	@ApiOperation(value="Returns all rows with the specified name from the Tim table")
	public Collection<Tim> getTimByNaziv(@PathVariable String naziv){
		return timRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("/tim")
	@ApiOperation(value="Creates a new row in the Tim table")
	public ResponseEntity<Tim> createTim(@RequestBody Tim tim){
		if(!timRepository.existsById(tim.getId())) {
			Tim temp = timRepository.save(tim);
			return new ResponseEntity<Tim>(temp,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Tim>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/tim")
	@ApiOperation(value="Updates a existing row in the Tim table")
	public ResponseEntity<Tim> updateTim(@RequestBody Tim tim){
		if(timRepository.existsById(tim.getId())) {
			timRepository.save(tim);
			return new ResponseEntity<Tim>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Tim>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/tim/{id}")
	@ApiOperation(value="Deletes a row from the Tim table")
	public ResponseEntity<Tim> deleteTim(@PathVariable int id){
		if(timRepository.existsById(id)) {
			if(id == -100) {
				timRepository.deleteById(id);
				jdbcTemplate.execute("Insert into tim(id,\"naziv\",\"osnovan\",\"sediste\",\"liga\")"
						+ "values(-100, 'test naziv', to_date('22.04.2022.','dd.mm.yyyy'), 1000, 1)");
				return new ResponseEntity<Tim>(HttpStatus.OK);
			}else {
				timRepository.deleteById(id);
				return new ResponseEntity<Tim>(HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<Tim>(HttpStatus.NOT_FOUND);
		}
		
	}
}
