package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    @Autowired
    private AiService aiService;

    @Autowired
    private KeyWordQuestionService keyWordQuestionService;

    public int getScore(User user, JobPreview jobPreview) {
        List<Optional<Integer>> optionals = new ArrayList<>();
        optionals.add(keyWordQuestionService.getScore(user,jobPreview));
        optionals.add(aiService.getScore(user,jobPreview));

        return ((Double)optionals
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.summarizingInt(Integer::intValue))
                .getAverage()).intValue();
    }

}
