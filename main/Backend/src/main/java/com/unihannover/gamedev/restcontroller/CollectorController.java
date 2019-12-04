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

@RestController
public class CollectorController extends BaseController {
	private final String SECRET = "GamerControlDINGS";

	@Autowired
	private CollectorRepository repository;

	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping(value = "/collectors/all", method = RequestMethod.GET)
	public List<Collector> getAllCollectors() {
		return repository.findAll();
	}

	@RequestMapping(value = "/collectors/by-id", method = RequestMethod.GET)
	public List<Collector> getCollectorsById(@RequestParam(value = "id") String id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/collectors", method = RequestMethod.POST)
	public ResponseEntity<String> addCollector(@RequestParam(value = "secret") String secret,
			@RequestBody CollectorWOT collectorWOT) {
		if (secret.equals(SECRET)) {
			Collector newCollector = new Collector(collectorWOT);
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

	private boolean isNumber(String s) {
		for (char chr : s.toCharArray()) {
			if (!Character.isDigit(chr)) {
				return false;
			}
		}
		return true;
	}
}