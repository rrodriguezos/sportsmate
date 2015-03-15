

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.TeamService;
import domain.Team;
@Component
@Transactional
public class StringToTeamConverter implements Converter<String,Team>{
@Autowired
private TeamService teamService;
@Override
public Team convert(String arg0) {
return teamService.findOne(Integer.valueOf(arg0));
}
}
