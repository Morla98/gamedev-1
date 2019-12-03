package com.unihannover.gamedev.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.CollectorWOT;
import com.unihannover.gamedev.repositories.CollectorRepository;

@RestController
public class CollectorController extends BaseController {
	private final String SECRET = "GamerControlDINGS";

    @Autowired
    private CollectorRepository repository;

    @RequestMapping(value="/collectors/all", method = RequestMethod.GET)
    public List<Collector> getAllCollectors() {
        return repository.findAll();
    }

    @RequestMapping(value="/collectors/by-id", method = RequestMethod.GET)
    public List<Collector> getCollectorsById(@RequestParam(value="id") String id) {
        return repository.findById(id);
    }

    @RequestMapping(value="/collectors", method = RequestMethod.POST)
    public void addCollector(@RequestParam(value="secret")String secret, @RequestBody CollectorWOT collectorWOT) {
    	if (secret.equals(SECRET)) {
    		
    	}
        repository.save(new Collector(collectorWOT));
        // System.out.println(collector.toString()); // UNDONE: Debug print
    }
}