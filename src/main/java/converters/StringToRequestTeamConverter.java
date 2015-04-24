package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.RequestTeamService;
import domain.RequestTeam;

@Component
@Transactional
public class StringToRequestTeamConverter implements Converter<String,RequestTeam>{
	@Autowired
	private RequestTeamService requestTeamService;
	@Override
	public RequestTeam convert(String arg0) {
	return requestTeamService.findOne(Integer.valueOf(arg0));
	}

}
