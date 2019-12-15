package com.unihannover.gamedev.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.CollectorWOT;
import com.unihannover.gamedev.repositories.CollectorRepository;
import com.unihannover.gamedev.security.JwtTokenProvider;

/**
 *A controller to handle HTTP requests about Collectors.
 *
 * @author Lukas Niehus
 */
@RestController
public class CollectorController extends BaseController {
	private final String SECRET = "GamerControlDINGS";

	@Autowired
	private CollectorRepository repository;

	@Autowired
	JwtTokenProvider tokenProvider;

	/**
	 * Returns all collectors in the repository.
	 *
	 * @return A list containg all collectors.
	 */
	@RequestMapping(value = "/collectors/all", method = RequestMethod.GET)
	public List<Collector> getAllCollectors() {
		return repository.findAll();
	}

	/**
	 * Returns all collectors with a given id.
	 *
	 * @param id The id to search for
	 * @return A list containing all collectors with given id
	 */
	@RequestMapping(value = "/collectors/by-id", method = RequestMethod.GET)
	public List<Collector> getCollectorsById(@RequestParam(value = "id") String id) {
		return repository.findById(id);
	}

	/**
	 * Adds a collector to the repository.
	 *
	 * Before adding the collector, the method will validate a JWt with a given secret.
	 *
	 * @param secret The JWT secret
	 * @param collectorWOTs The collector to be added
	 * @return
	 */
	@RequestMapping(value = "/collectors", method = RequestMethod.POST)
	public ResponseEntity<String> addCollector(@RequestParam(value = "secret") String secret,
			@RequestBody CollectorWOT[] collectorWOTs) {
		for(CollectorWOT cWOT: collectorWOTs){
			if (secret.equals(SECRET)) {
				Collector newCollector = new Collector(cWOT);
				List<Collector> foundCollectors = repository.findAll();
				int highestId = 0;
				for (Collector c : foundCollectors) {
					int checkThis = -1;
					if (isNumber(c.getId())) {
						checkThis = Integer.parseInt((c.getId()));
						if (checkThis > -1 && highestId < checkThis) {
							highestId = checkThis;
						}
					}
				}
				newCollector.setId(highestId + 1 + "");
				// repository.save(newCollector);
				String token = tokenProvider.generateTokenWithSecretAndId(newCollector.getId(), SECRET);
				newCollector.setToken(token);
				repository.save(newCollector);
				return new ResponseEntity<>(token, HttpStatus.OK);

			}
			// System.out.println(collector.toString()); // UNDONE: Debug print
			return new ResponseEntity<>("Could not verfiy Collector", HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	/**
	 * Checks, if a given String is numerical.
	 * Mostly used to convert ids into integers from Strings.
	 *
	 * @param s The given String
	 * @return True, if the String represents a numerical value
	 */
	private boolean isNumber(String s) {
		for (char chr : s.toCharArray()) {
			if (!Character.isDigit(chr)) {
				return false;
			}
		}
		return true;
	}
}