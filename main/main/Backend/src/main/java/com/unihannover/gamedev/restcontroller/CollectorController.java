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
	public ResponseEntity addCollector(@RequestParam(value = "secret") String secret,
			@RequestBody CollectorWOT collectorWOT) {
		List<Collector> foundCollectors = repository.findAll();
		boolean matchingCollector = false;
		for (Collector c : foundCollectors) {
			if (c.getId().equals(collectorWOT.getId())) {
				matchingCollector = true;
			}
		}
		if (secret.equals(SECRET)) {
			Collector newCollector = new Collector(collectorWOT);
			String token = tokenProvider.generateTokenWithSecretAndId(newCollector.getId(), SECRET);
			newCollector.setToken(token);
			collectorWOT.setToken(token);
			if (!matchingCollector) {
				repository.save(newCollector);
				return ResponseEntity.status(HttpStatus.OK).body(collectorWOT);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(collectorWOT);

		}
		// System.out.println(collector.toString()); // UNDONE: Debug print
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not verify Collector");

	}

	/*
	 * private boolean isNumber(String s) { for (char chr : s.toCharArray()) { if
	 * (!Character.isDigit(chr)) { return false; } } return true; }
	 */
}