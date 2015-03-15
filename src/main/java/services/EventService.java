
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.EventRepository;
import domain.Event;
@Service
@Transactional
public class EventService {
@Autowired
	private EventRepository eventRepository;
public Collection<Event>  findAll(){
return eventRepository.findAll();
}
public Event findOne(Integer valueOf) {
return eventRepository.findOne(valueOf);
}
public Event save(Event event){
return eventRepository.save(event);
}
}