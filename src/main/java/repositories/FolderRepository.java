package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
	
	@Query("select f from Folder f where f.name='Inbox' and f.actor.id=?1")
	Folder findInboxFolderByActorId(int actorId);
	
	@Query("select f from Folder f where f.name='Outbox' and f.actor.id=?1")
	Folder findOutboxFolderByActorId(int actorId);
	
	@Query("select a.folders from Actor a where a.id=?1")
	Collection<Folder> findAllFoldersByActorId(int actorId);
	
}
