

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.TournamentService;
import domain.Tournament;
@Component
@Transactional
public class StringToTournamentConverter implements Converter<String,Tournament>{
@Autowired
private TournamentService tournamentService;
@Override
public Tournament convert(String arg0) {
return tournamentService.findOne(Integer.valueOf(arg0));
}
}
