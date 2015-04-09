

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.FriendshipService;
import domain.Friendship;
@Component
@Transactional
public class StringToFriendshipConverter implements Converter<String,Friendship>{
@Autowired
private FriendshipService friendshipService;
@Override
public Friendship convert(String arg0) {
return friendshipService.findOne(Integer.valueOf(arg0));
}
}
