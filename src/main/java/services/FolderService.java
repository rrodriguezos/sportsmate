
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.FolderRepository;
import domain.Folder;
@Service
@Transactional
public class FolderService {
@Autowired
	private FolderRepository folderRepository;
public Collection<Folder>  findAll(){
return folderRepository.findAll();
}
public Folder findOne(Integer valueOf) {
return folderRepository.findOne(valueOf);
}
public Folder save(Folder folder){
return folderRepository.save(folder);
}
}