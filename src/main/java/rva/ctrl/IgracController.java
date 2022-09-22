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
import rva.model.Igrac;
import rva.model.Tim;
import rva.repositories.IgracRepository;
import rva.repositories.TimRepository;

@RestController
@CrossOrigin
@Api(value= "CRUD operations on Igrac table")
public class IgracController {
	
	@Autowired
	private IgracRepository igracRepository;
	
	@Autowired
	private TimRepository timRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/igrac")
	@ApiOperation(value="Returns all rows from the Igrac table")
	public Collection<Igrac> getAllIgrac() {
		return igracRepository.findAll();
	}
	
	@GetMapping("/igrac/{id}")
	@ApiOperation(value="Returns 1 specific row by ID from the Igrac table")
	public Igrac getIgracById(@PathVariable int id) {
		return igracRepository.getById(id);
	}
	
	@GetMapping("/igrac/ime/{ime}")
	@ApiOperation(value="Returns all rows with the specified first name from the Igrac table")
	public Collection<Igrac> getIgracByIme(@PathVariable String ime){
		return igracRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@GetMapping("/igrac/tim/{tim}")
	@ApiOperation(value="Returns all rows with the specified Tim from the Igrac table")
	public Collection<Igrac> getIgracByTim(@PathVariable int tim){
		Tim temp = timRepository.getById(tim);
		return igracRepository.findByTim(temp);
	}
	
	@PostMapping("/igrac")
	@ApiOperation(value="Creates a new row in the Igrac table")
	public ResponseEntity<Igrac> createNacionalnost(@RequestBody Igrac igrac){
		if(!igracRepository.existsById(igrac.getId())) {
			Igrac temp = igracRepository.save(igrac);
			return new ResponseEntity<Igrac>(temp,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Igrac>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/igrac")
	@ApiOperation(value="Updates a existing row in the Igrac table")
	public ResponseEntity<Igrac> updateIgrac(@RequestBody Igrac igrac){
		if(igracRepository.existsById(igrac.getId())) {
			igracRepository.save(igrac);
			return new ResponseEntity<Igrac>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Igrac>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/igrac/{id}")
	@ApiOperation(value="Deletes a row from the Igrac table")
	public ResponseEntity<Igrac> deleteIgrac(@PathVariable int id){
		if(igracRepository.existsById(id)) {
			if(id == -100) {
				igracRepository.deleteById(id);
				jdbcTemplate.execute("Insert into igrac(id,\"ime\",\"prezime\",\"broj_reg\",\"datum_rodjenja\",\"nacionalnost\",\"tim\")"
						+ "values(-100, 'test ime', 'test prezime', '11', to_date('22.04.2022.','dd.mm.yyyy'), 1, 1)");
				return new ResponseEntity<Igrac>(HttpStatus.OK);
			}else {
				igracRepository.deleteById(id);
				return new ResponseEntity<Igrac>(HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<Igrac>(HttpStatus.NOT_FOUND);
		}
		
	} 
}
