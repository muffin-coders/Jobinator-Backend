package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.Favorite;
import ch.wintihack.jobinator.persistence.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public void saveFavorite(Favorite favorite) throws Exception {
        favoriteRepository.save(favorite);
    }
}
